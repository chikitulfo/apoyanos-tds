package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class VentanaInfoProyecto extends JDialog {
	private JTextField textProyecto;
	private JTextField textFechaFin;
	private JTextField textImporte;
	private JTextField textCategoria;


	
	public VentanaInfoProyecto() {
		getContentPane().setBackground(SystemColor.window);
		
		JPanel Proyecto = new JPanel();
		Proyecto.setAlignmentX(Component.LEFT_ALIGNMENT);
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
		textProyecto.setForeground(Color.GRAY);
		textProyecto.setEditable(false);
		textProyecto.setSelectedTextColor(Color.BLACK);
		textProyecto.setText("El título del proyecto");
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
		txtDescripcionProyecto.setWrapStyleWord(true);
		txtDescripcionProyecto.setForeground(Color.GRAY);
		txtDescripcionProyecto.setPreferredSize(new Dimension(375, 16));
		txtDescripcionProyecto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtDescripcionProyecto.setRows(10);
		txtDescripcionProyecto.setMinimumSize(new Dimension(300, 50));
		txtDescripcionProyecto.setText("Aquí va toda la descripción del proyecto donde nos dice en qué consiste y todas sus bondades");
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
		textFechaFin.setForeground(Color.GRAY);
		textFechaFin.setText("24/05/2015");
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
		textImporte.setForeground(Color.GRAY);
		textImporte.setHorizontalAlignment(SwingConstants.RIGHT);
		textImporte.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textImporte.setText("1500,00");
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
		
		textCategoria = new JTextField();
		textCategoria.setText("Software");
		textCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		textCategoria.setForeground(Color.GRAY);
		textCategoria.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textCategoria.setColumns(10);
		panel_1.add(textCategoria);
				
		
		
		getContentPane().add(Proyecto, BorderLayout.NORTH);

		
		
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
		
		JMenu mnProyectosEnVotacion = new JMenu("Proyectos Votación");
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
		
		JMenu mnProyectosEnFinanciacion = new JMenu("Proyectos Financiación");
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
		
	}

}
