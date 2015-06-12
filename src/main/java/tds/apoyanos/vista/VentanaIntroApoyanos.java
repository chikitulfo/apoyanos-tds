package tds.apoyanos.vista;

import net.miginfocom.swing.MigLayout;
import pulsador.IEncendidoListener;
import pulsador.Luz;
import tds.apoyanos.controlador.Controlador;
import umu.tds.cargador.ComponenteCargadorFinanciacion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.EventObject;


@SuppressWarnings("serial")
public class VentanaIntroApoyanos extends JFrame {

	private JPanel panelContenidos;
	private JLabel lblInicioApoyanos;
	@SuppressWarnings("unused")
	private Menu menu_apoyanos;
	
	public VentanaIntroApoyanos() {
		getContentPane().setBackground(Color.WHITE);
		
		//Características del JFrame
		setBackground(new Color(255, 255, 255));
		setTitle("Apóyanos - Tu plataforma crowdfunding para lanzar tus proyectos.");
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);			////////////
		setBounds(100, 100, 1024, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		
		
		
		//Panel Inicial
		panelContenidos = new JPanel();
		panelContenidos.setBackground(new Color(255, 255, 255));
		panelContenidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenidos.setLayout(new BorderLayout(0, 0));
		//JTextField txtDescripcionRecompensa;
		
		///MENU
		menu_apoyanos = new Menu(this);
		//getContentPane().add(panelContenidos);
		setContentPane(panelContenidos);
		lblInicioApoyanos = new JLabel("");
		panelContenidos.add(lblInicioApoyanos);
		lblInicioApoyanos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblInicioApoyanos.setIcon(new ImageIcon(VentanaIntroApoyanos.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		//lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBounds(new Rectangle(500, 500, 500, 500));
		panelNorte.setBackground(new Color(255, 255, 255));
		panelNorte.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblAvanzar = new JLabel("Avanzar un día: ");
		lblAvanzar.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.setLayout(new MigLayout("", "[502px][502px]", "[30px][30px]"));
		panelNorte.add(lblAvanzar, "cell 0 0,grow");
		Luz luzAvanzar = new Luz();
		panelNorte.add(luzAvanzar, "flowx,cell 1 0,grow");
		
		
		
		
		
		JLabel label = new JLabel("");
		panelNorte.add(label, "cell 1 0,grow");
		
		JLabel label_1 = new JLabel("");
		panelNorte.add(label_1, "flowx,cell 0 1,grow");
		panelContenidos.add(panelNorte, BorderLayout.NORTH);
		
		JLabel label_2 = new JLabel("Cargar financiación externa: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.add(label_2, "cell 0 1");
		
		Luz luzCargar = new Luz();
		panelNorte.add(luzCargar, "cell 1 1");
		luzCargar.addEncendidoListener(crearListenerLuzFinanciacion());
		
		
	
	}
	
	public void setPanel(Panel jp){
		//panelContenidos.setVisible(false);
		//panelContenidos = jp;
		//panelContenidos.setVisible(true);
		//panelContenidos.updateUI();
		panelContenidos.add(jp);
		panelContenidos.updateUI();
	}

	private IEncendidoListener crearListenerLuzFinanciacion(){
		return new IEncendidoListener() {
			@Override
			public void enteradoCambioEncendido(EventObject eventObject) {
				Luz pulsador = (Luz)eventObject.getSource();
				if (pulsador.isEncendido()) {
					JFileChooser fileChooser = new JFileChooser();
					int returnVal = fileChooser.showOpenDialog(panelContenidos);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						ComponenteCargadorFinanciacion cargador = new ComponenteCargadorFinanciacion();
						Controlador.getUnicaInstancia().setComponenteFinanciacion(cargador);
						cargador.asignarArchivo(file.getPath());
					}
				}
			}
		};
	}

}
