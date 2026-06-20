package util.removedata;

import data.GestorDatos;
import util.core.metadata.TipoEntidad;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Clase utilitaria encargada de gestionar la operación de eliminación (Delete) dentro del ciclo CRUD.
 * Proporciona una interfaz interactiva por consola para que el usuario visualice los registros
 * actuales de una entidad específica, seleccione un elemento mediante un índice numérico,
 * y lo elimine de forma permanente de la base de datos física.
 */
public class QuitarRegistro {
    /**
     * Ejecuta el flujo interactivo de eliminación de registros.
     * <p>
     * El método extrae la totalidad de los datos desde el archivo físico hacia la memoria RAM
     * ({@code ArrayList}). Posteriormente, despliega un menú en bucle que lista los registros numerados.
     * Al recibir un índice válido por parte del usuario, el método remueve el elemento de la colección
     * en memoria e invoca inmediatamente a {@link GestorDatos#sobrescribirBaseDatos(String, ArrayList)}
     * para aplicar una mutación destructiva en el disco, garantizando la sincronización de los datos.
     * </p>
     *
     * @param entidad El enumerador {@link TipoEntidad} que define la base de datos operativa y
     * proporciona la ruta del archivo físico a modificar.
     * @param teclado La instancia de {@code Scanner} utilizada para capturar la selección del índice
     * o el comando de salida por parte del usuario.
     */

    public static void eliminarRegistro(TipoEntidad entidad, Scanner teclado) {
        String rutaArchivo = entidad.getRutaArchivo();
        ArrayList<String> registrosBaseDatos = new ArrayList<>(GestorDatos.cargarDatos(rutaArchivo));

        while (true) {
            System.out.println("\n====== Historial de registros: " + entidad.name().toLowerCase() + " ======");

            if (registrosBaseDatos.isEmpty()) {
                System.out.println("La base de datos esta vacia");
                break;
            }

            for (int i = 0; i < registrosBaseDatos.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + registrosBaseDatos.get(i));
            }

            System.out.println("\nIngresa el número del registro a eliminar o Escribe [salir] para finalizar:");
            String entrada = teclado.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Finalizando edición de base de datos");
                break;
            }

            try {
                int numeroRegistroElegido = Integer.parseInt(entrada) - 1;

                if (numeroRegistroElegido >= 0 && numeroRegistroElegido < registrosBaseDatos.size()) {
                    registrosBaseDatos.remove(numeroRegistroElegido);
                    System.out.println("Registro eliminado exitosamente");

                    GestorDatos.sobrescribirBaseDatos(rutaArchivo, registrosBaseDatos);
                } else {
                    System.out.println("Registro no encontrado");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero o la palabra [salir]");
            }
        }
    }
}
