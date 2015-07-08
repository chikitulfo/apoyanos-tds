package tds.apoyanos.vista;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.modelo.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

//import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaPrincipalApoyanos extends JFrame {
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	private String fase="Votación";
	private String categoria = "Todos";
	
	private ModeloTabla modeloVistaVotacion;
	private ModeloTabla modeloVistaFinanciacion;
	private JTable tbListadoProyectosVotacion;
	private JTable tbListadoProyectosFinanciacion;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnMasInfo = new JButton("Más Información");

	private LinkedList<Proyecto> listaProyectos;
//	private Proyecto proyecto;
	
	public VentanaPrincipalApoyanos() throws InvalidArgumentException{
		this("Votación", "Todos");
	}
	
	public VentanaPrincipalApoyanos(String faseP, String categoriaP) throws InvalidArgumentException {
		fase=faseP;
		categoria=categoriaP;
		
		
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.window);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		
		if (fase.equals("Votación")){
			if (categoria.equals("Todos")){
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnVotacion();
			} else {
					listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnVotacion(categoria);
			}
			Collections.sort(listaProyectos, comparadorVotacion);
			///////////////////////////////////TABLA
			tbListadoProyectosVotacion = new JTable();
			tbListadoProyectosVotacion.setRowSelectionAllowed(true);
			tbListadoProyectosVotacion.setSelectionBackground(SystemColor.inactiveCaptionText);
			tbListadoProyectosVotacion.setName("Listado de proyectos en Votación.");
			tbListadoProyectosVotacion.setGridColor(Color.LIGHT_GRAY);
			tbListadoProyectosVotacion.setSelectionBackground(SystemColor.inactiveCaptionText);
			tbListadoProyectosVotacion.setSelectionBackground(UIManager.getColor("Button.background"));
			//tbListadoProyectosVotacion.setSe
			scrollPane.setViewportView(tbListadoProyectosVotacion);
			vistaTablaVotacion();
			//probarListado();
		} else {
			if (categoria.equals("Todos")){
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnFinanciacion();
			} else {

					listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnFinanciacion(categoria);
			}
			Collections.sort(listaProyectos, comparadorFinanciacion);
			///////////////////////////////////TABLA
			tbListadoProyectosFinanciacion = new JTable();
			tbListadoProyectosFinanciacion.setRowSelectionAllowed(true);
			tbListadoProyectosFinanciacion.setSelectionBackground(SystemColor.inactiveCaptionText);
			tbListadoProyectosFinanciacion.setSelectionBackground(UIManager.getColor("Button.background"));
			tbListadoProyectosFinanciacion.setName("Listado de proyectos en finaciación.");
			tbListadoProyectosFinanciacion.setGridColor(Color.LIGHT_GRAY);
			tbListadoProyectosFinanciacion.setSelectionBackground(UIManager.getColor("Button.background"));
			scrollPane.setViewportView(tbListadoProyectosFinanciacion);
			vistaTablaFinanciacion();
		}
		/*
		 * según se utilize y según los datos de entrada en la creación el texto será distinto
		 * 
		 * 
		 * */
		if (listaProyectos.isEmpty())
			btnMasInfo.setEnabled(false);
		panel_texto.setMaximumSize(new Dimension(1024, 300));
		panel_texto.setBackground(Color.WHITE);
		
		getContentPane().add(panel_texto);
		panel_texto.setLayout(new BoxLayout(panel_texto, BoxLayout.X_AXIS));
		rigidArea.setSize(new Dimension(100, 50));
		rigidArea.setPreferredSize(new Dimension(100, 50));
		rigidArea.setMaximumSize(new Dimension(100, 50));
		rigidArea.setMinimumSize(new Dimension(100, 50));
		
		panel_texto.add(rigidArea);
		
		JLabel lbTitulo = new JLabel();
		lbTitulo.setHorizontalTextPosition(SwingConstants.LEFT);
		lbTitulo.setBackground(Color.WHITE);
		panel_texto.add(lbTitulo);
		lbTitulo.setText("Listado de proyectos en " + fase + " - " + categoria + ".");
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setMaximumSize(new Dimension(1024, 700));
		
		getContentPane().add(panelTabla);
		panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.X_AXIS));
		panelTabla.add(horizontalStrut_1);
		scrollPane.setMaximumSize(new Dimension(1024, 700));
		panelTabla.add(scrollPane);
		panelTabla.add(horizontalStrut);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		
		getContentPane().add(verticalStrut);
		panelBoton.setBackground(Color.WHITE);
		
		getContentPane().add(panelBoton);
		panelBoton.setLayout(new BoxLayout(panelBoton, BoxLayout.X_AXIS));
		btnMasInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		panelBoton.add(btnMasInfo);
		
		btnMasInfo.setEnabled(true);
		panel.setBackground(Color.WHITE);
		
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		verticalStrut_1.setPreferredSize(new Dimension(0, 40));
		
		panel.add(verticalStrut_1);
		
		
		
		btnMasInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fase.equals("Votación")){

					try {
						DefaultTableModel dtm = (DefaultTableModel) tbListadoProyectosVotacion.getModel();
						if (tbListadoProyectosVotacion.getSelectedRow()==-1){
							//Alerta
							new VentanaMensajes("Debes seleccionar un proyecto");
						} else {
							//TODO VA BIEN
							String nombreProyecto = String.valueOf(dtm.getValueAt(tbListadoProyectosVotacion.getSelectedRow(),0));
							VentanaInfoProyecto ventanaInfo = new VentanaInfoProyecto(nombreProyecto);
		                    ventanaInfo.setVisible(true);
		                    setVisible(false); //you can't see me!
		                    dispose(); //Destroy the JFrame object
						}
					} catch (InvalidArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel dtm = (DefaultTableModel) tbListadoProyectosFinanciacion.getModel(); 
					if (tbListadoProyectosFinanciacion.getSelectedRow()==-1){
						//Alerta
						new VentanaMensajes("Debes seleccionar un proyecto");
					} else {
						//TODO VA BIEN
						String nombreProyecto = String.valueOf(dtm.getValueAt(tbListadoProyectosFinanciacion.getSelectedRow(),0));
						VentanaInfoFinanciacionProyecto ventanaInfo = new VentanaInfoFinanciacionProyecto(nombreProyecto);
	                    ventanaInfo.setVisible(true);
	                    setVisible(false); //you can't see me!
	                    dispose(); //Destroy the JFrame object
					}
				}
			}
		});

						
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setModalityType(ModalityType.APPLICATION_MODAL); 	////////////
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			////////////
		
		
		setBounds(100,100, 1024, 600);
		setLocationRelativeTo(null);
		
		///MENU
		menu_apoyanos = new Menu(this);
		
	}
	
	@SuppressWarnings("unused")
	private void probarListado() {		
		for (Proyecto p : listaProyectos) {
			System.out.print(p.getNombre()+"\n");
			System.out.print(p.getDescripcion()+"\n");
			System.out.print(p.getDiasRestantes()+"\n");
			System.out.print(p.getNumvotos()+"\n");
		}
	}
	
	private void vistaTablaVotacion() {
		modeloVistaVotacion = new ModeloTabla();
		modeloVistaVotacion.addColumn("Proyecto");
		modeloVistaVotacion.addColumn("Descripción"); //mostar sólo 120 caracteres
		modeloVistaVotacion.addColumn("Días Restan");
		modeloVistaVotacion.addColumn("Votos");
		
		for (Proyecto p : listaProyectos) {
			Object[] objProyecto = new Object[4];

			try {
				objProyecto[0] = p.getNombre();
				if (p.getDescripcion().length()>119){
					objProyecto[1] = p.getDescripcion().substring(0, 119) + "...";
				} else {
					objProyecto[1] = p.getDescripcion();
				}
				objProyecto[2] = p.getDiasRestantes();
				objProyecto[3] = p.getNumvotos();
				
			} catch (Exception e) {
			}
			modeloVistaVotacion.addRow(objProyecto);

		}
		tbListadoProyectosVotacion.setModel(modeloVistaVotacion);
		tbListadoProyectosVotacion.getColumnModel().getColumn(0).setResizable(false);
		tbListadoProyectosVotacion.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbListadoProyectosVotacion.getColumnModel().getColumn(1).setResizable(false);
		tbListadoProyectosVotacion.getColumnModel().getColumn(1).setPreferredWidth(220);
		tbListadoProyectosVotacion.getColumnModel().getColumn(2).setResizable(false);
		tbListadoProyectosVotacion.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbListadoProyectosVotacion.getColumnModel().getColumn(3).setResizable(false);
		tbListadoProyectosVotacion.getColumnModel().getColumn(3).setPreferredWidth(30);
	}
	
	private void vistaTablaFinanciacion() {
			modeloVistaFinanciacion = new ModeloTabla();
			modeloVistaFinanciacion.addColumn("Proyecto");
			modeloVistaFinanciacion.addColumn("Descripción"); //mostar sólo 120 caracteres
			modeloVistaFinanciacion.addColumn("Días Restan");
			modeloVistaFinanciacion.addColumn("Recaudado");
			modeloVistaFinanciacion.addColumn("% Recaudado");
			
			for (Proyecto p : listaProyectos) {
				Object[] objProyecto = new Object[5];

				try {
					objProyecto[0] = p.getNombre();
					if (p.getDescripcion().length()>119){
						objProyecto[1] = p.getDescripcion().substring(0, 119) + "...";
					} else {
						objProyecto[1] = p.getDescripcion();
					}
					objProyecto[2] = p.getDiasRestantes(); //PERO PARA PROYECTOS YA EN Financiación
					objProyecto[3] = p.getCantidadRecaudada();
					objProyecto[4] = Math.abs(p.getCantidadRecaudada()*100./p.getCantidadMinima());
					
				} catch (Exception e) {
				}
				modeloVistaFinanciacion.addRow(objProyecto);

			}
			tbListadoProyectosFinanciacion.setModel(modeloVistaFinanciacion);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(0).setResizable(false);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(0).setPreferredWidth(150);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(1).setResizable(false);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(1).setPreferredWidth(220);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(2).setResizable(false);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(2).setPreferredWidth(30);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(3).setResizable(false);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(3).setPreferredWidth(30);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(4).setResizable(false);
			tbListadoProyectosFinanciacion.getColumnModel().getColumn(4).setPreferredWidth(30);
		}

	Comparator<Proyecto> comparadorVotacion = new Comparator<Proyecto>() {
		@Override
		public int compare(Proyecto proyecto, Proyecto t1) {
			return Integer.compare(t1.getNumvotos(),proyecto.getNumvotos());
		}
	};

	Comparator<Proyecto> comparadorFinanciacion = new Comparator<Proyecto>() {
		@Override
		public int compare(Proyecto proyecto, Proyecto t1) {
			return Integer.compare(proyecto.getDiasRestantes(),t1.getDiasRestantes());
		}
	};
	private final JPanel panel_texto = new JPanel();
	private final JPanel panelTabla = new JPanel();
	private final Component horizontalStrut = Box.createHorizontalStrut(100);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(100);
	private final JPanel panelBoton = new JPanel();
	private final Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final JPanel panel = new JPanel();
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
}
