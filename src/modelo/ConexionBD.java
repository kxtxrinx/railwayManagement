package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private static ConexionBD instancia = null;
	private Connection conexion = null;
	
	private ConexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/metro", "root", "");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error al conectar con la BD Metro.");
		}
		
	}
	
	public static Connection getConexion() {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia.conexion;
	}
	
	public static void closeConexion() throws SQLException {
		instancia.conexion.close();
		instancia.conexion=null;
		instancia=null;
	}

}
