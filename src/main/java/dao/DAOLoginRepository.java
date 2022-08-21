package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;

import connection.SingleConnectionDB;
import models.ModelLogin;

public class DAOLoginRepository {
private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionDB.getConnection();
	}
	
	/*** Comando SQL atrav�s da chamada de Stored Procedure existente no banco de dados Microsoft SQL Server com passagem de par�metros ***/
	//public boolean validarLogin(ModelLogin modelLogin) throws Exception {
	public ModelLogin  validarLogin(ModelLogin modelLogin) throws Exception {
				
		ModelLogin modelLoginResult = new ModelLogin();

		PreparedStatement statement = connection.prepareStatement("{call dbo.SP_UsuarioAutenticar (?,?)}");
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		ResultSet resultado = statement.executeQuery();
		
		/*
		if(resultado.next()) {
			return true;	//possui usu�rio
		} else {
			return false; 	//n�o validou usu�rio
		}
		*/
	   while (resultado.next()) {
				
				//ModelLogin modelLoginResult = new ModelLogin();
				modelLoginResult.setId(resultado.getLong("id"));
				modelLoginResult.setLogin(resultado.getString("login"));
				//modelLogin.setSenha(resultado.getString("senha"));
				modelLoginResult.setNome(resultado.getString("nome"));
				modelLoginResult.setEmail(resultado.getString("email"));
				//modelLogin.setStatusId(resultado.getString("statusId"));
				
				//retorno.add(modelLoginResult);
		}
		return modelLoginResult;
		
	}
	
	
	
	
	/*** Comando SQL atrav�s de uma String pr�-definida no c�digo da aplica��o com ou sem passagem de par�metros ***/
	/*public boolean validarLogin(ModelLogin modelLogin) throws Exception {
		
		
		//String table_name = "TBUsuarios";
		//String sql = "SELECT * FROM " + table_name + " WHERE login = '" + modelLogin.getLogin() + "' AND senha = '" + modelLogin.getSenha() + "'";
		//String sql = "SELECT * FROM " + table_name + " WHERE login = ? AND senha = ?";
		
		//PreparedStatement statement = connection.prepareStatement(sql);
		//statement.setString(1, modelLogin.getLogin());
		//statement.setString(2, modelLogin.getSenha());
		//ResultSet resultado = statement.executeQuery();
		
		
		if(resultado.next()) {
			return true;	//possui usu�rio
		} else {
			return false; 	//n�o validou usu�rio
		}
		
	}*/
}
