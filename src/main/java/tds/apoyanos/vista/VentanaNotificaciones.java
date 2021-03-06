package tds.apoyanos.vista;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.modelo.Notificacion;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

//import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaNotificaciones extends JFrame {
	private JTable table;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;

	private ModeloTabla modeloVistaNotificacion;
	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private LinkedList<Notificacion> listaNotificacion =  (LinkedList<Notificacion>)controlador.getUsuario().getNotificaciones();
	
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
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setSelectionBackground(UIManager.getColor("Button.background"));
		table.setName("Listado de notificaciones.");
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
				objNot[1] = not.getProyecto().getCreador().getNombre() + " " + not.getProyecto().getCreador().getApellidos();
				objNot[2] = not.getDescripcion();
				//objApoyo[3] = apoyo.getId();
				
			} catch (Exception e) {
			}
			modeloVistaNotificacion.addRow(objNot);

		}
		table.setModel(modeloVistaNotificacion);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		table.addMouseListener(mouseListenerRecibidas);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		//table.getColumnModel().getColumn(2).setPreferredWidth(50);

	}

	private class CustomTableCellRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			//new JLabel(value == null? "": value.toString());
			if (!listaNotificacion.isEmpty()) {
				if (!listaNotificacion.get(row).isLeida()) {
					lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
				}
				lbl.setToolTipText(lbl.getText());
			}
			return lbl;
		}
	}

	MouseAdapter mouseListenerRecibidas = new MouseAdapter() {
		public void mouseClicked(MouseEvent mouseEvent){

			if(mouseEvent.getClickCount() == 1 ){
				int index = ((JTable) mouseEvent.getSource()).rowAtPoint(mouseEvent.getPoint());
				if(index >=0){
					Notificacion n = listaNotificacion.get(index);
					try {
						controlador.marcarNotificacionLeida(n.getId());
					} catch (InvalidArgumentException e) {}
					DefaultTableModel dtmR = (DefaultTableModel) table.getModel();
					dtmR.fireTableRowsUpdated(index,index);
					new VentanaMensajes(n.getDescripcion());
				}
			}
		}
	};
}
