package model;

import java.util.Date;

public class Matricula {

	private int Registro, Aluno, Secretario, Responsavel, Mensalidade, VencimentoDia;
	private Date Data_de_Matricula;
	private String ano, Nivel_Em_Que__Esta_Sendo_Matriculado,
	serie, turno, Pai_Responsavel;

	public int getRegistro() {
		return Registro;
	}

	public void setRegistro(int registro) {
		Registro = registro;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Date getData_de_Matricula() {
		return Data_de_Matricula;
	}

	public void setData_de_Matricula(Date data_de_Matricula) {
		Data_de_Matricula = data_de_Matricula;
	}

	public String getNivel_Em_Que__Esta_Sendo_Matriculado() {
		return Nivel_Em_Que__Esta_Sendo_Matriculado;
	}

	public void setNivel_Em_Que__Esta_Sendo_Matriculado(String nivel_Em_Que__Esta_Sendo_Matriculado) {
		Nivel_Em_Que__Esta_Sendo_Matriculado = nivel_Em_Que__Esta_Sendo_Matriculado;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getPai_Responsavel() {
		return Pai_Responsavel;
	}

	public void setPai_Responsavel(String pai_Responsavel) {
		Pai_Responsavel = pai_Responsavel;
	}

	public int getAluno() {
		return Aluno;
	}

	public void setAluno(int aluno) {
		Aluno = aluno;
	}

	public int getResponsavel() {
		return Responsavel;
	}

	public void setResponsavel(int responsavel) {
		Responsavel = responsavel;
	}

	public int getMensalidade() {
		return Mensalidade;
	}

	public void setMensalidade(int mensalidade) {
		Mensalidade = mensalidade;
	}

	public void setSecretario(int secretario) {
		Secretario = secretario;
	}

	public int getVencimentoDia() {
		return VencimentoDia;
	}

	public void setVencimentoDia(int vencimentoDia) {
		VencimentoDia = vencimentoDia;
	}

	public int getSecretario() {
		return Secretario;
	} 
}
