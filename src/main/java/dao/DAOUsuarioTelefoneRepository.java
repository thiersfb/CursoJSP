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
import models.ModelTelefone;

public class DAOUsuarioTelefoneRepository {

	private Connection connection;
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public DAOUsuarioTelefoneRepository() {
		connection = SingleConnectionDB.getConnection();
	}
	
	public boolean validarTelefoneEmUso(String telefone) throws Exception {
		
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
	
	public ModelTelefone gravaTelefone(ModelTelefone modelTelefone /*, Long userLogado*/) throws Exception {
		
		if (modelTelefone.isNovo()) {
			
			String chamadaProc = "{call dbo.SP_UsuarioTelefoneCriar (?, ?, ?)}";
							
			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setString(1, modelTelefone.getNumero());
			statement.setLong(2, modelTelefone.getUsuario_id().getId());
			statement.setLong(3, modelTelefone.getCriado_por().getId());
			
			statement.execute();

			connection.commit();
		} else {
			String chamadaProc = "{call dbo.SP_UsuarioTelefoneAtualizar (" + modelTelefone.getId() + ",?, ?, ?)}";

			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setString(1, modelTelefone.getNumero());
			statement.setLong(2, modelTelefone.getUsuario_id().getId());
			statement.setLong(3, modelTelefone.getCriado_por().getId());
			
			statement.execute();

			connection.commit();
		}
		
		//return null;
		return this.consultaDadosUsuarioTelefone(modelTelefone.getNumero());
	}
	
	public void excluirUsuarioTelefone(Long idUserTelefone) throws Exception {
		
		PreparedStatement statement = connection.prepareStatement("{call dbo.SP_UsuarioTelefoneExcluir (?)}");
		statement.setLong(1, idUserTelefone);
		
		statement.execute();

		connection.commit();
		
	}
	
	//Lista de telefones vinculados ao usuário
	public List<ModelTelefone> consultaUsuarioTelefonesList(Long usuario_id) throws Exception {
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();

		String sql = "SELECT * FROM TBUsuariosTelefone WHERE UsuarioID = " + usuario_id ;

		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
	    while (resultado.next()) {
			
	    	ModelTelefone modelTelefone = new ModelTelefone();
			
	    	modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("usuarioID")));
			modelTelefone.setCriado_por(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("criadoPor")));
			retorno.add(modelTelefone);
	    }
		return retorno;
	}
	
	public ModelTelefone consultaDadosTelefoneUsuarioPorId(Long id) throws Exception {
	 
		ModelTelefone modelTelefone = new ModelTelefone();
		  
		String sql = "SELECT * FROM TBUsuariosTelefone WHERE UPPER([id]) = UPPER('" + id + "')";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
		 
			modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			  
		} 
		return modelTelefone; 
	}
	
	public ModelTelefone consultaDadosUsuarioTelefone(String numero) throws Exception {
		 
		ModelTelefone modelTelefone = new ModelTelefone();
		
		String sql = "SELECT * FROM TBUsuariosTelefone WHERE UPPER([numero]) = UPPER('" + numero + "')";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
			
			modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_id(daoUsuarioRepository.consultaDadosUsuarioPorId(resultado.getLong("usuarioID")));
		} 
		return modelTelefone; 
	}
	
	
}
