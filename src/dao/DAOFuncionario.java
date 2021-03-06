package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cargo;
import model.Funcionario;
import model.Usuario;

public class DAOFuncionario {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoFuncionario(Funcionario funcionario) throws SQLException {
		SQL = "INSERT INTO FUNCIONARIO (NOME, DATA_NASCIMENTO, RG, CPF, LOGRADOURO, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, " +
			"TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL, CELULAR, EMAIL, CARGO, DATA_ADMISSAO, DATA_DEMISSAO, SALARIO, " + 
			" FORMACAO, NUMERO_SER, DATA_SER, NUMERO_DIPLOMA) VALUES (?, ?, ?, ?, ? , ? , ?, ?, " + 
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
		preparedStatement.setInt(15, funcionario.getCargo());
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
		SQL = "UPDATE FUNCIONARIO SET NOME = ?, DATA_NASCIMENTO = ?, RG = ?, CPF = ?, LOGRADOURO = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, " +
			"TELEFONE_RESIDENCIAL = ?, TELEFONE_COMERCIAL = ?, CELULAR = ?, EMAIL = ?, CARGO = ?, DATA_ADMISSAO = ?, DATA_DEMISSAO = ?, SALARIO = ?, " + 
			" FORMACAO = ?, NUMERO_SER = ?, DATA_SER = ?, NUMERO_DIPLOMA = ? WHERE ID = ?";
			
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
		preparedStatement.setInt(15, funcionario.getCargo());
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
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID ORDER BY ID ASC";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getInt("Cargo"), rs.getString("NOME_CARGO")));
		}
		
		return lista;
	}
	
	public ArrayList<Funcionario> listaFuncionario(int ID) throws SQLException {
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID AND FUNCIONARIO.ID = ? ORDER BY ID ASC";
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getInt("Cargo"), rs.getString("NOME_CARGO")));
		}
		
		return lista;
	}

	public ArrayList<Funcionario> listaFuncionarioNome(String nome) throws SQLException {
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID AND FUNCIONARIO.NOME LIKE ? ORDER BY ID ASC";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + nome + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getInt("Cargo"), rs.getString("NOME_CARGO")));
		}
		
		return lista;
	}
	
	public ArrayList<Funcionario> listaFuncionarioCargo(String cargo) throws SQLException {
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID AND CARGO.NOME LIKE ?";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + cargo + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getInt("Cargo"), rs.getString("NOME_CARGO")));
		}
		
		return lista;
	}
	
	public ArrayList<Funcionario> listaFuncionarioCPF(String CPF) throws SQLException {
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID AND FUNCIONARIO.CPF LIKE ? ORDER BY ID ASC";
		
		ArrayList<Funcionario> lista = new ArrayList<>();
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setString(1, "%" + CPF + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new Funcionario(rs.getInt("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getInt("Cargo"), rs.getString("NOME_CARGO")));
		}
		
		return lista;
	}
	
	public Funcionario buscaFuncionario(int ID) throws SQLException {
		SQL = "SELECT FUNCIONARIO.*, CARGO.NOME AS NOME_CARGO FROM FUNCIONARIO, CARGO WHERE FUNCIONARIO.CARGO = CARGO.ID AND FUNCIONARIO.ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		rs.next();
		
		return new Funcionario(rs.getInt("ID"), rs.getInt("Numero"), rs.getInt("NUMERO_SER"), rs.getInt("NUMERO_DIPLOMA"), 
				rs.getDouble("Salario"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("RG"), rs.getString("LOGRADOURO"), rs.getString("Endereco"), rs.getString("Bairro"),
				rs.getString("Cidade"), rs.getString("Estado"), rs.getString("TELEFONE_RESIDENCIAL"), rs.getString("TELEFONE_COMERCIAL"), rs.getString("CELULAR"), rs.getString("Email"), 
				rs.getString("Formacao"), rs.getInt("Cargo"), rs.getString("NOME_CARGO"), rs.getDate("DATA_SER"), rs.getDate("DATA_ADMISSAO"), rs.getDate("DATA_DEMISSAO"), rs.getDate("DATA_NASCIMENTO"));
		
	}

	public void excluirFuncionario(int ID) throws SQLException {
		SQL = "DELETE FROM FUNCIONARIO WHERE ID = ?";
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, ID);
		preparedStatement.executeUpdate();
	}

	public Cargo buscaCargo(Usuario usuario) throws SQLException {
		SQL = "SELECT USUARIO.*, FUNCIONARIO.CARGO, CARGO.NOME AS NOME_CARGO FROM\r\n" + 
				"USUARIO LEFT JOIN FUNCIONARIO ON USUARIO.ID_FUNC = FUNCIONARIO.ID\r\n" + 
				"LEFT JOIN CARGO ON CARGO.ID = FUNCIONARIO.CARGO WHERE USUARIO.ID = ?";
		
		preparedStatement = DAOConexaoMySQL.getInstance().prepareStatement(SQL);
		preparedStatement.setInt(1, usuario.getRegistro());
		
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		
		return rs.getString("LOGIN").equals("Administrador") ? 
				new Cargo("Administrador") : 
					new Cargo(rs.getString("NOME_CARGO"));
	}
}
