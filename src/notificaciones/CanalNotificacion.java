package notificaciones;

public interface CanalNotificacion {

    /**
     * envia la confirmacion al destinatario especifico.
     * @param mensaje texto de la confirmacion de la reserva
     * @param destinatario direccion, correo electronico. numero o token 
     * @throws IllegalArgumentException si el mensaje o el destinatario es null
     */
    
    void enviarConfirmacion(String Destinatario, String mensaje);
}
