package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Proyecto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentanaInfoFinanciacionProyecto extends JDialog {
	private JTextField txtTtuloDelProyecot;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtSoftware;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;
	private JTextField textField_4;
	
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");
	
	private Controlador controlador = Controlador.getUnicaInstancia();
	//private Proyecto proyecto;


	
	public VentanaInfoFinanciacionProyecto(Proyecto proyecto) {

		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		
		JLabel lblTtuloDelProyecto = new JLabel("Título del Proyecto:");
		lblTtuloDelProyecto.setHorizontalAlignment(SwingConstants.LEFT);
		lblTtuloDelProyecto.setBounds(90, 28, 130, 20);
		getContentPane().add(lblTtuloDelProyecto);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescripcion.setBounds(90, 80, 130, 20);
		getContentPane().add(lblDescripcion);
		
		txtTtuloDelProyecot = new JTextField();
		txtTtuloDelProyecot.setEditable(false);
		txtTtuloDelProyecot.setForeground(Color.GRAY);
		txtTtuloDelProyecot.setText(proyecto.getNombre());
		txtTtuloDelProyecot.setBounds(110, 48, 880, 28);
		getContentPane().add(txtTtuloDelProyecot);
		txtTtuloDelProyecot.setColumns(10);
		
		JTextArea txtrEsteProyectoEs = new JTextArea();
		txtrEsteProyectoEs.setLineWrap(true);
		txtrEsteProyectoEs.setForeground(Color.GRAY);
		txtrEsteProyectoEs.setEditable(false);
		txtrEsteProyectoEs.setWrapStyleWord(true);
		txtrEsteProyectoEs.setText(proyecto.getDescripcion());
		txtrEsteProyectoEs.setRows(10);
		txtrEsteProyectoEs.setPreferredSize(new Dimension(375, 16));
		txtrEsteProyectoEs.setMinimumSize(new Dimension(300, 50));
		txtrEsteProyectoEs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrEsteProyectoEs.setBounds(110, 100, 375, 164);
		getContentPane().add(txtrEsteProyectoEs);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalización:");
		lblFechaFinalizacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFinalizacin.setBounds(90, 274, 130, 20);
		getContentPane().add(lblFechaFinalizacin);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(Color.GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(fechaDia.format(proyecto.getPlazoFinanciacion()));
		textField.setColumns(10);
		textField.setBounds(110, 294, 150, 28);
		getContentPane().add(textField);
		
		JLabel lblImporte = new JLabel("Importe (€):");
		lblImporte.setHorizontalAlignment(SwingConstants.LEFT);
		lblImporte.setBounds(90, 326, 130, 20);
		getContentPane().add(lblImporte);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setForeground(Color.GRAY);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText(formatoDecimal.format(proyecto.getCantidadMinima()) + " €.");
		textField_1.setColumns(10);
		textField_1.setBounds(110, 346, 150, 28);
		getContentPane().add(textField_1);
		
		JLabel lblCategora = new JLabel("Categoría:");
		lblCategora.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora.setBounds(90, 378, 130, 20);
		getContentPane().add(lblCategora);
		
		txtSoftware = new JTextField();
		txtSoftware.setEditable(false);
		txtSoftware.setForeground(Color.GRAY);
		txtSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoftware.setText(proyecto.getCategoria().getNombre());
		txtSoftware.setColumns(10);
		txtSoftware.setBounds(110, 398, 150, 28);
		getContentPane().add(txtSoftware);
		
		JLabel lblRestan = new JLabel("Restan:");
		lblRestan.setHorizontalAlignment(SwingConstants.LEFT);
		lblRestan.setBounds(315, 274, 130, 20);
		getContentPane().add(lblRestan);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setText(String.valueOf(proyecto.getDiasRestantes()));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.GRAY);
		textField_2.setColumns(10);
		textField_2.setBounds(335, 294, 80, 28);
		getContentPane().add(textField_2);
		
		JLabel lblDas = new JLabel("días.");
		lblDas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDas.setBounds(414, 298, 58, 20);
		getContentPane().add(lblDas);
		
		JLabel lblVotosRecibidos = new JLabel("Financiado:");
		lblVotosRecibidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblVotosRecibidos.setBounds(315, 326, 130, 20);
		getContentPane().add(lblVotosRecibidos);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText(formatoDecimal.format(proyecto.getCantidadRecaudada()) + " €.");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setForeground(Color.GRAY);
		textField_3.setColumns(10);
		textField_3.setBounds(335, 346, 120, 28);
		getContentPane().add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(544, 100, 446, 75);
		getContentPane().add(scrollPane);
		
		
		//scrollPane.setColumnHeaderView(table);
///////////////////////////////////////////////		
		
		String[] columnas = new String[]{
	            "Recompensa",
	            "Cantidad",
	            "Apoyar"};

///        
		JButton btApoyo = new JButton("Apoyar");
		
        //Una única fila
        Object[][] datos = new Object[][]{
                {"Un duplicado oficial en BluRay firmado por el director del bódrio.",
                2000.00,
                btApoyo}};
		
/////		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado de recompensas del proyecto");
		table.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table_1.setEnabled(false);
		table_1.setForeground(Color.GRAY);
		table_1.setRowSelectionAllowed(false);
		table_1.setSelectionBackground(SystemColor.inactiveCaptionText);
		table_1.setName("Listado de proyectos en votación");
		table_1.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas
        table_1.setModel(new DefaultTableModel(
                datos,
                columnas) {
            
            Class[] tipos = new Class[]{
                    String.class,
                    double.class,
                    JButton.class
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
        
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(250);
		//scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table_1);
		
		JLabel lblFinanciado = new JLabel("% Financiado:");
		lblFinanciado.setHorizontalAlignment(SwingConstants.LEFT);
		lblFinanciado.setBounds(315, 378, 130, 20);
		getContentPane().add(lblFinanciado);
		
		textField_4 = new JTextField();
		textField_4.setText(formatoDecimal.format(proyecto.getCantidadRecaudada()*100./proyecto.getCantidadMinima()));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.GRAY);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(335, 398, 120, 28);
		getContentPane().add(textField_4);
/////	
		
/////////////////////////////////////////////		
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 480);
		
		
		
	}
}
