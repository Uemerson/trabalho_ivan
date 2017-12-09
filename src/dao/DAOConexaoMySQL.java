package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConexaoMySQL implements Serializable{
	private static DAOConexaoMySQL conexao = null;
	private static Connection connection;
	private String usuario;
	private String senha;
	private String url;
	
	public DAOConexaoMySQL() {
		//usuario = "root";
		//senha = "1234";
		//url = "jdbc:mysql://localhost:3306/colegio";
		
		usuario = "uemerson";
		senha = "12345678";
		url = "jdbc:mysql://bancodados.cvbbsyhqb8hp.sa-east-1.rds.amazonaws.com:3306/COLEGIO";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, usuario, senha);
			
			System.out.println("Conexão realizada com sucesso!");
		}catch (ClassNotFoundException | SQLException e) {
            System.err.print(e.getMessage());
		}
	}
	
	public static Connection getInstance() {
		if (connection == null) {
			synchronized (DAOConexaoMySQL.class) {
				conexao = new DAOConexaoMySQL();
			}
		}
		return connection;
	}
	
	public static void closeInstance() throws SQLException{
		if (connection != null) {
			connection.close();
			System.out.println("Conexão finalizada com sucesso!");
		}
	}
}