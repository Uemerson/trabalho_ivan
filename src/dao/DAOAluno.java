package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Aluno;

public class DAOAluno {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoAluno(Aluno aluno) throws SQLException {
		SQL = "INSERT INTO ALUNO (NOME, RG, ORGAO_EMISSOR, CPF, DATA_NASCIMENTO, SEXO, COR, RACA, LOGRADOURO, ENDERECO"
				+ ", NUMERO, BAIRRO, CIDADE, ESTADO, TELEFONE_RESIDENCIAL, CELULAR, EMAIL"
				+ ", ORDEM_ALUNO, SITUACAO_ALUNO, OBSERVACOES, ID_PAI, ID_MAE, LOCAL_ORIGEM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, aluno.getNome());
		preparedStatement.setString(2, aluno.getRG());
		preparedStatement.setString(3, aluno.getOrgao_Emissor());
		preparedStatement.setString(4, aluno.getCPF());
		preparedStatement.setDate(5, new java.sql.Date(aluno.getData_de_Nascimento().getTime()));
		preparedStatement.setString(6, aluno.getSexo());
		preparedStatement.setString(7, aluno.getCor());
		preparedStatement.setString(8, aluno.getRaca());
		preparedStatement.setString(9, aluno.getLogradouro());
		preparedStatement.setString(10, aluno.getEnderco());
		preparedStatement.setInt(11, aluno.getNumero_da_Casa());
		preparedStatement.setString(12, aluno.getBairro());
		preparedStatement.setString(13, aluno.getCidade());
		preparedStatement.setString(14, aluno.getEstado());
		preparedStatement.setString(15, aluno.getTel_Residencial());
		preparedStatement.setString(16, aluno.getCelular());
		preparedStatement.setString(17, aluno.getEmail());
		preparedStatement.setString(18, aluno.getRede_Estabelecimento_de_Ordem_do_aluno());
		preparedStatement.setString(19, aluno.getSituacao_do_Aluno_no_Ano_Anterior());
		preparedStatement.setString(20, aluno.getObservacoes_do_Aluno());
		preparedStatement.setInt(21, aluno.getIdPai());
		preparedStatement.setInt(22, aluno.getIdMae());
		preparedStatement.setString(23, aluno.getLocal_de_Origem_do_Aluno());
		
		preparedStatement.execute();
		System.out.println("Cadastro de aluno realizado com sucesso!");
	}

