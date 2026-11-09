package notificaciones;

/**
 * Canal de notificación Push (aplicación móvil).
 * SRP: valida el token del dispositivo y envía la notificación push.
 */
public class NotificacionPush implements CanalNotificacion {

    /** SOBREESCRITURA del método del contrato, con la lógica propia del Push. */
    @Override
    public void enviarConfirmacion(String destinatario, String mensaje) {
        if (!esTokenValido(destinatario)) {
            throw new IllegalArgumentException(
                "Token Push inválido (mínimo 8 caracteres: letras, números o guiones): " + destinatario);
        }
        System.out.println("[PUSH] Dispositivo: " + destinatario + " | " + mensaje);
    }

    /** Formato válido: 8 o más caracteres entre letras, números y guiones. */
    private boolean esTokenValido(String destinatario) {
        return destinatario != null && destinatario.matches("[A-Za-z0-9-]{8,}");
    }
}