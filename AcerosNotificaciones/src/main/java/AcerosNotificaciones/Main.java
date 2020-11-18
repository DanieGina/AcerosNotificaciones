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
    public static void main(String[] args) throws JsonProcessingException {
        RestClient res = new RestClient();
        
        String notificacionesJson =res.GetObjectRest_GetMethod("http://www.acerosviajes.com.mx/api/Notify/GetNotifyConfiguration?username=admin&password=Aceros123*");
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        Notificaciones not = objectMapper.readValue(notificacionesJson, Notificaciones.class);
        
        System.out.println(not);
    }
    
}
