package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		panelContenidos.setLayout(new GridLayout(0, 1, 0, 0));
		lblInicioApoyanos = new JLabel("");
		lblInicioApoyanos.setIcon(new ImageIcon(VentanaIntroApoyanos.class.getResource("/recursos/apoyanos.png")));
		lblInicioApoyanos.setBackground(new Color(255, 255, 255));
		lblInicioApoyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblInicioApoyanos.setHorizontalAlignment(SwingConstants.CENTER);
		panelContenidos.add(lblInicioApoyanos);
		//JTextField txtDescripcionRecompensa;
		
		///MENU
		menu_apoyanos = new Menu(this);
		//getContentPane().add(panelContenidos);
		setContentPane(panelContenidos);
	
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
