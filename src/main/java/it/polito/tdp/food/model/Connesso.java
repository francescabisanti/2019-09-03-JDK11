package it.polito.tdp.food.model;

public class Connesso {
	private String p;
	private Double peso;
	public Connesso(String p, Double peso) {
		super();
		this.p = p;
		this.peso = peso;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return  p + ", peso=" + peso + "\n";
	}
	
	
	
}
