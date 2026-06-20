package data;

import java.io.*;
import java.util.ArrayList;

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

    public static ArrayList<String> cargarDatos(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        ArrayList<String> datos = new ArrayList<>();


        if (archivo.exists() &&  archivo.length() > 0) {

            try (BufferedReader lectorArchivo = new BufferedReader(new FileReader(rutaArchivo))) {
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
    /**
     * Agrega un nuevo registro al final de un archivo de texto especificado.
     * Este método abre el archivo en modo de adición (append = true), lo que garantiza
     * que la información existente no sea alterada ni eliminada.
     * Si ocurre un error físico en el disco durante la operación, la excepción es capturada
     * y reportada en la consola sin detener la ejecución del programa.
     *
     * @param rutaArchivo La ruta relativa o absoluta del archivo de destino (ej. "resource/turistas.txt").
     * @param registro    La cadena de texto formateada (separada por delimitadores) que se inyectará en el archivo.
     */
    public static void guardarRegistro(String rutaArchivo, String registro) {
        try (BufferedWriter escrito = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            escrito.write(registro);
            escrito.newLine();

            System.out.println("[ÉXITO] Registro realizado exitosamente en la base de datos: " + rutaArchivo);

        } catch (IOException ex) {
            System.out.println("[ERROR CRÍTICO] Falla en la escrita del disco: " + ex.getMessage());
        }
    }
    /**
     * Sobrescribe por completo el contenido de un archivo de texto con una nueva lista de registros.
     * Este método es destructivo: abre el archivo en modo de sobrescritura (append = false),
     * eliminando todo el contenido anterior para reemplazarlo línea por línea con los elementos
     * de la lista proporcionada. Es el motor principal para las operaciones de eliminación y edición.
     *
     * @param rutaArchivo        La ruta relativa o absoluta del archivo que será sobrescrito.
     * @param registrosBaseDatos Un {@code ArrayList<String>} que contiene la base de datos actualizada en la memoria RAM.
     */
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


