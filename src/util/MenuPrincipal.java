package util;

import util.core.ui.SelectorBaseDatos;
import util.adddata.AgregarRegistros;
import util.removedata.QuitarRegistro;
import util.core.metadata.TipoEntidad;
import java.util.Scanner;

public class MenuPrincipal {

    public static void iniciarMenuPrincipal(Scanner teclado) {
        while (true) {
            System.out.println("\n======= Menú de operaciones =======");
            System.out.println("\t1. Agregar un registro");
            System.out.println("\t2. Eliminar un registro");
            System.out.println("\t3. Salir del sistema\n");
            System.out.println("Selecciona la operación que quieres realizar (1,2 o 3): ");

            String opcion = teclado.nextLine().trim();

            if(opcion.equals("1")) {
                TipoEntidad seleccion = SelectorBaseDatos.seleccionarEntidad(teclado);
                if (seleccion != null) {
                    AgregarRegistros.registrarNuevaEntidad(seleccion, teclado);
                }
            }
            else if (opcion.equals("2")) {
                TipoEntidad seleccion = SelectorBaseDatos.seleccionarEntidad(teclado);
                if (seleccion != null) {
                    QuitarRegistro.eliminarRegistro(seleccion, teclado);
                }
            }
            else if (opcion.equals("3")) {
                System.out.println("Finalizando la edición de la base de datos.");
                break;
            }
            else {
                System.out.println("Error, seleccione una opcion valida.");
            }

        }
    }
}
