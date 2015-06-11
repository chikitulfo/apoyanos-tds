package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import net.miginfocom.swing.MigLayout;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




//import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
//import javax.swing.table.DefaultTableModel;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.Pregunta;
import tds.apoyanos.modelo.Proyecto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.Collection;

@SuppressWarnings("serial")
public class VentanaPreguntasRespuestas extends JFrame {
	private JTextField textAsuntoEm;
	private JTextField textAsuntoRe;
	private JTable tbEmitidas;
	private JTable tbRecibidas;
	private ModeloTabla modeloVistaRecibidas;
	private ModeloTabla modeloVistaEmitidas;
	private MouseListener mouseListenerRecibidas;
	private MouseListener mouseListenerEmitidas;
	
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;

	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private Pregunta pregunta;
	private JTextField textCreadorEm;
	private JTextField textProyectoEm;
	private final JButton btnEnviar;
	private final JTextArea textPreguntaEm;
	private JTextArea textRespuestaEm;
	private JButton btResponder;
	
	
	private ArrayList<Pregunta> listaPreguntasEmitidas = (ArrayList<Pregunta>)controlador.getUsuario().getPreguntasEmitidas();
	private ArrayList<Pregunta> listaPreguntasRecibidas = (ArrayList<Pregunta>)controlador.getUsuario().getPreguntasRecibidas();

	
	private JTextField textProyectoRe;
	private JTextField textMecenasRe;
	private Proyecto proyecto;
	private JTextField textId;

	public VentanaPreguntasRespuestas() {
		this(null);
	}
	
	
	public VentanaPreguntasRespuestas(final Proyecto p) {
		proyecto=p;
		
		
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setBounds(6,6,1024,480);
		setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(6, 6, 1012, 480);
		getContentPane().add(tabbedPane);
		
		JPanel pnRealizadas = new JPanel();
		pnRealizadas.setBackground(Color.WHITE);
		tabbedPane.addTab("Preguntas Realizadas", null, pnRealizadas, null);
		pnRealizadas.setLayout(null);
		
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(Color.WHITE);
		panelIzq.setBounds(6, 6, 488, 432);
		pnRealizadas.add(panelIzq);
		panelIzq.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 24, 476, 294);
		panelIzq.add(scrollPane);
		
		
		
	///////////////////////////////////TABLA EMITIDAS
		tbEmitidas = new JTable();
		tbEmitidas.setRowSelectionAllowed(true);
		tbEmitidas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbEmitidas.setName("Listado de preguntas");
		tbEmitidas.setGridColor(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tbEmitidas);


