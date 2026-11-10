package salas;

import modelo.Boleto;

public class Asiento {
    private int numero;
    private Boleto boleto;

    Asiento(int numero) {
        if (numero < 1) {
            throw new IllegalArgumentException("El numero de asientos debe ser mayor o igual a 1. ");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean estaLibre() {
        return boleto == null;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    void ocupar(Boleto boleto) {
        this.boleto = boleto;
    }

    void liberar() {
        this.boleto = null;
    }
}