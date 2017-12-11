package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Matricula;

public class DAOMatricula {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoMatricula(Matricula matricula) throws SQLException {
		SQL = "INSERT INTO MATRICULA (ID_ALUNO, DATA_MATRICULA, NIVEL, SERIE, TURNO, DIA_VENCIMENTO, ID_RESPONSAVEL, ID_MENSALIDADE, SECRETARIO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, matricula.getAluno());
		preparedStatement.setDate(2, new java.sql.Date(matricula.getData_de_Matricula().getTime()));
		preparedStatement.setString(3, matricula.getNivel_Em_Que__Esta_Sendo_Matriculado());
		preparedStatement.setString(4, matricula.getSerie());
		preparedStatement.setString(5, matricula.getTurno());
		preparedStatement.setInt(6, matricula.getVencimentoDia());
		preparedStatement.setInt(7, matricula.getResponsavel());
		preparedStatement.setInt(8, matricula.getMensalidade());
		preparedStatement.setInt(9, matricula.getSecretario());
		
		preparedStatement.execute();
		System.out.println("Matricula criada com sucesso!");
	}
	
	public ArrayList<Matricula> listaMatricula() throws SQLException{
		ArrayList<Matricula> listaMatricula = new ArrayList<>();
		
		SQL = 	"SELECT MATRICULA.*, RESPONSAVEL.NOME AS RESPONSAVEL, MENSALIDADE.ID AS MENSALIDADE,\r\n" + 
				"FUNCIONARIO.NOME AS NOME_SECRETARIO, ALUNO.NOME AS ALUNO\r\n" + 
				"FROM MATRICULA, RESPONSAVEL, MENSALIDADE, FUNCIONARIO, ALUNO WHERE MATRICULA.ID_RESPONSAVEL = RESPONSAVEL.ID AND\r\n" + 
				"MATRICULA.ID_MENSALIDADE = MENSALIDADE.ID AND MATRICULA.SECRETARIO = FUNCIONARIO.ID AND MATRICULA.ID_ALUNO = ALUNO.ID";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			listaMatricula.add(new Matricula(rs.getInt("ID"), rs.getInt("ID_ALUNO"), rs.getInt("SECRETARIO"), rs.getInt("ID_RESPONSAVEL"),
					rs.getInt("ID_MENSALIDADE"), rs.getInt("DIA_VENCIMENTO"), rs.getDate("DATA_MATRICULA"), rs.getString("NIVEL"),
					rs.getString("SERIE"), rs.getString("TURNO"), rs.getString("ALUNO"), rs.getString("RESPONSAVEL"), rs.getString("NOME_SECRETARIO"),
					"Mensalidade n° " + rs.getString("MENSALIDADE")));
		}
		
