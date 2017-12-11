package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

public class DAOUsuario {
	private String SQL;
	private PreparedStatement preparedStatement;

	public ArrayList<Usuario> listaUsuarios() throws SQLException {

		SQL = "SELECT USUARIO.*, FUNCIONARIO.NOME AS NOME_FUNCIONARIO FROM USUARIO "
				+ "LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID ORDER BY FUNCIONARIO.ID ASC";

		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<>();

		while (rs.next()) {
			lista.add(new Usuario(rs.getInt("ID"), rs.getString("LOGIN"), rs.getString("SENHA"),
					rs.getString("NOME_FUNCIONARIO"), rs.getInt("ID_FUNC")));
		}

		return lista;
	}

	public ArrayList<Usuario> listaUsuarios(int ID) throws SQLException {

		SQL = "SELECT USUARIO.*, FUNCIONARIO.NOME AS NOME_FUNCIONARIO FROM USUARIO "
				+ "LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID WHERE USUARIO.ID = ? ORDER BY FUNCIONARIO.ID ASC";

		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<>();

		while (rs.next()) {
			lista.add(new Usuario(rs.getInt("ID"), rs.getString("LOGIN"), rs.getString("SENHA"),
					rs.getString("NOME_FUNCIONARIO"), rs.getInt("ID_FUNC")));
		}

		return lista;
	}
	
	public ArrayList<Usuario> listaUsuariosNome(String Nome) throws SQLException {

		SQL = "SELECT USUARIO.*, FUNCIONARIO.NOME AS NOME_FUNCIONARIO FROM USUARIO "
				+ "LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID WHERE FUNCIONARIO.NOME LIKE ? ORDER BY FUNCIONARIO.ID ASC";

		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Nome + "%");
		ResultSet rs = preparedStatement.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<>();

		while (rs.next()) {
			lista.add(new Usuario(rs.getInt("ID"), rs.getString("LOGIN"), rs.getString("SENHA"),
					rs.getString("NOME_FUNCIONARIO"), rs.getInt("ID_FUNC")));
		}

		return lista;
	}
	
	public ArrayList<Usuario> listaUsuariosLogin(String Login) throws SQLException {

		SQL = "SELECT USUARIO.*, FUNCIONARIO.NOME AS NOME_FUNCIONARIO FROM USUARIO "
				+ "LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID WHERE USUARIO.LOGIN LIKE ? ORDER BY FUNCIONARIO.ID ASC";

		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Login + "%");
		ResultSet rs = preparedStatement.executeQuery();

		ArrayList<Usuario> lista = new ArrayList<>();

		while (rs.next()) {
			lista.add(new Usuario(rs.getInt("ID"), rs.getString("LOGIN"), rs.getString("SENHA"),
					rs.getString("NOME_FUNCIONARIO"), rs.getInt("ID_FUNC")));
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
			return new Usuario(rs.getInt("ID"), rs.getString("Login"), rs.getString("Senha"));

		return null;
	}

	public void novoUsuario(Usuario usuario) throws SQLException {
		SQL = "INSERT INTO USUARIO (ID_FUNC, LOGIN, SENHA) VALUES (?, ?, ?)";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);

		preparedStatement.setInt(1, usuario.getId_funcionario());
		preparedStatement.setString(2, usuario.getLogin());
		preparedStatement.setString(3, usuario.getSenha());

		preparedStatement.execute();
		System.out.println("Cadastro de usuario com sucesso!");
	}

	public Usuario buscaUsuario(int ID) throws SQLException {
		SQL = "SELECT USUARIO.*, FUNCIONARIO.NOME AS NOME_FUNCIONARIO FROM USUARIO "
				+ "LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID WHERE USUARIO.ID = ? ORDER BY FUNCIONARIO.ID ASC";

		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);

		ResultSet rs = preparedStatement.executeQuery();
		rs.next();

		return new Usuario(rs.getInt("ID"), rs.getString("LOGIN"), rs.getString("SENHA"),
				rs.getString("NOME_FUNCIONARIO"), rs.getInt("ID_FUNC"));
	}

	public void excluirUsuario(int ID) throws SQLException {
		SQL = "DELETE FROM USUARIO WHERE USUARIO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
		System.out.println("Cadastro de usuario excluido com sucesso!");
	}

	public void alterarUsuario(Usuario usuario) throws SQLException {
		SQL = "UPDATE USUARIO SET USUARIO.LOGIN = ?, USUARIO.SENHA = ? WHERE USUARIO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, usuario.getLogin());
		preparedStatement.setString(2, usuario.getSenha());
		preparedStatement.setInt(3, usuario.getRegistro());
		preparedStatement.execute();

		System.out.println("Cadastro de usuario alterado com sucesso!");
	}

	public int idUsuario(String nome) throws SQLException {
		SQL = "SELECT * FROM USUARIO WHERE USUARIO.LOGIN = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, nome);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return rs.getInt("ID");
	}
}
