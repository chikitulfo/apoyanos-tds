package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaCrearProyecto extends JDialog {
	private JTextField textProyecto;
	private JTextField textFechaFin;
	private JTextField textImporte;
	private JTextField txt_UsuarioCreador;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;


	
	public VentanaCrearProyecto() {
		setResizable(false);
		getContentPane().setBackground(SystemColor.window);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel Proyecto = new JPanel();
		Proyecto.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabbedPane.addTab("Proyecto", null, Proyecto, null);
		Proyecto.setLayout(new BoxLayout(Proyecto, BoxLayout.Y_AXIS));
		
		JPanel paneltitulo = new JPanel();
		Proyecto.add(paneltitulo);
		paneltitulo.setLayout(new BoxLayout(paneltitulo, BoxLayout.Y_AXIS));
		
		JPanel panel_labeltitulo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_labeltitulo.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(300);
		flowLayout.setAlignment(FlowLayout.LEFT);
		paneltitulo.add(panel_labeltitulo);
		
		JLabel lblTtuloDelProyecto = new JLabel("Título del Proyecto:");
		panel_labeltitulo.add(lblTtuloDelProyecto);
		
		JPanel panel_texttitulo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_texttitulo.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(350);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		paneltitulo.add(panel_texttitulo);
		
		textProyecto = new JTextField();
		panel_texttitulo.add(textProyecto);
		textProyecto.setColumns(30);
		
		JPanel panel_descripcion = new JPanel();
		Proyecto.add(panel_descripcion);
		panel_descripcion.setLayout(new BoxLayout(panel_descripcion, BoxLayout.Y_AXIS));
		
		JPanel panel_lbldescripcion = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_lbldescripcion.getLayout();
		flowLayout_2.setHgap(300);
		flowLayout_2.setVgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_descripcion.add(panel_lbldescripcion);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción: ");
		panel_lbldescripcion.add(lblNewLabel_1);
		
		JPanel panel_txtareadescrip = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_txtareadescrip.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(350);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_descripcion.add(panel_txtareadescrip);
		
		JTextArea txtDescripcionProyecto = new JTextArea();
		txtDescripcionProyecto.setPreferredSize(new Dimension(375, 16));
		txtDescripcionProyecto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDescripcionProyecto.setRows(10);
		txtDescripcionProyecto.setMinimumSize(new Dimension(300, 50));
		txtDescripcionProyecto.setWrapStyleWord(true);
		txtDescripcionProyecto.setText("text area");
		panel_txtareadescrip.add(txtDescripcionProyecto);
		
		JPanel panel_FechaFin = new JPanel();
		Proyecto.add(panel_FechaFin);
		panel_FechaFin.setLayout(new BoxLayout(panel_FechaFin, BoxLayout.Y_AXIS));
		
		JPanel panellabelFechafin = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panellabelFechafin.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(300);
		panel_FechaFin.add(panellabelFechafin);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalización:");
		panellabelFechafin.add(lblFechaFinalizacin);
		
		JPanel panel_textFechaFin = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_textFechaFin.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(350);
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_FechaFin.add(panel_textFechaFin);
		
		textFechaFin = new JTextField();
		panel_textFechaFin.add(textFechaFin);
		textFechaFin.setColumns(10);
		
		JPanel panel_Importe = new JPanel();
		Proyecto.add(panel_Importe);
		panel_Importe.setLayout(new BoxLayout(panel_Importe, BoxLayout.Y_AXIS));
		
		JPanel panellabelImporte = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panellabelImporte.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		flowLayout_5.setHgap(300);
		flowLayout_5.setVgap(0);
		panel_Importe.add(panellabelImporte);
		
		JLabel lblImporte = new JLabel("Importe: €");
		panellabelImporte.add(lblImporte);
		
		JPanel panelTxtImporte = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panelTxtImporte.getLayout();
		flowLayout_7.setVgap(0);
		flowLayout_7.setHgap(350);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_Importe.add(panelTxtImporte);
		
		textImporte = new JTextField();
		panelTxtImporte.add(textImporte);
		textImporte.setColumns(10);
		
		JPanel panelCategoria = new JPanel();
		Proyecto.add(panelCategoria);
		panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel.getLayout();
		flowLayout_8.setVgap(0);
		flowLayout_8.setHgap(300);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panelCategoria.add(panel);
		
		JLabel lblCategora = new JLabel("Categoría: ");
		panel.add(lblCategora);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_1.getLayout();
		flowLayout_9.setVgap(0);
		flowLayout_9.setHgap(350);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panelCategoria.add(panel_1);
		
		JComboBox comboBox = new JComboBox();
		//Se puede mejorar con un bucle y una lista que contenga las categorías
		comboBox.addItem("Música");
		comboBox.addItem("Libros");
		comboBox.addItem("Cine");
		comboBox.addItem("Social");
		comboBox.addItem("Software");
		comboBox.addItem("Deportes");
		panel_1.add(comboBox);
		
		JPanel panelUsuario = new JPanel();
		Proyecto.add(panelUsuario);
		panelUsuario.setLayout(new BoxLayout(panelUsuario, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_4.getLayout();
		flowLayout_10.setHgap(300);
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panelUsuario.add(panel_4);
		
		JLabel labelUsuario = new JLabel("Usuario que crea el proyecto: ");
		panel_4.add(labelUsuario);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_5.getLayout();
		flowLayout_11.setHgap(350);
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panelUsuario.add(panel_5);
		
		txt_UsuarioCreador = new JTextField();
		txt_UsuarioCreador.setEditable(false);
		txt_UsuarioCreador.setForeground(Color.GRAY);
		txt_UsuarioCreador.setText("<<Usuario en uso>>");
		txt_UsuarioCreador.setColumns(30);
		panel_5.add(txt_UsuarioCreador);
		
		JPanel Recompensas = new JPanel();
		tabbedPane.addTab("Recompensas", null, Recompensas, null);
		Recompensas.setLayout(new BoxLayout(Recompensas, BoxLayout.X_AXIS));
		
		JPanel panel_Izq = new JPanel();
		Recompensas.add(panel_Izq);
		panel_Izq.setLayout(null);
		
		JLabel lblRecompensa = new JLabel("Recompensa:");
		lblRecompensa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecompensa.setBounds(67, 65, 100, 28);
		panel_Izq.add(lblRecompensa);
		
		textField = new JTextField();
		textField.setBounds(172, 65, 250, 28);
		panel_Izq.add(textField);
		textField.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(67, 95, 100, 28);
		panel_Izq.add(lblCantidad);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(172, 95, 150, 28);
		panel_Izq.add(textField_1);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(67, 124, 100, 28);
		panel_Izq.add(lblDescripcin);
		
		JTextArea txtrDescripcion = new JTextArea();
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setLineWrap(true);
		txtrDescripcion.setCaretColor(new Color(0, 0, 0));
		txtrDescripcion.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtrDescripcion.setColumns(10);
		txtrDescripcion.setBounds(175, 130, 245, 100);
		panel_Izq.add(txtrDescripcion);
		
		JLabel label = new JLabel("€uros.");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(330, 95, 100, 28);
		panel_Izq.add(label);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(100, 270, 117, 29);
		panel_Izq.add(btnAadir);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(297, 270, 117, 29);
		panel_Izq.add(btnLimpiar);
		
		JPanel panel_Der = new JPanel();
		Recompensas.add(panel_Der);
		panel_Der.setLayout(new BoxLayout(panel_Der, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_Der.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 62, 489, 115);
		panel_2.add(scrollPane);
		
		
		String[] columnas = new String[]{
	            "Recompensa",
	            "Cantidad (€)",
	            "Editar",
	            "Eliminar"};

        
        //Una única fila
        Object[][] datos = new Object[][]{
                {"Regalo de un DVD", 1000, new JButton("E"),new JButton("X")}};

		
	/////		
			table = new JTable();
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
	                    int.class,
	                    JButton.class,
	                    JButton.class // <- noten que aquí se especifica que la última columna es un botón
	                };

	            @Override
	            public Class getColumnClass(int columnIndex) {
	                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
	                // observen que estamos retornando la clase que definimos de antemano.
	                return tipos[columnIndex];
	            }
	            
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			table.getColumnModel().getColumn(2).setResizable(false);
			table.getColumnModel().getColumn(3).setResizable(false);
			//scrollPane.setColumnHeaderView(table);
			scrollPane.setViewportView(table);
		
		
		
		
		
		
		JPanel panel_3 = new JPanel();
		panel_Der.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnRegistrar = new JButton("Registrar Proyecto");
		btnRegistrar.setBounds(45, 112, 214, 65);
		btnRegistrar.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		btnRegistrar.setForeground(Color.BLACK);
		panel_3.add(btnRegistrar);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(284, 132, 165, 29);
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton_1.setForeground(Color.BLACK);
		panel_3.add(btnNewButton_1);

		
				
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
		menuBar.add(btnCrearNuevoProyecto);
		
		JButton button = new JButton("Notificaciones");
		menuBar.add(button);
		
		JButton button_1 = new JButton("Preguntas");
		menuBar.add(button_1);
		
		JButton btSalir = new JButton("Salir");
		btSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					//frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		menuBar.add(btSalir);
		
	}
}
