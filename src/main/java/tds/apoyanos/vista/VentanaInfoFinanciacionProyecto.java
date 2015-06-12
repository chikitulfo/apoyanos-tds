package tds.apoyanos.vista;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.Proyecto;
import tds.apoyanos.modelo.Recompensa;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

//import javax.swing.border.BevelBorder;
//import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaInfoFinanciacionProyecto extends JFrame {
	private JTextField txtTtuloDelProyecot;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtSoftware;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private JTable tbRecompensas;
	private ModeloTabla modeloVistaRecompensa;
	private Collection<Recompensa> listaRecompensas;
	
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	
	
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");
//	private SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");
	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private Proyecto proyecto;


	
	public VentanaInfoFinanciacionProyecto(String nombreProyecto) {
		proyecto = controlador.getProyecto(nombreProyecto);
		listaRecompensas = proyecto.getRecompensas();
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
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
		txtTtuloDelProyecot.setForeground(Color.DARK_GRAY);
		txtTtuloDelProyecot.setText(proyecto.getNombre());
		txtTtuloDelProyecot.setBounds(110, 48, 880, 28);
		getContentPane().add(txtTtuloDelProyecot);
		txtTtuloDelProyecot.setColumns(10);
		
		JTextArea txtrEsteProyectoEs = new JTextArea();
		txtrEsteProyectoEs.setLineWrap(true);
		txtrEsteProyectoEs.setForeground(Color.DARK_GRAY);
		txtrEsteProyectoEs.setEditable(false);
		txtrEsteProyectoEs.setWrapStyleWord(true);
		txtrEsteProyectoEs.setText(proyecto.getDescripcion());
		txtrEsteProyectoEs.setRows(10);
		txtrEsteProyectoEs.setPreferredSize(new Dimension(375, 16));
		txtrEsteProyectoEs.setMinimumSize(new Dimension(300, 50));
		txtrEsteProyectoEs.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		txtrEsteProyectoEs.setBounds(110, 100, 375, 164);
		getContentPane().add(txtrEsteProyectoEs);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalización:");
		lblFechaFinalizacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFinalizacin.setBounds(90, 274, 130, 20);
		getContentPane().add(lblFechaFinalizacin);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(Color.DARK_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText(fechaDia.format(proyecto.getPlazoFinanciacion().getTime()));
		textField.setColumns(10);
		textField.setBounds(110, 294, 150, 28);
		getContentPane().add(textField);
		
		JLabel lblImporte = new JLabel("Importe (€):");
		lblImporte.setHorizontalAlignment(SwingConstants.LEFT);
		lblImporte.setBounds(90, 326, 130, 20);
		getContentPane().add(lblImporte);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setForeground(Color.DARK_GRAY);
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
		txtSoftware.setForeground(Color.DARK_GRAY);
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
		textField_2.setForeground(Color.DARK_GRAY);
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
		textField_3.setForeground(Color.DARK_GRAY);
		textField_3.setColumns(10);
		textField_3.setBounds(335, 346, 120, 28);
		getContentPane().add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(544, 100, 446, 82);
		getContentPane().add(scrollPane);
		
		///////////////////////////////////TABLA
        tbRecompensas = new JTable();
		tbRecompensas.setRowSelectionAllowed(true);
		tbRecompensas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbRecompensas.setName("Listado de recompensas");
		tbRecompensas.setGridColor(Color.LIGHT_GRAY);
		tbRecompensas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbRecompensas.setSelectionBackground(UIManager.getColor("Button.background"));
		scrollPane.setViewportView(tbRecompensas);
		vistaTablaRecompensas();
		
		JLabel lblFinanciado = new JLabel("% Financiado:");
		lblFinanciado.setHorizontalAlignment(SwingConstants.LEFT);
		lblFinanciado.setBounds(315, 378, 130, 20);
		getContentPane().add(lblFinanciado);
		
		textField_4 = new JTextField();
		textField_4.setText(formatoDecimal.format(proyecto.getCantidadRecaudada()*100./proyecto.getCantidadMinima()));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setForeground(Color.DARK_GRAY);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(335, 398, 120, 28);
		getContentPane().add(textField_4);
		
		JButton button = new JButton("¡¡Apoyar Proyecto!!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel dtm = (DefaultTableModel) tbRecompensas.getModel(); 
				if (tbRecompensas.getSelectedRow()==-1){
					//Alerta
					new VentanaMensajes("Para Apoyar un proyecto debe seleccionar primero una recompensa.");
				} else {
					//TODO VA BIEN
					String nombreRecompensa = String.valueOf(dtm.getValueAt(tbRecompensas.getSelectedRow(),0));
					double cantidadRecompensa = (Double) dtm.getValueAt(tbRecompensas.getSelectedRow(),1);
					int idRecompensa = (int) dtm.getValueAt(tbRecompensas.getSelectedRow(),3);
					
					Recompensa re = buscarRecompensa(idRecompensa);
					String nombreProyecto = proyecto.getNombre();
					
					if ((re.getMaximoParticipantes() == 0) || (re.getApoyos().size() < re.getMaximoParticipantes())){
						//se puede
						//Ventana confirmación
						int n = JOptionPane.showConfirmDialog(
							    null,
							    "¿Deseas apoyar el siguiente proyecto con la recompensa seleccionada?",
							    "An Inane Question",
							    JOptionPane.YES_NO_OPTION);
						if(n==0){
							//Apoyar el proyecto
							try {
								controlador.apoyarProyecto(nombreProyecto, nombreRecompensa, cantidadRecompensa, "");
							} catch (InvalidStateException | InvalidArgumentException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//Ir al listado de proyectos en financiación
		                    VentanaPrincipalApoyanos ventanaPrincipal;
							try {
								ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Todos");
			                    ventanaPrincipal.setVisible(true);
			                    setVisible(false); //you can't see me!
			                    dispose(); //Destroy the JFrame object
							} catch (InvalidArgumentException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					} else {
						new VentanaMensajes("La recompensa elegida ha llegado a su máximo de apoyos.");
					}
					///
				}

			}
		});
		button.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		button.setEnabled(proyecto.estaEnFinanciacion());
		if (proyecto.esCompletado())
			button.setText("¡¡Proyecto Completado!!");
		button.setBounds(544, 360, 444, 66);
		getContentPane().add(button);
		
		final JTextArea textDescripcion = new JTextArea();
		textDescripcion.setWrapStyleWord(true);
		textDescripcion.setText((String) null);
		textDescripcion.setRows(10);
		textDescripcion.setPreferredSize(new Dimension(375, 16));
		textDescripcion.setMinimumSize(new Dimension(300, 50));
		textDescripcion.setLineWrap(true);
		textDescripcion.setForeground(Color.DARK_GRAY);
		textDescripcion.setEditable(false);
		textDescripcion.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textDescripcion.setBounds(545, 187, 444, 77);
		getContentPane().add(textDescripcion);
		
		JButton btninfo = new JButton("+Info Recompensa");
		btninfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tbRecompensas.getModel(); 
				if (tbRecompensas.getSelectedRow()==-1){
					//Alerta
					new VentanaMensajes("Para +Info de una recompensa hay que seleccionar primero una.");
				} else {
					//TODO VA BIEN
					//String nombreR = String.valueOf(dtm.getValueAt(tbRecompensas.getSelectedRow(),0));
					int reId = Integer.valueOf((String) dtm.getValueAt(tbRecompensas.getSelectedRow(),3));
					//Recompensa r = buscarRecompensa(reId);
					textDescripcion.setText(buscarRecompensa(reId).getDescripcion());
				}
			}
		});
		btninfo.setBounds(680, 271, 176, 29);
		getContentPane().add(btninfo);
		
		JLabel lblSiTienesAlguna = new JLabel("Si tienes alguna duda:");
		lblSiTienesAlguna.setHorizontalAlignment(SwingConstants.LEFT);
		lblSiTienesAlguna.setBounds(544, 322, 150, 16);
		getContentPane().add(lblSiTienesAlguna);
		
		JButton btnPreguntas = new JButton("Preguntas");
		btnPreguntas.setBounds(706, 317, 117, 29);
		getContentPane().add(btnPreguntas);
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);
		//Menú
		menu_apoyanos = new Menu(this);
		
	}
	private void vistaTablaRecompensas() {
		modeloVistaRecompensa = new ModeloTabla();
		modeloVistaRecompensa.addColumn("Recompensa");
		modeloVistaRecompensa.addColumn("Cantidad (€)");
		modeloVistaRecompensa.addColumn("Límite Apoyos");
		modeloVistaRecompensa.addColumn("Id");
		// poner mas informacion

		for (Recompensa re : listaRecompensas) {
			Object[] objRecompensa = new Object[4];

			try {

				objRecompensa[0] = re.getNombre();
				objRecompensa[1] = re.getCantidadMinima();
				if(re.getMaximoParticipantes()==0)
					objRecompensa[2] = "-";
				else
					objRecompensa[2] = re.getMaximoParticipantes();
				objRecompensa[3] = re.getId();
				
			} catch (Exception e) {
			}
			modeloVistaRecompensa.addRow(objRecompensa);

		}
		tbRecompensas.setModel(modeloVistaRecompensa);
		tbRecompensas.getColumnModel().getColumn(0).setResizable(false);
		tbRecompensas.getColumnModel().getColumn(0).setPreferredWidth(150);
		tbRecompensas.getColumnModel().getColumn(1).setResizable(false);
		tbRecompensas.getColumnModel().getColumn(1).setPreferredWidth(50);
		tbRecompensas.getColumnModel().getColumn(2).setResizable(false);
		tbRecompensas.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		tbRecompensas.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);

	}
	
	private Recompensa buscarRecompensa (int id){
		Recompensa reEncontrada = null;
		for (Recompensa re : listaRecompensas) {
			if (re.getId()==id) {
				reEncontrada = re;
			}
		}
		return reEncontrada;
	}
}
