package modelo;

import modelo.Linea;
import modelo.Tren;

public class Tren {
	
	private int codTren;
	private String nombre;
	private String tipo;
	private Linea linea;
	private int codCochera;
	
	public Tren(int codTren, String nombre, String tipo, Linea linea, int codCochera) {
		super();
		this.codTren = codTren;
		this.nombre = nombre;
		this.tipo = tipo;
		this.linea = linea;
		this.codCochera = codCochera;
	}

	/*
	private static Tren tren; //para el getInstance()
	
	private Tren() { //¡¡importante el private para que sea singleton!!

	}
	
	public Tren getInstance() {
		if(tren==null) {
			tren = new Tren();
		}
		return tren;
	}
	 */
	
	public int getCodTren() {
		return codTren;
	}

	public void setCodTren(int codTren) {
		this.codTren = codTren;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public int getCodCochera() {
		return codCochera;
	}

	public void setCodCochera(int codCochera) {
		this.codCochera = codCochera;
	}
	
	@Override
	public String toString() {
		return "ID: " + codTren + " | Nombre: " + nombre + " | Tipo: " + tipo + " | Línea: " + linea + " | Cochera: " + codCochera;
	}
	
	


}
