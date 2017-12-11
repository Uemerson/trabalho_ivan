package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	}
}
