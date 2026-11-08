package app.reservas;

import modelo.Boleto;
import salas.SalaCine;

/** Programa de prueba del sistema de reservas de cine. */
public class Main {

    public static void main(String[] args) {

        // Creación de la sala
        SalaCine sala = new SalaCine("La Era de Ultron", 20);
        System.out.println("***** Sala creada *****");
        sala.mostrarAsientos();

        // Prueba de reserva de boletos
        System.out.println("\n**** Prueba de compra: reservar asientos 1, 2 y 10 ****");
        reservarYMostrar(sala, 1);
        reservarYMostrar(sala, 2);
        reservarYMostrar(sala, 10);
        mostrarResumen(sala);

        // Asiento ocupado
        System.out.println("\n**** Asiento ocupado: reservar el asiento 10 otra vez ****");
        reservarYMostrar(sala, 10);

        // Asientos fuera de rango
        System.out.println("\n**** Fuera de rango: asientos 0 y 25 ****");
        try {
            sala.reservar(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
        try {
            sala.reservar(25);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        // Cancelaciones
        System.out.println("\n**** Cancelación: cancelar el asiento 10 dos veces ****");
        cancelarYMostrar(sala, 10);
        cancelarYMostrar(sala, 10);
        mostrarResumen(sala);

        // Validación de Boleto
        System.out.println("\n**** Validación de Boleto: datos inválidos ****");
        try {
            new Boleto("La avispa", 3, -5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        System.out.println("\n**** Boleto del asiento 1 ****");
        System.out.println(sala.getBoleto(1));

        System.out.println("\n**** Estado final ****");
        mostrarResumen(sala);
    }

    /** Intenta reservar un asiento y muestra el resultado. */
    private static void reservarYMostrar(SalaCine sala, int asiento) {
        if (sala.reservar(asiento)) {
            System.out.println("Asiento " + asiento + " reservado con éxito.");
        } else {
            System.out.println("El asiento " + asiento + " ya está ocupado.");
        }
    }

    /** Intenta cancelar un asiento y muestra el resultado. */
    private static void cancelarYMostrar(SalaCine sala, int asiento) {
        if (sala.cancelar(asiento)) {
            System.out.println("Reserva del asiento " + asiento + " cancelada.");
        } else {
            System.out.println("El asiento " + asiento + " ya estaba libre.");
        }
    }

    /** Imprime el mapa de asientos y las estadísticas de la sala. */
    private static void mostrarResumen(SalaCine sala) {
        sala.mostrarAsientos();
        System.out.println("Asientos libres: " + sala.asientosLibres());
        System.out.println("Boletos vendidos: " + SalaCine.getBoletosVendidos());
        System.out.println("Total recaudado: $" + String.format("%.2f", sala.calcularTotalRecaudado()));
    }
}