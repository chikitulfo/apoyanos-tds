package tds.apoyanos.vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.*;

import java.awt.*;
import java.util.Collection;

import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Notificacion;

@SuppressWarnings("serial")
public class VentanaNotificaciones extends JFrame {
	private JTable table;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;

	private ModeloTabla modeloVistaNotificacion;
	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private Collection<Notificacion> listaNotificacion =  controlador.getUsuario().getNotificaciones();
	
	public VentanaNotificaciones() {
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado de notificaciones.");
		table.setGridColor(Color.LIGHT_GRAY);
		//Muestra la vista de la tabla
		vistaTablaNotificaciones();
		
		scrollPane.setViewportView(table);	

		
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
		setLocationRelativeTo(null);
		
		///MENU
		menu_apoyanos = new Menu(this);
		
	}
	
	private void vistaTablaNotificaciones() {
		modeloVistaNotificacion = new ModeloTabla();
		modeloVistaNotificacion.addColumn("Proyecto");
		modeloVistaNotificacion.addColumn("Creador");
		modeloVistaNotificacion.addColumn("Estado");
		
		for (Notificacion not : listaNotificacion) {
			Object[] objNot = new Object[3];

			try {
				
				objNot[0] = not.getProyecto().getNombre();
				objNot[1] = not.getProyecto().getCreador().getNombre() + " " + not.getProyecto().getCreador().getApellidos() + " (@" + not.getProyecto().getCreador().getLogin() + ") ";
				objNot[2] = not.getDescripcion();
				//objApoyo[3] = apoyo.getId();
				
			} catch (Exception e) {
			}
			modeloVistaNotificacion.addRow(objNot);

		}
		table.setModel(modeloVistaNotificacion);
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(175);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);

	}
	
}
