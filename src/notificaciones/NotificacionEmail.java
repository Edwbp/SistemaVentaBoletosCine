package notificaciones;


public class NotificacionEmail implements CanalNotificaciones {

    /**
     * envia la confirmacion al destinatario especifico.
     * @param mensaje texto de la confirmacion de la reserva
     * @param destinatario direccion, correo electronico. numero o token 
     * @throws IllegalArgumentException si el mensaje o el destinatario es null
     */
    @Override
    public void enviarConfirmacion(String destinatario, String mensaje) {
        if (!esCorreoValido(destinatario)) {
            throw new IllegalArgumentException("Correo con formato inválido: " + destinatario);
        }
        System.out.println("[EMAIL] Para: " + destinatario + " | " + mensaje);
    }

    /** Validación interna: texto antes de la @ y un punto después de la @. */
    private boolean esCorreoValido(String destinatario) {
        if (destinatario == null || destinatario.contains(" ")) {
            return false;
        }
        int arroba = destinatario.indexOf('@');
        int punto = destinatario.lastIndexOf('.');
        return arroba > 0 && punto > arroba + 1 && punto < destinatario.length() - 1;
    }
}