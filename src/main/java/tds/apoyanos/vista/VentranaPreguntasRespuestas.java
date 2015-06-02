package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.modelo.Pregunta;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VentranaPreguntasRespuestas extends JDialog {
	private JTable table;
	private JTextField textAsunto;
	private JTextField textField_1;
	private JTable table_1;

	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private Pregunta pregunta;

	
	public VentranaPreguntasRespuestas() {
		setResizable(false);
		getContentPane().setBackground(SystemColor.window);
		getContentPane().setLayout(null);
		setBounds(6,6,1024,480);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 1012, 480);
		getContentPane().add(tabbedPane);
		
		JPanel pnRealizadas = new JPanel();
		tabbedPane.addTab("Preguntas Realizadas", null, pnRealizadas, null);
		pnRealizadas.setLayout(null);
		
		JPanel panelIzq = new JPanel();
		panelIzq.setBounds(6, 6, 488, 432);
		pnRealizadas.add(panelIzq);
		panelIzq.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 64, 476, 305);
		panelIzq.add(scrollPane);
		
		String[] columnas = new String[]{
	            "Asunto"};

        
        //Una única fila
        Object[][] datos = new Object[][]{
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"},
                {"¿El proyecto es único o tendrá varias fases?"}
        };
		
        
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Al clicar envía la info de esta pregunta al formulario
			}
		});
		table.setRowSelectionAllowed(false);
		table.setSelectionBackground(SystemColor.inactiveCaptionText);
		table.setName("Listado Preguntas");
		table.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas
        table.setModel(new DefaultTableModel(
                datos,
                columnas) {
            
            Class[] tipos = new Class[]{
                    String.class,
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		//scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		
		JPanel panelDer = new JPanel();
		panelDer.setBounds(493, 6, 492, 432);
		pnRealizadas.add(panelDer);
		panelDer.setLayout(null);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(35, 42, 61, 16);
		panelDer.add(lblAsunto);
		
		textAsunto = new JTextField();
		textAsunto.setEditable(false);
		textAsunto.setText(pregunta.getAsunto());
		textAsunto.setBounds(60, 62, 401, 30);
		panelDer.add(textAsunto);
		textAsunto.setColumns(10);
		
		JLabel lblPregunta = new JLabel("Pregunta:");
		lblPregunta.setBounds(35, 102, 61, 16);
		panelDer.add(lblPregunta);
		
		JLabel label = new JLabel("Respuesta:");
		label.setVisible(false);
		label.setBounds(35, 237, 100, 16);
		panelDer.add(label);
		
		JTextArea textAPregunta = new JTextArea();
		textAPregunta.setEditable(false);
		textAPregunta.setText(pregunta.getCuerpo());
		textAPregunta.setWrapStyleWord(true);
		textAPregunta.setLineWrap(true);
		textAPregunta.setColumns(10);
		textAPregunta.setCaretColor(Color.BLACK);
		textAPregunta.setBorder(new LineBorder(new Color(128, 128, 128)));
		textAPregunta.setBounds(69, 122, 390, 100);
		panelDer.add(textAPregunta);
		
		JTextArea textARespuesta = new JTextArea();
		textARespuesta.setEditable(false);
		textARespuesta.setText(pregunta.getRespuesta());
		textARespuesta.setWrapStyleWord(true);
		textARespuesta.setLineWrap(true);
		textARespuesta.setColumns(10);
		textARespuesta.setCaretColor(Color.BLACK);
		textARespuesta.setBorder(new LineBorder(new Color(128, 128, 128)));
		textARespuesta.setBounds(69, 257, 392, 100);
		panelDer.add(textARespuesta);
		
		final JButton btnHacer = new JButton("Hacer Pregunta");
		final JButton btnEnviar = new JButton("Enviar Pregunta");

		btnHacer.setActionCommand("hacer");
		btnEnviar.setActionCommand("enviar");
		btnHacer.setEnabled(true);
		btnEnviar.setEnabled(false);
		
		btnHacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si en el boton pone hacer pregunta limpia el formulario y los text los vuelve editables
				if (e.equals("hacer")){
					btnHacer.setEnabled(false);
					btnEnviar.setEnabled(true);
				}	
			}
		});
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si en el boton pone hacer pregunta limpia el formulario y los text los vuelve editables
				if (e.equals("enviar")){
					btnHacer.setEnabled(true);
					btnEnviar.setEnabled(false);
				}	
			}
		});
		
		
		btnEnviar.setBounds(291, 369, 160, 29);
		panelDer.add(btnEnviar);
		btnHacer.setBounds(79, 369, 160, 29);
		panelDer.add(btnHacer);
		
		
		JPanel pnRecibidas = new JPanel();
		tabbedPane.addTab("Preguntas Recibidas", null, pnRecibidas, null);
		pnRecibidas.setLayout(null);
		
		JPanel panelIzq2 = new JPanel();
		panelIzq2.setLayout(null);
		panelIzq2.setBounds(6, 6, 488, 432);
		pnRecibidas.add(panelIzq2);
		
		JScrollPane scrollPaneRecibidas = new JScrollPane();
		scrollPaneRecibidas.setBounds(6, 64, 476, 305);
		panelIzq2.add(scrollPaneRecibidas);
		
		
		String[] columnas2 = new String[]{
        "Título"};


