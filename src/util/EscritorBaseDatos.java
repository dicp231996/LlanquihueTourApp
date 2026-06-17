package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;

public class EscritorBaseDatos {
    public static void guardarRegistro(String rutaArchivo, String registro) {
        try (BufferedWriter escrito = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            escrito.write(registro);
            escrito.newLine();

            System.out.println("[ÉXITO] Registro realizado exitosamente en la base de datos: " + rutaArchivo);

        } catch (IOException ex) {
            System.out.println("[ERROR CRÍTICO] Falla en la escrita del disco: " + ex.getMessage());
        }
    }
}
