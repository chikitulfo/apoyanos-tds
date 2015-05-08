package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaInfoProyecto extends JDialog {
	private JTextField txtTtuloDelProyecot;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtSoftware;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;


	
	public VentanaInfoProyecto() {
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		
		JLabel lblTtuloDelProyecto = new JLabel("Título del Proyecto:");
		lblTtuloDelProyecto.setHorizontalAlignment(SwingConstants.LEFT);
		lblTtuloDelProyecto.setBounds(90, 28, 130, 20);
		getContentPane().add(lblTtuloDelProyecto);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(90, 80, 130, 20);
		getContentPane().add(lblDescripcion);
		
		txtTtuloDelProyecot = new JTextField();
		txtTtuloDelProyecot.setEditable(false);
		txtTtuloDelProyecot.setForeground(Color.GRAY);
		txtTtuloDelProyecot.setText("La leja de la mesa");
		txtTtuloDelProyecot.setBounds(110, 48, 880, 28);
		getContentPane().add(txtTtuloDelProyecot);
		txtTtuloDelProyecot.setColumns(10);
		
		JTextArea txtrEsteProyectoEs = new JTextArea();
		txtrEsteProyectoEs.setLineWrap(true);
		txtrEsteProyectoEs.setForeground(Color.GRAY);
		txtrEsteProyectoEs.setEditable(false);
		txtrEsteProyectoEs.setWrapStyleWord(true);
		txtrEsteProyectoEs.setText("Este proyecto es una película del famoso director de cine Fuman Chú de los chú de toa la vida de Diós. En ella nos cuenta su vida desde su más tierna infancia hasta la maduréz y de cómo lo único que comió caliente de pequeño fue cuando se cayó de boca al brasero con el taca-tá.");
		txtrEsteProyectoEs.setRows(10);
		txtrEsteProyectoEs.setPreferredSize(new Dimension(375, 16));
		txtrEsteProyectoEs.setMinimumSize(new Dimension(300, 50));
		txtrEsteProyectoEs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrEsteProyectoEs.setBounds(110, 100, 375, 164);
		getContentPane().add(txtrEsteProyectoEs);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalización:");
		lblFechaFinalizacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFinalizacin.setBounds(90, 274, 130, 20);
		getContentPane().add(lblFechaFinalizacin);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(Color.GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("27/04/2015");
		textField.setColumns(10);
		textField.setBounds(110, 294, 150, 28);
		getContentPane().add(textField);
		
		JLabel lblImporte = new JLabel("Importe (€):");
		lblImporte.setHorizontalAlignment(SwingConstants.LEFT);
		lblImporte.setBounds(90, 326, 130, 20);
		getContentPane().add(lblImporte);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setForeground(Color.GRAY);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("6800,00");
		textField_1.setColumns(10);
		textField_1.setBounds(110, 346, 150, 28);
		getContentPane().add(textField_1);
		
		JLabel lblCategora = new JLabel("Categoría:");
		lblCategora.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora.setBounds(90, 378, 130, 20);
		getContentPane().add(lblCategora);
		
		txtSoftware = new JTextField();
		txtSoftware.setEditable(false);
		txtSoftware.setForeground(Color.GRAY);
		txtSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoftware.setText("CINE");
		txtSoftware.setColumns(10);
		txtSoftware.setBounds(110, 398, 150, 28);
		getContentPane().add(txtSoftware);
		
		JLabel lblRestan = new JLabel("Restan:");
		lblRestan.setHorizontalAlignment(SwingConstants.LEFT);
		lblRestan.setBounds(315, 274, 130, 20);
		getContentPane().add(lblRestan);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText("12");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.GRAY);
		textField_2.setColumns(10);
		textField_2.setBounds(335, 294, 80, 28);
		getContentPane().add(textField_2);
		
		JLabel lblDas = new JLabel("días.");
		lblDas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDas.setBounds(414, 298, 58, 20);
		getContentPane().add(lblDas);
		
		JLabel lblVotosRecibidos = new JLabel("Votos Recibidos:");
		lblVotosRecibidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblVotosRecibidos.setBounds(315, 326, 130, 20);
		getContentPane().add(lblVotosRecibidos);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText("12");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.GRAY);
		textField_3.setColumns(10);
		textField_3.setBounds(335, 346, 80, 28);
		getContentPane().add(textField_3);
		
		JButton btnVotarProyecto = new JButton("¡¡Votar Proyecto!!");
		btnVotarProyecto.setEnabled(false);
		/*
		 * Si el usuario ya ha votado este proyecto no puede volver a votarlo. 
		 * btnVotarProyecto.setEnabled(false);
		 * */
		
		
		btnVotarProyecto.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		btnVotarProyecto.setBounds(546, 360, 444, 66);
		getContentPane().add(btnVotarProyecto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(544, 100, 446, 75);
		getContentPane().add(scrollPane);
		
		
		//scrollPane.setColumnHeaderView(table);
///////////////////////////////////////////////		
		
		String[] columnas = new String[]{
	            "Recompensa",
	            "Cantidad"};

        
        //Una única fila
        Object[][] datos = new Object[][]{
                {"Un duplicado oficial en BluRay firmado por el director del bódrio.",
                2000,00}};
		
/////		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado de recompensas del proyecto");
		table.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas

		table_1 = new JTable();
		table_1.setForeground(Color.GRAY);
		table_1.setEnabled(false);
		table_1.setRowSelectionAllowed(false);
		table_1.setSelectionBackground(SystemColor.inactiveCaptionText);
		table_1.setName("Listado de proyectos en votación");
		table_1.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas
        table_1.setModel(new DefaultTableModel(
                datos,
                columnas) {
            
            Class[] tipos = new Class[]{
                    String.class,
                    double.class
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
        
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(250);
		//scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table_1);
/////	
		
/////////////////////////////////////////////		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JPanel panelIcon = new JPanel();
		panelIcon.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelIcon.setBorder(null);
		panelIcon.setBackground(Color.WHITE);
		panelIcon.setToolTipText("\n");
		menuBar.add(panelIcon);
		panelIcon.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVisible(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(VentanaCrearProyecto.class.getResource("/recursos/apoyanos_75aire-50.png")));
		panelIcon.add(lblNewLabel);
		
		JMenu mnProyectosEnVotacion = new JMenu("En Votación");
		mnProyectosEnVotacion.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnProyectosEnVotacion);
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mnProyectosEnVotacion.add(mntmTodos);
		
		JMenuItem mntmMusica = new JMenuItem("Música");
		mnProyectosEnVotacion.add(mntmMusica);
		
		JMenuItem mntmLibros = new JMenuItem("Libros");
		mnProyectosEnVotacion.add(mntmLibros);
		
		JMenuItem mntmCine = new JMenuItem("Cine");
		mnProyectosEnVotacion.add(mntmCine);
		
		JMenuItem mntmSocial = new JMenuItem("Social");
		mnProyectosEnVotacion.add(mntmSocial);
		
		JMenuItem mntmSoftware = new JMenuItem("Software");
		mnProyectosEnVotacion.add(mntmSoftware);
		
		JMenuItem mntmDeportes = new JMenuItem("Deportes");
		mnProyectosEnVotacion.add(mntmDeportes);
		
		JMenu mnProyectosEnFinanciacion = new JMenu("En Financiación");
		menuBar.add(mnProyectosEnFinanciacion);
		
		JMenuItem menuItem = new JMenuItem("Todos");
		mnProyectosEnFinanciacion.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Música");
		mnProyectosEnFinanciacion.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Libros");
		mnProyectosEnFinanciacion.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Cine");
		mnProyectosEnFinanciacion.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Social");
		mnProyectosEnFinanciacion.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Software");
		mnProyectosEnFinanciacion.add(menuItem_5);
		
		JMenuItem menuItem_6 = new JMenuItem("Deportes");
		mnProyectosEnFinanciacion.add(menuItem_6);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(100);
		horizontalStrut_1.setPreferredSize(new Dimension(23, 0));
		horizontalStrut_1.setMinimumSize(new Dimension(23, 0));
		horizontalStrut_1.setMaximumSize(new Dimension(23, 32767));
		menuBar.add(horizontalStrut_1);
		
		JButton btnCrearNuevoProyecto = new JButton("Nuevo Proyecto");
		btnCrearNuevoProyecto.setFocusable(false);
		menuBar.add(btnCrearNuevoProyecto);
		
		JButton button = new JButton("Notificaciones");
		button.setFocusable(false);
		menuBar.add(button);
		
		JButton button_1 = new JButton("Preguntas");
		button_1.setFocusable(false);
		menuBar.add(button_1);
		
		JButton btSalir = new JButton("Salir");
		btSalir.setFocusable(false);
		btSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					//frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		menuBar.add(btSalir);
		
		
	}
}