	public ArrayList<Aluno> listaAluno() throws SQLException{
		SQL = "SELECT * FROM ALUNO";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getString("RG"), rs.getString("CPF")));
		}
		
		return lista;
	}
	
	public ArrayList<Aluno> listaAluno(int ID) throws SQLException{
		SQL = "SELECT * FROM ALUNO WHERE ALUNO.ID = ?";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getString("RG"), rs.getString("CPF")));
		}
		
		return lista;
	}
	
	public ArrayList<Aluno> listaAlunoNome(String Nome) throws SQLException{
		SQL = "SELECT * FROM ALUNO WHERE ALUNO.NOME LIKE ?";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + Nome + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getString("RG"), rs.getString("CPF")));
		}
		
		return lista;
	}
	
	public ArrayList<Aluno> listaAlunoRG(String RG) throws SQLException{
		SQL = "SELECT * FROM ALUNO WHERE ALUNO.RG LIKE ?";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + RG + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getString("RG"), rs.getString("CPF")));
		}
		
		return lista;
	}
	
	public ArrayList<Aluno> listaAlunoCPF(String CPF) throws SQLException{
		SQL = "SELECT * FROM ALUNO WHERE ALUNO.CPF LIKE ?";
		
		ArrayList<Aluno> lista = new ArrayList<>();
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + CPF + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			lista.add(new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getString("RG"), rs.getString("CPF")));
		}
		
		return lista;
	}

	public Aluno buscaAluno(int ID) throws SQLException {
		SQL = "select a.*, r.NOME AS NOME_PAI, r2.NOME NOME_MAE \r\n" + 
				"FROM ALUNO as a \r\n" + 
				"join RESPONSAVEL as r on a.ID_PAI = r.ID\r\n" + 
				"join RESPONSAVEL as r2 on a.ID_MAE = r2.ID WHERE a.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return new Aluno(rs.getInt("ID"), rs.getDate("DATA_NASCIMENTO"), rs.getInt("NUMERO"), rs.getInt("ID_PAI"), 
				rs.getInt("ID_MAE"), rs.getString("NOME"), rs.getString("RG"), rs.getString("ORGAO_EMISSOR"), rs.getString("CPF"),
				rs.getString("SEXO"), rs.getString("COR"), rs.getString("RACA"), rs.getString("LOGRADOURO"), rs.getString("ENDERECO"),
				rs.getString("BAIRRO"), rs.getString("TELEFONE_RESIDENCIAL"), rs.getString("CELULAR"), rs.getString("EMAIL"),
				rs.getString("LOCAL_ORIGEM"), rs.getString("ORDEM_ALUNO"), rs.getString("SITUACAO_ALUNO"),
				rs.getString("OBSERVACOES"), rs.getString("CIDADE"), rs.getString("ESTADO"), rs.getString("NOME_PAI"), rs.getString("NOME_MAE"));
	}
	
	public void excluirAluno(int ID) throws SQLException {
		SQL = "DELETE FROM ALUNO WHERE ALUNO.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
	}
	
	public void atualizaAluno(Aluno aluno) throws SQLException {
		SQL = "UPDATE ALUNO SET NOME = ?, RG = ?, ORGAO_EMISSOR = ?, CPF = ?, DATA_NASCIMENTO = ?, SEXO = ?, COR = ?, RACA = ?,"
				+ "LOGRADOURO = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, TELEFONE_RESIDENCIAL = ?,"
				+ "CELULAR = ?, EMAIL = ?, ORDEM_ALUNO = ?, SITUACAO_ALUNO = ?, OBSERVACOES = ?, ID_PAI = ?,"
				+ "ID_MAE = ?, LOCAL_ORIGEM = ? WHERE ALUNO.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, aluno.getNome());
		preparedStatement.setString(2, aluno.getRG());
		preparedStatement.setString(3, aluno.getOrgao_Emissor());
		preparedStatement.setString(4, aluno.getCPF());
		preparedStatement.setDate(5, new java.sql.Date(aluno.getData_de_Nascimento().getTime()));
		preparedStatement.setString(6, aluno.getSexo());
		preparedStatement.setString(7, aluno.getCor());
		preparedStatement.setString(8, aluno.getRaca());
		preparedStatement.setString(9, aluno.getLogradouro());
		preparedStatement.setString(10, aluno.getEndereco());
		preparedStatement.setInt(11, aluno.getNumero_da_Casa());
		preparedStatement.setString(12, aluno.getBairro());
		preparedStatement.setString(13, aluno.getCidade());
		preparedStatement.setString(14, aluno.getEstado());
		preparedStatement.setString(15, aluno.getTel_Residencial());
		preparedStatement.setString(16, aluno.getCelular());
		preparedStatement.setString(17, aluno.getEmail());
		preparedStatement.setString(18, aluno.getRede_Estabelecimento_de_Ordem_do_aluno());
		preparedStatement.setString(19, aluno.getSituacao_do_Aluno_no_Ano_Anterior());
		preparedStatement.setString(20, aluno.getObservacoes_do_Aluno());
		preparedStatement.setInt(21, aluno.getIdPai());
		preparedStatement.setInt(22, aluno.getIdMae());
		preparedStatement.setString(23, aluno.getLocal_de_Origem_do_Aluno());
		preparedStatement.setInt(24, aluno.getRegistro());
		
		preparedStatement.execute();
		System.out.println("Cadastro de aluno atualizado com sucesso!");
	}
}
