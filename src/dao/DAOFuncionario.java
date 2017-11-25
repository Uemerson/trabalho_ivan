package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Funcionario;

public class DAOFuncionario {
	private String SQL;
	private PreparedStatement preparedStatement;
	
	public void novoFuncionario(Funcionario funcionario) throws SQLException {
		SQL = "INSERT INTO Funcionario (NOME, DATA_NASCIMENTO, RG, CPF, LOGRADOURO, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, " +
			"TELEFONE_RESIDENCIAL, TELEFONE_COMERCIAL, CELULAR, EMAIL, CARGO, DATA_ADMISSAO, DATA_DEMISSAO, SALARIO, " + 
			" FORMACAO, NUM_AUTORIZACAO_SER, DATA_AUTORIZACAO_SER, NUM_REGISTRO_DIPLOMA) VALUES (?, ?, ?, ?, ? , ? , ?, ?, " + 
			"?, ?, ?, ?, ?, ?, ? , ? , ?, ?, ?, ?, ?, ?)";
		
		//System.out.println(funcionario.getData_de_Nascimento());
		//System.out.println(data);
		
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
		/*
		 * NOME VARCHAR(50) NOT NULL,
			DATA_NASCIMENTO DATE,
			RG VARCHAR(20),
			CPF VARCHAR(10),
			LOGRADOURO VARCHAR(15),
			ENDERECO VARCHAR(20),
			NUMERO INT(10),
			BAIRRO VARCHAR(20),
			CIDADE VARCHAR(25),
			ESTADO VARCHAR(2),
			TELEFONE_RESIDENCIAL VARCHAR(15),
			TELEFONE_COMERCIAL VARCHAR(15),
			CELULAR VARCHAR(15),
			EMAIL VARCHAR(25),
			CARGO VARCHAR(15),
			DATA_ADMISSAO DATE,
			DATA_DEMISSAO DATE,
			SALARIO DOUBLE,
			FORMACAO CHAR(50),
			NUM_AUTORIZACAO_SER INT,
			DATA_AUTORIZACAO_SER DATE,
			NUM_REGISTRO_DIPLOMA INT,
		 * */
	}
}
