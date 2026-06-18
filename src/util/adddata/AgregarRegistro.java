package util.adddata;

import util.core.metadata.TipoEntidad;

import java.util.Scanner;

public class AgregarRegistro {

    public static TipoEntidad seleccionarEntidad(Scanner teclado) {
        while(true) {
            System.out.println("====== Sistema de registros ======");
            System.out.println("\t1.- Registrar nuevo turista");
            System.out.println("\t2.- Registrar nuevo guía turistico");
            System.out.println("\t3.- Registrar nuevo grupo turistico");
            System.out.println("Ingresa la opciòn correspondiente (1, 2 o 3): ");

            String opcion = teclado.nextLine().trim();

            if (opcion.equals("1")) {
                return TipoEntidad.TURISTA;
            }
            else if (opcion.equals("2")) {
                return TipoEntidad.GUIA;
            }
            else if (opcion.equals("3")){
                return TipoEntidad.GRUPO;
            }
            else {
                System.out.println("Opción invalida");
            }
        }
    }
}
