package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		//Panel Inicial
		panelContenidos = new JPanel();
		panelContenidos.setBackground(new Color(255, 255, 255));
		panelContenidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenidos);
		
		//Menus
		mb = new JMenuBar();
		mb.setBorderPainted(false);
		menuProyectosVotacion = new JMenu("Proyectos Votación");
		mb.add(menuProyectosVotacion); 
		menuProyectosFinanciacion = new JMenu("Proyectos Financiación");
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
		
		Component horizontalStrut_423 = Box.createHorizontalStrut(23);
		mb.add(horizontalStrut_423);
		
		JButton btnNuevoProyecto = new JButton("Nuevo Proyecto");
		mb.add(btnNuevoProyecto);
		
		JButton btnNewButton = new JButton("Notificaciones");
		mb.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Preguntas");
		mb.add(btnNewButton_1);
		
		JButton button = new JButton("Salir");
		mb.add(button);
		
		Component verticalStrut = Box.createVerticalStrut(74);
		mb.add(verticalStrut);
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
