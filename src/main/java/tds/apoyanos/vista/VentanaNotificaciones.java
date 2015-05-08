package tds.apoyanos.vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaNotificaciones extends JDialog {
	private JTable table;


	
	public VentanaNotificaciones() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		
		String[] columnas = new String[]{
	            "Título",
	            "Creador",
	            "Estado"};
		 //“Proyecto supera no supera la fase de financiación con % financiación de un total de x euros
        
        //Una única fila
        Object[][] datos = new Object[][]{
                {"El proyecto de pepito", "Antonio Fuengirola","El proyecto <<supero>> <<no supera>> la fase de financiación con un % financiado de un total de xx €."}};
		
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
                    String.class
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(350);
		//scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JLabel lblNotificaciones = new JLabel("Notificaciones");
		lblNotificaciones.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotificaciones.setBounds(100, 60, 152, 30);
		getContentPane().add(lblNotificaciones);
/////		
				
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JPanel panelIcon = new JPanel();
		panelIcon.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelIcon.setBorder(null);
		panelIcon.setBackground(Color.WHITE);
		panelIcon.setToolTipText("\n");
		menuBar.add(panelIcon);
		panelIcon.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setVisible(false);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(VentanaCrearProyecto.class.getResource("/recursos/apoyanos_75aire-50.png")));
		panelIcon.add(lblNewLabel);
		
		JMenu mnProyectosEnVotacion = new JMenu("En Votación");
		mnProyectosEnVotacion.setHorizontalAlignment(SwingConstants.LEFT);
		mnProyectosEnVotacion.setBackground(Color.WHITE);
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
		mnProyectosEnFinanciacion.setBackground(Color.WHITE);
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
		menuBar.add(horizontalStrut_1);
		
		JButton btnCrearNuevoProyecto = new JButton("Nuevo Proyecto");
		btnCrearNuevoProyecto.setFocusable(false);
		menuBar.add(btnCrearNuevoProyecto);
		
		JButton button = new JButton("Notificaciones");
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
