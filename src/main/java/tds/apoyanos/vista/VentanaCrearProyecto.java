package tds.apoyanos.vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;

//import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.exceptions.InvalidArgumentException;
import tds.apoyanos.modelo.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class VentanaCrearProyecto extends JFrame{
	private JTextField textProyecto;
	private final JTextArea txtDescripcionProyecto;
	private JTextField textImporte;
	JDateChooser fechaFin = new JDateChooser();
	@SuppressWarnings("rawtypes")
	final JComboBox cbCategoria = new JComboBox();
	private JTextField txt_UsuarioCreador;
	private JTable tbRecompensas;
	private ModeloTabla modeloVistaRecompensa;
	private JTextField textRecompensa;
	private JTextField textCantidad;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	
	private String msg_error="HAY UN ERROR. Revisa todos los campos y recuerda: \n - Título obligatorio.\n - Descripción obligatoria. \n - Fecha obligatoria, formato dd/mm/aaaa y a partir del día de mañana. \n - Importe ###.## \n - Una o más recompensas.";
	private String msg_exito="Proyecto Registrado Correctamente.\n";
//	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd/MM/yyyy");
//	private DecimalFormat formatoDecimal = new DecimalFormat("#.##");
	private String nombreProyecto;
	private String descripcionProyecto;
	private String categoriaProyecto;
	private Double cantidadProyecto;
	private GregorianCalendar cplazoProyecto = new GregorianCalendar();
	
	
	private Controlador controlador = Controlador.getUnicaInstancia();
	private Usuario usuario = controlador.getUsuario();
	
	
	private LinkedList<RecompensaVista> listaRecompensas = new LinkedList<RecompensaVista>();
	private RecompensaVista reV=null;
	private JTextField textNumApoyos;
	
	@SuppressWarnings("unchecked")
	public VentanaCrearProyecto() {
		setResizable(false);
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel Proyecto = new JPanel();
		Proyecto.setBackground(Color.WHITE);
		Proyecto.setAlignmentX(Component.LEFT_ALIGNMENT);
		tabbedPane.addTab("Proyecto", null, Proyecto, null);
		Proyecto.setLayout(new BoxLayout(Proyecto, BoxLayout.Y_AXIS));
		
		JPanel paneltitulo = new JPanel();
		paneltitulo.setBackground(Color.WHITE);
		Proyecto.add(paneltitulo);
		paneltitulo.setLayout(new BoxLayout(paneltitulo, BoxLayout.Y_AXIS));
		
		
		JPanel panel_labeltitulo = new JPanel();
		panel_labeltitulo.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_labeltitulo.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(300);
		flowLayout.setAlignment(FlowLayout.LEFT);
		paneltitulo.add(panel_labeltitulo);
		
		JLabel lblTtuloDelProyecto = new JLabel("Título del Proyecto:");
		panel_labeltitulo.add(lblTtuloDelProyecto);
		
		final JLabel lblProyectoExiste = new JLabel("* Proyecto existe o está en blanco ");
		lblProyectoExiste.setVisible(false);
		lblProyectoExiste.setForeground(Color.RED);
		lblProyectoExiste.setHorizontalAlignment(SwingConstants.LEFT);
		panel_labeltitulo.add(lblProyectoExiste);
		
		JPanel panel_texttitulo = new JPanel();
		panel_texttitulo.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_texttitulo.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(350);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		paneltitulo.add(panel_texttitulo);
		
		textProyecto = new JTextField();
		textProyecto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if ((textProyecto.getText().trim().isEmpty()) || (controlador.esCreado(textProyecto.getText()))){
					lblProyectoExiste.setVisible(true);
				} else {
					lblProyectoExiste.setVisible(false);
				}
			}
		});
		panel_texttitulo.add(textProyecto);
		textProyecto.setColumns(30);
		
		JPanel panel_descripcion = new JPanel();
		panel_descripcion.setBackground(Color.WHITE);
		Proyecto.add(panel_descripcion);
		panel_descripcion.setLayout(new BoxLayout(panel_descripcion, BoxLayout.Y_AXIS));
		
		JPanel panel_lbldescripcion = new JPanel();
		panel_lbldescripcion.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_lbldescripcion.getLayout();
		flowLayout_2.setHgap(300);
		flowLayout_2.setVgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_descripcion.add(panel_lbldescripcion);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción: ");
		panel_lbldescripcion.add(lblNewLabel_1);
		
		JLabel lblNoPuede = new JLabel("* No puede dejar en blanco");
		lblNoPuede.setVisible(false);
		lblNoPuede.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoPuede.setForeground(Color.RED);
		panel_lbldescripcion.add(lblNoPuede);
		
		JPanel panel_txtareadescrip = new JPanel();
		panel_txtareadescrip.setBackground(Color.WHITE);
		FlowLayout flowLayout_3 = (FlowLayout) panel_txtareadescrip.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(350);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_descripcion.add(panel_txtareadescrip);
		
		txtDescripcionProyecto = new JTextArea();
		txtDescripcionProyecto.setLineWrap(true);
		txtDescripcionProyecto.setPreferredSize(new Dimension(375, 16));
		txtDescripcionProyecto.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtDescripcionProyecto.setRows(10);
		txtDescripcionProyecto.setMinimumSize(new Dimension(300, 50));
		txtDescripcionProyecto.setWrapStyleWord(true);
		txtDescripcionProyecto.setText("");
		panel_txtareadescrip.add(txtDescripcionProyecto);
		
		JPanel panel_FechaFin = new JPanel();
		panel_FechaFin.setBackground(Color.WHITE);
		Proyecto.add(panel_FechaFin);
		panel_FechaFin.setLayout(new BoxLayout(panel_FechaFin, BoxLayout.Y_AXIS));
		
		JPanel panellabelFechafin = new JPanel();
		panellabelFechafin.setBackground(Color.WHITE);
		FlowLayout flowLayout_4 = (FlowLayout) panellabelFechafin.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(300);
		panel_FechaFin.add(panellabelFechafin);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalización:");
		panellabelFechafin.add(lblFechaFinalizacin);
		
		JLabel lblIntroduzcaUna = new JLabel("* Introduzca una fecha correcta dd/mm/aaaa");
		lblIntroduzcaUna.setHorizontalTextPosition(SwingConstants.LEFT);
		lblIntroduzcaUna.setVisible(false);
		lblIntroduzcaUna.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaUna.setForeground(Color.RED);
		panellabelFechafin.add(lblIntroduzcaUna);
		
		JPanel panel_textFechaFin = new JPanel();
		panel_textFechaFin.setBackground(Color.WHITE);
		FlowLayout flowLayout_6 = (FlowLayout) panel_textFechaFin.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(350);
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_FechaFin.add(panel_textFechaFin);
		
		panel_textFechaFin.add(fechaFin);
		
		JPanel panel_Importe = new JPanel();
		panel_Importe.setBackground(Color.WHITE);
		Proyecto.add(panel_Importe);
		panel_Importe.setLayout(new BoxLayout(panel_Importe, BoxLayout.Y_AXIS));
		
		JPanel panellabelImporte = new JPanel();
		panellabelImporte.setBackground(Color.WHITE);
		FlowLayout flowLayout_5 = (FlowLayout) panellabelImporte.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		flowLayout_5.setHgap(300);
		flowLayout_5.setVgap(0);
		panel_Importe.add(panellabelImporte);
		
		JLabel lblImporte = new JLabel("Importe: €");
		panellabelImporte.add(lblImporte);
		
		JLabel lblIntroduzcaUn = new JLabel("* Introduzca un importe correcto ####,##");
		lblIntroduzcaUn.setVisible(false);
		lblIntroduzcaUn.setHorizontalAlignment(SwingConstants.LEFT);
		lblIntroduzcaUn.setForeground(Color.RED);
		panellabelImporte.add(lblIntroduzcaUn);
		
		JPanel panelTxtImporte = new JPanel();
		panelTxtImporte.setBackground(Color.WHITE);
		FlowLayout flowLayout_7 = (FlowLayout) panelTxtImporte.getLayout();
		flowLayout_7.setVgap(0);
		flowLayout_7.setHgap(350);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panel_Importe.add(panelTxtImporte);
		
		textImporte = new JTextField();
		textImporte.setText("####.##");
		panelTxtImporte.add(textImporte);
		textImporte.setColumns(10);
		
		JPanel panelCategoria = new JPanel();
		panelCategoria.setBackground(Color.WHITE);
		Proyecto.add(panelCategoria);
		panelCategoria.setLayout(new BoxLayout(panelCategoria, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout_8 = (FlowLayout) panel.getLayout();
		flowLayout_8.setVgap(0);
		flowLayout_8.setHgap(300);
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panelCategoria.add(panel);
		
		JLabel lblCategora = new JLabel("Categoría: ");
		panel.add(lblCategora);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout_9 = (FlowLayout) panel_1.getLayout();
		flowLayout_9.setVgap(0);
		flowLayout_9.setHgap(350);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panelCategoria.add(panel_1);
		
		//Se puede mejorar con un bucle y una lista que contena las categorías
		cbCategoria.addItem("Música");
		cbCategoria.addItem("Libros");
		cbCategoria.addItem("Cine");
		cbCategoria.addItem("Social");
		cbCategoria.addItem("Software");
		cbCategoria.addItem("Deportes");
		panel_1.add(cbCategoria);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBackground(Color.WHITE);
		Proyecto.add(panelUsuario);
		panelUsuario.setLayout(new BoxLayout(panelUsuario, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout_10 = (FlowLayout) panel_4.getLayout();
		flowLayout_10.setHgap(300);
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panelUsuario.add(panel_4);
		
		JLabel labelUsuario = new JLabel("Usuario que crea el proyecto: ");
		panel_4.add(labelUsuario);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_11 = (FlowLayout) panel_5.getLayout();
		flowLayout_11.setHgap(350);
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panelUsuario.add(panel_5);
		
		txt_UsuarioCreador = new JTextField();
		txt_UsuarioCreador.setEditable(false);
		txt_UsuarioCreador.setForeground(Color.GRAY);
		txt_UsuarioCreador.setText(usuario.getNombre() + " " + usuario.getApellidos());
		txt_UsuarioCreador.setColumns(30);
		panel_5.add(txt_UsuarioCreador);
		
		JPanel Recompensas = new JPanel();
		tabbedPane.addTab("Recompensas", null, Recompensas, null);
		Recompensas.setLayout(new BoxLayout(Recompensas, BoxLayout.X_AXIS));
		
		JPanel panel_Izq = new JPanel();
		panel_Izq.setBackground(Color.WHITE);
		Recompensas.add(panel_Izq);
		panel_Izq.setLayout(null);
		
		JLabel lblRecompensa = new JLabel("Recompensa:");
		lblRecompensa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecompensa.setBounds(67, 65, 100, 28);
		panel_Izq.add(lblRecompensa);
		
		textRecompensa = new JTextField();
		textRecompensa.setBounds(172, 65, 250, 28);
		panel_Izq.add(textRecompensa);
		textRecompensa.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(67, 95, 100, 28);
		panel_Izq.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(172, 95, 150, 28);
		panel_Izq.add(textCantidad);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcin.setBounds(67, 124, 100, 28);
		panel_Izq.add(lblDescripcin);
		
		final JTextArea txtrDescripcion = new JTextArea();
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setLineWrap(true);
		txtrDescripcion.setCaretColor(new Color(0, 0, 0));
		txtrDescripcion.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtrDescripcion.setColumns(10);
		txtrDescripcion.setBounds(175, 130, 245, 100);
		panel_Izq.add(txtrDescripcion);
		
		JLabel label = new JLabel("€uros.");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(330, 95, 100, 28);
		panel_Izq.add(label);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textRecompensa.getText().trim().isEmpty() || textCantidad.getText().trim().isEmpty() || txtrDescripcion.getText().trim().isEmpty()){
					//Saltar alarma informando que son todos los campos obligatorios.
					new VentanaMensajes("Todos los campos obligatorios. Excepto \"Límite de votos\".");
				} else if (existeRecompensa(textRecompensa.getText()) && reV==null) {
					//Saltar alarma informando que ya existe una recompensa con ese nombre y se pone en blanco el campo de nombre recompensa
					new VentanaMensajes("Ya existe una recompensa con ese mismo nombre.");
					textRecompensa.setText(null);
				} else {
					//Todo va bien, es una nueva recompensa o una modificación
					int num_apoyos;
					if (textNumApoyos.getText().trim().length()==0)
						num_apoyos=0;
					else
						num_apoyos=Integer.parseInt(textNumApoyos.getText().trim());
					
					if (reV!=null){
						//Actualizamos el valor de la recompensa y volvemos a liberar reV
						reV.setRecompensaVista(textRecompensa.getText(), txtrDescripcion.getText(), Double.parseDouble(textCantidad.getText()), num_apoyos);
						reV=null;
					} else {
						listaRecompensas.add(new RecompensaVista(textRecompensa.getText(), txtrDescripcion.getText(), Double.parseDouble(textCantidad.getText()), num_apoyos));
					}
					textRecompensa.setText(null);
					textCantidad.setText(null);
					txtrDescripcion.setText(null);
					textNumApoyos.setText(null);
					//ACTUALIZAR LA VISTA DE LA TABLA
					vistaTablaRecompensas();
					
				}
			}
		});
		btnAadir.setBounds(100, 324, 117, 29);
		panel_Izq.add(btnAadir);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reV=null;
				textRecompensa.setText(null);
				textCantidad.setText(null);
				txtrDescripcion.setText(null);
				textNumApoyos.setText(null);
			}
		});
		btnLimpiar.setBounds(297, 324, 117, 29);
		panel_Izq.add(btnLimpiar);
		
		JLabel lblMximoNmeroApoyos = new JLabel("Máximo apoyos:");
		lblMximoNmeroApoyos.setToolTipText("Número máximo de apoyos que puede recibir la recompensa");
		lblMximoNmeroApoyos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMximoNmeroApoyos.setBounds(17, 242, 150, 28);
		panel_Izq.add(lblMximoNmeroApoyos);
		
		textNumApoyos = new JTextField();
		textNumApoyos.setColumns(10);
		textNumApoyos.setBounds(172, 242, 150, 28);
		panel_Izq.add(textNumApoyos);
		
		JLabel lblEnBlanco = new JLabel("* En blanco si no hay límite.");
		lblEnBlanco.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblEnBlanco.setToolTipText("");
		lblEnBlanco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnBlanco.setBounds(324, 242, 150, 28);
		panel_Izq.add(lblEnBlanco);
		
		JPanel panel_Der = new JPanel();
		Recompensas.add(panel_Der);
		panel_Der.setLayout(new BoxLayout(panel_Der, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_Der.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 62, 489, 115);
		panel_2.add(scrollPane);

		
	///////////////////////////////////TABLA
        tbRecompensas = new JTable();
		tbRecompensas.setRowSelectionAllowed(true);
		tbRecompensas.setSelectionBackground(SystemColor.inactiveCaptionText);
		tbRecompensas.setName("Listado de recompensas");
		tbRecompensas.setGridColor(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tbRecompensas);
		vistaTablaRecompensas();
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Obtenemos el modelo de la vista de la tabla
				//Capturamos de la fila seleccionada el valor de la celda primera (0) que contiene el nombre de la recompensa
				//Buscamos la recompensa en la lista de recompensas
				//Rellenamos el formulario con esos valores
				//Posteriormente una vez en el formulario si se pulsa limpiar no hará nada y si se pulsa añadir no la añadirá (ya que ya existe en la lista) sino que modificará sus datos
				DefaultTableModel dtm = (DefaultTableModel) tbRecompensas.getModel(); 
				String nombreRecompensa = String.valueOf(dtm.getValueAt(tbRecompensas.getSelectedRow(),0));
				
				reV = buscarRecompensa(nombreRecompensa);
				textRecompensa.setText(reV.getNombre());
				textCantidad.setText(String.valueOf(reV.getCantidadMinima()));
				txtrDescripcion.setText(reV.getDescripcion());
				if (reV.getMaximoParticipantes()==0){
					textNumApoyos.setText(null);
				} else {
					textNumApoyos.setText(String.valueOf(reV.getMaximoParticipantes()));
				}
			}
		});
		btnNewButton.setBounds(75, 179, 117, 29);
		panel_2.add(btnNewButton);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tbRecompensas.getModel(); 
				String nombreRecompensa = String.valueOf(dtm.getValueAt(tbRecompensas.getSelectedRow(),0));
				dtm.removeRow(tbRecompensas.getSelectedRow()); 
				eliminarRecompensa (nombreRecompensa);
				vistaTablaRecompensas();
				
			}
		});
		btnEliminar.setBounds(307, 179, 117, 29);
		panel_2.add(btnEliminar);
		
		
		JButton btnRegistrar = new JButton("Registrar Proyecto");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TRATAR LOS CAMPOS QUE HAY ANTES DE OPERAR CON ELLOS SI HAY ERRORES MANDAR MENSAJE ALERTA
				if (!datosCorrectos()){
					new VentanaMensajes(msg_error);
					tabbedPane.setSelectedIndex(0);
				} else {
					//REGISTRAR PROYECTO E IR AL LISTADO DE PROYECTOS EN VOTACION
		            try {
		            	//fechaFin.getCalendar()
		            	//cplazoProyecto.setTime(fechaDia.parse(fechaFin.getDateFormatString()));
		            	cplazoProyecto.setTime(fechaFin.getDate());
		                controlador.crearProyecto(nombreProyecto, descripcionProyecto, cantidadProyecto, cplazoProyecto, categoriaProyecto,listaRecompensas);
		                new VentanaMensajes(msg_exito);
		                
		            } catch (InvalidArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		btnRegistrar.setBounds(45, 300, 214, 65);
		btnRegistrar.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		btnRegistrar.setForeground(Color.BLACK);
		panel_2.add(btnRegistrar);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(284, 315, 165, 29);
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton_1.setForeground(Color.BLACK);
		panel_2.add(btnNewButton_1);

		
				
		//TODO Solicitar al controlador las categorías y hacer los sub-menús automáticamente
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setLocationRelativeTo(null);
		
		
		//MENÚ
		menu_apoyanos = new Menu(this);
		
	}
	
	
	private void vistaTablaRecompensas() {
		modeloVistaRecompensa = new ModeloTabla();
		modeloVistaRecompensa.addColumn("Recompensa");
		modeloVistaRecompensa.addColumn("Cantidad (€)");
		modeloVistaRecompensa.addColumn("Límite Apoyos");
		//modeloVistaRecompensa.addColumn("Eliminar");
		// poner mas informacion

		for (RecompensaVista re : listaRecompensas) {
			Object[] objRecompensa = new Object[3];

			try {

				objRecompensa[0] = re.getNombre();
				objRecompensa[1] = re.getCantidadMinima();
				if(re.getMaximoParticipantes()==0)
					objRecompensa[2] = "-";
				else
					objRecompensa[2] = re.getMaximoParticipantes();
				
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

	}
	
	private void eliminarRecompensa (String r){
		for (RecompensaVista re : listaRecompensas) {
			if (re.getNombre().equals(r)) {
				listaRecompensas.remove(re);
			}

		}
	}
	
	private RecompensaVista buscarRecompensa (String r){
		RecompensaVista rev=null;
		for (RecompensaVista re : listaRecompensas) {
			if (re.getNombre().equals(r)) {
				rev = re;
			}
		}
		return rev;
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
	
	private boolean datosCorrectos(){
		boolean todoOk;
		todoOk=(!listaRecompensas.isEmpty());
		todoOk=((textImporte.getText()!="####.##") && (textImporte.getText().trim().length()!=0));
		todoOk=(txtDescripcionProyecto.getText().trim().length()!=0);
		todoOk=(!controlador.esCreado(textProyecto.getText()) && (textProyecto.getText().trim().length()!=0));
				
		if (todoOk) {
			nombreProyecto = textProyecto.getText();
			descripcionProyecto = txtDescripcionProyecto.getText();
			cantidadProyecto = Double.valueOf(textImporte.getText());
			categoriaProyecto = (String)cbCategoria.getSelectedItem();
		}	
		return todoOk;
	}
}
