package data;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Clase utilitaria encargada de la gestión y lectura de datos desde archivos de texto.
 * Proporciona métodos estáticos para cargar líneas de texto desde el directorio local
 * y procesarlas en estructuras de datos separadas.
 */

public class GestorDatos {

    /**
     * Lee un archivo de texto ubicado en el directorio "resource" y carga cada una de sus líneas en una lista.
     * Si el archivo no existe, está vacío o ocurre un error de lectura, imprime un mensaje descriptivo en consola
     * y retorna una lista vacía.
     * * @param nombreArchivo El nombre del archivo (incluyendo su extensión) que se desea leer.
     * @return Un {@code ArrayList<String>} que contiene cada línea del texto del archivo como un elemento.
     */

    public static ArrayList<String> cargarDatos(String nombreArchivo) {
        File archivo = new File("resource",nombreArchivo);
        ArrayList<String> datos = new ArrayList<>();


        if (archivo.exists() &&  archivo.length() > 0) {

            try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(archivo))) {
                String linea;

                while ((linea = lectorArchivo.readLine()) != null) {
                    datos.add(linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer datos del archivo");
            }
        } else {
            System.out.println("Archivo no encontrado");
        }

        return datos;
    }

    /**
     * Procesa una lista de cadenas de texto, donde cada cadena representa un registro
     * cuyos valores están separados por punto y coma (;), y los divide en arreglos de elementos individuales.
     * * @param listaInstancias Un {@code ArrayList<String>} con los registros en formato de texto plano.
     * @return Un {@code ArrayList<String[]>} donde cada elemento es un arreglo que contiene las partes extraídas del registro original.
     * @throws IllegalArgumentException Si la lista de instancias proporcionada está vacía.
     */

    public static ArrayList<String[]> registroInstancias(ArrayList<String> listaInstancias) {
        if (listaInstancias.isEmpty()) {
            throw new IllegalArgumentException("No se puede registrar ninguna persona en el sistema.");
        }
        ArrayList<String[]> registros = new ArrayList<>();

        for (int i = 0; i < listaInstancias.size(); i++) {
            String registro = listaInstancias.get(i);
            String[] partes = registro.split(";");

            registros.add(partes);
        }
        return registros;
    }
}
