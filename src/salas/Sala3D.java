package salas;

import modelo.Pelicula;

public class Sala3D extends SalaCine {
    private static final double RECARGO_GAFAS = 2.00;

    public Sala3D(int numeroSala, int capacidad, double precioBase, Pelicula pelicula) {
        super(numeroSala, capacidad, precioBase, pelicula);
    }

    @Override
    public double calcularCostoEntrada() {
        return getPrecioBase() + RECARGO_GAFAS;

    }
}