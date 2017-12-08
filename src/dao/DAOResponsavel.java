package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Responsavel;

public class DAOResponsavel {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoResponsavel(Responsavel responsavel) throws SQLException {
		SQL = "INSERT INTO RESPONSAVEL(NOME, GRAU_INSTRUCAO, PROFISSAO, NOME_LOCAL, HORARIO, LOGRADOURO, ENDERECO, NUMERO, DATA_NASCIMENTO, "
				+ " RG, CPF, RENDA, CASA_PROPRIA, NUMERO_FILHOS, NUMERO_PESSOAS_CASA) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, responsavel.getNome_do_Responsavel());
		preparedStatement.setString(2, responsavel.getGrau_de_Intrucao());
		preparedStatement.setString(3, responsavel.getProfissao());
		preparedStatement.setString(4, responsavel.getLocal_de_Trabalho());
		preparedStatement.setTime(5, responsavel.getHorario_de_Trabalho());
		preparedStatement.setString(6, responsavel.getLogradouro());
		preparedStatement.setString(7, responsavel.getEndereco());
		preparedStatement.setInt(8, responsavel.getNumeroCasa());
		preparedStatement.setDate(9, new java.sql.Date(responsavel.getData_de_Nascimento().getTime()));
		preparedStatement.setString(10, responsavel.getRG());
		preparedStatement.setString(11, responsavel.getCPF());
		preparedStatement.setDouble(12, responsavel.getRenda());
		preparedStatement.setBoolean(13, responsavel.getCasa_Propria());
		preparedStatement.setInt(14, responsavel.getNumero_de_Filhos());
		preparedStatement.setInt(15, responsavel.getNumero_de_Pessoas_que_Residem_na_Casa());
		
		preparedStatement.execute();
		System.out.println("Cadastro de responsavel realizado com sucesso!");
	}
	
	public void excluirResponsavel(int ID) throws SQLException {
		SQL = "DELETE FROM RESPONSAVEL WHERE RESPONSAVEL.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
	}
	
	public ArrayList<Responsavel> listaResponsavel() throws SQLException{
		ArrayList<Responsavel> lista = new ArrayList<>();
		SQL = "SELECT * FROM RESPONSAVEL";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG")));
		}
		
		return lista;
	}
	
	public ArrayList<Responsavel> listaResponsavel(int ID) throws SQLException{
		ArrayList<Responsavel> lista = new ArrayList<>();
		SQL = "SELECT * FROM RESPONSAVEL WHERE RESPONSAVEL.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG")));
		}
		
		return lista;
	}
	
	public ArrayList<Responsavel> listaResponsavelNome(String nome) throws SQLException{
		ArrayList<Responsavel> lista = new ArrayList<>();
		SQL = "SELECT * FROM RESPONSAVEL WHERE RESPONSAVEL.NOME LIKE ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + nome + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG")));
		}
		
		return lista;
	}
	
	public ArrayList<Responsavel> listaResponsavelRG(String RG) throws SQLException{
		ArrayList<Responsavel> lista = new ArrayList<>();
		SQL = "SELECT * FROM RESPONSAVEL WHERE RESPONSAVEL.RG LIKE ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + RG + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG")));
		}
		
		return lista;
	}
	
	public ArrayList<Responsavel> listaResponsavelCPF(String CPF) throws SQLException{
		ArrayList<Responsavel> lista = new ArrayList<>();
		SQL = "SELECT * FROM RESPONSAVEL WHERE RESPONSAVEL.CPF LIKE ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + CPF + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG")));
		}
		
		return lista;
	}

	public Responsavel buscaResponsavel(int ID) throws SQLException {
		SQL = "SELECT * FROM RESPONSAVEL WHERE RESPONSAVEL.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
	
		return new Responsavel(rs.getInt("ID"), rs.getString("NOME"), rs.getString("GRAU_INSTRUCAO"), rs.getString("PROFISSAO"),
								rs.getString("NOME_LOCAL"), rs.getTime("HORARIO"), rs.getString("LOGRADOURO"), rs.getString("ENDERECO"),
								rs.getInt("NUMERO"), rs.getDate("DATA_NASCIMENTO"), rs.getString("RG"), rs.getString("CPF"),
								rs.getDouble("RENDA"), rs.getBoolean("CASA_PROPRIA"), rs.getInt("NUMERO_FILHOS"), rs.getInt("NUMERO_PESSOAS_CASA")); 
		
		
	}
	
}
