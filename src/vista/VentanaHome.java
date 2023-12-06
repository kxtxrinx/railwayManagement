package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaHome extends JFrame implements ActionListener{

	private Coordinador coordinador;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVistaGestionarTrenes;
	private JButton btnVistaInfoLineas;
	private JButton btnCerrar;

	public VentanaHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new GridLayout(3,1, 10, 10));
		
		btnVistaGestionarTrenes = new JButton("Gestionar trenes");
		contentPane.add(btnVistaGestionarTrenes);
		
		btnVistaInfoLineas = new JButton("Información líneas");
		contentPane.add(btnVistaInfoLineas);
		
		btnCerrar = new JButton("Cerrar");
		contentPane.add(btnCerrar);	
		
		btnCerrar.addActionListener(this);
		btnVistaGestionarTrenes.addActionListener(this);
		btnVistaInfoLineas.addActionListener(this);
		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	public JButton getBtnVistaGestionarTrenes() {
		return btnVistaGestionarTrenes;
	}

	public void setBtnVistaGestionarTrenes(JButton btnVistaGestionarTrenes) {
		this.btnVistaGestionarTrenes = btnVistaGestionarTrenes;
	}

	public JButton getBtnVistaInfoLineas() {
		return btnVistaInfoLineas;
	}

	public void setBtnVistaInfoLineas(JButton btnVistaInfoLineas) {
		this.btnVistaInfoLineas = btnVistaInfoLineas;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnVistaGestionarTrenes) {
			coordinador.mostrarVistaGestionarTrenes();
			
		}else if(e.getSource() == this.btnVistaInfoLineas) {
			coordinador.mostrarVistaInformacionTrenes();
			
		}else if(e.getSource() == this.btnCerrar) {
			coordinador.cerrarVentana(this);
		}
			
		
	}
	

}
