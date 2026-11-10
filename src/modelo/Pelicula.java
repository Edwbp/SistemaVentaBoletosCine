package modelo;

public class Pelicula {
    private String titulo;
    private int duracionMinutos;
    
    public Pelicula(String titulo, int duracionMinutos) {
          setTitulo(titulo);
          setDuracionMinutos(duracionMinutos);
    }

    public String getTitulo() {
        return titulo;
    }
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo o vacio.");
        }
        this.titulo= titulo;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 1) {
            throw new IllegalArgumentException("La duracion debe ser mayor a 0");
        }
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
    return titulo + " (" + duracionMinutos + "min)";
    }
}