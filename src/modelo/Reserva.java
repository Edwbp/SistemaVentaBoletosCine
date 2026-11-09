package modelo;

public class Reserva {


    private String codigoReserva;
    private String pelicula;
    private int sala;
    private int asiento;
    private double montoTotal;
    private String cliente;

    /**
     * constructor de la clase Reserva
     * setters se scriben y se validadn una sola vez por eso se llaman de un solo get.
     */
    public Reserva(String codigoReserva, String pelicula, int sala,
                      int asiento, double montoTotal, String Cliente) {
            setCodigoReserva(codigoReserva);
            setPelicula(pelicula);
            setSala(sala);
            setAsiento(asiento);
            setMontoTotal(montoTotal);
            setCliente(cliente);
            
        }

        /**
         * Getters
         */

        public String getCodigoReserva () {
            return codigoReserva;

        }

        public String getPelicula () {
            return pelicula;

        }

        public int getSala () {
            return sala; 
        }
        public int getAsiento () {
            return asiento;
        }
         
       public double getMontoTotal () {
             return montoTotal;
        }
    
        public String getCliente () {
            return cliente;
        }
/** 
 * Setters con validacion
 */

    public void setCodigoReserva(String codigoReserva) {
        if (esVacio(codigoReserva)){
            throw new IllegalArgumentException( "El codigo de reserva no puede estar vacio");
        }
        this.codigoReserva = codigoReserva;

    }

    public void setPelicula(String pelicula) {
        if (esVacio(pelicula)){
            throw new IllegalArgumentException( "La pelicula no puede estar vacia");
        }
        this.pelicula = pelicula;

    }

    public void setSala(int sala) {
        if (sala < 1 ) {  // no puede ser menor a 1 porque si no no existe la sala sin sala no hay pelicula
            throw new IllegalArgumentException( "El numero de la sala debe ser mayor o igual a 1");
        }
        this.sala = sala;

        }

    public void setAsiento(int asiento) {
            if (asiento < 1 ) { // sin asiento no hay clientes..
                throw new IllegalArgumentException( "El numero de asiento debe ser mayor o igual a 1");
            }
            this.asiento = asiento;
        
        }

    public void setMontoTotal(double montoTotal) {
            if (montoTotal <= 0 ) { 
                throw new IllegalArgumentException( "El Monto Total debe ser mayor que 0");
            }
            this.montoTotal = montoTotal;
        }

        public void setCliente(String cliente) {
            if (esVacio(cliente)) { 
                throw new IllegalArgumentException( "El cliente no puede estar vacio");
            }
            this.cliente = cliente;
        }

    private boolean esVacio(String texto) {
        return  texto == null || texto.trim().isEmpty();
    }

    @Override 
    public String toString() {
        return  "Reserva [codigo=" + codigoReserva + ", pelicula=" + pelicula
                + ", sala=" + sala + ", asiento=" + asiento
                + ", monto=$" + String.format("%.2f", montoTotal)
                + ", cliente=" + cliente + "]";

        }
}