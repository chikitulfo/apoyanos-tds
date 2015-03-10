package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApoyanosMainWindow extends JFrame {

	private JPanel contentPane;
	
	public ApoyanosMainWindow() {
		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		
		//Menus
		JMenuBar mb = new JMenuBar();
		mb.setBorderPainted(false);
		JMenu menuInicio = new JMenu("Inicio");
		mb.add(menuInicio); 
		JMenu menuCrearProyecto = new JMenu("Crear Proyecto");
		mb.add(menuCrearProyecto);
		JMenu menuProyectos = new JMenu("Proyectos");
		mb.add(menuProyectos);
		JMenu menuProyectosVotacion = new JMenu("Proyectos Votación");
		mb.add(menuProyectosVotacion); 
		JMenu menuProyectosFinanciacion = new JMenu("Proyectos Financiación");
		mb.add(menuProyectosFinanciacion);
		JMenu menuAyuda = new JMenu("Ayuda");
		mb.add(menuAyuda); 
		
		//Submenus
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
		
		this.setJMenuBar(mb);
		
		//JPanel panelRegistro = new RegistroW(frame);
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblInicioApoyanos = new JLabel("");
		lblInicioApoyanos.setIcon(new ImageIcon(ApoyanosMainWindow.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblInicioApoyanos, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		lblInicioApoyanos.setVisible(true);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); 
		this.getContentPane().add(tabbedPane);
		JPanel panel = new JPanel(); 
		panel.setLayout(new BorderLayout()); 
		tabbedPane.addTab("Tab 1", null, panel, null);
		
	}

}
