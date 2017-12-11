package model;

public class Cargo {

	private int Registro;
	private String Nome, Descricao;

	public Cargo() {}
	
	public Cargo(int Registro, String Nome, String Descricao) {
		this.Registro = Registro;
		this.Nome = Nome;
		this.Descricao = Descricao;
	}
	
	public Cargo(String Nome) {
		this.Nome = Nome;
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

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	
}