//Una única fila
Object[][] datos2 = new Object[][]{
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"},
        {"¿El proyecto es único o tendrá varias fases?"}
};

		
		JTable table2 = new JTable();
		table2.setRowSelectionAllowed(false);
		table2.setSelectionBackground(SystemColor.inactiveCaptionText);
		table2.setName("Listado Preguntas");
		table2.setGridColor(Color.LIGHT_GRAY);
        // Defino el TableModel y le indico los datos y nombres de columnas
        table2.setModel(new DefaultTableModel(
                datos2,
                columnas2) {
            
            Class[] tipos = new Class[]{
                    String.class,
                };

            @Override
            public Class getColumnClass(int columnIndex) {
                // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
                // observen que estamos retornando la clase que definimos de antemano.
                return tipos[columnIndex];
            }
            
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table2.getColumnModel().getColumn(0).setResizable(false);
		table2.getColumnModel().getColumn(0).setPreferredWidth(150);
		//scrollPane.setColumnHeaderView(table);
		scrollPaneRecibidas.setViewportView(table2);
		
		JPanel panelDer2 = new JPanel();
		panelDer2.setLayout(null);
		panelDer2.setBounds(493, 6, 492, 432);
		pnRecibidas.add(panelDer2);
		
		JLabel label_1 = new JLabel("Título:");
		label_1.setBounds(35, 42, 61, 16);
		panelDer2.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(60, 62, 401, 30);
		panelDer2.add(textField_1);
		
		JLabel label_2 = new JLabel("Pregunta:");
		label_2.setBounds(35, 102, 61, 16);
		panelDer2.add(label_2);
		
		JLabel label_3 = new JLabel("Respuesta:");
		label_3.setBounds(35, 237, 100, 16);
		panelDer2.add(label_3);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setWrapStyleWord(true);
		textArea_2.setLineWrap(true);
		textArea_2.setColumns(10);
		textArea_2.setCaretColor(Color.BLACK);
		textArea_2.setBorder(new LineBorder(new Color(128, 128, 128)));
		textArea_2.setBounds(69, 122, 390, 100);
		panelDer2.add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setWrapStyleWord(true);
		textArea_3.setLineWrap(true);
		textArea_3.setColumns(10);
		textArea_3.setCaretColor(Color.BLACK);
		textArea_3.setBorder(new LineBorder(new Color(128, 128, 128)));
		textArea_3.setBounds(69, 257, 392, 100);
		panelDer2.add(textArea_3);
		
		JButton button_2 = new JButton("Responder");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(186, 369, 160, 29);
		panelDer2.add(button_2);
		
				
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		
	}
}
