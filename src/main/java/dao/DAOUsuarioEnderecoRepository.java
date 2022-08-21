package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Types;
import java.util.ArrayList;
//import java.sql.SQLException;
//import java.sql.Types;
import java.util.List;

import connection.SingleConnectionDB;
import models.ModelEndereco;

public class DAOUsuarioEnderecoRepository {

	private Connection connection;
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public DAOUsuarioEnderecoRepository() {
		connection = SingleConnectionDB.getConnection();
	}
	
	//revisar 
	public boolean validarEnderecoUsuarioEmUso(Long idUser, String telefone) throws Exception {
		
		try(CallableStatement statement = connection.prepareCall("{? = call dbo.SP_UsuarioVerificaTelefone(?)}");) {
			statement.registerOutParameter(1, java.sql.Types.INTEGER);
			statement.setString(2, telefone);
			statement.execute(); 
			if (statement.getInt(1) == 1) { //se 1, telefone já existente na base de dados 
				return true; 
			} else { 
				return false; 
			}
		}
	}
	
	
	public ModelEndereco gravaEndereco(ModelEndereco modelEndereco /*, Long userLogado*/) throws Exception {
		
		if (modelEndereco.isNovo()) {
			
			String chamadaProc = "{call dbo.SP_UsuarioEnderecoCriar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
							
			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setLong(1, modelEndereco.getUsuario_id().getId());
			statement.setBoolean(2, modelEndereco.isEnderecoPrincipal());
			statement.setString(3, modelEndereco.getCep());
			statement.setString(4, modelEndereco.getLogradouro());
			statement.setString(5, modelEndereco.getNumero());
			statement.setString(6, modelEndereco.getComplemento());
			statement.setString(7, modelEndereco.getBairro());
			statement.setString(8, modelEndereco.getCidade());
			statement.setString(9, modelEndereco.getUf());
			statement.setLong(10, modelEndereco.getCriado_por().getId());
			
			statement.execute();

			connection.commit();
		} else {
			String chamadaProc = "{call dbo.SP_UsuarioEnderecoAtualizar (" + modelEndereco.getId() + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setLong(1, modelEndereco.getUsuario_id().getId());
			statement.setBoolean(2, modelEndereco.isEnderecoPrincipal());
			statement.setString(3, modelEndereco.getCep());
			statement.setString(4, modelEndereco.getLogradouro());
			statement.setString(5, modelEndereco.getNumero());
			statement.setString(6, modelEndereco.getComplemento());
			statement.setString(7, modelEndereco.getBairro());
			statement.setString(8, modelEndereco.getCidade());
			statement.setString(9, modelEndereco.getUf());
			statement.setLong(10, modelEndereco.getCriado_por().getId());
			
			statement.execute();

			connection.commit();
		}
		
		//return null;
		return this.consultaDadosUsuarioEndereco(modelEndereco.getId());
	}
	
	public void excluirUsuarioEndereco(Long idUserEndereco) throws Exception {
		
		PreparedStatement statement = connection.prepareStatement("{call dbo.SP_UsuarioEnderecoExcluir (?)}");
		statement.setLong(1, idUserEndereco);
		
		statement.execute();

		connection.commit();
		
	}
	
	//Lista de telefones vinculados ao usuário
	public List<ModelEndereco> consultaUsuarioEnderecoList(Long usuario_id) throws Exception {
		
		List<ModelEndereco> retorno = new ArrayList<ModelEndereco>();

		String sql = "SELECT * FROM TBUsuariosEndereco WHERE UsuarioID = " + usuario_id ;

		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
	    while (resultado.next()) {
			
	    	ModelEndereco modelEndereco = new ModelEndereco();

			modelEndereco.setId(resultado.getLong("id"));
			modelEndereco.setEnderecoPrincipal(resultado.getBoolean("isEnderecoPrincipal"));			
			
			modelEndereco.setCep(resultado.getString("cep"));
			modelEndereco.setLogradouro(resultado.getString("logradouro"));
			modelEndereco.setNumero(resultado.getString("numero"));
			modelEndereco.setComplemento(resultado.getString("complemento"));
			modelEndereco.setBairro(resultado.getString("bairro"));
			modelEndereco.setCidade(resultado.getString("cidade"));
			modelEndereco.setUf(resultado.getString("uf"));
			
	    	modelEndereco.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("usuarioID")));
	    	modelEndereco.setCriado_por(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("criadoPor")));
			retorno.add(modelEndereco);
	    }
		return retorno;
	}
	
	public ModelEndereco consultaDadosEnderecoUsuarioPorId(Long id) throws Exception {
	 
		ModelEndereco modelEndereco = new ModelEndereco();
		  
		String sql = "SELECT * FROM TBUsuariosEndereco WHERE UPPER([id]) = UPPER('" + id + "')";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado

			modelEndereco.setId(resultado.getLong("id"));
			modelEndereco.setEnderecoPrincipal(resultado.getBoolean("isEnderecoPrincipal"));			
			
			modelEndereco.setCep(resultado.getString("cep"));
			modelEndereco.setLogradouro(resultado.getString("logradouro"));
			modelEndereco.setNumero(resultado.getString("numero"));
			modelEndereco.setComplemento(resultado.getString("complemento"));
			modelEndereco.setBairro(resultado.getString("bairro"));
			modelEndereco.setCidade(resultado.getString("cidade"));
			modelEndereco.setUf(resultado.getString("uf"));
			
		} 
		return modelEndereco; 
	}
	
	public ModelEndereco consultaDadosUsuarioEndereco(Long id) throws Exception {
		 
		ModelEndereco modelEndereco = new ModelEndereco();
		
		String sql = "SELECT * FROM TBUsuariosEndereco WHERE UPPER([id]) = UPPER('" + id + "')";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
			
			modelEndereco.setId(resultado.getLong("id"));
			modelEndereco.setNumero(resultado.getString("numero"));
			modelEndereco.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("usuarioID")));
		} 
		return modelEndereco; 
	}
	
	
}
