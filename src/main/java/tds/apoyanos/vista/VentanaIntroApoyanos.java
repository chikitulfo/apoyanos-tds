package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaIntroApoyanos extends JFrame {

	private JPanel panelContenidos;
	private JLabel lblInicioApoyanos;
	
	public VentanaIntroApoyanos() {
		getContentPane().setBackground(Color.WHITE);
		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setResizable(false);								////////////
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setModalityType(ModalityType.APPLICATION_MODAL); 	////////////
		//setDefaultCloseOperation(HIDE_ON_CLOSE);			////////////
		setBounds(100, 100, 1024, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//ver como funciona
		


		//getContentPane().setLayout(new BorderLayout());		////////////
		getContentPane().setLayout(new BorderLayout());
		
		
		
		//Panel Inicial
		panelContenidos = new JPanel();
		panelContenidos.setBackground(new Color(255, 255, 255));
		panelContenidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(panelContenidos);
		
		getContentPane().add(panelContenidos);
		
		
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
		
		JButton btnCrearNuevoProyecto = new JButton("Nuevo Proyecto");
		btnCrearNuevoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearProyecto ventanaCrearProyecto = new VentanaCrearProyecto();
				ventanaCrearProyecto.setVisible(true);
				setVisible(false); //you can't see me!
				//dispose(); //Destroy the JFrame object
			}
		});
		menuBar.add(btnCrearNuevoProyecto);
		
		JButton btnApoyos = new JButton("Apoyos");
		btnApoyos.setFocusable(false);
		menuBar.add(btnApoyos);
		
		JButton btnNotificaciones = new JButton("Notificaciones");
		menuBar.add(btnNotificaciones);
		
		JButton btnPreguntas = new JButton("Preguntas");
		menuBar.add(btnPreguntas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					//frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		menuBar.add(btnSalir);
		
		
		panelContenidos.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblInicioApoyanos = new JLabel("");
		lblInicioApoyanos.setIcon(new ImageIcon(VentanaIntroApoyanos.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		panelContenidos.add(lblInicioApoyanos);
		//JTextField txtDescripcionRecompensa;
		
	}

}
