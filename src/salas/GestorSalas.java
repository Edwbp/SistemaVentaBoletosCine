package salas;

import java.util.ArrayList;
import java.util.List;

public class GestorSalas {

    private List<SalaCine> salas;

    public GestorSalas() {
        this.salas = new ArrayList<>();
    }
/** agregar salas */
    public boolean agregarSala(SalaCine sala) {
        if (sala == null || buscarSala(sala.getNumeroSala()) != null) {
            return false;
        }
        salas.add(sala);
        return true;
    }
    /** buscar salas */
    public  SalaCine buscarSala(int numeroSala) {
        for (SalaCine sala : salas ) {
            if (sala.getNumeroSala() == numeroSala ) {
                return sala;
            }
        }
        return null;
    }
    public int getCantidadSalas() {
        return salas.size();
    }

    /** vender entradas de forma polimorfica  */

    public boolean venderEntrada(int numeroSala, int numeroAsiento ) {
        SalaCine sala = buscarSala(numeroSala);
        if (sala == null) {
            System.out.println( " [GESTOR] La sala " + numeroSala+ "no existe.");
            return false;
        }
        try {
            if (sala.reservar(numeroAsiento)) {
                System.out.println( "[GESTOR] OK " + sala + ", asiento" + numeroAsiento
                    + ": $ " +String.format("%2f", sala.calcularCostoEntrada()));
                    return  true;
            }
            System.out.println("[GESTOR] El asiento"+ numeroAsiento + "de la sala"
                +numeroSala + "ya esta ocupado.");
                return false;
        } catch (IllegalArgumentException e) {
            System.out.println("[GESTOR] no se puede vender: " +  e.getMessage());
            return false;
            }

    }
    public void mostrarTarifas() {
        for (SalaCine sala : salas) {
            System.out.println(sala + " -> $" + String.format("%2f", sala.calcularCostoEntrada()));
        }
    }

    public double calcularRecaudacionTotal() {
        double total = 0;
        for (SalaCine sala : salas) {
            total += sala.calcularTotalRecaudado();
        }
        return total;
    }
}
