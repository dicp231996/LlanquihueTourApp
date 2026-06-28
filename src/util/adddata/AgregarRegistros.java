package util.adddata;

import data.GestorInstancias;
import model.valueobjects.GrupoTuristico;
import util.core.metadata.Campo;
import util.core.metadata.TipoDato;
import util.core.metadata.TipoEntidad;
import data.GestorDatos;
import util.core.rules.GestorValidaciones;

import java.util.ArrayList;
import java.util.Scanner;

import static data.GestorInstancias.ensamblarGruposTuristicos;

/**
 * Clase utilitaria encargada de gestionar la creación e inserción de nuevos registros en el sistema.
 * Utiliza los metadatos de las entidades para guiar al usuario mediante un proceso interactivo,
 * estructurando y validando la información antes de persistirla en la base de datos.
 */

public class AgregarRegistros {

    /**
     * Ejecuta el flujo de captura de datos en la consola para una nueva entidad.
     * El método recorre dinámicamente el esquema de campos definido para la entidad, solicita
     * el ingreso del dato correspondiente y lo evalúa contra el motor de validación.
     * Los procesos se repiten en bucle hasta que la entrada es válida. Finalmente, ensambla
     * la cadena de texto con el delimitador (;) y delega la escritura física al repositorio de datos.
     *
     * @param tipoEntidad El enumerador que define el tipo de registro a crear (ej. GUIA, TURISTA),
     * proporcionando acceso a su esquema de metadatos y ruta física.
     * @param teclado     La instancia de {@code Scanner} utilizada para leer la entrada del usuario
     * desde la consola de comandos.
     */

    public static void registrarNuevaEntidad(TipoEntidad tipoEntidad, Scanner teclado) {
        System.out.println("\n====== Realizar un registro de un " + tipoEntidad.name().toLowerCase() + " ======");
        StringBuilder registroCompleto = new StringBuilder();
        Campo[] esquema = tipoEntidad.getEsquemaDatos();

        for (int i = 0; i < esquema.length; i++) {
            Campo campoRegistro = esquema[i];
            String entradaDato = "";

            if (campoRegistro.getTipoRegla() == TipoDato.SELECCION_TOUR_INTERACTIVO) {
                System.out.println("\n" + campoRegistro.getPregunta());
                ArrayList<String> opciones = GestorInstancias.obtenerOpcionesTours();

                for (int j = 0; j < opciones.size(); j++) {
                    System.out.println((j + 1) + ". " + opciones.get(j));
                }

                System.out.println("Seleccione el tour que desea contratar:");
                int eleccion = Integer.parseInt(teclado.nextLine().trim());
                String opcionSeleccionada = opciones.get(eleccion - 1);

                entradaDato = GestorInstancias.extraerIdGrupoSeleccionado(opcionSeleccionada);
            }
            else {
                while (true) {
                    System.out.print(campoRegistro.getPregunta());
                    entradaDato = teclado.nextLine().trim();

                    if (GestorValidaciones.datoValido(campoRegistro.getTipoRegla(), entradaDato)) {
                        break;
                    }
                    else {
                        System.out.println("Error: El dato no cumple con el formato requerido" + campoRegistro.getTipoRegla().name() + ".");
                    }
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
        GestorDatos.guardarRegistro(tipoEntidad.getRutaArchivo(), registroCompleto.toString());
    }
}
