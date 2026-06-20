package app;

import java.util.Scanner;

import util.MenuPrincipal;


/**
 * Clase principal que actúa como punto de entrada de la aplicación LlanquihueTour.
 * Simula un sistema de gestión turística, instanciando guías, turistas y grupos de viaje
 * mediante datos de prueba ("Pseudo bases de datos"), para luego mostrar un resumen de los
 * grupos y sus participantes en consola.
 * @author Daniel Campos
 * @version 1.2.0
 */

public class LlanquihueTour {

    /**
     * Método principal que ejecuta la lógica de la aplicación.
     * Crea arreglos de datos simulados para generar objetos de dominio y muestra
     * la información resultante de los grupos turísticos configurados.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta implementación).
     */


    public static void main(String[] args) {

        //Menù principal
        var teclado = new Scanner(System.in);
        System.out.println("Iniciando el sistema de gestión de Llanquihue tour...\n");
        MenuPrincipal.iniciarMenuPrincipal(teclado);
        teclado.close();
        System.out.println("Saliendo del sistema de registros");


    }
}
