package tds.apoyanos.vista;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

public class Menu {

    JMenuBar menuBar = new JMenuBar();

    public Menu(final JFrame jf){
    //public Menu(final VentanaIntroApoyanos jf){
        //MENÚ

                jf.setJMenuBar(menuBar);

                JPanel panelIcon = new JPanel();
                panelIcon.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                panelIcon.setBorder(null);
                panelIcon.setBackground(Color.WHITE);
                panelIcon.setToolTipText("\n");
                menuBar.add(panelIcon);
                panelIcon.setLayout(new GridLayout(0, 1, 0, 0));

                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
                lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/recursos/apoyanos_75aire-50.png")));
                //lblNewLabel.setIcon(new ImageIcon("/Volumes/HD-Datos/Dropbox/Universidad/workspace/apoyanos/src/main/java/recursos/apoyanos_75aire-50.png"));
                panelIcon.add(lblNewLabel);

                JMenu mnProyectosEnVotacion = new JMenu("Votación");
                mnProyectosEnVotacion.setHorizontalAlignment(SwingConstants.LEFT);
                menuBar.add(mnProyectosEnVotacion);

                JMenuItem mntmTodos = new JMenuItem("Todos");
                mntmTodos.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Todos");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmTodos);

                JMenuItem mntmMusica = new JMenuItem("Música");
                mntmMusica.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Música");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmMusica);


                JMenuItem mntmLibros = new JMenuItem("Libros");
                mntmLibros.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Libros");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmLibros);

                JMenuItem mntmCine = new JMenuItem("Cine");
                mntmCine.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Cine");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmCine);

                JMenuItem mntmSocial = new JMenuItem("Social");
                mntmSocial.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Social");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmSocial);

                JMenuItem mntmSoftware = new JMenuItem("Software");
                mntmSoftware.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Software");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmSoftware);

                JMenuItem mntmDeportes = new JMenuItem("Deportes");
                mntmDeportes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("VOTACIÓN","Deportes");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnVotacion.add(mntmDeportes);

                JMenu mnProyectosEnFinanciacion = new JMenu("Financiación");
                menuBar.add(mnProyectosEnFinanciacion);

                JMenuItem mntmTodosV = new JMenuItem("Todos");
                mntmTodosV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Todos");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmTodosV);

                JMenuItem mntmMusicaV = new JMenuItem("Música");
                mntmMusicaV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Música");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmMusicaV);

                JMenuItem mntmLibrosV = new JMenuItem("Libros");
                mntmLibrosV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Libros");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmLibrosV);

                JMenuItem mntmCineV = new JMenuItem("Cine");
                mntmCineV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Cine");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmCineV);

                JMenuItem mntmSocialV = new JMenuItem("Social");
                mntmSocialV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Social");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmSocialV);

                JMenuItem mntmSoftwareV = new JMenuItem("Software");
                mntmSoftwareV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Software");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmSoftwareV);

                JMenuItem mntmDeportesV = new JMenuItem("Deportes");
                mntmDeportesV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal = new VentanaPrincipalApoyanos("FINANCIACIÓN","Deportes");
                        ventanaPrincipal.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                mnProyectosEnFinanciacion.add(mntmDeportesV);

                JButton btnCrearNuevoProyecto = new JButton("Nuevo Proyecto");
                btnCrearNuevoProyecto.setFocusPainted(false);
                btnCrearNuevoProyecto.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        VentanaCrearProyecto ventanaCrearProyecto = new VentanaCrearProyecto();
                        ventanaCrearProyecto.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                menuBar.add(btnCrearNuevoProyecto);

                JButton btnApoyos = new JButton("Apoyos");
                btnApoyos.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        VentanaApoyos ventanaApoyos = new VentanaApoyos();
                        ventanaApoyos.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                btnApoyos.setFocusable(false);
                menuBar.add(btnApoyos);

                JButton btnNotificaciones = new JButton("Notificaciones");
                btnNotificaciones.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        VentanaNotificaciones ventanaNotificaciones = new VentanaNotificaciones();
                        ventanaNotificaciones.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                menuBar.add(btnNotificaciones);

                JButton btnPreguntas = new JButton("Preguntas");
                btnPreguntas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        VentanaPreguntasRespuestas ventanaPreguntasRespuestas = new VentanaPreguntasRespuestas();
                        ventanaPreguntasRespuestas.setVisible(true);
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    }
                });
                menuBar.add(btnPreguntas);

                JButton btnSalir = new JButton("Salir");
                btnSalir.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                            //frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
                            System.exit(0);  /*no sería necesario en este caso*/
                    }
                });

                menuBar.add(btnSalir);

    }

}