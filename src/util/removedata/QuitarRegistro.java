package util.removedata;

import data.GestorDatos;
import util.core.metadata.TipoEntidad;

import java.util.ArrayList;
import java.util.Scanner;

public class QuitarRegistro {

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
