package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.ConexionBD;
import vista.VentanaGestionTrenes;
import vista.VentanaHome;
import vista.VentanaInfoLineas;
import vista.VentanaTabla;

public class Coordinador implements ActionListener{
	Connection conex;
	VentanaHome ventanaHome;	
	VentanaGestionTrenes ventanaGestion;
	VentanaInfoLineas ventanaInfo;
	//TrenDAO trendao;
	VentanaTabla tabla;
	//Statement sentencia;
	
	public Coordinador() {
		conex = ConexionBD.getConexion();

		ventanaHome = new VentanaHome();	
		ventanaGestion = new VentanaGestionTrenes();
		ventanaInfo = new VentanaInfoLineas();
		tabla = new VentanaTabla();
		//trendao = new TrenDAO();
		
		funcionalidadBotonesGestion();
		funcionalidadBotonesInfo();
		//funcionalidadBotonesHome();
	}
	
	/*
	public void funcionalidadBotonesHome() {
		ventanaHome.getBtnCerrar().addActionListener(this);
		ventanaHome.getBtnVistaInfoLineas().addActionListener(this);
		ventanaHome.getBtnVistaGestionarTrenes().addActionListener(this);
	}
	*/
	
	public void funcionalidadBotonesInfo(){
		ventanaInfo.getBtnCerrar().addActionListener(this);
		ventanaInfo.getBtnBuscar().addActionListener(this);
		ventanaInfo.getBtnVer().addActionListener(this);
	}
	
