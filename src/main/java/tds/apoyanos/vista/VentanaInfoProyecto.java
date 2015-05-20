package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Proyecto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentanaInfoProyecto extends JDialog {
	private JTextField textTitulo;
	private JTextField textFechaFin;
	private JTextField textImporte;
	private JTextField textCategoria;
	private JTextField textRestan;
	private JTextField textVotos;
	private JTable table;
	private JTable table_1;

	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");

	private Proyecto proyecto;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	
	public VentanaInfoProyecto() {
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		
		JLabel lbTitulo = new JLabel("Título del Proyecto:");
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setBounds(90, 28, 130, 20);
		getContentPane().add(lbTitulo);
		
		JLabel lbDescripcion = new JLabel("Descripción:");
		lbDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lbDescripcion.setBounds(90, 80, 130, 20);
		getContentPane().add(lbDescripcion);
		
		textTitulo = new JTextField();
		textTitulo.setEditable(false);
		textTitulo.setForeground(Color.GRAY);
		//textTitulo.setText("La leja de la mesa");
		textTitulo.setText(proyecto.getNombre());
		textTitulo.setBounds(110, 48, 880, 28);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);
		
		JTextArea textrDescripcion = new JTextArea();
		textrDescripcion.setLineWrap(true);
		textrDescripcion.setForeground(Color.GRAY);
		textrDescripcion.setEditable(false);
		textrDescripcion.setWrapStyleWord(true);
		//textrDescripcion.setText("Este proyecto es una película del famoso director de cine Fuman Chú de los chú de toa la vida de Diós. En ella nos cuenta su vida desde su más tierna infancia hasta la maduréz y de cómo lo único que comió caliente de pequeño fue cuando se cayó de boca al brasero con el taca-tá.");
		textrDescripcion.setText(proyecto.getDescripcion());
		textrDescripcion.setRows(10);
		textrDescripcion.setPreferredSize(new Dimension(375, 16));
		textrDescripcion.setMinimumSize(new Dimension(300, 50));
		textrDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textrDescripcion.setBounds(110, 100, 375, 164);
		getContentPane().add(textrDescripcion);
		
		JLabel lbFechaFin = new JLabel("Fecha Finalización:");
		lbFechaFin.setHorizontalAlignment(SwingConstants.LEFT);
		lbFechaFin.setBounds(90, 274, 130, 20);
		getContentPane().add(lbFechaFin);
		
		textFechaFin = new JTextField();
		textFechaFin.setEditable(false);
		textFechaFin.setForeground(Color.GRAY);
		textFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		//textFechaFin.setText("27/04/2015");
		textFechaFin.setText(fechaDia.format(proyecto.getPlazoFinanciacion()));
		textFechaFin.setColumns(10);
		textFechaFin.setBounds(110, 294, 150, 28);
		getContentPane().add(textFechaFin);
		
		JLabel lbImporte = new JLabel("Importe (€):");
		lbImporte.setHorizontalAlignment(SwingConstants.LEFT);
		lbImporte.setBounds(90, 326, 130, 20);
		getContentPane().add(lbImporte);
		
		textImporte = new JTextField();
		textImporte.setEditable(false);
		textImporte.setForeground(Color.GRAY);
		textImporte.setHorizontalAlignment(SwingConstants.CENTER);
		textImporte.setText((String.valueOf(formatoDecimal.format(proyecto.getCantidadMinima()))));
		textImporte.setColumns(10);
		textImporte.setBounds(110, 346, 150, 28);
		getContentPane().add(textImporte);
		
		JLabel lbCategoria = new JLabel("Categoría:");
		lbCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		lbCategoria.setBounds(90, 378, 130, 20);
		getContentPane().add(lbCategoria);
		
		textCategoria = new JTextField();
		textCategoria.setEditable(false);
		textCategoria.setForeground(Color.GRAY);
		textCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		textCategoria.setText(proyecto.getCategoria().getNombre());
		textCategoria.setColumns(10);
		textCategoria.setBounds(110, 398, 150, 28);
		getContentPane().add(textCategoria);
		
		JLabel lbDiasRestan = new JLabel("Restan:");
		lbDiasRestan.setHorizontalAlignment(SwingConstants.LEFT);
		lbDiasRestan.setBounds(315, 274, 130, 20);
		getContentPane().add(lbDiasRestan);
		
		textRestan = new JTextField();
		textRestan.setEditable(false);
		textRestan.setText(String.valueOf(proyecto.getDiasRestantes()));
		textRestan.setHorizontalAlignment(SwingConstants.CENTER);
		textRestan.setForeground(Color.GRAY);
		textRestan.setColumns(10);
		textRestan.setBounds(335, 294, 80, 28);
		getContentPane().add(textRestan);
		
		JLabel lblDas = new JLabel("días.");
		lblDas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDas.setBounds(414, 298, 58, 20);
		getContentPane().add(lblDas);
		
		JLabel lbVotos = new JLabel("Votos Recibidos:");
		lbVotos.setHorizontalAlignment(SwingConstants.LEFT);
		lbVotos.setBounds(315, 326, 130, 20);
		getContentPane().add(lbVotos);
		
		textVotos = new JTextField();
		textVotos.setEditable(false);
		textVotos.setText(String.valueOf(proyecto.getNumvotos()));
		textVotos.setHorizontalAlignment(SwingConstants.CENTER);
		textVotos.setForeground(Color.GRAY);
		textVotos.setColumns(10);
		textVotos.setBounds(335, 346, 80, 28);
		getContentPane().add(textVotos);
		
		
		/*
		 * Si el usuario ya ha votado este proyecto no puede volver a votarlo. 
		 * btnVotarProyecto.setEnabled(false);
		 * */
		JButton btVotar = new JButton("¡¡Votar Proyecto!!");
		btVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btVotar.setEnabled(true);
		if (controlador.getUsuarioHaVotado()){
			btVotar.setEnabled(false);
		}
		
		
		btVotar.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		btVotar.setBounds(546, 360, 444, 66);
		getContentPane().add(btVotar);
		
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
		
		JMenu mnProyectosEnVotacion = new JMenu("Votación");
		mnProyectosEnVotacion.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnProyectosEnVotacion);
		
		JMenuItem mntmTodos = new JMenuItem("Todos");
		mntmTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Ir a la ventana VentanaPrincipalApoyanos(e);
			}
		});
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
		
		JMenu mnProyectosEnFinanciacion = new JMenu("Financiación");
		menuBar.add(mnProyectosEnFinanciacion);
		
		JMenuItem mntmTodosV = new JMenuItem("Todos");
		mnProyectosEnFinanciacion.add(mntmTodosV);
		
		JMenuItem mntmMusicaV = new JMenuItem("Música");
		mnProyectosEnFinanciacion.add(mntmMusicaV);
		
		JMenuItem mntmLibrosV = new JMenuItem("Libros");
		mnProyectosEnFinanciacion.add(mntmLibrosV);
		
		JMenuItem mntmCineV = new JMenuItem("Cine");
		mnProyectosEnFinanciacion.add(mntmCineV);
		
		JMenuItem mntmSocialV = new JMenuItem("Social");
		mnProyectosEnFinanciacion.add(mntmSocialV);
		
		JMenuItem mntmSoftwareV = new JMenuItem("Software");
		mnProyectosEnFinanciacion.add(mntmSoftwareV);
		
		JMenuItem mntmDeportesV = new JMenuItem("Deportes");
		mnProyectosEnFinanciacion.add(mntmDeportesV);
		
		JButton btnCrearProyecto = new JButton("Nuevo Proyecto");
		btnCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearProyecto.setFocusable(false);
		menuBar.add(btnCrearProyecto);
		
		JButton btnApoyos = new JButton("Apoyos");
		btnApoyos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnApoyos.setFocusable(false);
		menuBar.add(btnApoyos);
		
		JButton btnNotificaciones = new JButton("Notificaciones");
		btnNotificaciones.setFocusable(false);
		menuBar.add(btnNotificaciones);
		
		JButton btnPreguntas = new JButton("Preguntas");
		btnPreguntas.setFocusable(false);
		menuBar.add(btnPreguntas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFocusable(false);
		btnSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					//frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		menuBar.add(btnSalir);
		
		
	}
}
