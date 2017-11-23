package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

public class DAOUsuario {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public ArrayList<Usuario> listaUsuarios() throws SQLException{
		
		SQL = "SELECT * FROM USUARIO";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Usuario> lista = new ArrayList<>();
		
		while(rs.next()) {
			lista.add(new Usuario(rs.getInt("REGISTRO"), rs.getString("LOGIN"), rs.getString("SENHA")));
		}
		
		return lista;
	}
	
	public Usuario verificaLogin(Usuario usuario) throws SQLException {
		
		SQL = "SELECT * FROM USUARIO WHERE Login = ? AND Senha = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, usuario.getLogin());
		preparedStatement.setString(2, usuario.getSenha());
		
		ResultSet rs = preparedStatement.executeQuery();
		
		if (rs.next())
			return new Usuario(rs.getInt("REGISTRO"), rs.getString("Login"), rs.getString("Senha"));
		
		return null;
	}
}