		return listaMatricula;
	}
	
	public ArrayList<Matricula> listaMatricula(int ID) throws SQLException{
		ArrayList<Matricula> listaMatricula = new ArrayList<>();
		
		SQL = 	"SELECT MATRICULA.*, RESPONSAVEL.NOME AS RESPONSAVEL, MENSALIDADE.ID AS MENSALIDADE,\r\n" + 
				"FUNCIONARIO.NOME AS NOME_SECRETARIO, ALUNO.NOME AS ALUNO\r\n" + 
				"FROM MATRICULA, RESPONSAVEL, MENSALIDADE, FUNCIONARIO, ALUNO WHERE MATRICULA.ID_RESPONSAVEL = RESPONSAVEL.ID AND\r\n" + 
				"MATRICULA.ID_MENSALIDADE = MENSALIDADE.ID AND MATRICULA.SECRETARIO = FUNCIONARIO.ID AND MATRICULA.ID_ALUNO = ALUNO.ID AND ALUNO.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			listaMatricula.add(new Matricula(rs.getInt("ID"), rs.getInt("ID_ALUNO"), rs.getInt("SECRETARIO"), rs.getInt("ID_RESPONSAVEL"),
					rs.getInt("ID_MENSALIDADE"), rs.getInt("DIA_VENCIMENTO"), rs.getDate("DATA_MATRICULA"), rs.getString("NIVEL"),
					rs.getString("SERIE"), rs.getString("TURNO"), rs.getString("ALUNO"), rs.getString("RESPONSAVEL"), rs.getString("NOME_SECRETARIO"),
					"Mensalidade n° " + rs.getString("MENSALIDADE")));
		}
		
		return listaMatricula;
	}

	public ArrayList<Matricula> listaMatriculaAluno(String Aluno) throws SQLException{
		ArrayList<Matricula> listaMatricula = new ArrayList<>();
		
		SQL = 	"SELECT MATRICULA.*, RESPONSAVEL.NOME AS RESPONSAVEL, MENSALIDADE.ID AS MENSALIDADE,\r\n" + 
				"FUNCIONARIO.NOME AS NOME_SECRETARIO, ALUNO.NOME AS ALUNO\r\n" + 
				"FROM MATRICULA, RESPONSAVEL, MENSALIDADE, FUNCIONARIO, ALUNO WHERE MATRICULA.ID_RESPONSAVEL = RESPONSAVEL.ID AND\r\n" + 
				"MATRICULA.ID_MENSALIDADE = MENSALIDADE.ID AND MATRICULA.SECRETARIO = FUNCIONARIO.ID AND MATRICULA.ID_ALUNO = ALUNO.ID AND ALUNO.NOME LIKE ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Aluno + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			listaMatricula.add(new Matricula(rs.getInt("ID"), rs.getInt("ID_ALUNO"), rs.getInt("SECRETARIO"), rs.getInt("ID_RESPONSAVEL"),
					rs.getInt("ID_MENSALIDADE"), rs.getInt("DIA_VENCIMENTO"), rs.getDate("DATA_MATRICULA"), rs.getString("NIVEL"),
					rs.getString("SERIE"), rs.getString("TURNO"), rs.getString("ALUNO"), rs.getString("RESPONSAVEL"), rs.getString("NOME_SECRETARIO"),
					"Mensalidade n° " + rs.getString("MENSALIDADE")));
		}
		
		return listaMatricula;
	}
	
	public ArrayList<Matricula> listaMatriculaResponsavel(String Responsavel) throws SQLException{
		ArrayList<Matricula> listaMatricula = new ArrayList<>();
		
		SQL = 	"SELECT MATRICULA.*, RESPONSAVEL.NOME AS RESPONSAVEL, MENSALIDADE.ID AS MENSALIDADE,\r\n" + 
				"FUNCIONARIO.NOME AS NOME_SECRETARIO, ALUNO.NOME AS ALUNO\r\n" + 
				"FROM MATRICULA, RESPONSAVEL, MENSALIDADE, FUNCIONARIO, ALUNO WHERE MATRICULA.ID_RESPONSAVEL = RESPONSAVEL.ID AND\r\n" + 
				"MATRICULA.ID_MENSALIDADE = MENSALIDADE.ID AND MATRICULA.SECRETARIO = FUNCIONARIO.ID AND MATRICULA.ID_ALUNO = ALUNO.ID AND RESPONSAVEL.NOME LIKE ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Responsavel + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			listaMatricula.add(new Matricula(rs.getInt("ID"), rs.getInt("ID_ALUNO"), rs.getInt("SECRETARIO"), rs.getInt("ID_RESPONSAVEL"),
					rs.getInt("ID_MENSALIDADE"), rs.getInt("DIA_VENCIMENTO"), rs.getDate("DATA_MATRICULA"), rs.getString("NIVEL"),
					rs.getString("SERIE"), rs.getString("TURNO"), rs.getString("ALUNO"), rs.getString("RESPONSAVEL"), rs.getString("NOME_SECRETARIO"),
					"Mensalidade n° " + rs.getString("MENSALIDADE")));
		}
		
		return listaMatricula;
	}
	
	public Matricula buscaMatricula(int ID) throws SQLException{
		ArrayList<Matricula> listaMatricula = new ArrayList<>();
		
		SQL = 	"SELECT MATRICULA.*, RESPONSAVEL.NOME AS RESPONSAVEL, MENSALIDADE.ID AS MENSALIDADE,\r\n" + 
				"FUNCIONARIO.NOME AS NOME_SECRETARIO, ALUNO.NOME AS ALUNO\r\n" + 
				"FROM MATRICULA, RESPONSAVEL, MENSALIDADE, FUNCIONARIO, ALUNO WHERE MATRICULA.ID_RESPONSAVEL = RESPONSAVEL.ID AND\r\n" + 
				"MATRICULA.ID_MENSALIDADE = MENSALIDADE.ID AND MATRICULA.SECRETARIO = FUNCIONARIO.ID AND MATRICULA.ID_ALUNO = ALUNO.ID AND MATRICULA.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return new Matricula(rs.getInt("ID"), rs.getInt("ID_ALUNO"), rs.getInt("SECRETARIO"), rs.getInt("ID_RESPONSAVEL"),
					rs.getInt("ID_MENSALIDADE"), rs.getInt("DIA_VENCIMENTO"), rs.getDate("DATA_MATRICULA"), rs.getString("NIVEL"),
					rs.getString("SERIE"), rs.getString("TURNO"), rs.getString("ALUNO"), rs.getString("RESPONSAVEL"), rs.getString("NOME_SECRETARIO"),
					"Mensalidade n° " + rs.getString("MENSALIDADE"));
		
		
	}

	public void atualizaMatricula(Matricula matricula) throws SQLException {
		SQL = "UPDATE MATRICULA SET ID_ALUNO = ?, DATA_MATRICULA = ?, NIVEL = ?, SERIE = ?, TURNO = ?, DIA_VENCIMENTO = ?, "
				+ "ID_RESPONSAVEL = ?, ID_MENSALIDADE = ?, SECRETARIO = ? WHERE MATRICULA.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, matricula.getAluno());
		preparedStatement.setDate(2, new java.sql.Date(matricula.getData_de_Matricula().getTime()));
		preparedStatement.setString(3, matricula.getNivel_Em_Que__Esta_Sendo_Matriculado());
		preparedStatement.setString(4, matricula.getSerie());
		preparedStatement.setString(5, matricula.getTurno());
		preparedStatement.setInt(6, matricula.getVencimentoDia());
		preparedStatement.setInt(7, matricula.getResponsavel());
		preparedStatement.setInt(8, matricula.getMensalidade());
		preparedStatement.setInt(9, matricula.getSecretario());
		preparedStatement.setInt(10, matricula.getRegistro());
		
		preparedStatement.execute();
		System.out.println("Matricula alterada com sucesso!");
	}
	
	public void excluirMatricula(int ID) throws SQLException {
		SQL = "DELETE FROM MATRICULA WHERE MATRICULA.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
		System.out.println("Matricula excluida com sucesso!");
	}
}
