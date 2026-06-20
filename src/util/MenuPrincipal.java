package util;

import data.GestionFiltro;
import model.valueobjects.GrupoTuristico;
import util.core.ui.SelectorBaseDatos;
import util.adddata.AgregarRegistros;
import util.queries.MenuConsultas;
import util.removedata.QuitarRegistro;
import util.core.metadata.TipoEntidad;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Controlador principal de la interfaz de usuario del sistema.
 * Actúa como el orquestador o conmutador central, desplegando el menú de nivel superior
 * para las operaciones de gestión de datos (Agregar, Eliminar, Filtrar). Su responsabilidad
 * es capturar la intención general del usuario y enrutar el flujo de ejecución hacia los módulos
 * especializados correspondientes, apoyándose en la selección previa de la entidad.
 */

public class MenuPrincipal {
    /**
     * Inicia el bucle de ejecución principal del programa.
     * <p>
     * Despliega un menú interactivo en la consola que permite al usuario seleccionar
     * la operación general a realizar. Una vez validada la opción, el método invoca a
     * {@link SelectorBaseDatos#seleccionarEntidad(Scanner)} para determinar el contexto
     * (Turista, Guía o Grupo) y posteriormente transfiere el control absoluto al submódulo
     * encargado ({@link AgregarRegistros}, {@link QuitarRegistro} o {@link MenuConsultas}).
     * El ciclo se mantiene activo bloqueando la finalización del programa hasta que se
     * ingresa explícitamente el comando de salida.
     * </p>
     *
     * @param teclado La instancia de {@code Scanner} inicializada en el sistema, utilizada
     * para capturar las pulsaciones y comandos del usuario en la consola.
     */

    public static void iniciarMenuPrincipal(Scanner teclado) {
        while (true) {
            System.out.println("\n======= Menú de operaciones =======");
            System.out.println("\t1. Agregar un registro");
            System.out.println("\t2. Eliminar un registro");
            System.out.println("\t3. Filtrar datos");
            System.out.println("\t4. Salir del sistema\n");
            System.out.println("Selecciona la operación que quieres realizar (del 1 al 3) (Preciona 4 para salir): ");

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
                TipoEntidad seleccion = SelectorBaseDatos.seleccionarEntidad(teclado);
                if (seleccion != null) {
                    MenuConsultas.iniciarConsulta(seleccion, teclado);
                }
            }
            else if (opcion.equals("4")) {
                System.out.println("Finalizando la edición de la base de datos.");
                break;
            }
            else {
                System.out.println("Error, seleccione una opcion valida.");
            }

        }
    }
}
