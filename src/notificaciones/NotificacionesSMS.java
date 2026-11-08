package notificaciones;

/**
 * Canal de notificación por SMS.
 * Implementa el contrato CanalNotificacion.
 * SRP: su única responsabilidad es validar un teléfono y enviar por ese medio.
 */
public class NotificacionesSMS implements CanalNotificaciones {

    @Override
    public void  enviarConfirmacion(String destinatario, String mensaje) {
        if (!esTelefonooValido(destinatario)) {
            throw new IllegalArgumentException(
                "Telefono SMS invalido(formato +593 y 9 digitos):" + destinatario);
        } 
        System.out.println ("[SMS] A: " + destinatario + " | " + mensaje); 
    }
    private boolean esTelefonoValido(String destinatario) {
        return destinatario != null && destinatario.matches("\\+593\\d{9}");
    }
}