/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcerosNotificaciones;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ginna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            try {

                ControlNotificaciones ctr = new ControlNotificaciones();
                ctr.ProcesarNotificaciones();
                Thread.sleep(ObtenerMiliSegundos());
            } catch (Exception ex) {
            }
        }

    }

    private static int ObtenerMiliSegundos() {

        int segundos = 5;

        Properties config = new Properties();
        InputStream configInput = null;

        try {

            configInput = new FileInputStream("Notificaciones.properties");
            config.load(configInput);
            int tiempo_Ejecucion = Integer.parseInt(config.getProperty("Tiempo_Ejecucion"));
            switch (config.getProperty("Tipo_Parametro_Ejecucion")) {
                case "1":
                    segundos = tiempo_Ejecucion;
                    break;
                case "2":
                    segundos = tiempo_Ejecucion * 60;
                case "3":
                    segundos = tiempo_Ejecucion * 60 * 60;
                    break;
                default:
                    segundos = 5;
            }

        } catch (Exception ex) {
            segundos = 5;
        }

        return segundos * 1000;

    }

}
