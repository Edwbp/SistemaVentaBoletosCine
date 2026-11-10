package salas;

import modelo.Pelicula;

public class SalaVIP extends SalaCine {
    private static final double COSTO_CATERING = 5.00;

    public SalaVIP(int numeroSala, int capacidad, double precioBase, Pelicula pelicula) {
        super(numeroSala, capacidad, precioBase, pelicula);
    }

    @Override
    public double calcularCostoEntrada() {
        return getPrecioBase() + COSTO_CATERING;
    }

}