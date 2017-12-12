package model;

public class AlunoRelatorio {
	private int registro;
	private String nome, cidade, estado, celular;
	
	public AlunoRelatorio(){};
	
	public AlunoRelatorio(int registro, String nome, String cidade, String estado, String celular) {
		this.registro = registro;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.celular = celular;
	};
	
	public int getRegistro() {
		return registro;
	}
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}
