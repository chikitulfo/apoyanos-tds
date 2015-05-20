package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaIntroApoyanos extends JDialog {

	private JMenuBar mb;
	private JMenu menuProyectosVotacion; 
	private JMenu menuProyectosFinanciacion;

	private JPanel panelContenidos;
	
	private JLabel lblInicioApoyanos;
	
	public VentanaIntroApoyanos() {
		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setResizable(false);								////////////
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL); 	////////////
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			////////////
		setBounds(100, 100, 1024, 600);
		
		//ver como funciona
		


		//getContentPane().setLayout(new BorderLayout());		////////////
		
		
		
		
		//Panel Inicial
		panelContenidos = new JPanel();
		panelContenidos.setBackground(new Color(255, 255, 255));
		panelContenidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenidos);
		
		
		
		
		//Menus
		mb = new JMenuBar();
		mb.setBorderPainted(false);
		
		Component rigidArea = Box.createRigidArea(new Dimension(300, 74));
		mb.add(rigidArea);
		menuProyectosVotacion = new JMenu("Votación");
		mb.add(menuProyectosVotacion); 
		menuProyectosFinanciacion = new JMenu("Financiación");
		menuProyectosFinanciacion.setRolloverEnabled(false);
		mb.add(menuProyectosFinanciacion);
		
		JMenuItem jmiTodosPV = new JMenuItem("Todos");
		JMenuItem jmiMusicaPV = new JMenuItem("Música");
		JMenuItem jmiDeportesPV = new JMenuItem("Deportes");
		JMenuItem jmiCinePV = new JMenuItem("Cine");
		JMenuItem jmiSoftwarePV = new JMenuItem("Software");
		JMenuItem jmiSocialPV = new JMenuItem("Social");
		JMenuItem jmiLibrosPV = new JMenuItem("Libros");
		
		menuProyectosVotacion.add(jmiTodosPV);
		menuProyectosVotacion.add(jmiMusicaPV);
		menuProyectosVotacion.add(jmiDeportesPV);
		menuProyectosVotacion.add(jmiCinePV);
		menuProyectosVotacion.add(jmiSoftwarePV);
		menuProyectosVotacion.add(jmiSocialPV);
		menuProyectosVotacion.add(jmiLibrosPV);
		
		JMenuItem jmiTodosPF = new JMenuItem("Todos");
		JMenuItem jmiMusicaPF = new JMenuItem("Música");
		JMenuItem jmiDeportesPF = new JMenuItem("Deportes");
		JMenuItem jmiCinePF = new JMenuItem("Cine");
		JMenuItem jmiSoftwarePF = new JMenuItem("Software");
		JMenuItem jmiSocialPF = new JMenuItem("Social");
		JMenuItem jmiLibrosPF = new JMenuItem("Libros");
		
		menuProyectosFinanciacion.add(jmiTodosPF);
		menuProyectosFinanciacion.add(jmiMusicaPF);
		menuProyectosFinanciacion.add(jmiDeportesPF);
		menuProyectosFinanciacion.add(jmiCinePF);
		menuProyectosFinanciacion.add(jmiSoftwarePF);
		menuProyectosFinanciacion.add(jmiSocialPF);
		menuProyectosFinanciacion.add(jmiLibrosPF);
		
		setJMenuBar(mb);
		
		JButton btnCrearProyecto = new JButton("Nuevo Proyecto");
		btnCrearProyecto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				VentanaCrearProyecto ventanaCrearProyecto = new VentanaCrearProyecto();
				//setContentPane(VentanaCrearProyecto);
				ventanaCrearProyecto.setLocationRelativeTo(VentanaIntroApoyanos.this);
				//ventanaCrearProyecto.setVisible(true);
				//frame.dispose();
			}
		});
		
		mb.add(btnCrearProyecto);
		
		JButton tbnApoyos = new JButton("Apoyos");
		tbnApoyos.setFocusable(false);
		mb.add(tbnApoyos);
		
		JButton btnNotificaciones = new JButton("Notificaciones");
		mb.add(btnNotificaciones);
		
		JButton btnPreguntas = new JButton("Preguntas");
		mb.add(btnPreguntas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					//frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
					System.exit(0);  /*no sería necesario en este caso*/
			}
		});
		
		mb.add(btnSalir);
		panelContenidos.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblInicioApoyanos = new JLabel("");
		lblInicioApoyanos.setIcon(new ImageIcon(VentanaIntroApoyanos.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		panelContenidos.add(lblInicioApoyanos);
		JTextField txtDescripcionRecompensa;
		
	}

}
