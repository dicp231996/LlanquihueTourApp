package data;

import model.core.ServicioTuristico;
import model.entities.actors.GuiaTuristico;
import model.entities.actors.Turista;
import model.entities.services.AvistamientoHumedales;
import model.entities.services.PaseoLacustre;
import model.entities.services.RutaPatrimonial;
import model.entities.services.TrekkingAltaMontania;
import model.valueobjects.Direccion;
import model.valueobjects.GrupoTuristico;
import model.valueobjects.Rut;
import util.core.metadata.TipoEntidad;

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
    private  static  ArrayList<GuiaTuristico> cacheGuias = null;
    private  static  ArrayList<GrupoTuristico> cacheGrupos = null;
    private static ArrayList<Turista> cacheTuristas = null;

    public static ArrayList<GuiaTuristico> ensamblarGuiasTuristas () {
        if (cacheGuias != null) {
            return cacheGuias;
        }

        var lineasGuiasTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GUIA.getRutaArchivo());
        var datosGuiasTuristicos = GestorDatos.registroInstancias(lineasGuiasTuristicosDB);
        ArrayList<GuiaTuristico> baseDatosGuias = new ArrayList<>();

        for (var partes:datosGuiasTuristicos) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7],Direccion.Region.valueOf(partes[8]));

            GuiaTuristico nuevoGuia = new GuiaTuristico(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    GuiaTuristico.NivelDeIngles.valueOf(partes[9]),partes[10],Boolean.parseBoolean(partes[11]),Boolean.parseBoolean(partes[12]),partes[13]);

            baseDatosGuias.add(nuevoGuia);
        }
        cacheGuias = baseDatosGuias;
        return cacheGuias;
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
        if (cacheGrupos != null) {
            return cacheGrupos;
        }

        var lineasGruposTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GRUPO.getRutaArchivo());
        var datosGruposTours = GestorDatos.registroInstancias(lineasGruposTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = ensamblarGuiasTuristas();
        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = new ArrayList<>();
        for (var partes:datosGruposTours) {

            GuiaTuristico guiaEncargado = buscarGuiaPorId(partes[1], baseDatosGuias);
            ServicioTuristico actividad = fabricarServicio(partes[2]);

            GrupoTuristico nuevoGrupo = new GrupoTuristico(actividad,guiaEncargado,partes[0]);

            baseDatosGruposTuristicos.add(nuevoGrupo);
        }
        cacheGrupos = baseDatosGruposTuristicos;
        return cacheGrupos;
    }

    private static GuiaTuristico buscarGuiaPorId(String idGuia, ArrayList<GuiaTuristico> baseDatosGuias) {
        String numeroEmpleado = idGuia.trim();

        for (GuiaTuristico guia : baseDatosGuias) {
            if (guia.getNumeroEmpleado().trim().equalsIgnoreCase(numeroEmpleado)) {
                return guia;
            }
        }
        throw new IllegalArgumentException("El guia no existe en el servidor.");
    }

    private static ServicioTuristico fabricarServicio(String idServicioBuscado){
        String prefijo = idServicioBuscado.substring(0, 3).toUpperCase();
        ServicioTuristico experiencia = null;

        switch (prefijo){
            case "TRK":
                var lineasTrekkingDB = GestorDatos.cargarDatos(TipoEntidad.TREKKING.getRutaArchivo());
                var datosTrekking = GestorDatos.registroInstancias(lineasTrekkingDB);

                for (var partes:datosTrekking) {
                    if (partes[0].trim().equalsIgnoreCase(idServicioBuscado.trim())) {
                        experiencia = new TrekkingAltaMontania(partes[1],Integer.parseInt(partes[2]),Integer.parseInt(partes[3]),Integer.parseInt(partes[4]),Integer.parseInt(partes[5]),Integer.parseInt(partes[6]),
                                ServicioTuristico.Dificultad.valueOf(partes[7].toUpperCase()),Integer.parseInt(partes[8]),Boolean.parseBoolean(partes[9]));
                        break;
                    }
                }
                break;
            case "NAU":
                var lineasPaseoLacustreDB = GestorDatos.cargarDatos(TipoEntidad.PASEO_LACUSTRE.getRutaArchivo());
                var datosPaseoLacustre = GestorDatos.registroInstancias(lineasPaseoLacustreDB);

                for (var partes:datosPaseoLacustre) {
                    if (partes[0].trim().equalsIgnoreCase(idServicioBuscado.trim())) {
                        experiencia = new PaseoLacustre(partes[1],Integer.parseInt(partes[2]),Integer.parseInt(partes[3]),Integer.parseInt(partes[4]),Integer.parseInt(partes[5]),Integer.parseInt(partes[6]),
                                ServicioTuristico.Dificultad.valueOf(partes[7].toUpperCase()),partes[8],Integer.parseInt(partes[9]),partes[10]);
                    break;
                    }
                }
                break;
            case "PAT":
                var lineasRutaPatrimonialDB = GestorDatos.cargarDatos(TipoEntidad.RUTA_PATRIMONIAL.getRutaArchivo());
                var datosRutaPatrimonial = GestorDatos.registroInstancias(lineasRutaPatrimonialDB);

                for (var partes:datosRutaPatrimonial) {
                    if (partes[0].trim().equalsIgnoreCase(idServicioBuscado.trim())) {
                        experiencia = new RutaPatrimonial(partes[1],Integer.parseInt(partes[2]),Integer.parseInt(partes[3]),Integer.parseInt(partes[4]),Integer.parseInt(partes[5]),Integer.parseInt(partes[6]),
                                ServicioTuristico.Dificultad.valueOf(partes[7].toUpperCase()),Boolean.parseBoolean(partes[8]),Boolean.parseBoolean(partes[9]),Integer.parseInt(partes[10]));
                    break;
                    }
                }
                break;
            case "AVI":
                var lineasAvistamientoHumedalDB = GestorDatos.cargarDatos(TipoEntidad.AVISTAMIENTO_HUMEDAL.getRutaArchivo());
                var datosAvistamientoHumedal = GestorDatos.registroInstancias(lineasAvistamientoHumedalDB);
                for (var partes:datosAvistamientoHumedal) {
                    if (partes[0].trim().equalsIgnoreCase(idServicioBuscado.trim())) {
                        experiencia = new AvistamientoHumedales(partes[1],Integer.parseInt(partes[2]),Integer.parseInt(partes[3]),Integer.parseInt(partes[4]),Integer.parseInt(partes[5]),Integer.parseInt(partes[6]),
                                ServicioTuristico.Dificultad.valueOf(partes[7].toUpperCase()),Boolean.parseBoolean(partes[8]),AvistamientoHumedales.EstacionRecomendada.valueOf(partes[9].toUpperCase()));
                    break;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("El tipo de servicio no existe en el servidor.");
        }
        return experiencia;
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
        if (cacheTuristas != null) {
            return cacheTuristas;
        }

        var lineasTuristasDB = GestorDatos.cargarDatos(TipoEntidad.TURISTA.getRutaArchivo());
        var DatosTuristas = GestorDatos.registroInstancias(lineasTuristasDB);

        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = ensamblarGruposTuristicos();
        ArrayList<Turista> baseDatosTuristas = new ArrayList<>();

        for (var partes:DatosTuristas) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7], Direccion.Region.valueOf(partes[8]));

            GrupoTuristico grupoContratado = buscarGrupoPorId(partes[11],cacheGrupos);

            Turista nuevoTurista = new Turista(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    partes[9],Integer.parseInt(partes[10]),grupoContratado.getActividad());

            grupoContratado.inscribirParticipante(nuevoTurista);

            baseDatosTuristas.add(nuevoTurista);
        }
        cacheTuristas = baseDatosTuristas;
        return cacheTuristas;
    }

    private static GrupoTuristico buscarGrupoPorId(String idBuscado,ArrayList<GrupoTuristico> gruposTuristicos) {
        String idGrupo = idBuscado.trim();

        for (GrupoTuristico grupo : gruposTuristicos) {
            if (grupo.getIdGrupo().trim().equalsIgnoreCase(idGrupo)) {
                return grupo;
            }
        }
        throw new IllegalArgumentException("El grupo no existe en el servidor.");
    }
    public static String extraerIdGrupoSeleccionado(String seleccionUsuario) {
        if (seleccionUsuario == null || !seleccionUsuario.startsWith("[")) {
            throw new IllegalArgumentException("La seleccion no posee el inicio requerido.");
        }
        int indiceFinal = seleccionUsuario.indexOf("]");
        if (indiceFinal == -1) {
            throw new IllegalArgumentException("La seleccion no posee el final requerido.");
        }
        return seleccionUsuario.substring(1, indiceFinal).trim();
    }

    public static ArrayList<String> obtenerOpcionesTours() {
        ArrayList<GrupoTuristico> grupos = ensamblarGruposTuristicos();
        ArrayList<String> opcionesDisponibles = new ArrayList<>();

        final int CAPACIDAD_MAXIMA = 10;

        for (GrupoTuristico grupo : grupos) {
            if (grupo.getParticipantesInscritos() < CAPACIDAD_MAXIMA) {
                int cuposDisponibles = CAPACIDAD_MAXIMA - grupo.getParticipantesInscritos();
                String descripcion = String.format("[%s] %s | Guía: %s | Cupos: %d",
                        grupo.getIdGrupo(),
                        grupo.getActividad().getClass().getSimpleName(),
                        grupo.getGuiaEncargado().getNombres(),
                        cuposDisponibles);

                opcionesDisponibles.add(descripcion);
            }
        }
        return opcionesDisponibles;
    }

}
