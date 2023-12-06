package controlador;

import vista.VentanaGestionTrenes;
import vista.VentanaHome;
import vista.VentanaInfoLineas;
import vista.VentanaTabla;

public class Principal {
	
	public static void main(String[]args) {

		VentanaHome home = new VentanaHome();	
		VentanaGestionTrenes ventanaGestion = new VentanaGestionTrenes();
		VentanaTabla tabla = new VentanaTabla();
		VentanaInfoLineas ventanaInfo = new VentanaInfoLineas();

		Coordinador coordinador = new Coordinador(); //Usamos el mismo con todas las ventanas
		
		home.setVisible(true);
		
		ventanaGestion.setCoordinador(coordinador);
		ventanaInfo.setCoordinador(coordinador);
		home.setCoordinador(coordinador);
	
				
	}

	
}
