package notificaciones;

/**
 * Canal de notificación por email.
 * Implementa el contrato CanalNotificacion.
 * SRP: su única responsabilidad es validar un teléfono y enviar por ese medio.
 */
public class NotificacionEmail implements CanalNotificacion {


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


