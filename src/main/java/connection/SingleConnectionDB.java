package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
 * @author thiers
 *
 */
public class SingleConnectionDB {

	
	private static String user = "thiersfb";
	private static String password = "thiers07";
	private static String strConnection = "jdbc:sqlserver://THIERS-DELL\\SQLSERVER;databaseName=cursojsp";
	private static Connection connection = null;
	
	
	// a partir do momento que esta classe for chamada, este metodo garantirá que a conexão esteja ativa
	static {
		conectar();
	}

	public SingleConnectionDB() {
		conectar();
	}

	public static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(strConnection, user, password);
				connection.setAutoCommit(false);
				//System.out.println("Conectado ao banco cursojsp com sucesso");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	// metodo que retorna a conexão
	public static Connection getConnection() {
		return connection;
	}
}
