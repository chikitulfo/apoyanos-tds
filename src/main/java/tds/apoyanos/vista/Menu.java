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

import tds.apoyanos.exceptions.InvalidArgumentException;

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
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Todos");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmTodos);

                JMenuItem mntmMusica = new JMenuItem("Música");
                mntmMusica.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Música");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmMusica);


                JMenuItem mntmLibros = new JMenuItem("Libros");
                mntmLibros.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Libros");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                });
                mnProyectosEnVotacion.add(mntmLibros);

                JMenuItem mntmCine = new JMenuItem("Cine");
                mntmCine.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Cine");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmCine);

                JMenuItem mntmSocial = new JMenuItem("Social");
                mntmSocial.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Social");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmSocial);

                JMenuItem mntmSoftware = new JMenuItem("Software");
                mntmSoftware.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Software");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmSoftware);

                JMenuItem mntmDeportes = new JMenuItem("Deportes");
                mntmDeportes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Votación","Deportes");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnVotacion.add(mntmDeportes);

                JMenu mnProyectosEnFinanciacion = new JMenu("Financiación");
                menuBar.add(mnProyectosEnFinanciacion);

                JMenuItem mntmTodosV = new JMenuItem("Todos");
                mntmTodosV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Todos");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnFinanciacion.add(mntmTodosV);

                JMenuItem mntmMusicaV = new JMenuItem("Música");
                mntmMusicaV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Música");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnFinanciacion.add(mntmMusicaV);

                JMenuItem mntmLibrosV = new JMenuItem("Libros");
                mntmLibrosV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Libros");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnFinanciacion.add(mntmLibrosV);

                JMenuItem mntmCineV = new JMenuItem("Cine");
                mntmCineV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Cine");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnFinanciacion.add(mntmCineV);

                JMenuItem mntmSocialV = new JMenuItem("Social");
                mntmSocialV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Social");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                });
                mnProyectosEnFinanciacion.add(mntmSocialV);

                JMenuItem mntmSoftwareV = new JMenuItem("Software");
                mntmSoftwareV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Software");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                });
                mnProyectosEnFinanciacion.add(mntmSoftwareV);

                JMenuItem mntmDeportesV = new JMenuItem("Deportes");
                mntmDeportesV.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        VentanaPrincipalApoyanos ventanaPrincipal;
						try {
							ventanaPrincipal = new VentanaPrincipalApoyanos("Financiación","Deportes");
	                        ventanaPrincipal.setVisible(true);
	                        jf.setVisible(false); //you can't see me!
	                        jf.dispose(); //Destroy the JFrame object
						} catch (InvalidArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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

                JButton btnSalir = new JButton("LogOut");
                btnSalir.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	LoginW ventana= new LoginW();
                        ventana.mostrarVentana();
                        jf.setVisible(false); //you can't see me!
                        jf.dispose(); //Destroy the JFrame object
                    	
                        //frame.dispose(); /*cuando se destruye la última ventana termina la maquina virtual*/
                        //System.exit(0);  /*no sería necesario en este caso*/
                    }
                });

                menuBar.add(btnSalir);

    }

}