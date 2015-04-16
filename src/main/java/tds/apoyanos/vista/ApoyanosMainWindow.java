package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class ApoyanosMainWindow extends JFrame {

	private JMenuBar mb;
	private JMenu menuInicio;
	private JMenu menuCrearProyecto;
	private JMenu menuProyectos;
	private JMenu menuProyectosVotacion; 
	private JMenu menuProyectosFinanciacion;
	private JMenu menuAyuda;

	private JPanel panelContenidos;
	
	private JLabel lblInicioApoyanos;
	private JTable table;
	
	public ApoyanosMainWindow() {
		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
		//Panel Inicial
		panelContenidos = new JPanel();
		panelContenidos.setBackground(new Color(255, 255, 255));
		panelContenidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenidos);
		
		//Menus
		mb = new JMenuBar();
		mb.setBorderPainted(false);
		menuInicio = new JMenu("Inicio");
		mb.add(menuInicio); 
		menuCrearProyecto = new JMenu("Crear Proyecto");
		mb.add(menuCrearProyecto);
		menuProyectos = new JMenu("Proyectos");
		mb.add(menuProyectos);
		menuProyectosVotacion = new JMenu("Proyectos Votación");
		mb.add(menuProyectosVotacion); 
		menuProyectosFinanciacion = new JMenu("Proyectos Financiación");
		mb.add(menuProyectosFinanciacion);
		menuAyuda = new JMenu("Ayuda");
		mb.add(menuAyuda); 
		
		//Submenus
		//Pedir al controlador una lista con las categorías
		//Por cada categoría crear un jmenuitem
		JMenuItem jmiTodos = new JMenuItem("Todos");
		JMenuItem jmiMusica = new JMenuItem("Música");
		JMenuItem jmiDeportes = new JMenuItem("Deportes");
		JMenuItem jmiCine = new JMenuItem("Cine");
		JMenuItem jmiSoftware = new JMenuItem("Software");
		JMenuItem jmiSocial = new JMenuItem("Social");
		JMenuItem jmiLibros = new JMenuItem("Libros");
		
		menuProyectos.add(jmiTodos);
		menuProyectos.add(jmiMusica);
		menuProyectos.add(jmiDeportes);
		menuProyectos.add(jmiCine);
		menuProyectos.add(jmiSoftware);
		menuProyectos.add(jmiSocial);
		menuProyectos.add(jmiLibros);
		
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
		panelContenidos.setLayout(new BoxLayout(panelContenidos, BoxLayout.X_AXIS));
		
		lblInicioApoyanos = new JLabel("");
		lblInicioApoyanos.setIcon(new ImageIcon(ApoyanosMainWindow.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		panelContenidos.add(lblInicioApoyanos);

		//Cuando se pulse un botón del menu se lanzará un evento que sustiturira el contentpanel por el panel correspondiente
		//Panel Inicial
		//panelContenidos = new JPanelListado();
		//por ejemplo
		
		
		
		//Crear Proyecto
		JTabbedPane tabbedPane;
		JPanel panelProyecto;
		
		JPanel panelRecompensas;
		JTextField txtDescripcionRecompensa;
		JPanel panelRecompensasIzq;
		JPanel panelRecompensasDer;
		
		JButton btnCrearProyecto = new JButton("Crear Proyecto");
		panelContenidos.add(btnCrearProyecto);
		btnCrearProyecto.setHorizontalAlignment(SwingConstants.LEFT);
		

		//Panel Proyecto
		tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
		
		//Panel Recompensas
		panelRecompensas = new JPanel(); 
		panelRecompensas.setLayout(new BorderLayout()); 
		tabbedPane.addTab("Votados", null, panelRecompensas, null);
		//Paneles Izquierdo y derecho de Recompensas
		panelRecompensasIzq = new JPanel();
		panelRecompensas.add(panelRecompensasIzq);
		panelRecompensasDer = new JPanel();
		panelRecompensas.add(panelRecompensasDer);
		
		table = new JTable(4,4);
		
		panelRecompensasDer.add(table);
		panelProyecto = new JPanel(); 
		panelProyecto.setLayout(new BorderLayout()); 
		tabbedPane.addTab("Financiación", null, panelProyecto, null);
		getContentPane().add(tabbedPane);
		
	}

}
