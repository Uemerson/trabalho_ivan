package model;

import java.util.Date;

public class Aluno {

	private int Registro;
	
	private Date Data_de_Nascimento;
	private int Numero_da_Casa, idPai, idMae;
	
	private String Nome, RG, Orgao_Emissor, CPF, Sexo,
	Cor, Raca, Logradouro, endereco, bairro, Tel_Residencial, Celular, Email,
	Local_de_Origem_do_Aluno, Rede_Estabelecimento_de_Ordem_do_aluno,
	Situacao_do_Aluno_no_Ano_Anterior, Observacoes_do_Aluno, Cidade, Estado, local_origem_aluno, Nome_Pai, Nome_Mae;

	public Aluno() {};
	
	public Aluno(int Registro, String Nome, String RG, String CPF) {
		this.Registro = Registro;
		this.Nome = Nome;
		this.RG = RG;
		this.CPF = CPF;
	};

	public Aluno(int registro, Date data_de_Nascimento, int numero_da_Casa, int idPai, int idMae, String nome,
			String rG, String orgao_Emissor, String cPF, String sexo, String cor, String raca, String logradouro,
			String endereco, String bairro, String tel_Residencial, String celular, String email,
			String local_de_Origem_do_Aluno, String rede_Estabelecimento_de_Ordem_do_aluno,
			String situacao_do_Aluno_no_Ano_Anterior, String observacoes_do_Aluno, String cidade, String estado,
			String Nome_pai, String Nome_mae) {
		Registro = registro;
		Data_de_Nascimento = data_de_Nascimento;
		Numero_da_Casa = numero_da_Casa;
		this.idPai = idPai;
		this.idMae = idMae;
		Nome = nome;
		RG = rG;
		Orgao_Emissor = orgao_Emissor;
		CPF = cPF;
		Sexo = sexo;
		Cor = cor;
		Raca = raca;
		Logradouro = logradouro;
		this.endereco = endereco;
		this.bairro = bairro;
		Tel_Residencial = tel_Residencial;
		Celular = celular;
		Email = email;
		Local_de_Origem_do_Aluno = local_de_Origem_do_Aluno;
		Rede_Estabelecimento_de_Ordem_do_aluno = rede_Estabelecimento_de_Ordem_do_aluno;
		Situacao_do_Aluno_no_Ano_Anterior = situacao_do_Aluno_no_Ano_Anterior;
		Observacoes_do_Aluno = observacoes_do_Aluno;
		Cidade = cidade;
		Estado = estado;
		this.local_origem_aluno = local_origem_aluno;
		this.Nome_Pai = Nome_pai;
		this.Nome_Mae = Nome_mae;
	}
	
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	public int getIdPai() {
		return idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}

	public int getIdMae() {
		return idMae;
	}

	public void setIdMae(int idMae) {
		this.idMae = idMae;
	}

	public String getLocal_origem_aluno() {
		return local_origem_aluno;
	}

	public void setLocal_origem_aluno(String local_origem_aluno) {
		this.local_origem_aluno = local_origem_aluno;
	}

	public String getNome_Pai() {
		return Nome_Pai;
	}

	public void setNome_Pai(String nome_Pai) {
		Nome_Pai = nome_Pai;
	}

	public String getNome_Mae() {
		return Nome_Mae;
	}

	public void setNome_Mae(String nome_Mae) {
		Nome_Mae = nome_Mae;
	}    

	
}
