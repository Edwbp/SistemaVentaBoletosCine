package notificaciones;

import java.util.ArrayList;
import java.util.List;
import modelo.Reserva;

public class GestorNotificacionesCine {
private List<CanalNotificacion> canalesRegistrados;

    public GestorNotificacionesCine() {
        this.canalesRegistrados = new ArrayList<>();
    }

    public boolean RegistrarCanal(CanalNotificacion canal) {
        if (canal == null || canalesRegistrados.contains(canal)) {
            return false;
        }
        canalesRegistrados.add(canal);
        return true;
    }

    public boolean removerCanal(CanalNotificacion canal) {
        return canalesRegistrados.remove(canal);
        }
        
    public int getCantidadCanales() {
        return canalesRegistrados.size();
    }

    /**
     * reserva sobre un canal espesifico
     */
    public void despacharNotificacion(Reserva reserva, CanalNotificacion canal) {
        intentarEnviar (reserva , canal);
        }
/**
 * confirmacion simultanea por multiples canales 
 */

    public void despacharNotificacion(Reserva reserva, List<CanalNotificacion> canales) {
        if (canales == null || canales.isEmpty()) {
            System.out.println("[GESTOR] no hay canales para despachar.");
            return;
        }
        int enviados = 0;
        for (CanalNotificacion canal : canales) {
            if (intentarEnviar(reserva, canal)) {
                enviados++;
            }
        }
        System.out.println("[GESTOR] Enviados: " + enviados + " de " + canales.size() + "canales.");
                
        
    }

/**
 * procesar reservas macivas
 */

    public void despacharNotificacion(List<Reserva> reservasGrupales, CanalNotificacion canal) {
        if ( reservasGrupales == null || reservasGrupales.isEmpty()){
            System.out.println("[GESTOR] No hay reservas para despachar");
            return;
        }
        int enviados = 0;
        for (Reserva reserva : reservasGrupales) {
            if (intentarEnviar(reserva, canal)) {
                enviados++;
            }
        }
        System.out.println("[GESTOR] Enviados: " + enviados + " de " + reservasGrupales.size() + "reservas.");
    }


    private  boolean intentarEnviar(Reserva reserva, CanalNotificacion canal) {
        if (reserva == null || canal == null) {
            System.out.println("[GESTOR] Reserva o canal nulo: no se envia.");
            return false;
            }
        if (!canalesRegistrados.contains(canal)) {
            System.out.println("[GESTOR] Canal no registrado" + nombreDe(canal) + ". No se envia.");
            return false;
            }
        try {
            canal.enviarConfirmacion(obtenerDestinatario(reserva), construirMensaje(reserva));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[GESTOR] no se puede enviar por " + nombreDe(canal)+ ": " + e.getMessage());
            return false;
            }
        }

    private String obtenerDestinatario(Reserva reserva) {
        return reserva.getCliente();
            }

    private String construirMensaje(Reserva reserva) {
        return "Reserva " + reserva.getCodigoReserva() + " cofirmada : " + reserva.getPelicula()
         + ", sala " + reserva.getSala() + ", asiento " + reserva.getAsiento()
         + ", Total : $" + String.format("%.2f", reserva.getMontoTotal());
    }
    private String nombreDe(CanalNotificacion canal) {
        return canal.getClass().getSimpleName();
    }
    
}


    

    