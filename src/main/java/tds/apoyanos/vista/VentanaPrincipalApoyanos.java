package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Proyecto;

@SuppressWarnings("serial")
public class VentanaPrincipalApoyanos extends JFrame {
	private JTable table;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	private String fase = "VOTACIÓN";
	private String categoria = "TODOS";
	
	List<Proyecto> listaProyectos;
	private Proyecto proyecto;
	

	
	public VentanaPrincipalApoyanos() {
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.window);
		
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		
		String[] columnas = new String[]{
	            "Proyecto",
	            "Descripción",
	            "Días Restan",
	            "Votos",
	            "Votar"};

        
        //Una única fila
        Object[][] datos = new Object[][]{
                {"El proyecto de pepito", "Un proyecto para hacer la casa de pepito", 24, 109, new JButton("Vótame")}};
		
/////		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado de proyectos en votación");
		table.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas
        table.setModel(new DefaultTableModel(
                datos,
                columnas) {
            
            Class[] tipos = new Class[]{
                    String.class,
                    String.class,
                    int.class,
                    int.class,
                    JButton.class // <- noten que aquí se especifica que la última columna es un botón
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel lbTitulo = new JLabel();
		/*
		 * según se utilize y según los datos de entrada en la creación el texto será distinto
		 * 
		 * 
		 * */
		lbTitulo.setText("Listado de proyectos en " + fase + " - " + categoria + ".");
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lbTitulo.setBounds(100, 59, 794, 34);
		getContentPane().add(lbTitulo);
/////		
				
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setModalityType(ModalityType.APPLICATION_MODAL); 	////////////
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			////////////
		
		
		setBounds(100, 100, 1024, 600);
		
		///MENU
		menu_apoyanos = new Menu(this);
		
	}
	
}
