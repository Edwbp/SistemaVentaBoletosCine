package salas;

import modelo.Boleto;

/**
 * Sala de cine que proyecta una película.
 * Guarda un arreglo de Boleto: la posición i corresponde al asiento i + 1.
 * Una posición null significa asiento libre.
 */
public class SalaCine {

    // Constante de clase: precio estándar de la entrada
    public static final double PRECIO_ESTANDAR = 5.00;

    // Atributo estático: conteo global de boletos vendidos
    private static int boletosVendidos = 0;

    private String pelicula;
    private Boleto[] asientos;

    /** Constructor: crea la sala validando película y capacidad. */
    public SalaCine(String pelicula, int capacidad) {
        if (pelicula == null || pelicula.trim().isEmpty()) {
            throw new IllegalArgumentException("La película no puede ser nula o vacía.");
        }
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }
        this.pelicula = pelicula;
        this.asientos = new Boleto[capacidad];
    }

    // ----- Consultas -----
    public String getPelicula() {
        return pelicula;
    }

    public int getCapacidad() {
        return asientos.length;
    }

    public static int getBoletosVendidos() {
        return boletosVendidos;
    }

    /** Valida que el asiento esté en el rango 1..capacidad (evita salirse del arreglo). */
    private void validarAsiento(int numero) {
        if (numero < 1 || numero > asientos.length) {
            throw new IllegalArgumentException("Asiento fuera de rango: " + numero
                    + " (válido: 1 a " + asientos.length + ").");
        }
    }

    public boolean estaDisponible(int asiento) {
        validarAsiento(asiento);
        return asientos[asiento - 1] == null;
    }

    /** Cuenta los asientos libres recorriendo el arreglo. */
    public int asientosLibres() {
        int libres = 0;
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i] == null) {
                libres++;
            }
        }
        return libres;
    }

    public Boleto getBoleto(int asiento) {
        validarAsiento(asiento);
        return asientos[asiento - 1];
    }

    // ----- Operaciones -----
    /** Reserva un asiento. @return true si se reservó; false si ya estaba ocupado. */
    public boolean reservar(int asiento) {
        validarAsiento(asiento);
        if (asientos[asiento - 1] != null) {
            return false;
        }
        asientos[asiento - 1] = new Boleto(pelicula, asiento, PRECIO_ESTANDAR);
        boletosVendidos++;
        return true;
    }

    /** Cancela una reserva. @return true si se canceló; false si ya estaba libre. */
    public boolean cancelar(int asiento) {
        validarAsiento(asiento);
        if (asientos[asiento - 1] == null) {
            return false;
        }
        asientos[asiento - 1] = null;
        boletosVendidos--;
        return true;
    }

    /** Suma el precio de los boletos vendidos en esta sala. */
    public double calcularTotalRecaudado() {
        double total = 0;
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i] != null) {
                total += asientos[i].getPrecio();
            }
        }
        return total;
    }

    /** Muestra el mapa de la sala: número = libre, X = ocupado. */
    public void mostrarAsientos() {
        System.out.println("Sala: " + pelicula);
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i] == null) {
                System.out.print("[" + (i + 1) + "] ");
            } else {
                System.out.print("[X] ");
            }
        }
        System.out.println();
    }
}