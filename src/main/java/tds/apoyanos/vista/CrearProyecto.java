package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class CrearProyecto extends JPanel {
	
	private JTabbedPane tabbedPane;
	private JPanel panelProyecto;
	private JPanel panelRecompensas;
	private JTextField textField;
	
	
	public CrearProyecto(){
		
		//Crear Proyecto
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		panelProyecto = new JPanel(); 
		panelProyecto.setLayout(new BorderLayout()); 
		tabbedPane.addTab("Proyecto", null, panelProyecto, null);
		
		textField = new JTextField();
		panelProyecto.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		panelRecompensas = new JPanel(); 
		panelRecompensas.setLayout(new BorderLayout()); 
		tabbedPane.addTab("Recompensas", null, panelRecompensas, null);
		
		add(tabbedPane);
		
	}

}
