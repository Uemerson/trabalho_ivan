package model;

public class Usuario {

	private int Registro;
	private String Login, Senha;
	
	public Usuario() {}
	
	public Usuario(int Registro, String Login, String Senha) {
		this.Registro = Registro;
		this.Login = Login;
		this.Senha = Senha;
	}
	
	public int getRegistro() {
		return Registro;
	}
	public void setRegistro(int registro) {
		Registro = registro;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	
	
	
}
