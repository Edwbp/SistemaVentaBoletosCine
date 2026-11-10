package salas;

import modelo.Boleto;
import modelo.Pelicula;
/**
 * Sala de cine que proyecta una película.
 * Guarda un arreglo de Boleto: la posición i corresponde al asiento i + 1.
 * Una posición null significa asiento libre.
 */
public class SalaCine {

    // Constante de clase: precio estándar de la entrada
    public static final int CAPACIDAD_MAXIMA = 500;

    // Atributo estático: conteo global de boletos vendidos
    private static int boletosVendidos = 0;

    private int numeroSala;
    private int capacidad;
    private double precioBase;
    private Asiento[] asientos;
    private Pelicula pelicula;


    /** Constructor: crea la sala validando película y capacidad. */
    public SalaCine(int numeroSala, int capacidad, double precioBase, Pelicula pelicula) {
        if (numeroSala < 1) {
            throw new IllegalArgumentException("el numero de sala debe ser mayor o igual a 1.");
        }
        if (capacidad < 1 || capacidad > CAPACIDAD_MAXIMA) {
            throw new IllegalArgumentException("La capacidad debe estar etre 1 y." + CAPACIDAD_MAXIMA + "asientos.");
        }
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio base debe ser mayor que 0");
            }

        this.numeroSala = numeroSala;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.pelicula = pelicula;
        this.asientos = new Asiento[capacidad];
        for (int i=0; i < capacidad; i++) {
            asientos[i] = new Asiento(i + 1);
        }
    }
        /** método abstracto */
        public abstract double calcularCostoEntrada();

        


        /**Getter */
    

    public int getNumeroSala() {
        return numeroSala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public int getCapacidad() {
        return asientos.length;
    }

    public static int getBoletosVendidos() {
        return boletosVendidos;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    /** Valida que el asiento esté en el rango 1..capacidad (evita salirse del arreglo). */
    private void validarAsiento(int numeroAsiento) {
        if (numeroAsiento < 1 || numeroAsiento > capacidad) {
            throw new IllegalArgumentException("Asiento fuera de rango: " + numeroAsiento
                    + " (válido: 1 a " + capacidad + ").");
        }
    }

    public boolean estaDisponible(int numeroAsiento) {
        validarAsiento(numeroAsiento);
        return asientos[numeroAsiento - 1].estaLibre();
    }

    /** Cuenta los asientos libres recorriendo el arreglo. */
    public int asientosLibres() {
        int libres = 0;
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i].estaLibre()) {
                libres++;
            }
        }
        return libres;
    }

    public Boleto getBoleto(int numeroAsiento) {
        validarAsiento(numeroAsiento);
        return asientos[numeroAsiento - 1].getBoleto();
    }

    // ----- Operaciones -----
    /** Reserva un asiento. @return true si se reservó; false si ya estaba ocupado. */
    public boolean reservar(int numeroAsiento) {
        validarAsiento(numeroAsiento);
        Asiento asiento = asientos[numeroAsiento - 1];
        if (!asiento.estaLibre()) {
            return false;
        }
        Boleto boleto = new Boleto(pelicula.getTitulo(), numeroAsiento, calcularCostoEntrada());
        asiento.ocupar(boleto);
        boletosVendidos++;
        return true;
    }

    /** Cancela una reserva. @return true si se canceló; false si ya estaba libre. */
    public boolean cancelar(int numeroAsiento) {
        validarAsiento(numeroAsiento);
        Asiento asiento = asientos[numeroAsiento - 1];
        if (asiento.estaLibre()) {
            return false;
        }
        asiento.liberar();;
        boletosVendidos--;
        return true;
    }

    /** Suma el precio de los boletos vendidos en esta sala. */
    public double calcularTotalRecaudado() {
        double total = 0;
        for (int i = 0; i < asientos.length; i++) {
            if (!asientos[i].estaLibre()) {
                total += asientos[i].getBoleto().getPrecio();
            }
        }
        return total;
    }

    /** Muestra el mapa de la sala: número = libre, X = ocupado. */
    public void mostrarAsientos() {
        System.out.println("Sala: " + numeroSala + ":" + pelicula.getTitulo());
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i].estaLibre()) {
                System.out.print("[" + asientos[i].getNumero() + "] ");
            } else {
                System.out.print("[X] ");
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
    return "Sala" + numeroSala + " [ " + getClass().getSimpleName() + "] " + pelicula.getTitulo();
    }
}