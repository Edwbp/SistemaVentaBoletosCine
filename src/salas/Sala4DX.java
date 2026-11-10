package salas;

import modelo.Pelicula;

public class Sala4DX extends SalaCine {
    private static final double PORCENTAJE_RECARGO = 0.30;

    public Sala4DX(int numeroSala, int capacidad, double precioBase, Pelicula pelicula) {
        super(numeroSala, capacidad, precioBase, pelicula);
    }

    @Override
    public double calcularCostoEntrada() {
        return getPrecioBase() * (1 + PORCENTAJE_RECARGO);
    }

}