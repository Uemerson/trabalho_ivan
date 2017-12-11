package model;

import java.util.Date;

public class Matricula {

	private int Registro, Aluno, Secretario, Responsavel, Mensalidade, VencimentoDia;
	private Date Data_de_Matricula;
	private String Nivel_Em_Que__Esta_Sendo_Matriculado,
	serie, turno, NomeAluno, NomeResponsavel, NomeSecretario, TituloMensalidade;

	public Matricula() {}
	
	public Matricula(int registro, int aluno, int secretario, int responsavel, int mensalidade, int vencimentoDia,
			Date data_de_Matricula, String nivel_Em_Que__Esta_Sendo_Matriculado, String serie, String turno,
			String nomeAluno, String nomeResponsavel, String nomeSecretario, String TituloMensalidade) {
		Registro = registro;
		Aluno = aluno;
		Secretario = secretario;
		Responsavel = responsavel;
		Mensalidade = mensalidade;
		VencimentoDia = vencimentoDia;
		Data_de_Matricula = data_de_Matricula;
		Nivel_Em_Que__Esta_Sendo_Matriculado = nivel_Em_Que__Esta_Sendo_Matriculado;
		this.serie = serie;
		this.turno = turno;
		NomeAluno = nomeAluno;
		NomeResponsavel = nomeResponsavel;
		NomeSecretario = nomeSecretario;
		this.TituloMensalidade = TituloMensalidade;
	}
	
	public int getRegistro() {
		return Registro;
	}

	public void setRegistro(int registro) {
		Registro = registro;
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

	public String getNomeAluno() {
		return NomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		NomeAluno = nomeAluno;
	}

	public String getNomeResponsavel() {
		return NomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		NomeResponsavel = nomeResponsavel;
	}

	public String getNomeSecretario() {
		return NomeSecretario;
	}

	public void setNomeSecretario(String nomeSecretario) {
		NomeSecretario = nomeSecretario;
	}

	public String getTituloMensalidade() {
		return TituloMensalidade;
	}

	public void setTituloMensalidade(String tituloMatricula) {
		TituloMensalidade = tituloMatricula;
	} 
}
