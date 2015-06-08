package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
//import javax.swing.table.DefaultTableModel;


import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Proyecto;

@SuppressWarnings("serial")
public class VentanaPrincipalApoyanos extends JFrame {
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	private String fase="VOTACIÓN";
	private String categoria = "TODOS";
	
	private ModeloTabla modeloVistaVotacion;
	private ModeloTabla modeloVistaFinanciacion;
	private JTable tbListadoProyectosVotacion;
	private JTable tbListadoProyectosFinanciacion;

	private LinkedList<Proyecto> listaProyectos;
//	private Proyecto proyecto;
	
	public VentanaPrincipalApoyanos(){
		this("VOTACIÓN", "TODOS");
	}
	
	public VentanaPrincipalApoyanos(String faseP, String categoriaP) {
		fase=faseP;
		categoria=categoriaP;
		
		
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.window);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		
		if (fase.equals("VOTACIÓN")){
			if (categoria.equals("Todos")){
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnVotacion();
			} else {
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnVotacion(categoria);
			}
			///////////////////////////////////TABLA
			tbListadoProyectosVotacion = new JTable();
			tbListadoProyectosVotacion.setRowSelectionAllowed(true);
			tbListadoProyectosVotacion.setSelectionBackground(SystemColor.inactiveCaptionText);
			tbListadoProyectosVotacion.setName("Listado de proyectos en votación.");
			tbListadoProyectosVotacion.setGridColor(Color.LIGHT_GRAY);
			scrollPane.setViewportView(tbListadoProyectosVotacion);
			vistaTablaVotacion();
			//probarListado();
		} else {
			if (categoria.equals("Todos")){
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnFinanciacion();
			} else {
				listaProyectos = (LinkedList<Proyecto>) controlador.getProyectosEnFinanciacion(categoria);
			}
			///////////////////////////////////TABLA
			tbListadoProyectosFinanciacion = new JTable();
			tbListadoProyectosFinanciacion.setRowSelectionAllowed(true);
			tbListadoProyectosFinanciacion.setSelectionBackground(SystemColor.inactiveCaptionText);
			tbListadoProyectosFinanciacion.setName("Listado de proyectos en finaciación.");
			tbListadoProyectosFinanciacion.setGridColor(Color.LIGHT_GRAY);
			scrollPane.setViewportView(tbListadoProyectosFinanciacion);
			vistaTablaFinanciacion();
		}
		
		
		JLabel lbTitulo = new JLabel();
		/*
		 * según se utilize y según los datos de entrada en la creación el texto será distinto
		 * 
		 * 
		 * */
		lbTitulo.setText("Listado de proyectos en " + fase + " - " + categoria + ".");
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lbTitulo.setBounds(100, 59, 794, 34);
		getContentPane().add(lbTitulo);
		
		JButton btnMasInfo = new JButton("Más Información");
		btnMasInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fase.equals("VOTACIÓN")){
					DefaultTableModel dtm = (DefaultTableModel) tbListadoProyectosVotacion.getModel(); 
					String nombreProyecto = String.valueOf(dtm.getValueAt(tbListadoProyectosVotacion.getSelectedRow(),0));
                    VentanaInfoProyecto ventanaInfo = new VentanaInfoProyecto(nombreProyecto);
                    ventanaInfo.setVisible(true);
                    setVisible(false); //you can't see me!
                    dispose(); //Destroy the JFrame object
				} else {
					DefaultTableModel dtm = (DefaultTableModel) tbListadoProyectosFinanciacion.getModel(); 
					String nombreProyecto = String.valueOf(dtm.getValueAt(tbListadoProyectosFinanciacion.getSelectedRow(),0));
                    VentanaInfoFinanciacionProyecto ventanaInfo = new VentanaInfoFinanciacionProyecto(nombreProyecto);
                    ventanaInfo.setVisible(true);
                    setVisible(false); //you can't see me!
                    dispose(); //Destroy the JFrame object
				}
			}
		});
		btnMasInfo.setBounds(398, 394, 200, 40);
		getContentPane().add(btnMasInfo);

						
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
				objProyecto[1] = p.getDescripcion();
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
					objProyecto[1] = p.getDescripcion();
					objProyecto[2] = p.getDiasRestantes(); //PERO PARA PROYECTOS YA EN FINANCIACIÓN
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
}
