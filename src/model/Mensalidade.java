package model;

public class Mensalidade {

	private int Registro;
	private double Valor_da_Mensalidade;
	private String Serie, Forma_de_Pagamento;

	public Mensalidade() {}
	
	public Mensalidade(int Registro, double Valor_da_Mensalidade, String Serie, String Forma_de_Pagamento) {
		this.Registro = Registro;
		this.Valor_da_Mensalidade = Valor_da_Mensalidade;
		this.Serie = Serie;
		this.Forma_de_Pagamento = Forma_de_Pagamento;
	}
	
	public int getRegistro() {
		return Registro;
	}

	public void setRegistro(int registro) {
		Registro = registro;
	}

	public String getSerie() {
		return Serie;
	}

	public void setSerie(String serie) {
		Serie = serie;
	}

	public double getValor_da_Mensalidade() {
		return Valor_da_Mensalidade;
	}

	public void setValor_da_Mensalidade(double valor_da_Mensalidade) {
		Valor_da_Mensalidade = valor_da_Mensalidade;
	}

	public String getForma_de_Pagamento() {
		return Forma_de_Pagamento;
	}

	public void setForma_de_Pagamento(String forma_de_Pagamento) {
		Forma_de_Pagamento = forma_de_Pagamento;
	}
	
}
