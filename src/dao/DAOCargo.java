package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cargo;

public class DAOCargo {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public ArrayList<Cargo> listaCargos() throws SQLException{
		SQL = "SELECT * FROM CARGO ORDER BY CARGO.ID ASC;";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Cargo> lista = new ArrayList<>();
		
		while(rs.next()) {
			lista.add(new Cargo(rs.getInt("ID"), rs.getString("NOME"), rs.getString("DESCRICAO")));
		}
		
		return lista;
	}
	
	public int idCargo(String nome) throws SQLException {
		SQL = "SELECT ID FROM CARGO WHERE NOME = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, nome);
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		
		return rs.getInt("ID");
	}
	
	public void novoCargo(Cargo cargo) throws SQLException {
		SQL = "INSERT INTO CARGO (NOME, DESCRICAO) VALUES (?, ?)";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, cargo.getNome());
		preparedStatement.setString(2, cargo.getDescricao());
		
		preparedStatement.execute();
		
		System.out.println("Cadastro de cargo com sucesso!");
	}

	public void excluirCargo(int ID) throws SQLException {
		SQL = "DELETE FROM CARGO WHERE CARGO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
		
		System.out.println("Cadastro de cargo excluido com sucesso!");
	}
	
	public Cargo buscaCargo(int ID) throws SQLException {
		SQL = "SELECT * FROM CARGO WHERE CARGO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return new Cargo(rs.getInt("ID"), rs.getString("NOME"), rs.getString("DESCRICAO"));
	}
	
	public ArrayList<Cargo> listaCargos(int ID) throws SQLException{
		SQL = "SELECT * FROM CARGO WHERE CARGO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Cargo> lista = new ArrayList<>();
		
		while(rs.next()) {
			lista.add(new Cargo(rs.getInt("ID"), rs.getString("NOME"), rs.getString("DESCRICAO")));
		}
		
		return lista;
	}
	
	public ArrayList<Cargo> listaCargosNome(String Nome) throws SQLException{
		SQL = "SELECT * FROM CARGO WHERE CARGO.NOME LIKE ? ORDER BY CARGO.ID ASC";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Nome + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Cargo> lista = new ArrayList<>();
		
		while(rs.next()) {
			lista.add(new Cargo(rs.getInt("ID"), rs.getString("NOME"), rs.getString("DESCRICAO")));
		}
		
		return lista;
	}

	public void atualizaCargo(Cargo cargo) throws SQLException {
		SQL = "UPDATE CARGO SET CARGO.NOME = ?, CARGO.DESCRICAO = ? WHERE CARGO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, cargo.getNome());
		preparedStatement.setString(2, cargo.getDescricao());
		preparedStatement.setInt(3, cargo.getRegistro());
		
		preparedStatement.execute();
		
		System.out.println("Cadastro de cargo atualizado com sucesso!");
	}
}
