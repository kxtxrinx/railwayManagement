package vista;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;

public class VentanaGestionTrenes extends JFrame{

	private Coordinador coordinador;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCodTren;
	private JLabel lblTitulo;
	private JLabel lblNomTren;
	private JLabel lblTipoTren;
	private JLabel lblLineaTren;
	private JLabel lblCocheraTren;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textTipo;
	private JTextField textLinea;
	private JTextField textCochera;
	private JButton btnBuscar;
	private JButton btnInsertar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCerrar;


	/**
	 * Create the frame.
	 */
	public VentanaGestionTrenes() {
		setBounds(100, 100, 510, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Layout
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		//Label Título
		lblTitulo = new JLabel("Gestión de trenes");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.fill = GridBagConstraints.BOTH;
		gbc_lblTitulo.gridx = 0; //columna
		gbc_lblTitulo.gridy = 0; //fila
		gbc_lblTitulo.gridwidth = 3; //colspan
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(lblTitulo, gbc_lblTitulo);
		
		//Labels campos
		lblCodTren = new JLabel("Código:");
		
		GridBagConstraints gbc_lblCodTren = new GridBagConstraints();
		gbc_lblCodTren.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodTren.gridx = 0;
		gbc_lblCodTren.gridy = 1;
		contentPane.add(lblCodTren, gbc_lblCodTren);
		
		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 1;
		contentPane.add(btnBuscar, gbc_btnBuscar);
		
		
		lblNomTren = new JLabel("Nombre:");
		
		GridBagConstraints gbc_lblNomTren = new GridBagConstraints();
		gbc_lblNomTren.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomTren.gridx = 0;
		gbc_lblNomTren.gridy = 2;
		contentPane.add(lblNomTren, gbc_lblNomTren);
		
		btnInsertar = new JButton("Insertar");
		GridBagConstraints gbc_btnInsertar = new GridBagConstraints();
		gbc_btnInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsertar.gridx = 2;
		gbc_btnInsertar.gridy = 2;
		contentPane.add(btnInsertar, gbc_btnInsertar);
		
		
		lblTipoTren = new JLabel("Tipo:");
		
		GridBagConstraints gbc_lblTipoTren = new GridBagConstraints();
		gbc_lblTipoTren.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoTren.gridx = 0;
		gbc_lblTipoTren.gridy = 3;
		contentPane.add(lblTipoTren, gbc_lblTipoTren);
		
		btnModificar = new JButton("Modificar");
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 5, 5);
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 3;
		contentPane.add(btnModificar, gbc_btnModificar);
		
		
		lblLineaTren = new JLabel("Línea:");
		
		GridBagConstraints gbc_lblLineaTren = new GridBagConstraints();
		gbc_lblLineaTren.insets = new Insets(0, 0, 5, 5);
		gbc_lblLineaTren.gridx = 0;
		gbc_lblLineaTren.gridy = 4;
		contentPane.add(lblLineaTren, gbc_lblLineaTren);
		
		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 4;
		contentPane.add(btnEliminar, gbc_btnEliminar);
		
		
		lblCocheraTren = new JLabel("Cochera:");
		
		GridBagConstraints gbc_lblCocheraTren = new GridBagConstraints();
		gbc_lblCocheraTren.insets = new Insets(0, 0, 0, 5);
		gbc_lblCocheraTren.gridx = 0;
		gbc_lblCocheraTren.gridy = 5;
		contentPane.add(lblCocheraTren, gbc_lblCocheraTren);
		
		//Textfields
		textCodigo = new JTextField();
		GridBagConstraints gbc_textCodigo = new GridBagConstraints();
		gbc_textCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_textCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCodigo.gridx = 1;
		gbc_textCodigo.gridy = 1;
		contentPane.add(textCodigo, gbc_textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 2;
		contentPane.add(textNombre, gbc_textNombre);
		textCodigo.setColumns(10);
		
		textTipo = new JTextField();
		GridBagConstraints gbc_textTipo = new GridBagConstraints();
		gbc_textTipo.insets = new Insets(0, 0, 5, 5);
		gbc_textTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTipo.gridx = 1;
		gbc_textTipo.gridy = 3;
		contentPane.add(textTipo, gbc_textTipo);
		textCodigo.setColumns(10);
		
		textLinea = new JTextField();
		GridBagConstraints gbc_textLinea= new GridBagConstraints();
		gbc_textLinea.insets = new Insets(0, 0, 5, 5);
		gbc_textLinea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLinea.gridx = 1;
		gbc_textLinea.gridy = 4;
		contentPane.add(textLinea, gbc_textLinea);
		textCodigo.setColumns(10);
		
		textCochera = new JTextField();
		GridBagConstraints gbc_textCochera= new GridBagConstraints();
		gbc_textCochera.insets = new Insets(0, 0, 0, 5);
		gbc_textCochera.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCochera.gridx = 1;
		gbc_textCochera.gridy = 5;
		contentPane.add(textCochera, gbc_textCochera);
		textCodigo.setColumns(10);
		
		btnCerrar = new JButton("Cerrar");
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.gridx = 2;
		gbc_btnCerrar.gridy = 5;
		contentPane.add(btnCerrar, gbc_btnCerrar);
		
	}
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}


	public JTextField getTextCodigo() {
		return textCodigo;
	}

	public Coordinador getCoordinador() {
		return coordinador;
	}

	public void setTextCodigo(JTextField textCodigo) {
		this.textCodigo = textCodigo;
	}


	public JTextField getTextNombre() {
		return textNombre;
	}


	public void setTextNombre(JTextField textNombre) {
		this.textNombre = textNombre;
	}


	public JTextField getTextTipo() {
		return textTipo;
	}


	public void setTextTipo(JTextField textTipo) {
		this.textTipo = textTipo;
	}


	public JTextField getTextLinea() {
		return textLinea;
	}


	public void setTextLinea(JTextField textLinea) {
		this.textLinea = textLinea;
	}


	public JTextField getTextCochera() {
		return textCochera;
	}


	public void setTextCochera(JTextField textCochera) {
		this.textCochera = textCochera;
	}


	public JButton getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}


	public JButton getBtnInsertar() {
		return btnInsertar;
	}


	public void setBtnInsertar(JButton btnInsertar) {
		this.btnInsertar = btnInsertar;
	}


	public JButton getBtnModificar() {
		return btnModificar;
	}


	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}


	public JButton getBtnEliminar() {
		return btnEliminar;
	}


	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}


	public JButton getBtnCerrar() {
		return btnCerrar;
	}


	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}



}
