package util.core.ui;

import util.core.metadata.TipoEntidad;

import java.util.Scanner;

public class SelectorBaseDatos {

    public static TipoEntidad seleccionarEntidad(Scanner teclado) {
        while(true) {
            System.out.println("====== Sistema de registros ======");
            System.out.println("\t1.- Base de datos [Turistas]");
            System.out.println("\t2.- Base de datos [guía turistico]");
            System.out.println("\t3.- Base de datos [grupo turistico]");
            System.out.println("\t4.- Escribe [salir] para volver atrás");
            System.out.println("Ingresa la opción correspondiente (1, 2 o 3): ");

            String opcion = teclado.nextLine().trim();

            if (opcion.equals("1")) {
                return TipoEntidad.TURISTA;
            }
            else if (opcion.equals("2")) {
                return TipoEntidad.GUIA;
            }
            else if (opcion.equals("3")) {
                return TipoEntidad.GRUPO;
            }
            else if (opcion.equalsIgnoreCase("salir")) {
                System.out.println("Finalizando edición de base de datos");
                break;
            }
            else {
                System.out.println("Opción invalida");
            }
        }
        return null;
    }
}
