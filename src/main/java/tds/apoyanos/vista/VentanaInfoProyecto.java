package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

//import javax.swing.border.BevelBorder;
//import javax.swing.table.DefaultTableModel;





import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.exceptions.InvalidStateException;
import tds.apoyanos.modelo.*;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VentanaInfoProyecto extends JFrame {
	private JTextField textTitulo;
	private JTextField textFechaFin;
	private JTextField textImporte;
	private JTextField textCategoria;
	private JTextField textRestan;
	private JTextField textVotos;
	private JTable tbRecompensas;
	private JTextArea textDescripcion;
	private ModeloTabla modeloVistaRecompensa;
	private Collection<Recompensa> listaRecompensas;
	
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd-MMM-yyyy");
//	private SimpleDateFormat fechaHora = new SimpleDateFormat("HH:mm");
	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");

	private Proyecto proyecto;
	private Controlador controlador = Controlador.getUnicaInstancia();
	
	
	public VentanaInfoProyecto(final String nombreProyecto) throws InvalidArgumentException {
		proyecto = controlador.getProyecto(nombreProyecto);
		listaRecompensas = proyecto.getRecompensas();
		
		boolean mismoUsuario=controlador.getUsuario().getId()==proyecto.getCreador().getId();
		
		
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lbTitulo = new JLabel("Título del Proyecto:");
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setBounds(90, 28, 130, 20);
		getContentPane().add(lbTitulo);
		
		JLabel lbDescripcion = new JLabel("Descripción:");
		lbDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		lbDescripcion.setBounds(90, 80, 130, 20);
		getContentPane().add(lbDescripcion);
		
		textTitulo = new JTextField();
		textTitulo.setEditable(false);
		textTitulo.setForeground(Color.DARK_GRAY);
		//textTitulo.setText("La leja de la mesa");
		textTitulo.setText(proyecto.getNombre());
		textTitulo.setBounds(110, 48, 880, 28);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);
		
		JTextArea textrDescripcion = new JTextArea();
		textrDescripcion.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textrDescripcion.setLineWrap(true);
		textrDescripcion.setForeground(Color.DARK_GRAY);
		textrDescripcion.setEditable(false);
		textrDescripcion.setWrapStyleWord(true);
		textrDescripcion.setText(proyecto.getDescripcion());
		textrDescripcion.setRows(10);
		textrDescripcion.setPreferredSize(new Dimension(375, 16));
		textrDescripcion.setMinimumSize(new Dimension(300, 50));
		textrDescripcion.setBounds(110, 100, 375, 164);
		getContentPane().add(textrDescripcion);
		
		JLabel lbFechaFin = new JLabel("Fecha Finalización:");
		lbFechaFin.setHorizontalAlignment(SwingConstants.LEFT);
		lbFechaFin.setBounds(90, 274, 130, 20);
		getContentPane().add(lbFechaFin);
		
		textFechaFin = new JTextField();
		textFechaFin.setEditable(false);
		textFechaFin.setForeground(Color.DARK_GRAY);
		textFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaFin.setText(fechaDia.format(proyecto.getPlazoFinanciacion().getTime()));
		textFechaFin.setColumns(10);
		textFechaFin.setBounds(110, 294, 150, 28);
		getContentPane().add(textFechaFin);
		
		JLabel lbImporte = new JLabel("Importe (€):");
		lbImporte.setHorizontalAlignment(SwingConstants.LEFT);
		lbImporte.setBounds(90, 326, 130, 20);
		getContentPane().add(lbImporte);
		
		textImporte = new JTextField();
		textImporte.setEditable(false);
		textImporte.setForeground(Color.DARK_GRAY);
		textImporte.setHorizontalAlignment(SwingConstants.CENTER);
		textImporte.setText((String.valueOf(formatoDecimal.format(proyecto.getCantidadMinima()))) + " €.");
		textImporte.setColumns(10);
		textImporte.setBounds(110, 346, 150, 28);
		getContentPane().add(textImporte);
		
		JLabel lbCategoria = new JLabel("Categoría:");
		lbCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		lbCategoria.setBounds(90, 378, 130, 20);
		getContentPane().add(lbCategoria);
		
		textCategoria = new JTextField();
		textCategoria.setEditable(false);
		textCategoria.setForeground(Color.DARK_GRAY);
		textCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		textCategoria.setText(proyecto.getCategoria().getNombre());
		textCategoria.setColumns(10);
		textCategoria.setBounds(110, 398, 150, 28);
		getContentPane().add(textCategoria);
		
		JLabel lbDiasRestan = new JLabel("Restan:");
		lbDiasRestan.setHorizontalAlignment(SwingConstants.LEFT);
		lbDiasRestan.setBounds(315, 274, 130, 20);
		getContentPane().add(lbDiasRestan);
		
		textRestan = new JTextField();
		textRestan.setEditable(false);
		textRestan.setText(String.valueOf(proyecto.getDiasRestantes()));
		textRestan.setHorizontalAlignment(SwingConstants.CENTER);
		textRestan.setForeground(Color.DARK_GRAY);
		textRestan.setColumns(10);
		textRestan.setBounds(335, 294, 80, 28);
		getContentPane().add(textRestan);
		
		JLabel lblDas = new JLabel("días.");
		lblDas.setHorizontalAlignment(SwingConstants.LEFT);
		lblDas.setBounds(414, 298, 58, 20);
		getContentPane().add(lblDas);
		
		JLabel lbVotos = new JLabel("Votos Recibidos:");
		lbVotos.setHorizontalAlignment(SwingConstants.LEFT);
		lbVotos.setBounds(315, 326, 130, 20);
		getContentPane().add(lbVotos);
		
		textVotos = new JTextField();
		textVotos.setEditable(false);
		textVotos.setText(String.valueOf(proyecto.getNumvotos()));
		textVotos.setHorizontalAlignment(SwingConstants.CENTER);
		textVotos.setForeground(Color.DARK_GRAY);
		textVotos.setColumns(10);
		textVotos.setBounds(335, 346, 80, 28);
		getContentPane().add(textVotos);
		
		
		/*
		 * Si el usuario ya ha votado este proyecto no puede volver a votarlo. 
		 * btnVotarProyecto.setEnabled(false);
		 * */
		JButton btVotar = new JButton("¡¡Votar Proyecto!!");
		btVotar.setEnabled(true);
		if (mismoUsuario || controlador.isVotadoProyecto(nombreProyecto)) {
			btVotar.setEnabled(false);
		}
		btVotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ventana confirmación
				int n = JOptionPane.showConfirmDialog(
					    null,
					    "¿Deseas votar el siguiente proyecto?",
					    "An Inane Question",
					    JOptionPane.YES_NO_OPTION);
				if(n==0){
					//Apoyar el proyecto
					try {
						controlador.votarProyecto(nombreProyecto);
					} catch (InvalidStateException | InvalidArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Ir al listado de proyectos en financiación
                    VentanaPrincipalApoyanos ventanaPrincipal;
					try {
						ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Todos");
	                    ventanaPrincipal.setVisible(true);
	                    setVisible(false); //you can't see me!
	                    dispose(); //Destroy the JFrame object
					} catch (InvalidArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		
		
		btVotar.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		btVotar.setBounds(546, 360, 444, 66);
		getContentPane().add(btVotar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(544, 100, 446, 75);
		getContentPane().add(scrollPane);
		
		///////////////////////////////////TABLA
        tbRecompensas = new JTable();
		tbRecompensas.setRowSelectionAllowed(true);
		tbRecompensas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbRecompensas.setName("Listado de recompensas");
		tbRecompensas.setGridColor(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tbRecompensas);
		
		textDescripcion = new JTextArea();
		textDescripcion.setWrapStyleWord(true);
		textDescripcion.setText((String) null);
		textDescripcion.setRows(10);
		textDescripcion.setPreferredSize(new Dimension(375, 16));
		textDescripcion.setMinimumSize(new Dimension(300, 50));
		textDescripcion.setLineWrap(true);
		textDescripcion.setForeground(Color.DARK_GRAY);
		textDescripcion.setEditable(false);
		textDescripcion.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		textDescripcion.setBounds(546, 187, 444, 77);
		getContentPane().add(textDescripcion);
		
		JButton btninfo = new JButton("+Info Recompensa");
		btninfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tbRecompensas.getModel(); 
				String nombreR = String.valueOf(dtm.getValueAt(tbRecompensas.getSelectedRow(),0));
				textDescripcion.setText(buscarRecompensa(nombreR).getDescripcion());
			}
		});
		btninfo.setBounds(680, 271, 176, 29);
		getContentPane().add(btninfo);
		
		JLabel lblSiTienesAlguna = new JLabel("Si tienes alguna duda:");
		lblSiTienesAlguna.setHorizontalAlignment(SwingConstants.LEFT);
		lblSiTienesAlguna.setBounds(546, 317, 150, 16);
		getContentPane().add(lblSiTienesAlguna);
		
		JButton btnPreguntas = new JButton("Preguntas");
		btnPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                VentanaPreguntasRespuestas ventanaPrincipal = new VentanaPreguntasRespuestas(proyecto);
                ventanaPrincipal.setVisible(true);
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
			}
		});
		btnPreguntas.setBounds(710, 312, 117, 29);
		getContentPane().add(btnPreguntas);
		vistaTablaRecompensas();

		
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);
		
		///MENU
		menu_apoyanos = new Menu(this);
	}
	
	private void vistaTablaRecompensas() {
		modeloVistaRecompensa = new ModeloTabla();
		modeloVistaRecompensa.addColumn("Recompensa");
		modeloVistaRecompensa.addColumn("Cantidad (€)");
		modeloVistaRecompensa.addColumn("Límite Apoyos");
		//modeloVistaRecompensa.addColumn("Descripcion");
		//modeloVistaRecompensa.addColumn("Eliminar");
		// poner mas informacion

		for (final Recompensa re : listaRecompensas) {
			Object[] objRecompensa = new Object[4];

			try {

				objRecompensa[0] = re.getNombre();
				objRecompensa[1] = re.getCantidadMinima();
				if(re.getMaximoParticipantes()==0)
					objRecompensa[2] = "-";
				else
					objRecompensa[2] = re.getMaximoParticipantes();
				/*
				objRecompensa[3] = new JButton("+Info");
				((AbstractButton) objRecompensa[3]).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textDescripcion.setText(re.getDescripcion());
					}
				
				});
				*/
				
				
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
		//tbRecompensas.getColumnModel().getColumn(3).setResizable(false);
		//tbRecompensas.getColumnModel().getColumn(3).setPreferredWidth(20);

	}
	private Recompensa buscarRecompensa (String r){
		Recompensa reEncontrada = null;
		for (Recompensa re : listaRecompensas) {
			if (re.getNombre().equals(r)) {
				reEncontrada = re;
			}
		}
		return reEncontrada;
	}
}
