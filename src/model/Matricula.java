package model;

public class Matricula {

	private int Registro;
	
	private String ano, Data_de_Matricula, Nivel_Em_Que__Esta_Sendo_Matriculado,
	serie, turno, Pai_Responsavel, secretario;

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

	public String getData_de_Matricula() {
		return Data_de_Matricula;
	}

	public void setData_de_Matricula(String data_de_Matricula) {
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

	public String getSecretario() {
		return secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	} 
	
	
	
}
