package model;

public class Usuario {

	private int Registro, Id_funcionario;
	private String Login, Senha, Nome_funcionario;
	
	public Usuario() {}
	
	public Usuario(int Registro, String Login, String Senha) {
		this.Registro = Registro;
		this.Login = Login;
		this.Senha = Senha;
	}
	
	public Usuario(int Registro, String Login, String Senha, String Nome_funcionario, int Id_funcionario) {
		this.Registro = Registro;
		this.Login = Login;
		this.Senha = Senha;
		this.Nome_funcionario = Nome_funcionario;
		this.Id_funcionario = Id_funcionario;
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

	public int getId_funcionario() {
		return Id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		Id_funcionario = id_funcionario;
	}

	public String getNome_funcionario() {
		return Nome_funcionario;
	}

	public void setNome_funcionario(String nome_funcionario) {
		Nome_funcionario = nome_funcionario;
	}
	
}
