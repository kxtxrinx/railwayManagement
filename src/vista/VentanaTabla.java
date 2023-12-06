package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class VentanaTabla extends JFrame {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
    private String[] nombresColumnas = {"Código tren","Nombre","Tipo","Línea","Cochera"};
    private DefaultTableModel modelo;


	public VentanaTabla() {
		setBounds(100, 100, 510, 633);
        setTitle("Información de línea");
        
        //Datos, nombres de columnas
        modelo = new DefaultTableModel(null, nombresColumnas);
        JTable tabla = new JTable(modelo);
        
        add(new JScrollPane(tabla), BorderLayout.CENTER);
                
	}


	public DefaultTableModel getModelo() {
		return modelo;
	}


	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

}
