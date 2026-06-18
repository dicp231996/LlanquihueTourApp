package util.removedata;

import util.core.io.EscritorBaseDatos;
import util.core.metadata.TipoEntidad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static util.core.io.EscritorBaseDatos.guardarRegistro;

public class QuitarRegistro {

    public static void eliminarRegistro(TipoEntidad entidad, Scanner teclado) {
        String rutaArchivo = entidad.getRutaArchivo();
        ArrayList<String> registrosBaseDatos = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                registrosBaseDatos.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se puede entrar a la base de datos");
            return;
        }

        while (true) {
            System.out.println("\n====== Historial de registros: " + entidad.name().toLowerCase() + " ======");

            if (registrosBaseDatos.isEmpty()) {
                System.out.println("La base de datos esta vacia");
                break;
            }

            for (int i=0; i<registrosBaseDatos.size(); i++) {
                System.out.println("[" + (i+1) + "] " + registrosBaseDatos.get(i));
            }

            System.out.println("\nIngresa el número del registro a eliminar o Escribe [salir] para finalizar:");
            String entrada = teclado.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Finalizando edición de base de datos");
                break;
            }

            try {
                int numeroRegistroElegido = Integer.parseInt(entrada)-1;

                if (numeroRegistroElegido >= 0 && numeroRegistroElegido < registrosBaseDatos.size()) {
                    registrosBaseDatos.remove(numeroRegistroElegido);
                    System.out.println("Registro eliminado exitosamente");

                    sobrescribirBaseDatos(rutaArchivo, registrosBaseDatos);
                }
                else {
                    System.out.println("Registro no encontrado");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero o la palabra [salir]");
            }
        }
    }

    public static void sobrescribirBaseDatos(String rutaArchivo, ArrayList<String> registrosBaseDatos) {

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo,false))) {
            for (String registro : registrosBaseDatos) {
                escritor.write(registro);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se puede sobrescribir a la base de datos");
        }
    }
}