		mouseListenerEmitidas = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent){

				//listaPreguntasRecibidas = (JList) mouseEvent.getSource();
				if(mouseEvent.getClickCount() == 1 ){
					int index = ((JTable) mouseEvent.getSource()).rowAtPoint(mouseEvent.getPoint());
					if(index >=0) {
						//Relleno información ventana respuestas y lo muestro.
						DefaultTableModel dtmE = (DefaultTableModel) tbEmitidas.getModel();
						int idPregunta = Integer.valueOf(String.valueOf(dtmE.getValueAt(index, 2)));
						pregunta = listaPreguntasEmitidas.get(index);
						//VER RESPUESTA
						//System.out.print(idPregunta);
						textProyectoEm.setText(pregunta.getProyecto().getNombre());
						textCreadorEm.setText(pregunta.getReceptor().getNombre() + " " + pregunta.getReceptor().getApellidos());
						textAsuntoEm.setText(pregunta.getAsunto());
						textPreguntaEm.setText(pregunta.getCuerpo());
						textRespuestaEm.setText(pregunta.getRespuesta());
						if (pregunta.getRespuesta() != null) {
							btnEnviar.setText("Nueva pregunta");
						}
						pregunta = null; //??
					}
				}
			}
		};


		
		
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setBounds(493, 6, 492, 432);
		pnRealizadas.add(panelDer);
		panelDer.setLayout(null);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(35, 106, 61, 16);
		panelDer.add(lblAsunto);
		
		textAsuntoEm = new JTextField();
		textAsuntoEm.setBounds(60, 126, 401, 30);
		panelDer.add(textAsuntoEm);
		textAsuntoEm.setColumns(10);
		
		JLabel lblPregunta = new JLabel("Pregunta:");
		lblPregunta.setBounds(35, 166, 61, 16);
		panelDer.add(lblPregunta);
		
		JLabel label = new JLabel("Respuesta:");
		label.setVisible(false);
		label.setBounds(35, 263, 100, 16);
		panelDer.add(label);
		
		textPreguntaEm = new JTextArea();
		textPreguntaEm.setWrapStyleWord(true);
		textPreguntaEm.setLineWrap(true);
		textPreguntaEm.setColumns(10);
		textPreguntaEm.setCaretColor(Color.BLACK);
		textPreguntaEm.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textPreguntaEm.setBounds(69, 186, 390, 72);
		panelDer.add(textPreguntaEm);
		
		textRespuestaEm = new JTextArea();
		textRespuestaEm.setEditable(false);
		textRespuestaEm.setWrapStyleWord(true);
		textRespuestaEm.setLineWrap(true);
		textRespuestaEm.setColumns(10);
		textRespuestaEm.setCaretColor(Color.BLACK);
		textRespuestaEm.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textRespuestaEm.setBounds(69, 283, 392, 72);
		panelDer.add(textRespuestaEm);
		btnEnviar = new JButton("Enviar Pregunta");
		btnEnviar.setActionCommand("enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEnviar.getText().equals("Enviar Pregunta")){
					if (textAsuntoEm.getText().trim().isEmpty() || textPreguntaEm.getText().trim().isEmpty()){
						//Saltar alarma informando que son todos los campos obligatorios.
						new VentanaMensajes("Debes incluir un asunto y una pregunta");
					} else {
						//Todo va bien, envía la pregunta
						try {
							controlador.hacerPregunta(textProyectoEm.getText(), textAsuntoEm.getText(), textPreguntaEm.getText());
							listaPreguntasEmitidas = (ArrayList<Pregunta>) controlador.getUsuario().getPreguntasEmitidas();
							//ACTUALIZAR LA VISTA DE LA TABLA
							vistaTablaEmitidas();
							new VentanaMensajes("La pregunta se ha enviado correctamente");
							pregunta=null;
							proyecto=null;
							textProyectoEm.setText(null);
							textCreadorEm.setText(null);
							textAsuntoEm.setText(null);
							textPreguntaEm.setText(null);
						} catch (InvalidArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				} else {
					//Nueva Pregunta
					textAsuntoEm.setText(null);
					textPreguntaEm.setText(null);
					textRespuestaEm.setText(null);
					btnEnviar.setText("Enviar Pregunta");
				}
			}
			});
		
		btnEnviar.setBounds(187, 369, 160, 29);
		panelDer.add(btnEnviar);
		
		textCreadorEm = new JTextField();
		textCreadorEm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		textCreadorEm.setEditable(false);
		textCreadorEm.setColumns(10);
		textCreadorEm.setBounds(60, 60, 401, 30);
		panelDer.add(textCreadorEm);
		
		textProyectoEm = new JTextField();
		textProyectoEm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		textProyectoEm.setEditable(false);
		textProyectoEm.setText((String) null);
		textProyectoEm.setColumns(10);
		textProyectoEm.setBounds(60, 21, 401, 30);
		panelDer.add(textProyectoEm);
		
		if (proyecto!=null){
			textCreadorEm.setText(proyecto.getCreador().getNombre()+" "+proyecto.getCreador().getApellidos());
			textProyectoEm.setText(proyecto.getNombre());
		}

		
		
		JPanel pnRecibidas = new JPanel();
		pnRecibidas.setBackground(Color.WHITE);
		tabbedPane.addTab("Preguntas Recibidas", null, pnRecibidas, null);
		pnRecibidas.setLayout(null);
		
		JPanel panelIzq2 = new JPanel();
		panelIzq2.setBackground(Color.WHITE);
		panelIzq2.setLayout(null);
		panelIzq2.setBounds(6, 6, 488, 432);
		pnRecibidas.add(panelIzq2);
		
		JScrollPane scrollPaneRecibidas = new JScrollPane();
		scrollPaneRecibidas.setBounds(6, 22, 476, 305);
		panelIzq2.add(scrollPaneRecibidas);
		
		///////////////////////////////////TABLA RECIBIDAS
		tbRecibidas = new JTable();
		tbRecibidas.setRowSelectionAllowed(true);
		tbRecibidas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbRecibidas.setName("Listado de preguntas");
		tbRecibidas.setGridColor(Color.LIGHT_GRAY);
		scrollPaneRecibidas.setViewportView(tbRecibidas);
		
		JPanel panelDer2 = new JPanel();
		panelDer2.setBackground(Color.WHITE);
		panelDer2.setLayout(null);
		panelDer2.setBounds(493, 6, 492, 432);
		pnRecibidas.add(panelDer2);
		
		JLabel lblAsunto_1 = new JLabel("Pregunta:");
		lblAsunto_1.setBounds(35, 166, 61, 16);
		panelDer2.add(lblAsunto_1);
		
		textAsuntoRe = new JTextField();
		textAsuntoRe.setEditable(false);
		textAsuntoRe.setColumns(10);
		textAsuntoRe.setBounds(60, 126, 401, 30);
		panelDer2.add(textAsuntoRe);
		
		JLabel lblAsunto_2 = new JLabel("Asunto:");
		lblAsunto_2.setBounds(35, 102, 61, 16);
		panelDer2.add(lblAsunto_2);
		
		JLabel label_3 = new JLabel("Respuesta:");
		label_3.setBounds(35, 263, 100, 16);
		panelDer2.add(label_3);
		
		final JTextArea textPreguntaRe = new JTextArea();
		textPreguntaRe.setEditable(false);
		textPreguntaRe.setWrapStyleWord(true);
		textPreguntaRe.setLineWrap(true);
		textPreguntaRe.setColumns(10);
		textPreguntaRe.setCaretColor(Color.BLACK);
		textPreguntaRe.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textPreguntaRe.setBounds(69, 186, 390, 72);
		panelDer2.add(textPreguntaRe);
		
		final JTextArea textRespuestaRe = new JTextArea();
		textRespuestaRe.setWrapStyleWord(true);
		textRespuestaRe.setLineWrap(true);
		textRespuestaRe.setColumns(10);
		textRespuestaRe.setCaretColor(Color.BLACK);
		textRespuestaRe.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textRespuestaRe.setBounds(69, 283, 392, 72);
		panelDer2.add(textRespuestaRe);
		
		btResponder = new JButton("Responder");
		btResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (textRespuestaRe.getText().trim().isEmpty()){
						//Saltar alarma informando que son todos los campos obligatorios.
						new VentanaMensajes("Debes responder la pregunta");
					} else {
						//Todo va bien, envía la pregunta
						try {
							//controlador.hacerPregunta(textProyectoEm.getText(), textAsuntoEm.getText(), textPreguntaEm.getText());
							//listaPreguntasEmitidas = controlador.getUsuario().getPreguntasEmitidas();
							
							
							controlador.responderPregunta(textProyectoRe.getText(), Integer.valueOf(textId.getText()), textRespuestaRe.getText());
							listaPreguntasRecibidas = (ArrayList<Pregunta>) controlador.getUsuario().getPreguntasRecibidas();
							
							
							//ACTUALIZAR LA VISTA DE LA TABLA
							vistaTablaRecibidas();
							new VentanaMensajes("La pregunta se ha enviado correctamente");
							pregunta=null;
							proyecto=null;
							textProyectoRe.setText(null);
							textMecenasRe.setText(null);
							textAsuntoRe.setText(null);
							textPreguntaRe.setText(null);
							textRespuestaRe.setText(null);
						} catch (InvalidArgumentException | InvalidStateException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
			}
		});
		btResponder.setBounds(186, 369, 160, 29);
		panelDer2.add(btResponder);
		
		textProyectoRe = new JTextField();
		textProyectoRe.setText((String) null);
		textProyectoRe.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		textProyectoRe.setEditable(false);
		textProyectoRe.setColumns(10);
		textProyectoRe.setBounds(60, 21, 401, 30);
		panelDer2.add(textProyectoRe);
		
		textMecenasRe = new JTextField();
		textMecenasRe.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		textMecenasRe.setEditable(false);
		textMecenasRe.setColumns(10);
		textMecenasRe.setBounds(60, 60, 401, 30);
		panelDer2.add(textMecenasRe);
		
		textId = new JTextField();
		textId.setVisible(false);
		textId.setEnabled(false);
		textId.setEditable(false);
		textId.setBounds(35, 367, 134, 28);
		panelDer2.add(textId);
		textId.setColumns(10);
		
				

		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);

		mouseListenerRecibidas = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent){

				//listaPreguntasRecibidas = (JList) mouseEvent.getSource();
				if(mouseEvent.getClickCount() == 1 ){
					int index = ((JTable) mouseEvent.getSource()).rowAtPoint(mouseEvent.getPoint());
					if(index >=0){
						//Relleno información ventana respuestas y lo muestro.
						DefaultTableModel dtmR = (DefaultTableModel) tbRecibidas.getModel();
						int idPregunta = Integer.valueOf(String.valueOf(dtmR.getValueAt(index,1)));
						pregunta = listaPreguntasRecibidas.get(index);
						//VER RESPUESTA
						textId.setText(String.valueOf(idPregunta));
						textProyectoRe.setText(pregunta.getProyecto().getNombre());
						textMecenasRe.setText(pregunta.getEmisor().getNombre()+ " "+pregunta.getEmisor().getApellidos());
						textAsuntoRe.setText(pregunta.getAsunto());
						textPreguntaRe.setText(pregunta.getCuerpo());
						textRespuestaRe.setText(pregunta.getRespuesta());
						if(pregunta.getRespuesta()!=null){
							btResponder.setEnabled(false);
						} else {
							btResponder.setEnabled(true);
						}
					}
				}
			}
		};
		
		vistaTablaRecibidas();
		
		
		
		//PINTAR LAS TABLAS
		vistaTablaEmitidas();
		//recorrerPreguntaEmitida();
		
		//MENU
		menu_apoyanos = new Menu(this);
		
	}
	
	private void vistaTablaRecibidas() {
		modeloVistaRecibidas = new ModeloTabla();
		modeloVistaRecibidas.addColumn("Pregunta recibida");
		modeloVistaRecibidas.addColumn("Id");
		
		

		for (Pregunta pe : listaPreguntasRecibidas) {
			Object[] objRecibida = new Object[2];

			try {

				objRecibida[0] = pe.getAsunto();
				objRecibida[1] = pe.getId();
				//JLabel lbl = new JLabel(pe.getAsunto());
				//if (pe.getRespuesta()==null){
				//	lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
				//} else {
				//	lbl.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				//}
				//objRecibida[0] = lbl;
				if (pe.getRespuesta()==null){
					( (JTextField) objRecibida[0]).setFont(new Font("Lucida Grande", Font.BOLD, 13));
				} else {
					( (JComponent) objRecibida[0]).setFont(new Font("Lucida Grande", Font.PLAIN, 10));
				}
				
			} catch (Exception e) {
			}
			modeloVistaRecibidas.addRow(objRecibida);

		}
		tbRecibidas.setModel(modeloVistaRecibidas);
		tbRecibidas.addMouseListener(mouseListenerRecibidas);
		tbRecibidas.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer());
		tbRecibidas.getColumnModel().getColumn(0).setResizable(false);
		tbRecibidas.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbRecibidas.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		tbRecibidas.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
	}
	
	private void vistaTablaEmitidas() {
		modeloVistaEmitidas = new ModeloTabla();
		modeloVistaEmitidas.addColumn("Pregunta emitida");
		modeloVistaEmitidas.addColumn("Respondida");
		modeloVistaEmitidas.addColumn("Id");
		
		for (Pregunta pre : listaPreguntasEmitidas) {
			Object[] objEmitida = new Object[3];

			try {
				objEmitida[0] = pre.getAsunto();
				if (pre.getRespuesta()==null) {
					objEmitida[1] = "No";
				} else {
					objEmitida[1] = "Sí";
				}
				objEmitida[2] = pre.getId();
			} catch (Exception e) {
			}
			modeloVistaEmitidas.addRow(objEmitida);
		}
		
		tbEmitidas.setModel(modeloVistaEmitidas);
		tbEmitidas.addMouseListener(mouseListenerEmitidas);
		tbEmitidas.getColumnModel().getColumn(0).setResizable(false);
		tbEmitidas.getColumnModel().getColumn(0).setPreferredWidth(250);
		tbEmitidas.getColumnModel().getColumn(1).setResizable(false);
		//tbEmitidas.getColumnModel().getColumn(1).setPreferredWidth(20);
		tbEmitidas.getColumnModel().getColumn(2).setResizable(false);
		tbEmitidas.getColumnModel().getColumn(2).setPreferredWidth(0);
		tbEmitidas.getColumnModel().getColumn(2).setMaxWidth(0);
		tbEmitidas.getColumnModel().getColumn(2).setMinWidth(0);
		tbEmitidas.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		tbEmitidas.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
	   
	}
	
	@SuppressWarnings("unused")
	private void recorrerPreguntaEmitida(){
		for(Pregunta p : listaPreguntasEmitidas){
			System.out.print(p.getAsunto() + " " + p.getCuerpo() +" "+ p.getRespuesta() + " " + p.getId());
		}
	}
	
	private Pregunta buscarPreguntaEmitida(int id){
		Pregunta preguntaEncontrada = null;
		for (Pregunta p : listaPreguntasEmitidas) {
			if (p.getId()==id) {
				preguntaEncontrada = p;
			}
		}
		return preguntaEncontrada;
	}
	
	private Pregunta buscarPreguntaRecibida(int id){
		Pregunta preguntaEncontrada = null;
		for (Pregunta p : listaPreguntasRecibidas) {
			if (p.getId()==id) {
				preguntaEncontrada = p;
			}
		}
		return preguntaEncontrada;
	}
	
	
	private class CustomTableCellRenderer extends DefaultTableCellRenderer {
		TableCellRenderer decorado;
		
		public void setDecorado(TableCellRenderer tcr){
			this.decorado=tcr;
		}
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JLabel lbl = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            		//new JLabel(value == null? "": value.toString());
            if (!listaPreguntasRecibidas.isEmpty() && listaPreguntasRecibidas.get(row).getRespuesta() == null)
            	lbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
            return lbl;
        }
    }

}
