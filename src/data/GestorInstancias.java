package data;

import model.entities.GuiaTuristico;
import model.entities.Turista;
import model.valueobjects.Direccion;
import model.valueobjects.GrupoTuristico;
import model.valueobjects.Rut;
import util.core.metadata.TipoEntidad;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Actúa como un Mapeador de Datos (Data Mapper) y Fábrica de Objetos para el sistema.
 * Su responsabilidad es solicitar los datos crudos a {@link GestorDatos}, procesar las cadenas
 * de texto y ensamblar las colecciones de objetos de dominio con sus respectivas relaciones.
 */

public class  GestorInstancias {

    /**
     * Extrae los registros del archivo de guías y construye una lista de objetos {@code GuiaTuristico}.
     * Instancia internamente los objetos de valor asociados, como {@code Rut} y {@code Direccion}.
     *
     * @return Un {@code ArrayList<GuiaTuristico>} puro, listo para ser consumido por la capa de lógica o interfaz.
     */

    public static ArrayList<GuiaTuristico> ensamblarGuiasTuristas () {
        var lineasGuiasTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GUIA.getRutaArchivo());
        var datosGuiasTuristicos = GestorDatos.registroInstancias(lineasGuiasTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = new ArrayList<>();

        for (var partes:datosGuiasTuristicos) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7], Direccion.Region.valueOf(partes[8]));

            GuiaTuristico nuevoGuia = new GuiaTuristico(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    GuiaTuristico.NivelDeIngles.valueOf(partes[9]),partes[10],Boolean.parseBoolean(partes[11]),Boolean.parseBoolean(partes[12]));

            baseDatosGuias.add(nuevoGuia);
        }
        return baseDatosGuias;
    }

    /**
     * Construye la lista de objetos {@code GrupoTuristico}.
     * Para garantizar la integridad del algoritmo, este método invoca primero a
     * {@link #ensamblarGuiasTuristas()} para inyectar una instancia real de guía en cada grupo.
     * <p>
     * <b>Nota de Arquitectura (Entorno de Prueba):</b> Actualmente, la asignación entre guías y grupos
     * se realiza de forma secuencial (1 a 1) utilizando un contador. Esta estructura es solo temporal, ya que depende que ambas listas
     * tengan el mismo tamaño, eventualmente se agregaran nuevas funciones para realizar una asignacion más adecuada para un caso real.
     * </p>
     *
     * @return Un {@code ArrayList<GrupoTuristico>} con sus respectivos guías asignados en memoria.
     */

    public static ArrayList<GrupoTuristico> ensamblarGruposTuristicos () {
        var lineasGruposTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GRUPO.getRutaArchivo());
        var datosGruposTours = GestorDatos.registroInstancias(lineasGruposTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = ensamblarGuiasTuristas();
        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = new ArrayList<>();
        //Los guias se asignan a los grupos de manera 1 a 1, es decir, el guia 0 pertenece al grupo 0, esta estructura es solo de prueba.
        int contador = 0;
        for (var partes:datosGruposTours) {

            GrupoTuristico nuevoGrupo = new GrupoTuristico (LocalTime.of(Integer.parseInt(partes[0]),(Integer.parseInt(partes[1]))),
                    LocalTime.of(Integer.parseInt(partes[2]),(Integer.parseInt(partes[3]))),partes[4],baseDatosGuias.get(contador),Boolean.parseBoolean(partes[6]),
                    Integer.parseInt(partes[7]),GrupoTuristico.Dificultad.valueOf(partes[9]));
            baseDatosGruposTuristicos.add(nuevoGrupo);
            contador++;
        }
        return baseDatosGruposTuristicos;
    }

    /**
     * Ensambla la colección de objetos {@code Turista}.
     * Este método invoca a {@link #ensamblarGruposTuristicos()} para asignar un tour a cada turista
     * y genera una relación bidireccional al registrar al turista dentro del grupo seleccionado.
     * <p>
     * <b>Nota de Arquitectura (Entorno de Prueba):</b> La asignación a los grupos turísticos se realiza
     * de manera no determinista (aleatoria). Eventualemnte se implementarán identificadores relacionales
     * para mapear la asignación exacta desde la base de datos.
     * </p>
     *
     * @return Un {@code ArrayList<Turista>} completamente poblado y vinculado a sus respectivos grupos.
     */

    public static ArrayList<Turista> ensamblarTuristas () {
        var lineasTuristasDB = GestorDatos.cargarDatos(TipoEntidad.TURISTA.getRutaArchivo());
        var DatosTuristas = GestorDatos.registroInstancias(lineasTuristasDB);

        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = ensamblarGruposTuristicos();
        ArrayList<Turista> baseDatosTuristas = new ArrayList<>();
        Random contadorAleatorio = new Random();

        final int CUPOS_MAXIMOS_POR_GRUPO = 10;

        for (var partes:DatosTuristas) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7], Direccion.Region.valueOf(partes[8]));


            //La asignacion a los grupos turisticos se realiza de manera aleatoria a modo de prueba, en futuras versiones se agregarán atributos dentro de la clase
            //para garantizar un funcionamiento optimo del sistema.
            int indiceAleatorio = contadorAleatorio.nextInt(baseDatosGruposTuristicos.size());
            GrupoTuristico tourElegido = baseDatosGruposTuristicos.get(indiceAleatorio);

            do{
                indiceAleatorio = contadorAleatorio.nextInt(baseDatosGruposTuristicos.size());
                tourElegido = baseDatosGruposTuristicos.get(indiceAleatorio);
            }
            while(tourElegido.getParticipantesInscritos() >= CUPOS_MAXIMOS_POR_GRUPO);

            //Instancia de turista

            Turista nuevoTurista = new Turista(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    partes[9],Integer.parseInt(partes[10]),tourElegido);

            baseDatosTuristas.add(nuevoTurista);

            tourElegido.inscribirParticipante(nuevoTurista);
        }

        return baseDatosTuristas;
    }
}
