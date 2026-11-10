package app.Salas;


import modelo.Pelicula;
import salas.GestorSalas;
import salas.Sala2D;
import salas.Sala3D;
import salas.Sala4DX;
import salas.SalaCine;
import salas.SalaVIP;


public class Main {
    
    public static void main (String [] args) {
        /** creacion de peliculas */

        Pelicula one_piece = new Pelicula("One Piece", 200);
        Pelicula Avatar = new Pelicula("Avatar", 200);
        Pelicula coco = new Pelicula("Coco", 200);
        Pelicula Toy_Store = new Pelicula("Toy Store", 200);

        /** creaciones de salas  */

        SalaCine sala2D = new Sala2D(1, 50, 5.00 , one_piece);
        SalaCine sala3D = new Sala3D(2, 50, 8.00 , Avatar);
        SalaCine salaVIP = new SalaVIP(3, 50, 12.00 , coco);
        SalaCine sala4Dx = new Sala4DX(5, 50, 10, Toy_Store);

        /** gestor de salas */

        GestorSalas gestor = new GestorSalas();
        gestor.agregarSala(sala2D);
        gestor.agregarSala(sala3D);
        gestor.agregarSala(salaVIP);
        gestor.agregarSala(sala4Dx);

        System.out.println( "Salas registradas:" + gestor.getCantidadSalas());

        /** Prueba 1 calcular costo entrada  */

        System.out.println("\n === Prueba 1 : Precios por tipo de sala (precio base $5.00)===");
        gestor.mostrarTarifas();


        /** venta de entradas por cada tipo de salas */

        System.out.println("\n === Prueba 2 : una entrada en cada sala ===");
        gestor.venderEntrada(1, 5);
        gestor.venderEntrada(2, 20);
        gestor.venderEntrada(3, 15);
        gestor.venderEntrada(5, 15);
        gestor.venderEntrada(5, 25);
        gestor.venderEntrada(50, 12);

        /** asientos ocupados */

        System.out.println("\n === Prueba 3 : asientos ocupados ===");
        gestor.venderEntrada(2, 20);


        /** venta de asientos fuera dle rango disponible */

        System.out.println("\n === Prueba 4 : asientos fuera de rango ===");
        gestor.venderEntrada(2, 51);
        gestor.venderEntrada(1, 55);

        /** prueba de salas que no existe */

        System.out.println("\n === Prueba 5 : salas inexistentes ===");
        gestor.venderEntrada(5, 1);


        /** prueba del constructor valores erroneso o insconsistentes */

        System.out.println("\n === Prueba 6 : salas con datos invalidos ===");
        try {
            new Sala2D(-2, 20, 5, coco);
        }catch(IllegalArgumentException e) {
            System.out.println("error capturado: "+ e.getMessage());
        }

        try {
            new Sala3D(2, 20, 5, coco);
        }catch(IllegalArgumentException e) {
            System.out.println("error capturado: "+ e.getMessage());
        }
        try {
                new SalaVIP(3, 2000, 5, Avatar);
            }catch(IllegalArgumentException e) {
                System.out.println("error capturado: "+ e.getMessage());

    }
    /** prueba final recaudacion total informacio o mapeo de sala */

    System.out.println("\n === Prueba 7 : mapeo de la sala 4 DX  y recaudacion total ===");
    sala4Dx.mostrarAsientos();
    System.out.println("Boletos vendidos" + SalaCine.getBoletosVendidos());
    System.out.println("Recaudacion TOtal:" + String.format("%.2f", gestor.calcularRecaudacionTotal()));
}
}
