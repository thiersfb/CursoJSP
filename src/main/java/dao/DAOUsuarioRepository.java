package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
//import java.sql.Types;
import java.util.ArrayList;
//import java.sql.SQLException;
//import java.sql.Types;
import java.util.List;

import connection.SingleConnectionDB;
import models.ModelLogin;
import models.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;
	
	// DAOUsuarioTelefoneRepository daoUsuarioTelefoneRepository = new DAOUsuarioTelefoneRepository();

	public DAOUsuarioRepository() {
		connection = SingleConnectionDB.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws Exception {
		
		if (modelLogin.isNovo()) {
			
			String chamadaProc = "{call dbo.SP_UsuarioCriar (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				chamadaProc = chamadaProc + ", ?, ?";
			}
			
			chamadaProc = chamadaProc + ")}";
			
			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getSobrenome());
			statement.setString(5, modelLogin.getEmail());
			statement.setString(6, modelLogin.getCpf());
			statement.setString(7, modelLogin.getRg());
			statement.setDate(8, modelLogin.getDtNascimento());
			statement.setDouble(9, modelLogin.getRendamensal());
			statement.setString(10, modelLogin.getStatusId());
			statement.setLong(11, userLogado);
			statement.setString(12, modelLogin.getPerfil());
			statement.setString(13, modelLogin.getGenero());
			
			/*
			statement.setString(14, modelLogin.getCep());
			statement.setString(15, modelLogin.getLogradouro());
			statement.setString(16, modelLogin.getNumero());
			statement.setString(17, modelLogin.getComplemento());
			statement.setString(18, modelLogin.getBairro());
			statement.setString(19, modelLogin.getCidade());
			statement.setString(20, modelLogin.getUf());
			*/
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				statement.setString(14, modelLogin.getFotoUser());
				statement.setString(15, modelLogin.getFotoUserExtensao());

			}
			
			statement.execute();

			connection.commit();
			
		} else {
			
			String chamadaProc = "{call dbo.SP_UsuarioAtualizar (" + modelLogin.getId() + ",?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				chamadaProc = chamadaProc + ", ?, ?";
			}
			
			chamadaProc = chamadaProc + ")}";
			
			PreparedStatement statement = connection.prepareStatement(chamadaProc);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getSobrenome());
			statement.setString(5, modelLogin.getEmail());
			statement.setString(6, modelLogin.getCpf());
			statement.setString(7, modelLogin.getRg());
			statement.setDate(8, modelLogin.getDtNascimento());
			statement.setDouble(9, modelLogin.getRendamensal());
			statement.setString(10, modelLogin.getStatusId());
			statement.setLong(11, userLogado);
			statement.setString(12, modelLogin.getPerfil());
			statement.setString(13, modelLogin.getGenero());
			
			/*
			statement.setString(14, modelLogin.getCep());
			statement.setString(15, modelLogin.getLogradouro());
			statement.setString(16, modelLogin.getNumero());
			statement.setString(17, modelLogin.getComplemento());
			statement.setString(18, modelLogin.getBairro());
			statement.setString(19, modelLogin.getCidade());
			statement.setString(20, modelLogin.getUf());
			*/
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				statement.setString(14, modelLogin.getFotoUser());
				statement.setString(15, modelLogin.getFotoUserExtensao());

			}

			statement.execute();

			connection.commit();

		}
		
		return this.consultaDadosUsuario(modelLogin.getLogin(), userLogado);

	}
	
	
	public int totalPagina(/*Long userLogado*/) throws Exception {
		
		String sql = "SELECT COUNT(*) AS total FROM TBUsuarios WHERE [IsUserAdmin] = 0 ";
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		
		Double cadastros = resultado.getDouble("total");
		Double porpagina = 10.0;
		Double pagina = cadastros / porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina ++;
		}
		
		return pagina.intValue();
	}
	
	//Listagem Paginada de usu�rios cadastrados
	public List<ModelLogin> consultaListaPaginadaUsuario(Integer offset) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
					
		String chamadaProc = "{call dbo.SP_UsuarioListagemPaginada (?)}";
		
		CallableStatement cstmt = connection.prepareCall(chamadaProc);
		cstmt.setLong(1, offset);
		
		
	    ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}

	//Lista usu�rios cadastrados
	public List<ModelLogin> consultaListaUsuario() throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		CallableStatement cstmt = connection.prepareCall("{call dbo.SP_UsuarioListar}");
		
	    ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}
	
	
	public int pesquisaUsuarioListTotalPaginaPaginacao(String textoPesquisa /*, Long userLogado*/) throws Exception {
		
		String sql = "SELECT COUNT(*) AS Total FROM TBUsuarios WHERE UPPER([Login]) LIKE UPPER(?) OR UPPER([Nome]) LIKE UPPER(?) OR UPPER([Sobrenome]) LIKE UPPER(?) OR UPPER([Email]) LIKE UPPER(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + textoPesquisa + "%");
		statement.setString(2, "%" + textoPesquisa + "%");
		statement.setString(3, "%" + textoPesquisa + "%");
		statement.setString(4, "%" + textoPesquisa + "%");
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		
		Double cadastros = resultado.getDouble("Total");
		Double porpagina = 10.0;
		Double pagina = cadastros / porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina ++;
		}
		
		return pagina.intValue();
		
	}
	
	//Lista de usu�rios encontrados na pesquisa
	public List<ModelLogin> pesquisaUsuarioListPaginada(String textoPesquisa, int offset /*, Long userLogado*/) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		CallableStatement cstmt = connection.prepareCall("{call dbo.SP_UsuarioConsultaPaginada (?, ?)}");
		
		cstmt.setString("strPesquisa", textoPesquisa);	//"strPesquisa" � o nome do par�metro da proc
		//cstmt.setLong("userLogado", userLogado);
		cstmt.setInt("offset", offset);
		
	    ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}
	
	
	//Lista de usu�rios encontrados na pesquisa
	public List<ModelLogin> consultaUsuarioList(String textoPesquisa) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		CallableStatement cstmt = connection.prepareCall("{call dbo.SP_UsuarioConsultar (?)}");
		
		cstmt.setString("strPesquisa", textoPesquisa);	//"strPesquisa" � o nome do par�metro da proc
	    
	    ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			
			
			/*
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListRelatorio(/*String textoPesquisa*/) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		/*
		CallableStatement cstmt = connection.prepareCall("{call dbo.SP_UsuarioConsultar (?)}");
		cstmt.setString("strPesquisa", textoPesquisa);	//"strPesquisa" � o nome do par�metro da proc
		*/
	    
		String sql = "SELECT * FROM TBUsuarios WHERE IsUserAdmin = 0";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultado = stmt.executeQuery();
	    //ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSobrenome(resultado.getString("sobrenome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setCpf(resultado.getString("cpf"));
			modelLogin.setRg(resultado.getString("rg"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			
			modelLogin.setDtNascimento(resultado.getDate("datanascimento"));
			modelLogin.setTelefones(this.consultaUsuarioTelefonesList(resultado.getLong("id")));
			
			
			/*
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListRelatorio(String dataInicial, String dataFinal) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		
		String sql = "SELECT * FROM TBUsuarios WHERE IsUserAdmin = 0 AND DataNascimento BETWEEN ? AND ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		stmt.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		
		ResultSet resultado = stmt.executeQuery();
	    //ResultSet resultado = cstmt.executeQuery();
	    while (resultado.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSobrenome(resultado.getString("sobrenome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setCpf(resultado.getString("cpf"));
			modelLogin.setRg(resultado.getString("rg"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));

			modelLogin.setDtNascimento(resultado.getDate("datanascimento"));
			modelLogin.setTelefones(this.consultaUsuarioTelefonesList(resultado.getLong("id")));
			
			
			/*
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/
			
			retorno.add(modelLogin);
	    }
		return retorno;
	}
	
	//Lista de telefones vinculados ao usu�rio
	public List<ModelTelefone> consultaUsuarioTelefonesList(Long usuario_id) throws Exception {
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();

		String sql = "SELECT * FROM TBUsuariosTelefone WHERE UsuarioID = " + usuario_id ;

		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
	    while (resultado.next()) {
			
	    	ModelTelefone modelTelefone = new ModelTelefone();
			
	    	modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_id(this.consultaDadosUsuarioPorId(resultado.getLong("usuarioID")));
			modelTelefone.setCriado_por(this.consultaDadosUsuarioPorId(resultado.getLong("criadoPor")));
			retorno.add(modelTelefone);
	    }
		return retorno;
	}

	
	public ModelLogin consultaDadosUsuarioLogado(String login) throws Exception {
	 
		ModelLogin modelLogin = new ModelLogin();
		  
		String sql = "SELECT * FROM TBUsuarios WHERE UPPER([Login]) = UPPER('" + login + "') ";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
		 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSobrenome(resultado.getString("sobrenome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setIsAdmin(resultado.getBoolean("isUserAdmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			  
		} 
		return modelLogin; 
	}
	
	public ModelLogin consultaDadosUsuario(String login) throws Exception {
	 
		ModelLogin modelLogin = new ModelLogin();
		  
		String sql = "SELECT * FROM TBUsuarios WHERE UPPER([Login]) = UPPER('" + login + "') AND IsUserAdmin = 0 ";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
		 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setIsAdmin(resultado.getBoolean("isUserAdmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			//modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			/*
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/  
		} 
		return modelLogin; 
	}
	
	public ModelLogin consultaDadosUsuario(String login, Long userLogado) throws Exception {
	 
		ModelLogin modelLogin = new ModelLogin();
		  
		String sql = "SELECT * FROM TBUsuarios WHERE UPPER([Login]) = UPPER('" + login + "') AND IsUserAdmin = 0";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
		 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSobrenome(resultado.getString("sobrenome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			modelLogin.setCpf(resultado.getString("cpf"));
			modelLogin.setRg(resultado.getString("rg"));
			
			//modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			/*
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/
		} 
		return modelLogin; 
	}
	
	public ModelLogin consultaDadosUsuarioPorId(Long id) throws Exception {
	 
		ModelLogin modelLogin = new ModelLogin();
		  
		String sql = "SELECT * FROM TBUsuarios WHERE ID = ? AND IsUserAdmin = 0";
		 
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		 
		ResultSet resultado = statement.executeQuery();
		 
		while (resultado.next()) { // Se encontrar resultado
		 
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSobrenome(resultado.getString("sobrenome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setCpf(resultado.getString("cpf"));
			modelLogin.setRg(resultado.getString("rg"));
			modelLogin.setDtNascimento(resultado.getDate("dataNascimento"));
			modelLogin.setRendamensal(resultado.getDouble("rendamensal"));
			modelLogin.setStatusId(resultado.getString("statusId"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setGenero(resultado.getString("genero"));
			modelLogin.setFotoUser(resultado.getString("fotoUser"));
			modelLogin.setFotoUserExtensao(resultado.getString("fotoUserExtensao"));
			/*
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setComplemento(resultado.getString("complemento"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setCidade(resultado.getString("cidade"));
			modelLogin.setUf(resultado.getString("uf"));
			*/
			  
		} 
		return modelLogin; 
	}
	
	public void excluirUsuario(String idUser) throws Exception {
		
		PreparedStatement statement = connection.prepareStatement("{call dbo.SP_UsuarioExcluir (?)}");
		statement.setLong(1, Long.parseLong(idUser));
		
		statement.execute();

		connection.commit();
		
	}
	
	public boolean validarLoginEmUso(String login) throws Exception {
		
		try(CallableStatement statement = connection.prepareCall("{? = call dbo.SP_UsuarioVerificaLogin(?)}");) {
			statement.registerOutParameter(1, java.sql.Types.INTEGER);
			statement.setString(2, login);
			statement.execute();
			//System.out.println("RETURN STATUS: " + statement.getInt(1)); 
			if (statement.getInt(1) >= 1) { //se maior que zero, login j� existente na base de dados 
				return true; 
			} else { 
				return false; 
			}
			/*
			if (statement.getInt(1) > 0) { //se maior que zero, login j� existente na base de dados 
				return false; 
			} else { 
				return true; 
			}
			*/
		}
	}
	
}
