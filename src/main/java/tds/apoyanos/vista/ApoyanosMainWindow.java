package tds.apoyanos.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApoyanosMainWindow extends JFrame {

	private JPanel contentPane;
	
	public ApoyanosMainWindow() {
		setTitle("Apoyanos Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblBienvenidosAApyanos = new JLabel("Bienvenidos a Apóyanos");
		lblBienvenidosAApyanos.setFont(new Font("Arial", Font.PLAIN, 30));
		lblBienvenidosAApyanos.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBienvenidosAApyanos, BorderLayout.CENTER);
	}

}
