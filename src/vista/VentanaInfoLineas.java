package vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

public class VentanaInfoLineas extends JFrame{

	private Coordinador coordinador;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCodigoLinea;
	private JTextField textNombreLinea;
	//private JComboBox comboTrenes;
	private JLabel lblNumTrenes;
	private JLabel lblNombreLinea;
	private JButton btnBuscar;
	private JButton btnVer;
	private JButton btnCerrar;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaInfoLineas() {
		setBounds(100, 100, 510, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitulo = new JLabel("Información de líneas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		gbc_lblTitulo.gridwidth = 3;
		contentPane.add(lblTitulo, gbc_lblTitulo);
		
		JLabel lblCodigoLinea = new JLabel("Código:");
		GridBagConstraints gbc_lblCodigoLinea = new GridBagConstraints();
		gbc_lblCodigoLinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoLinea.gridx = 0;
		gbc_lblCodigoLinea.gridy = 1;
		contentPane.add(lblCodigoLinea, gbc_lblCodigoLinea);
		
		textCodigoLinea = new JTextField();
		GridBagConstraints gbc_textCodigoLinea = new GridBagConstraints();
		gbc_textCodigoLinea.insets = new Insets(0, 0, 5, 5);
		gbc_textCodigoLinea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodigoLinea.gridx = 1;
		gbc_textCodigoLinea.gridy = 1;
		contentPane.add(textCodigoLinea, gbc_textCodigoLinea);
		textCodigoLinea.setColumns(10);
		
		lblNombreLinea = new JLabel("Nombre de línea:");
		GridBagConstraints gbc_lblNombreLinea = new GridBagConstraints();
		gbc_lblNombreLinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLinea.gridx = 0;
		gbc_lblNombreLinea.gridy = 2;
		contentPane.add(lblNombreLinea, gbc_lblNombreLinea);
		
		textNombreLinea = new JTextField();
		GridBagConstraints gbc_textNombreLinea = new GridBagConstraints();
		gbc_textNombreLinea.insets = new Insets(0, 0, 5, 5);
		gbc_textNombreLinea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombreLinea.gridx = 1;
		gbc_textNombreLinea.gridy = 2;
		contentPane.add(textNombreLinea, gbc_textNombreLinea);
		textNombreLinea.setColumns(10);
		
		JLabel lblTrenesLinea = new JLabel("Número de trenes:");
		GridBagConstraints gbc_lblTrenesLinea = new GridBagConstraints();
		gbc_lblTrenesLinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrenesLinea.gridx = 0;
		gbc_lblTrenesLinea.gridy = 3;
		contentPane.add(lblTrenesLinea, gbc_lblTrenesLinea);
		
		gbc_lblNombreLinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLinea.gridx = 1;
		gbc_lblNombreLinea.gridy = 3;
		
		/*
		comboTrenes = new JComboBox();
		GridBagConstraints gbc_comboTrenes = new GridBagConstraints();
		gbc_comboTrenes.insets = new Insets(0, 0, 5, 5);
		gbc_comboTrenes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTrenes.gridx = 1;
		gbc_comboTrenes.gridy = 3;
		contentPane.add(comboTrenes, gbc_comboTrenes);
		*/
		
		
		lblNumTrenes = new JLabel("");
		GridBagConstraints gbc_lblNumTrenes = new GridBagConstraints();
		gbc_lblNumTrenes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumTrenes.gridx = 1;
		gbc_lblNumTrenes.gridy = 3;
		contentPane.add(lblNumTrenes, gbc_lblNumTrenes);
		
		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 4;
		contentPane.add(btnBuscar, gbc_btnBuscar);
		
		btnVer = new JButton("Ver trenes");
		GridBagConstraints gbc_btnVer = new GridBagConstraints();
		gbc_btnVer.insets = new Insets(0, 0, 0, 5);
		gbc_btnVer.gridx = 1;
		gbc_btnVer.gridy = 4;
		contentPane.add(btnVer, gbc_btnVer);
		
		btnCerrar = new JButton("Cerrar");
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.gridx = 2;
		gbc_btnCerrar.gridy = 4;
		contentPane.add(btnCerrar, gbc_btnCerrar);
		
	}
	

	public void setCoordinador(Coordinador coordinador) { //Para usar el mismo coordinador aún estando en distintas vistas
		this.coordinador = coordinador;
	}
	

	public JTextField getTextCodigoLinea() {
		return textCodigoLinea;
	}



	public void setTextCodigoLinea(JTextField textCodigoLinea) {
		this.textCodigoLinea = textCodigoLinea;
	}



	public JTextField getTextNombreLinea() {
		return textNombreLinea;
	}



	public void setTextNombreLinea(JTextField textNombreLinea) {
		this.textNombreLinea = textNombreLinea;
	}


	public JLabel getLblNombreLinea() {
		return lblNombreLinea;
	}



	public void setLblNombreLinea(JLabel lblNombreLinea) {
		this.lblNombreLinea = lblNombreLinea;
	}



	public JButton getBtnBuscar() {
		return btnBuscar;
	}



	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}



	public JButton getBtnVer() {
		return btnVer;
	}



	public void setBtnVer(JButton btnVer) {
		this.btnVer = btnVer;
	}



	public JButton getBtnCerrar() {
		return btnCerrar;
	}



	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}


	public JLabel getLblNumTrenes() {
		return lblNumTrenes;
	}


	public void setLblNumTrenes(JLabel lblNumTrenes) {
		this.lblNumTrenes = lblNumTrenes;
	}

}
