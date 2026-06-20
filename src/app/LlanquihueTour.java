package app;

import java.util.Scanner;

import util.MenuPrincipal;


/**
 * Clase principal que actúa como punto de entrada de la aplicación LlanquihueTour.
 * Simula un sistema de gestión turística por medio de la clase menu principal, la que permite
 * agregar registros.
 * quitar registros.
 * filtrar.
 * @author Daniel Campos
 * @version 2.0.0
 */

public class LlanquihueTour {

    /**
     * Método principal que ejecuta la lógica de la aplicación.
     * gestionada desde la clase MenuPrincipal.
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
