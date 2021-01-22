package model;

public class Carro {

	private int id;
	private String modelo;
	private String ano;

	public Carro() {
	}
	
	public Carro(int id, String modelo, String ano) {
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Carro [ modelo=" + modelo + ", ano=" + ano + "]";
	}
}
