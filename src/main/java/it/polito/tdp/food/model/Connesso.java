package it.polito.tdp.food.model;

public class Connesso {
	private String tipo;
	private double peso;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Connesso(String tipo, double peso) {
		super();
		this.tipo = tipo;
		this.peso = peso;
	}
	@Override
	public String toString() {
		return tipo + " = " + peso + "\n";
	}
	
	
	
	
}
