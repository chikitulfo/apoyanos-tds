package tds.apoyanos;

import tds.apoyanos.vista.LoginW;

import java.awt.*;

public class App {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginW ventana= new LoginW();
                    ventana.mostrarVentana();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
