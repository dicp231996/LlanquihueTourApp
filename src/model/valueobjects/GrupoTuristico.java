package model.valueobjects;

import model.entities.actors.Turista;
import model.entities.actors.GuiaTuristico;
import model.core.ServicioTuristico;

import java.util.ArrayList;

/**
 * Representa un grupo turístico con un destino, horario, guía a cargo y una lista de participantes.
 * Administra la inscripción de turistas, la dificultad del tour y los detalles logísticos.
 */

public class GrupoTuristico {
    
    private Turista[] participantes = new Turista[10];
    private ServicioTuristico actividad;
    private GuiaTuristico guiaEncargado;
    private String idGrupo;
    private int participantesInscritos = 0;

    /**
     * Define los niveles de dificultad disponibles para un tour.
     */

    
    //Contructor por defecto

    /**
     * Constructor por defecto.
     * Inicializa el grupo turístico con valores predeterminados (horarios en 00:00,
     * sin destino, guía vacío, sin condición física requerida, valor 0 y dificultad Principiante).
     */
    
    public GrupoTuristico() {
        this.actividad = null;
        this.guiaEncargado = new GuiaTuristico();
        this.idGrupo = "";
    }

    //Constructor con atributos

    
    public GrupoTuristico(ServicioTuristico actividad, GuiaTuristico guiaEncargado, String idGrupo) {
        this.setActividad(actividad);
        this.setGuiaEncargado(guiaEncargado);
        this.setIdGrupo(idGrupo);
    }
    
    //Metodos de [Lista de participantes]

    /**
     * Genera un reporte formateado con los participantes inscritos en el grupo.
     * * @return Un String con el listado enumerado de participantes o un mensaje indicando que no hay inscritos.
     */
    
    public String getListadoParticipantes(){
        if (this.participantesInscritos == 0) {
            return "El grupo no tiene participantes inscritos";
        }
        
        StringBuilder reporteParticipantes = new StringBuilder();
        reporteParticipantes.append("==== Lista de participantes ====\n");
        
        for (int i = 0; i < this.participantesInscritos; i++ ) {
            Turista clienteActual = this.participantes[i];
            reporteParticipantes.append((i + 1)).append(". ").append(clienteActual.getNombres()).append(" ").append(clienteActual.getApellidoPaterno()).append(" ").append(clienteActual.getApellidoMaterno()).append("\n");
        }
        
        return reporteParticipantes.toString();
    }



    /**
     * Añade un nuevo turista al arreglo de participantes del grupo.
     * * @param nuevoCliente Objeto Turista que se desea inscribir.
     * @throws IllegalStateException Si el grupo ya alcanzó su capacidad máxima (10 participantes).
     */
    
    public void inscribirParticipante(Turista nuevoCliente) {
        if (this.participantesInscritos >= this.participantes.length) {
            throw new IllegalStateException("Grupo lleno: no se pueden inscribir mas participantes");
        }
        
        this.participantes[this.participantesInscritos] = nuevoCliente;
        this.participantesInscritos++;
    }

    public int getParticipantesInscritos() {
        return participantesInscritos;
    }

    //Metodos de [Servicio Turistico]

    public ServicioTuristico getActividad() {
        return this.actividad;
    }

    public void setActividad(ServicioTuristico actividad) {
        if (actividad == null) {
            throw new NullPointerException("Actividad sin asignar");
        }
        this.actividad = actividad;
    }

    //Metodos de [Guia turistico]

    /**
     * Obtiene el guía turístico encargado del grupo.
     * * @return Objeto GuiaTuristico asignado.
     */
    
    public GuiaTuristico getGuiaEncargado() {
        return this.guiaEncargado;
    }

    /**
     * Asigna un guía turístico al grupo.
     * * @param guia Objeto GuiaTuristico a asignar.
     * @throws IllegalArgumentException Si el objeto guía proporcionado es nulo.
     */
    
    public void setGuiaEncargado(GuiaTuristico guia) {
        if (guia == null) {
            throw new IllegalArgumentException("Guía aun no asignado para este tour");
        }        
        this.guiaEncargado = guia;
    }

    //Metodos de [id de grupo]

    public String getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        if (idGrupo == null || idGrupo.isBlank()) {
            throw new IllegalArgumentException("El ID del grupo no puede estar vacio");
        }
        this.idGrupo = idGrupo;
    }


    //Instancia de objeto

    /**
     * Genera un resumen completo con los detalles del grupo turístico.
     * Incluye destino, valor, condición física, dificultad, horario, guía a cargo y listado de participantes.
     * * @return String formateado con la información completa del tour.
     */
    
    @Override
    public String toString() {
        StringBuilder resumen = new StringBuilder();

        if (actividad != null) {
            resumen.append(actividad.toString());
        }
        else {
            resumen.append("Actividad pendiente de asignación");
        }

        resumen.append("Guia encargado: ").append(this.getGuiaEncargado().getNombres()).append(" ").append(this.getGuiaEncargado().getApellidoPaterno()).append(" ").append(this.getGuiaEncargado().getApellidoMaterno()).append("\n");
        
        resumen.append("\n").append(this.getListadoParticipantes());
        
        return resumen.toString();
    }
    
}
