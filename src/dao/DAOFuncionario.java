package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;

public class DAOFuncionario {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoFuncionario(Funcionario funcionario) throws SQLException {
		SQL = "INSERT INTO Funcionario (NOME, DATA_NASCIMENTO, RG, CPF, LOGRADOURO, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, " +
			"TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL, CELULAR, EMAIL, CARGO, DATA_ADMISSAO, DATA_DEMISSAO, SALARIO, " + 
			" FORMACAO, NUM_AUTORIZACAO_SER, DATA_AUTORIZACAO_SER, NUM_REGISTRO_DIPLOMA) VALUES (?, ?, ?, ?, ? , ? , ?, ?, " + 
			"?, ?, ?, ?, ?, ?, ? , ? , ?, ?, ?, ?, ?, ?)";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, funcionario.getNome());
		preparedStatement.setDate(2,  new java.sql.Date(funcionario.getData_de_Nascimento().getTime()));
		preparedStatement.setString(3, funcionario.getRG());
		preparedStatement.setString(4, funcionario.getCPF());
		preparedStatement.setString(5, funcionario.getLogradouro());
		preparedStatement.setString(6, funcionario.getEndereco());
		preparedStatement.setInt(7, funcionario.getNumero_da_Casa());
		preparedStatement.setString(8, funcionario.getBairro());
		preparedStatement.setString(9, funcionario.getCidade());
		preparedStatement.setString(10, funcionario.getEstado());
		preparedStatement.setString(11, funcionario.getTel_Residencial());
		preparedStatement.setString(12, funcionario.getTel_Comercial());
		preparedStatement.setString(13, funcionario.getCelular());
		preparedStatement.setString(14, funcionario.getEmail());
		preparedStatement.setString(15, funcionario.getCargo());
		preparedStatement.setDate(16, funcionario.getData_de_Admissao() == null ? null : new java.sql.Date(funcionario.getData_de_Admissao().getTime()));
		preparedStatement.setDate(17, funcionario.getData_de_Demissao() == null ? null : new java.sql.Date(funcionario.getData_de_Demissao().getTime()));
		preparedStatement.setDouble(18, funcionario.getSalario());
		preparedStatement.setString(19, funcionario.getFormacao_Academica());
		preparedStatement.setInt(20, funcionario.getNumero_de_Autorizacao_da_SER());
		preparedStatement.setDate(21, (funcionario.getData_de_Autorizacao() == null) ? null : new java.sql.Date(funcionario.getData_de_Autorizacao().getTime()));
		preparedStatement.setInt(22, funcionario.getNumero_do_Registro_do_Diploma());
		
		preparedStatement.execute();
		System.out.println("Cadastro de funcionario com sucesso!");
		
	}
	
	public void atualizaFuncionario(Funcionario funcionario) throws SQLException {
		SQL = "UPDATE Funcionario SET NOME = ?, DATA_NASCIMENTO = ?, RG = ?, CPF = ?, LOGRADOURO = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, " +
			"TELEFONE_RESIDENCIAL = ?, TELEFONE_COMERCIAL = ?, CELULAR = ?, EMAIL = ?, CARGO = ?, DATA_ADMISSAO = ?, DATA_DEMISSAO = ?, SALARIO = ?, " + 
			" FORMACAO = ?, NUM_AUTORIZACAO_SER = ?, DATA_AUTORIZACAO_SER = ?, NUM_REGISTRO_DIPLOMA = ? WHERE ID = ?";
			
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, funcionario.getNome());
		preparedStatement.setDate(2,  new java.sql.Date(funcionario.getData_de_Nascimento().getTime()));
		preparedStatement.setString(3, funcionario.getRG());
		preparedStatement.setString(4, funcionario.getCPF());
		preparedStatement.setString(5, funcionario.getLogradouro());
		preparedStatement.setString(6, funcionario.getEndereco());
		preparedStatement.setInt(7, funcionario.getNumero_da_Casa());
		preparedStatement.setString(8, funcionario.getBairro());
		preparedStatement.setString(9, funcionario.getCidade());
		preparedStatement.setString(10, funcionario.getEstado());
		preparedStatement.setString(11, funcionario.getTel_Residencial());
		preparedStatement.setString(12, funcionario.getTel_Comercial());
		preparedStatement.setString(13, funcionario.getCelular());
		preparedStatement.setString(14, funcionario.getEmail());
		preparedStatement.setString(15, funcionario.getCargo());
		preparedStatement.setDate(16, funcionario.getData_de_Admissao() == null ? null : new java.sql.Date(funcionario.getData_de_Admissao().getTime()));
		preparedStatement.setDate(17, funcionario.getData_de_Demissao() == null ? null : new java.sql.Date(funcionario.getData_de_Demissao().getTime()));
		preparedStatement.setDouble(18, funcionario.getSalario());
		preparedStatement.setString(19, funcionario.getFormacao_Academica());
		preparedStatement.setInt(20, funcionario.getNumero_de_Autorizacao_da_SER());
		preparedStatement.setDate(21, (funcionario.getData_de_Autorizacao() == null) ? null : new java.sql.Date(funcionario.getData_de_Autorizacao().getTime()));
		preparedStatement.setInt(22, funcionario.getNumero_do_Registro_do_Diploma());
		preparedStatement.setInt(23, funcionario.getRegistro());
		
		preparedStatement.execute();
		System.out.println("Funcionario atualizado com sucesso!");
		
	}
	
	public ArrayList<Funcionario> listaFuncionario() throws SQLException{
		SQL = "SELECT * FROM FUNCIONARIO";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("Cargo")));
		}
		
		return lista;
	}
	
	public ArrayList<Funcionario> listaFuncionario(int ID) throws SQLException {
		SQL = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("Cargo")));
		}
		
		return lista;
	}

	public ArrayList<Funcionario> listaFuncionario(String nome) throws SQLException {
		SQL = "SELECT * FROM FUNCIONARIO WHERE nome like ?";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + nome + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("Cargo")));
		}
		
		return lista;
	}
	
	public ArrayList<Funcionario> listaFuncionarioCargo(String cargo) throws SQLException {
		SQL = "SELECT * FROM FUNCIONARIO WHERE cargo like ?";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + cargo + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("Cargo")));
		}
		
		return lista;
	}
	
	public Funcionario buscaFuncionario(int ID) throws SQLException {
		SQL = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		
		return new Funcionario(rs.getInt("ID"), rs.getInt("Numero"), rs.getInt("NUM_AUTORIZACAO_SER"), rs.getInt("NUM_REGISTRO_DIPLOMA"), 
				rs.getDouble("Salario"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("RG"), rs.getString("LOGRADOURO"), rs.getString("Endereco"), rs.getString("Bairro"),
				rs.getString("Cidade"), rs.getString("Estado"), rs.getString("TELEFONE_RESIDENCIAL"), rs.getString("TELEFONE_COMERCIAL"), rs.getString("CELULAR"), rs.getString("Email"), 
				rs.getString("Formacao"), rs.getString("Cargo"), rs.getDate("DATA_AUTORIZACAO_SER"), rs.getDate("DATA_ADMISSAO"), rs.getDate("DATA_DEMISSAO"), rs.getDate("DATA_NASCIMENTO"));
		
	}

	public void excluirFuncionario(int ID) throws SQLException {
		SQL = "DELETE FROM FUNCIONARIO WHERE ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
	}
}
