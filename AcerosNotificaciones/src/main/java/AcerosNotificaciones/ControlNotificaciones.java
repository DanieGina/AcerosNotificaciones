/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcerosNotificaciones;

import AcerosNotificaciones.Modelos.Notificacion;
import AcerosNotificaciones.Modelos.Notificaciones;
import AcerosNotificaciones.Modelos.ResponseNotificacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ProtocolException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Ginna
 */
public class ControlNotificaciones {

    private final static Logger LOG_RAIZ = Logger.getLogger("Notificaciones");

    public void ProcesarNotificaciones() {
        try {
            Handler fileHandler = new FileHandler("./log/Notificaciones.log", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOG_RAIZ.addHandler(fileHandler);
            fileHandler.setLevel(Level.ALL);

            LOG_RAIZ.log(Level.INFO, "Lectura de notificaciones inicializada");

            Notificaciones lstNotificaciones = ObtenerNotificaciones();

            if (lstNotificaciones == null) {
                LOG_RAIZ.log(Level.WARNING, "No se obtuvieron notificaciones valide el log");
            }

            lstNotificaciones.NotifyConfigurationTableCollection.forEach((var notificacion) -> {
                if (ValidarEnvioNotificacion(notificacion)) {
                    ResponseNotificacion resultado = ProcesarNotificacion(notificacion);
                    if (resultado.Error) {
                        LOG_RAIZ.log(Level.WARNING, "Error de servicio: notifyconfigurationid={0} {1}", new Object[]{Integer.toString(notificacion.NotifyConfigurationId), resultado.ErrorDsc});
                    } else {
                        ActualizarNotificacion(notificacion.NotifyConfigurationId);
                    }
                }
            });
        } catch (Exception e) {
            LOG_RAIZ.log(Level.SEVERE, getStackTrace(e));
        }
    }

    private Notificaciones ObtenerNotificaciones() throws ProtocolException, IOException {
        RestClient res = new RestClient();

        try {
            String notificacionesJson = res.GetObjectRest_GetMethod(ObtenerUrlNotificaciones());

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(notificacionesJson, Notificaciones.class);

        } catch (JsonProcessingException e) {
            LOG_RAIZ.log(Level.SEVERE, getStackTrace(e));
        }
        return null;
    }

    private ResponseNotificacion ProcesarNotificacion(Notificacion notificacion) {
        ResponseNotificacion resultado = EnviarNotificacion(notificacion.NotifyConfigurationId);
        return resultado;
    }

    private ResponseNotificacion EnviarNotificacion(int notifyconfigurationid) {
        RestClient res = new RestClient();

        try {
            String respuestaJson = res.GetObjectRest_GetMethod(ObtenerUrlEnviarNotificacion(notifyconfigurationid));

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(respuestaJson, ResponseNotificacion.class);

        } catch (IOException e) {
            LOG_RAIZ.log(Level.SEVERE, getStackTrace(e));
        }
        return null;
    }

    private void ActualizarNotificacion(int notifyconfigurationid) {
        RestClient res = new RestClient();
        try {
            String respuestaJson = res.GetObjectRest_GetMethod(ObtenerUrlActualizarNotificacion(notifyconfigurationid));

            ObjectMapper objectMapper = new ObjectMapper();

            var resultado = objectMapper.readValue(respuestaJson, ResponseNotificacion.class);
            if (resultado.Error) {
                LOG_RAIZ.log(Level.WARNING, "Error de servicio: notifyconfigurationid={0} {1}", new Object[]{Integer.toString(notifyconfigurationid), resultado.ErrorDsc});
            }

        } catch (IOException e) {
            LOG_RAIZ.log(Level.SEVERE, getStackTrace(e));
        }
    }

    private boolean ValidarEnvioNotificacion(Notificacion notificacion) {

        if (!notificacion.Active) {
            return false;
        }

        if (notificacion.ValidateHour) {
            Date now = new Date();
            return (now.getTime() - notificacion.DateConfiguration.getTime() > 0);
        }

        return true;

    }

    private String ObtenerUrlNotificaciones() throws IOException {

        return getValueProperties("Url_Api") + "api/notify/GetNotifyConfiguration?username=" + getValueProperties("Usuario_Api") + "&password=" + getValueProperties("Password_Api");

    }

    private String ObtenerUrlEnviarNotificacion(int notifyconfigurationid) throws IOException {

        return getValueProperties("Url_Api") + "api/notify/sendnotification?username=" + getValueProperties("Usuario_Api") + "&password=" + getValueProperties("Password_Api") + "&notifyconfigurationid=" + Integer.toString(notifyconfigurationid);

    }

    private String ObtenerUrlActualizarNotificacion(int notifyconfigurationid) throws IOException {

        return getValueProperties("Url_Api") + "api/notify/updatenotification?username=" + getValueProperties("Usuario_Api") + "&password=" + getValueProperties("Password_Api") + "&notifyconfigurationid=" + Integer.toString(notifyconfigurationid);

    }

    private String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

    private String getValueProperties(String property) throws FileNotFoundException, IOException {

        Properties config = new Properties();
        InputStream configInput;
        configInput = new FileInputStream("Notificaciones.properties");
        config.load(configInput);
        return config.getProperty(property);

    }

}
