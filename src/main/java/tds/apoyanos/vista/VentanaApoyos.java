package tds.apoyanos.vista;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

//import javax.swing.table.DefaultTableModel;




import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Apoyo;

@SuppressWarnings("serial")
public class VentanaApoyos extends JFrame {
	private JTable table;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;

	private ModeloTabla modeloVistaApoyo;

	private Controlador controlador = Controlador.getUnicaInstancia();
	private Collection<Apoyo> listaApoyos =  controlador.getApoyos();
	
	public VentanaApoyos() {
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(SystemColor.window);
		
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 105, 794, 248);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setSelectionBackground(UIManager.getColor("Button.background"));
		table.setName("Listado de apoyos.");
		
		
		//Muestra la vista de la tabla
		vistaTablaProyectosApoyados();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNotificaciones = new JLabel("Apoyos");
		lblNotificaciones.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotificaciones.setBounds(100, 60, 152, 30);
		getContentPane().add(lblNotificaciones);
		
		JButton btnapoyar = new JButton("Más Info");
		btnapoyar.setEnabled(true);
		if (listaApoyos.isEmpty())
			btnapoyar.setEnabled(false);
		
		btnapoyar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            DefaultTableModel dtm = (DefaultTableModel) table.getModel();  
				if (table.getSelectedRow()==-1){
					//Alerta
					new VentanaMensajes("Debes seleccionar un proyecto");
				} else {
					//TODO VA BIEN
					String nombreProyecto = String.valueOf(dtm.getValueAt(table.getSelectedRow(),0));
					VentanaInfoFinanciacionProyecto ventanaInfoFinanciacion = new VentanaInfoFinanciacionProyecto(nombreProyecto);
					ventanaInfoFinanciacion.setVisible(true);
					setVisible(false); //you can't see me!
		            dispose(); //Destroy the JFrame object
				}
	            
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
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);

		///MENU
		menu_apoyanos = new Menu(this);
	}
	
	private void vistaTablaProyectosApoyados() {
		modeloVistaApoyo = new ModeloTabla();
		modeloVistaApoyo.addColumn("Proyecto");
		modeloVistaApoyo.addColumn("Recompensa");
		modeloVistaApoyo.addColumn("Cantidad (€)");
		//modeloVistaApoyo.addColumn("Id");
		
		for (Apoyo apoyo : listaApoyos) {
			Object[] objApoyo = new Object[3];

			try {
				
				objApoyo[0] = apoyo.getProyecto().getNombre();
				objApoyo[1] = apoyo.getRecompensa().getNombre();
				objApoyo[2] = apoyo.getCantidad();
				//objApoyo[3] = apoyo.getId();
				
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
		//table.getColumnModel().getColumn(3).setResizable(false);
		//table.getColumnModel().getColumn(3).setMaxWidth(0);
		//table.getColumnModel().getColumn(3).setMinWidth(0);
		//table.getColumnModel().getColumn(3).setPreferredWidth(0);
	}
	
	@SuppressWarnings("unused")
	private Apoyo buscarApoyo (int id){
		Apoyo apoyo=null;
		for (Apoyo ap : listaApoyos) {
			if (ap.getId()==id) {
				apoyo = ap;
			}
		}
		return apoyo;
	}
	
	@SuppressWarnings("unused")
	private boolean existeApoyo (int id){
		boolean existe=false;
		for (Apoyo ap : listaApoyos) {
			if (ap.getId()==id) {
				existe = true;
			}
		}
		return existe;
	}
	
	@SuppressWarnings("unused")
	private void recorrerApoyos (){
		System.out.print("Veamos los apoyos de: " + controlador.getUsuario().getLogin() + " con " + controlador.getApoyos().size() + " apoyos.\n");
		for (Apoyo ap : listaApoyos) {
			System.out.print(ap.getProyecto().getNombre() + " " + ap.getRecompensa().getNombre() + " " + ap.getCantidad());
		}
	}
	
	
}
