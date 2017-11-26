package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cargo;

public class DAOCargo {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public ArrayList<Cargo> listaCargo() throws SQLException{
		SQL = "SELECT * FROM CARGO ORDER BY CARGO.NOME ASC;";
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
}
