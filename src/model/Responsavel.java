package model;

import java.sql.Time;
import java.util.Date;

public class Responsavel {

	private int Registro, NumeroCasa, Numero_de_Filhos, Numero_de_Pessoas_que_Residem_na_Casa;
	private Date data_de_Nascimento;
	private Time horario_de_Trabalho;
	private Double Renda;
	private boolean Casa_Propria;
	private String Nome_do_Responsavel, Endereco, Logradouro, Grau_de_Intrucao, profissao, local_de_Trabalho, RG, CPF;

	public Responsavel() {}
	
	public Responsavel(int Registro, String Nome, String CPF, String RG) {
		this.Registro = Registro;
		this.Nome_do_Responsavel = Nome;
		this.CPF = CPF;
		this.RG = RG;
	}
	
	public Responsavel(int registro, String nome_do_Responsavel, String grau_de_Intrucao, String profissao,
			String local_de_Trabalho, Time horario_de_Trabalho, String logradouro, String endereco, int numeroCasa, 
			Date data_de_Nascimento, String rG, String cPF,  Double renda, boolean casa_Propria,
			int numero_de_Filhos, int numero_de_Pessoas_que_Residem_na_Casa) {
		Registro = registro;
		NumeroCasa = numeroCasa;
		Numero_de_Filhos = numero_de_Filhos;
		Numero_de_Pessoas_que_Residem_na_Casa = numero_de_Pessoas_que_Residem_na_Casa;
		this.data_de_Nascimento = data_de_Nascimento;
		this.horario_de_Trabalho = horario_de_Trabalho;
		Renda = renda;
		Casa_Propria = casa_Propria;
		Nome_do_Responsavel = nome_do_Responsavel;
		Endereco = endereco;
		Logradouro = logradouro;
		Grau_de_Intrucao = grau_de_Intrucao;
		this.profissao = profissao;
		this.local_de_Trabalho = local_de_Trabalho;
		RG = rG;
		CPF = cPF;
	}

	public int getRegistro() {
		return Registro;
	}

	public void setRegistro(int registro) {
		Registro = registro;
	}

	public String getNome_do_Responsavel() {
		return Nome_do_Responsavel;
	}

	public void setNome_do_Responsavel(String nome_do_Responsavel) {
		Nome_do_Responsavel = nome_do_Responsavel;
	}

	public String getGrau_de_Intrucao() {
		return Grau_de_Intrucao;
	}

	public void setGrau_de_Intrucao(String grau_de_Intrucao) {
		Grau_de_Intrucao = grau_de_Intrucao;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getLocal_de_Trabalho() {
		return local_de_Trabalho;
	}

	public void setLocal_de_Trabalho(String local_de_Trabalho) {
		this.local_de_Trabalho = local_de_Trabalho;
	}

	public Time getHorario_de_Trabalho() {
		return horario_de_Trabalho;
	}

	public void setHorario_de_Trabalho(Time horario_de_Trabalho) {
		this.horario_de_Trabalho = horario_de_Trabalho;
	}

	public Date getData_de_Nascimento() {
		return data_de_Nascimento;
	}

	public void setData_de_Nascimento(Date data_de_Nascimento) {
		this.data_de_Nascimento = data_de_Nascimento;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public Double getRenda() {
		return Renda;
	}

	public void setRenda(Double renda) {
		Renda = renda;
	}

	public boolean getCasa_Propria() {
		return Casa_Propria;
	}

	public void setCasa_Propria(boolean casa_Propria) {
		Casa_Propria = casa_Propria;
	}

	public int getNumero_de_Filhos() {
		return Numero_de_Filhos;
	}

	public void setNumero_de_Filhos(int numero_de_Filhos) {
		Numero_de_Filhos = numero_de_Filhos;
	}

	public int getNumero_de_Pessoas_que_Residem_na_Casa() {
		return Numero_de_Pessoas_que_Residem_na_Casa;
	}

	public void setNumero_de_Pessoas_que_Residem_na_Casa(int numero_de_Pessoas_que_Residem_na_Casa) {
		Numero_de_Pessoas_que_Residem_na_Casa = numero_de_Pessoas_que_Residem_na_Casa;
	}

	public int getNumeroCasa() {
		return NumeroCasa;
	}

	public void setNumeroCasa(int numeroCasa) {
		NumeroCasa = numeroCasa;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

}
