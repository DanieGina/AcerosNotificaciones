/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcerosNotificaciones;

import AcerosNotificaciones.Modelos.Notificacion;
import AcerosNotificaciones.Modelos.Notificaciones;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.function.Consumer;

/**
 *
 * @author Ginna
 */
public class ControlNotificaciones {

    public void ProcesarNotificaciones() {
        try {
            Notificaciones lstNotificaciones = ObtenerNotificaciones();

            lstNotificaciones.NotifyConfigurationTableCollection.forEach((Notificacion notificacion) -> {
                System.out.println(notificacion.UserGuid);
            });
        } catch (Exception e) {

        }
    }

    private Notificaciones ObtenerNotificaciones() {
        RestClient res = new RestClient();

        try {
            String notificacionesJson = res.GetObjectRest_GetMethod("http://www.acerosviajes.com.mx/api/Notify/GetNotifyConfiguration?username=admin&password=Aceros123*");

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(notificacionesJson, Notificaciones.class);

        } catch (Exception e) {

        }
        return null;
    }
}
