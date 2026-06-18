package util.adddata;

import util.core.metadata.Campo;
import util.core.metadata.TipoDato;
import util.core.metadata.TipoEntidad;
import util.core.io.EscritorBaseDatos;
import util.core.rules.GestorValidaciones;

import java.util.Scanner;

public class GestorRegistro {

    public static void registrarNuevaEntidad(TipoEntidad tipoEntidad, Scanner teclado) {
        System.out.println("\n====== Realizar un registro de un " + tipoEntidad.name().toLowerCase() + " ======");
        StringBuilder registroCompleto = new StringBuilder();
        Campo[] esquema = tipoEntidad.getEsquemaDatos();
        registroCompleto.append("\n");

        for (int i = 0; i < esquema.length; i++) {
            Campo campoRegistro = esquema[i];
            String entradaDato = "";

            while (true) {
                System.out.print(campoRegistro.getPregunta() + ": ");
                entradaDato = teclado.nextLine().trim();

                if (GestorValidaciones.datoValido(campoRegistro.getTipoRegla(), entradaDato)) {
                    break;
                }
                else {
                    System.out.println("Error: El dato no cumple con el formato requerido" + campoRegistro.getTipoRegla().name() + ".");
                }
            }

            if (campoRegistro.getTipoRegla() == TipoDato.REGION || campoRegistro.getTipoRegla() == TipoDato.NIVEL_INGLES || campoRegistro.getTipoRegla() == TipoDato.DIFICULTAD) {
                entradaDato = entradaDato.toUpperCase();
            }

            registroCompleto.append(entradaDato);

            if (i < esquema.length - 1) {
                registroCompleto.append(";");
            }
        }
        EscritorBaseDatos.guardarRegistro(tipoEntidad.getRutaArchivo(), registroCompleto.toString());
    }
}
