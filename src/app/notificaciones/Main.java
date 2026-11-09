package app.notificaciones;

import java.util.ArrayList;
import java.util.List;
import modelo.Reserva;
import notificaciones.CanalNotificacion;
import notificaciones.GestorNotificacionesCine;
import notificaciones.NotificacionEmail;
import notificaciones.NotificacionesSMS;
import notificaciones.NotificacionPush;
import notificaciones.NotificacionWhatsApp;


public class Main {
    public static void main (String [] args) {
        GestorNotificacionesCine gestor = new GestorNotificacionesCine();
        CanalNotificacion email = new NotificacionEmail();
        CanalNotificacion sms = new NotificacionesSMS();
        CanalNotificacion push = new NotificacionPush();
        CanalNotificacion Whatsapp = new NotificacionWhatsApp();

        gestor.RegistrarCanal(email);
        gestor.RegistrarCanal(sms);
        gestor.RegistrarCanal(push);
        gestor.RegistrarCanal(Whatsapp);
        System.out.println("Canales Registrados:" + gestor.getCantidadCanales());

        Reserva r1 = new Reserva (" R-001", "Avengers" , 5 , 5 , 5.00,"Jesus@gmail.com" );
        Reserva r2 = new Reserva (" R-002", "Avatar" , 2 , 10 ,5.00,"Evelyns@gmail.com" );
        Reserva r3 = new Reserva (" R-003", "Sherk" , 3 , 7 , 5.00,"+593123456789" );
        Reserva r4 = new Reserva (" R-004", "Kunfu Panda" , 6 , 2 , 5.00,"Carlos@gmail.com" );
        Reserva r5 = new Reserva (" R-005", "Bob Esponja" , 4 , 3 , 5.00,"Arturito@gmail.com" );

/**
 * prueba de reserva y un canal email
 */
        System.out.println("\n === Prueba 1: envio individual por email ===");
        gestor.despacharNotificacion(r5, email);
/**
 * prueba de una reserva varios canales a la vez
 */
        System.out.println("\n === Prueba 2: multicanal con telefono como destinatario ===");
        List<CanalNotificacion> todos = new ArrayList<>();
        todos.add(email);
        todos.add(sms);
        todos.add(push);
        todos.add(Whatsapp);
        gestor.despacharNotificacion(r3 , todos);

/**
 * varias reservas un canal reserva grupal 
 */
        System.out.println("\n === Prueba 3: reserva grupal por email ===");
        List<Reserva> grupo = new ArrayList<>();
        grupo.add(r1);
        grupo.add(r2);
        grupo.add(r5);
        grupo.add(r3);
        gestor.despacharNotificacion(grupo , email);

/**
 * destinatarios con formato invalido 
 */
        System.out.println("\n === Prueba 4: Destinatario con formato invalido ===");
        Reserva r6 = new Reserva("R-006", "GJ", 1, 4, 5.00, "jesus.gmail.com");
        gestor.despacharNotificacion(r6, email);

/**
 * remocion de canales 
 */
    System.out.println("\n === Prueba 5: Registro de canal y luego removido ===");
    Reserva r7 = new Reserva("R-007", "COCO", 12, 44, 5.00, "TOKEN-ABCD12345");
    gestor.despacharNotificacion(r7, push);
    System.out.println("Remover Push: "+ gestor.removerCanal(push));
    gestor.despacharNotificacion(r7, push);
    
    
/**
 * datos invalidos al crear una reserva
 */

    System.out.println("\n === Prueba 6: Rreserva con datos invalidos ===");
    try {
            new Reserva("R-008", "UP", -1, 2 , 5, "mar@gmail.com");
        }catch (IllegalArgumentException e) {
            System.out.println("Error encontrado: " + e.getMessage());
            }
    System.out.println("\n Canales registrados al final: " + gestor.getCantidadCanales());
    }

}
