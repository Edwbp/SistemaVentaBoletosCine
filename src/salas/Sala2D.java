package salas;

import modelo.Pelicula;

public class Sala2D extends SalaCine {

    public Sala2D (int numeroSala, int capacidad, double precioBase, Pelicula pelicula) {

        super (numeroSala, capacidad, precioBase, pelicula);
    }

    @Override
    public double calcularCostoEntrada() {
        return getPrecioBase();

    }
}