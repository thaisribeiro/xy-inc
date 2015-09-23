package br.com.xyinc.entities;

public class Address {

	private String Logradouro;
	private String Bairro;
	private String Localidade;
	private String UF;
	private String CEP;

	//getters and setters
	public String getLogradouro() {
		return Logradouro;
	}
	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getLocalidade() {
		return Localidade;
	}
	public void setLocalidade(String localidade) {
		Localidade = localidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}

}
