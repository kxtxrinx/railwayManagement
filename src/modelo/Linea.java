package modelo;

import java.util.ArrayList;

public class Linea {
	
	private int cod;
	private String nombre;
	private ArrayList<Tren> trenes;
	
	public Linea(int cod, String nombre, ArrayList<Tren> trenes) {
		super();
		this.cod = cod;
		this.nombre = nombre;
		this.trenes = trenes;
	}
	
	public Linea(int cod) {
		this.cod = cod;
		nombre = null;
		trenes = null;
	}
	
	/*
	private static Linea linea; //!!!!
	
	private Linea() { //!!!
		
	}

	public static Linea getInstance() { //devuelve el objeto singleton linea, importante el static!!
		if(linea==null) {
			linea = new Linea();
		}
		return linea;
	}*/
	
	

	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Código: " + cod + " | Nombre: " + nombre + " | Trenes: " + trenes.toString() + ".";
	}

	public ArrayList<Tren> getTrenes() {
		return trenes;
	}

	public void setTrenes(ArrayList<Tren> trenes) {
		this.trenes = trenes;
	}
	
	
}
