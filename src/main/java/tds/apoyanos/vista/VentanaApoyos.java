package tds.apoyanos.vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Apoyo;

@SuppressWarnings("serial")
public class VentanaApoyos extends JDialog {
	private JTable table;
	private ModeloTabla modeloVistaApoyo;


	private Controlador controlador = Controlador.getUnicaInstancia();
	private LinkedList<Apoyo> listaApoyos = (LinkedList<Apoyo>) controlador.getApoyos();
	
	public VentanaApoyos() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado de proyectos en votación");
		table.setGridColor(Color.LIGHT_GRAY);
		//Muestra la vista de la tabla
		vistaTablaProyectosApoyados();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNotificaciones = new JLabel("Apoyos");
		lblNotificaciones.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotificaciones.setBounds(100, 60, 152, 30);
		getContentPane().add(lblNotificaciones);
		
		JButton btnapoyar = new JButton("Más Info");
		btnapoyar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Proyecto proyecto = apoyo.;
				//VentanaInfoFinaciacionProyecto ventanaInfoFinanciacion = new VentanaInfoFinaciacionProyecto(proyecto);
				//ventanaCrearProyecto.setVisible(true);
				//setVisible(false);
			}
		});
		btnapoyar.setBounds(432, 365, 117, 29);
		getContentPane().add(btnapoyar);
/////		
				
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 480);

		
	}
	
	public class ModeloTabla extends DefaultTableModel {

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
	
	
	private void vistaTablaProyectosApoyados() {
		modeloVistaApoyo = new ModeloTabla();
		modeloVistaApoyo.addColumn("Proyecto");
		modeloVistaApoyo.addColumn("Recompensa");
		modeloVistaApoyo.addColumn("Cantidad (€)");
		
		for (Apoyo apoyo : listaApoyos) {
			Object[] objApoyo = new Object[3];

			try {

				objApoyo[0] = apoyo.getProyecto();
				objApoyo[1] = apoyo.getRecompensa().getNombre();
				objApoyo[2] = apoyo.getCantidad();
				
			} catch (Exception e) {
			}
			modeloVistaApoyo.addRow(objApoyo);

		}
		table.setModel(modeloVistaApoyo);
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(175);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
	}
	
	private Apoyo buscarApoyo (String a){
		Apoyo apoyo=null;
		for (Apoyo ap : listaApoyos) {
			if (ap.
					getNombre().equals(a)) {
				apoyo = ap;
			}
		}
		return apoyo;
	}
	
	private boolean existeRecompensa (String r){
		boolean existe=false;
		for (RecompensaVista re : listaRecompensas) {
			if (re.getNombre().equals(r)) {
				existe=true;
			}
		}
		return existe;
	}
	
	
}
