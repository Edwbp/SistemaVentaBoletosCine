package notificaciones;

/**
 * Canal de notificación por WhatsApp.
 * SRP: valida un teléfono en formato internacional y envía por WhatsApp.
 */
public class NotificacionWhatsApp implements CanalNotificacion {

    /** SOBREESCRITURA del método del contrato, con la lógica propia de WhatsApp. */
    @Override
    public void enviarConfirmacion(String destinatario, String mensaje) {
        if (!esTelefonoValido(destinatario)) {
            throw new IllegalArgumentException(
                "Número de WhatsApp inválido (formato +593 y 9 digitos): " + destinatario);
        }
        System.out.println("[WHATSAPP] A: " + destinatario + " | " + mensaje);
    }

    /** Formato válido: + seguido de 10 a 15 dígitos (código de país incluido). */
    private boolean esTelefonoValido(String destinatario) {
        return destinatario != null && destinatario.matches("\\+593\\d{9}");
    }
}