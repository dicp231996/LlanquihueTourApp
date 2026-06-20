package util.core.ui;

import util.core.metadata.TipoEntidad;

import java.util.Scanner;
/**
 * Clase utilitaria de interfaz de usuario encargada de gestionar la selección de la base de datos operativa.
 * Despliega un menú interactivo en la consola para que el usuario determine sobre qué
 * entidad del dominio (Turista, Guía o Grupo) desea ejecutar la operación solicitada.
 */
public class SelectorBaseDatos {
    /**
     * Presenta el menú de selección de entidades y evalúa la elección del usuario.
     * El método mantiene un bucle de ejecución continuo que bloquea el avance del programa
     * hasta que se ingrese una opción válida o se invoque explícitamente el comando de salida.
     * Actúa como el enrutador principal para proporcionar el contexto a los módulos.
     *
     * @param teclado La instancia de {@code Scanner} utilizada para capturar la entrada de texto por consola.
     * @return El enumerador {@link TipoEntidad} correspondiente a la selección del usuario,
     * o {@code null} si el usuario decide abortar la operación escribiendo el comando "salir".
     */
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
