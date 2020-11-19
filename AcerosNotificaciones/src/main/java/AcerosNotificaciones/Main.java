/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcerosNotificaciones;

import AcerosNotificaciones.Modelos.Notificaciones;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Ginna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ControlNotificaciones ctr = new ControlNotificaciones();
        ctr.ProcesarNotificaciones();

    }

}
