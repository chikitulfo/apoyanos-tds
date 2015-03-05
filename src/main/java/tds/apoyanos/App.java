package tds.apoyanos;

import tds.apoyanos.controlador.Controlador;
import tds.apoyanos.vista.LoginW;

import java.awt.*;

public class App {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	//Chapuza usuario prueba
                	Controlador.getUnicaInstancia().registrarUsuario("a", "a", "a", "a", "a", "a");
                    LoginW ventana= new LoginW();
                    ventana.mostrarVentana();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
