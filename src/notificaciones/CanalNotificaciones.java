package notificaciones;

public interface CanalNotificaciones {

    /**
     * envia la confirmacion al destinatario espesifico
     * @param mensaje texto de la confirmacion de la reserva
     * @param destinatario direccion, correo electronico. numero o token 
     * @throws IllegalArgumentException si el mensaje o el destinatario es null
     */
    
    void enviarConfirmcione(String mensaje, String destinatario);
}
