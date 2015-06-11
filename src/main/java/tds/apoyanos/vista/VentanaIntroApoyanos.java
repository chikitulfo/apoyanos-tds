package tds.apoyanos.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import pulsador.Luz;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import net.miginfocom.swing.MigLayout;


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
		JLabel lblAvanzar = new JLabel("Avanzamos un día: ");
		lblAvanzar.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.setLayout(new MigLayout("", "[502px][502px]", "[30px][30px]"));
		panelNorte.add(lblAvanzar, "cell 0 0,grow");
		Luz luzCargar = new Luz();
		panelNorte.add(luzCargar, "flowx,cell 1 0,grow");
		
		
		
		
		
		JLabel label = new JLabel("");
		panelNorte.add(label, "cell 1 0,grow");
		
		JLabel label_1 = new JLabel("");
		panelNorte.add(label_1, "flowx,cell 0 1,grow");
		panelContenidos.add(panelNorte, BorderLayout.NORTH);
		
		JLabel label_2 = new JLabel("Cargar Financiación externa: ");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panelNorte.add(label_2, "cell 0 1");
		
		Luz luz = new Luz();
		panelNorte.add(luz, "cell 1 1");
		
		
	
	}
	
	public void setPanel(Panel jp){
		//panelContenidos.setVisible(false);
		//panelContenidos = jp;
		//panelContenidos.setVisible(true);
		//panelContenidos.updateUI();
		panelContenidos.add(jp);
		panelContenidos.updateUI();
	}

}
