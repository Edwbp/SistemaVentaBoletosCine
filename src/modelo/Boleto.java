package modelo;

/**
 * Representa una entrada individual de cine.
 * Encapsulamiento: atributos privados, acceso mediante getters y setters con validación.
 */
public class Boleto {

    // Atributos privados
    private String pelicula;
    private int numeroAsiento;
    private double precio;

    /** Constructor: reutiliza los setters para validar en un solo lugar. */
    public Boleto(String pelicula, int numeroAsiento, double precio) {
        setPelicula(pelicula);
        setNumeroAsiento(numeroAsiento);
        setPrecio(precio);
    }

    // ----- Getters -----
    public String getPelicula() {
        return pelicula;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public double getPrecio() {
        return precio;
    }

    // ----- Setters con validaciones -----
    public void setPelicula(String pelicula) {
        if (pelicula == null || pelicula.trim().isEmpty()) {
            throw new IllegalArgumentException("La película no puede ser nula o vacía.");
        }
        this.pelicula = pelicula;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        if (numeroAsiento < 1) {
            throw new IllegalArgumentException("El número de asiento no puede ser menor a 1.");
        }
        this.numeroAsiento = numeroAsiento;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio no puede ser menor o igual a 0.");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Boleto [pelicula=" + pelicula + ", numeroAsiento=" + numeroAsiento
                + ", precio=$" + String.format("%.2f", precio) + "]";
    }
}