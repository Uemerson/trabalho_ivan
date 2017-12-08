package model;

import java.util.Date;

public class Aluno {
	
	private int Registro;
	
	private Date Data_de_Nascimento;
	private int Numero_da_Casa;
	
	private String Nome, RG, Orgao_Emissor, CPF, Sexo,
	Cor, Raca, Logradouro, endereco, Tel_Residencial, Tel_Comercial, Celular, Email,
	Local_de_Origem_do_Aluno, Rede_Estabelecimento_de_Ordem_do_aluno,
	Situacao_do_Aluno_no_Ano_Anterior, Observacoes_do_Aluno;

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero_da_Casa() {
		return Numero_da_Casa;
	}

	public void setNumero_da_Casa(int numero_da_Casa) {
		Numero_da_Casa = numero_da_Casa;
	}

	public String getSituacao_do_Aluno_no_Ano_Anterior() {
		return Situacao_do_Aluno_no_Ano_Anterior;
	}

	public void setSituacao_do_Aluno_no_Ano_Anterior(String situacao_do_Aluno_no_Ano_Anterior) {
		Situacao_do_Aluno_no_Ano_Anterior = situacao_do_Aluno_no_Ano_Anterior;
	}

	public int getRegistro() {
		return Registro;
	}

	public void setRegistro(int registro) {
		Registro = registro;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getOrgao_Emissor() {
		return Orgao_Emissor;
	}

	public void setOrgao_Emissor(String orgao_Emissor) {
		Orgao_Emissor = orgao_Emissor;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Date getData_de_Nascimento() {
		return Data_de_Nascimento;
	}

	public void setData_de_Nascimento(Date data_de_Nascimento) {
		Data_de_Nascimento = data_de_Nascimento;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public String getRaca() {
		return Raca;
	}

	public void setRaca(String raca) {
		Raca = raca;
	}

	public String getEnderco() {
		return endereco;
	}

	public void setEnderco(String enderco) {
		this.endereco = enderco;
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

	public String getLocal_de_Origem_do_Aluno() {
		return Local_de_Origem_do_Aluno;
	}

	public void setLocal_de_Origem_do_Aluno(String local_de_Origem_do_Aluno) {
		Local_de_Origem_do_Aluno = local_de_Origem_do_Aluno;
	}

	public String getRede_Estabelecimento_de_Ordem_do_aluno() {
		return Rede_Estabelecimento_de_Ordem_do_aluno;
	}

	public void setRede_Estabelecimento_de_Ordem_do_aluno(String rede_Estabelecimento_de_Ordem_do_aluno) {
		Rede_Estabelecimento_de_Ordem_do_aluno = rede_Estabelecimento_de_Ordem_do_aluno;
	}

	public String getSituacao_doAluno_no_Ano_Anterior() {
		return Situacao_do_Aluno_no_Ano_Anterior;
	}

	public void setSituacao_doAluno_no_Ano_Anterior(String situacao_doAluno_no_Ano_Anterior) {
		Situacao_do_Aluno_no_Ano_Anterior = situacao_doAluno_no_Ano_Anterior;
	}

	public String getObservacoes_do_Aluno() {
		return Observacoes_do_Aluno;
	}

	public void setObservacoes_do_Aluno(String observacoes_do_Aluno) {
		Observacoes_do_Aluno = observacoes_do_Aluno;
	}    
	
	
	
}