	public void funcionalidadBotonesGestion() {
		ventanaGestion.getBtnBuscar().addActionListener(this);
		ventanaGestion.getBtnCerrar().addActionListener(this);
		ventanaGestion.getBtnEliminar().addActionListener(this);
		ventanaGestion.getBtnInsertar().addActionListener(this);
		ventanaGestion.getBtnModificar().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Gestión de trenes
		if(e.getSource() == ventanaGestion.getBtnBuscar()) {
			boolean valido = false;
			int codTren = 0;
			try {
				codTren = Integer.parseInt(ventanaGestion.getTextCodigo().getText());
				valido = true;
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Asegúrate de introducir un número como código de tren.", "Error de formato", JOptionPane.ERROR_MESSAGE);
			}
			if(valido) {
				try {
					if(buscarTren(codTren)==false) {
						JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "No se han encontrado trenes.", "No results found", JOptionPane.ERROR_MESSAGE);	
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error al conectar con la BD.", "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		}else if(e.getSource() == ventanaGestion.getBtnInsertar()) {
			try {
				insertarTren();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error en la conexión con la BD.", "Error", JOptionPane.ERROR_MESSAGE);

			}
		}else if(e.getSource() == ventanaGestion.getBtnModificar()) {
			try {
				modificarTren();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error en la conexión con la BD.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == ventanaGestion.getBtnEliminar()) {
			eliminarTren();
		}else if(e.getSource() == ventanaGestion.getBtnCerrar()) {
			cerrarVentana(ventanaGestion);
		}
		//Información de líneas
		else if(e.getSource() == ventanaInfo.getBtnBuscar()) {
			try {
				mostrarInfoLineas();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error en la consulta a la BD.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == ventanaInfo.getBtnVer()) {
			verTrenes();
		}else if(e.getSource() == ventanaInfo.getBtnCerrar()) {
			cerrarVentana(ventanaInfo);
		}
		//Home
		/*
		else if(e.getSource() == ventanaHome.getBtnVistaGestionarTrenes()) {
			mostrarVistaGestionarTrenes();
			System.out.println("me cago en dios");
		}
		else if(e.getSource() == ventanaHome.getBtnVistaInfoLineas()) {
			mostrarVistaInformacionTrenes();
		}
		else if(e.getSource() == ventanaHome.getBtnCerrar()) {
			cerrarVentana(ventanaHome);
		}*/
		
		
	}
	
	public String numTrenesEnLinea(int codLinea) throws SQLException {
		String sql = "SELECT COUNT(*) FROM t_trenes WHERE cod_linea = " + codLinea;
		Statement sentencia = conex.createStatement();
		ResultSet res = sentencia.executeQuery(sql);
		res.next();
		return res.getString(1);
	}
	
	public boolean comprobarExistenciaLinea(int codLinea) throws SQLException {
		String sql = "SELECT * FROM t_lineas WHERE cod_linea = " + codLinea;
		Statement sentencia = conex.createStatement();
		ResultSet resul = sentencia.executeQuery(sql);
		boolean existe = false;
		
		if(resul.next()) {
			existe = true;
		}
		sentencia.close();
		return existe;
	}
	
	public void mostrarInfoLineas() throws SQLException {
		try {
			int codLinea = Integer.parseInt(ventanaInfo.getTextCodigoLinea().getText());
			String sql = "SELECT * FROM t_lineas WHERE cod_linea = " + codLinea;
			Statement sentencia = conex.createStatement();
			ResultSet resul = sentencia.executeQuery(sql);
			boolean existe = false;
			
			if(resul.next()) {
				existe = true;
			}
			
			if(!existe) {
				JOptionPane.showMessageDialog(ventanaInfo.getContentPane(), "Esta línea no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
				limpiarLinea();
			}else {
				ventanaInfo.getTextNombreLinea().setText(resul.getString(2));
				ventanaInfo.getLblNumTrenes().setText(numTrenesEnLinea(codLinea));
			}
			sentencia.close();
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaInfo.getContentPane(), "Asegúrate de introducir un número como código de línea.", "Error de formato", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	public void consultarTabla(int codLinea) throws SQLException {
		tabla.getModelo().setRowCount(0);
		String sql = "SELECT * FROM t_trenes WHERE cod_linea = " + codLinea;
		Statement sentencia = conex.createStatement();
		ResultSet resul = sentencia.executeQuery(sql);
		boolean existe = false;
		
		while(resul.next()) {
            Object[] infoTrenes = {
                    resul.getString(1), resul.getString(2), resul.getString(3), resul.getString(4), resul.getString(5)
            };
            tabla.getModelo().addRow(infoTrenes);
            existe = true;
		}
		
		if(!existe) {
			JOptionPane.showMessageDialog(ventanaInfo.getContentPane(), "Esta línea no existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			limpiarLinea();
		}else {
			tabla.setVisible(true);
		}
		sentencia.close();
	}
	
	public void limpiarLinea() {
		ventanaInfo.getTextNombreLinea().setText("");
		ventanaInfo.getTextCodigoLinea().setText("");
		ventanaInfo.getLblNumTrenes().setText("");
	}
	
	public void verTrenes() {
		try {
			int codLinea = Integer.parseInt(ventanaInfo.getTextCodigoLinea().getText());
			try {
				consultarTabla(codLinea);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventanaInfo.getContentPane(), "Error en la conexión con la BD.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaInfo.getContentPane(), "El código de línea debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mostrarVistaGestionarTrenes() {
		ventanaGestion.setVisible(true);
	}
	
	public void setVentanaHome(VentanaHome ventana) {
		ventanaHome = ventana;
	}
	
	public void mostrarVistaInformacionTrenes() {
		ventanaInfo.setVisible(true);
	}
	
	public void cerrarVentana(JFrame ventana) {
		ventana.setVisible(false);
		ventana.dispose();
	}

	public boolean buscarTren(int codTren) throws SQLException {
		String sql = "SELECT * FROM t_trenes WHERE cod_tren = " + codTren;
		boolean resultadosEncontrados = false;
		Statement sentencia = conex.createStatement();
		ResultSet resul = sentencia.executeQuery(sql);
		
		if(resul.next()) {
			String nombreTren = resul.getString(2);
			ventanaGestion.getTextNombre().setText(nombreTren);
			
			String tipoTren = resul.getString(3);
			ventanaGestion.getTextTipo().setText(tipoTren);
			
			String lineaTren = resul.getString(4);
			ventanaGestion.getTextLinea().setText(lineaTren);
			
			String cocheraTren = resul.getString(5);
			ventanaGestion.getTextCochera().setText(cocheraTren);
			
			resultadosEncontrados = true;
		}
		sentencia.close();
		
		return resultadosEncontrados;
	}
	
	public boolean comprobarFKCodLinea(int codLinea) throws SQLException {
		boolean existeClave = false;
		String sql = "SELECT cod_linea FROM t_lineas WHERE cod_linea = " + codLinea;
		Statement sentencia = conex.createStatement();
		ResultSet resul = sentencia.executeQuery(sql);
		
		if(resul.next()) {
			existeClave = true;
		}
		sentencia.close();
		
		return existeClave;
	}
	
	public void insertarTren() throws SQLException {
		String codigoTren = ventanaGestion.getTextCodigo().getText();
		String nombreTren = ventanaGestion.getTextNombre().getText();
		String tipoTren = ventanaGestion.getTextTipo().getText();
		String codigoLinea = ventanaGestion.getTextLinea().getText();
		String codigoCochera = ventanaGestion.getTextCochera().getText();
		
		if(codigoTren.isBlank() || nombreTren.isBlank() || tipoTren.isBlank() || codigoLinea.isBlank() || codigoCochera.isBlank()) {
			JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Debes rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
		}else{
			
			boolean puedeInsertar = false;
			
			try {
				if(buscarTren(Integer.parseInt(codigoTren)) == false) {
					puedeInsertar = true;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Asegúrate de introducir un número como código de tren.", "Error de formato", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error en la conexión con la BD.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(!codigoTren.isEmpty()) {
				if(puedeInsertar) {
					String sql = "INSERT INTO t_trenes VALUES (?, ?, ?, ?, ?)";
					PreparedStatement sentencia = conex.prepareStatement(sql);
					sentencia.setInt(1, Integer.parseInt(codigoTren));
					sentencia.setString(2, nombreTren);
					sentencia.setString(3, tipoTren);
					try {
						sentencia.setInt(4, Integer.parseInt(codigoLinea));
						sentencia.setInt(5, Integer.parseInt(codigoCochera));
						if(comprobarFKCodLinea(Integer.parseInt(codigoLinea))) {
							if(sentencia.executeUpdate() == 1) {
								JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Tren insertado correctamente..", "Registro insertado", JOptionPane.INFORMATION_MESSAGE);
								limpiarGestion();
							}else {
								JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error desconocido de inserción, no se ha registrado el tren en la base de datos.", "Error.", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "El código de línea debe existir en la base de datos.", "Error.", JOptionPane.ERROR_MESSAGE);
						}

					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Asegúrate de introducir un código de línea y cochera válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
					}
					sentencia.close();

				}else{
					JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Un tren con este código ya existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
	}

	
	public void limpiarGestion() {
		ventanaGestion.getTextCodigo().setText("");
		ventanaGestion.getTextNombre().setText("");
		ventanaGestion.getTextTipo().setText("");
		ventanaGestion.getTextLinea().setText("");
		ventanaGestion.getTextCochera().setText("");
	}
	
	public void eliminarTren() {
		try {
			int codTren = Integer.parseInt(ventanaGestion.getTextCodigo().getText());
			if(buscarTren(codTren)) {
				String sql = "DELETE FROM t_trenes WHERE cod_tren = " + codTren;
				Statement sentencia = conex.createStatement();
				if(sentencia.executeUpdate(sql) == 1) {
					JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Tren eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					limpiarGestion();
					sentencia.close();
				}else {
					JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error desconocido; no se ha podido eliminar el tren.", "Error", JOptionPane.ERROR_MESSAGE);
					sentencia.close();
				}
			}else {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Este tren no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			

		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Asegúrate de introducir un código de tren válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error en la consulta con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void modificarTren() throws SQLException {
		String nombreTren = ventanaGestion.getTextNombre().getText();
		String tipoTren = ventanaGestion.getTextTipo().getText();		
		try {
			int codTren = Integer.parseInt(ventanaGestion.getTextCodigo().getText());
			int codLinea = Integer.parseInt(ventanaGestion.getTextLinea().getText());
			int codCochera = Integer.parseInt(ventanaGestion.getTextCochera().getText());
			if(ventanaGestion.getTextCodigo().getText().isBlank() || ventanaGestion.getTextLinea().getText().isBlank() || ventanaGestion.getTextCochera().getText().isBlank() || nombreTren.isBlank() || tipoTren.isBlank() ) {
				JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Debes rellenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(!buscarTren(codTren)) {
					JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Este tren no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					if(comprobarFKCodLinea(codLinea)) {
						String sql = "UPDATE t_trenes SET nombre = ?, tipo = ?, cod_linea = ?, cod_cochera = ? WHERE cod_tren = ?";
						PreparedStatement sentencia = conex.prepareStatement(sql);
						sentencia.setString(1, nombreTren);
						sentencia.setString(2, tipoTren);
						sentencia.setInt(3, codLinea);
						sentencia.setInt(4, codCochera);
						sentencia.setInt(5, codTren);
						
						if(sentencia.executeUpdate() == 1) {
							JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Modificación realizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							limpiarGestion();
							sentencia.close();
						}else {
							JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Error inesperado al modificar el tren.", "Error", JOptionPane.ERROR_MESSAGE);
							sentencia.close();
						}
					}else {
						JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "El código de línea debe existir en la base de datos.", "Error.", JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaGestion.getContentPane(), "Asegúrate de introducir un número como código de tren, línea y cochera.", "Error de formato", JOptionPane.ERROR_MESSAGE);
		}
	}

}
