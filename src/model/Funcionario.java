package model;

import java.util.Date;

public class Funcionario {

	private int Registro, Numero_da_Casa, Numero_de_Autorizacao_da_SER, Numero_do_Registro_do_Diploma;
	private double Salario;
	private String Nome, CPF, RG, Logradouro, Endereco, Bairro, Cidade, Estado, Tel_Residencial, Tel_Comercial, Celular, Email, Formacao_Academica, Cargo;
	private Date Data_de_Autorizacao, Data_de_Admissao, Data_de_Demissao, Data_de_Nascimento;
	
	public Funcionario() {};
	
	public Funcionario(int Registro, String Nome, String CPF, String Cargo) {
		this.Registro = Registro;
		this.Nome = Nome;
		this.CPF = CPF;
		this.Cargo = Cargo;
	}
	
	public Funcionario(int registro, int numero_da_Casa, int numero_de_Autorizacao_da_SER,
			int numero_do_Registro_do_Diploma, double salario, String nome, String cPF, String rG, String logradouro,
			String endereco, String bairro, String cidade, String estado, String tel_Residencial, String tel_Comercial,
			String celular, String email, String formacao_Academica, String cargo, Date data_de_Autorizacao,
			Date data_de_Admissao, Date data_de_Demissao, Date data_de_Nascimento) {
		super();
		Registro = registro;
		Numero_da_Casa = numero_da_Casa;
		Numero_de_Autorizacao_da_SER = numero_de_Autorizacao_da_SER;
		Numero_do_Registro_do_Diploma = numero_do_Registro_do_Diploma;
		Salario = salario;
		Nome = nome;
		CPF = cPF;
		RG = rG;
		Logradouro = logradouro;
		Endereco = endereco;
		Bairro = bairro;
		Cidade = cidade;
		Estado = estado;
		Tel_Residencial = tel_Residencial;
		Tel_Comercial = tel_Comercial;
		Celular = celular;
		Email = email;
		Formacao_Academica = formacao_Academica;
		Cargo = cargo;
		Data_de_Autorizacao = data_de_Autorizacao;
		Data_de_Admissao = data_de_Admissao;
		Data_de_Demissao = data_de_Demissao;
		Data_de_Nascimento = data_de_Nascimento;
	}

	public Date getData_de_Admissao() {
		return Data_de_Admissao;
	}
	public void setData_de_Admissao(Date data_de_Admissao) {
		Data_de_Admissao = data_de_Admissao;
	}
	public int getRegistro() {
		return Registro;
	}
	public void setRegistro(int registro) {
		Registro = registro;
	}
	public int getNumero_de_Autorizacao_da_SER() {
		return Numero_de_Autorizacao_da_SER;
	}
	public void setNumero_de_Autorizacao_da_SER(int numero_de_Autorizacao_da_SER) {
		Numero_de_Autorizacao_da_SER = numero_de_Autorizacao_da_SER;
	}
	public int getNumero_do_Registro_do_Diploma() {
		return Numero_do_Registro_do_Diploma;
	}
	public void setNumero_do_Registro_do_Diploma(int numero_do_Registro_do_Diploma) {
		Numero_do_Registro_do_Diploma = numero_do_Registro_do_Diploma;
	}
	public double getSalario() {
		return Salario;
	}
	public void setSalario(double salario) {
		Salario = salario;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Date getData_de_Nascimento() {
		return Data_de_Nascimento;
	}
	public void setData_de_Nascimento(Date data_de_Nascimento) {
		Data_de_Nascimento = data_de_Nascimento;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public String getLogradouro() {
		return Logradouro;
	}
	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
	public int getNumero_da_Casa() {
		return Numero_da_Casa;
	}
	public void setNumero_da_Casa(int numero_da_Casa) {
		Numero_da_Casa = numero_da_Casa;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getTel_Residencial() {
		return Tel_Residencial;
	}
	public void setTel_Residencial(String tel_Residencial) {
		Tel_Residencial = tel_Residencial;
	}
	public String getTel_Comercial() {
		return Tel_Comercial;
	}
	public void setTel_Comercial(String tel_Comercial) {
		Tel_Comercial = tel_Comercial;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFormacao_Academica() {
		return Formacao_Academica;
	}
	public void setFormacao_Academica(String formacao_Academica) {
		Formacao_Academica = formacao_Academica;
	}
	public String getCargo() {
		return Cargo;
	}
	public void setCargo(String cargo) {
		Cargo = cargo;
	}
	public Date getData_de_Autorizacao() {
		return Data_de_Autorizacao;
	}
	public void setData_de_Autorizacao(Date data_de_Autorizacao) {
		Data_de_Autorizacao = data_de_Autorizacao;
	}
	public Date getData_de_Demissao() {
		return Data_de_Demissao;
	}
	public void setData_de_Demissao(Date data_de_Demissao) {
		Data_de_Demissao = data_de_Demissao;
	} 
	
}
