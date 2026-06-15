package model.valueobjects;

import java.time.LocalTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import model.entities.Turista;
import model.entities.GuiaTuristico;

/**
 * Representa un grupo turístico con un destino, horario, guía a cargo y una lista de participantes.
 * Administra la inscripción de turistas, la dificultad del tour y los detalles logísticos.
 */

public class GrupoTuristico {
    
    private Turista[] participantes = new Turista[10];
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private String destino;
    private GuiaTuristico guiaEncargado;
    private boolean condicionFisicaRequerida;
    private int valorTour;
    private Dificultad dificultad;
    private int participantesInscritos = 0;

    /**
     * Define los niveles de dificultad disponibles para un tour.
     */

    public enum Dificultad {
        PRINCIPIANTE("Principiante"),INTERMEDIO("Intermedio"),AVANZADO("Avanzado"),EXTREMO("Extremo");

        private String dificultad;

        /**
         * Constructor del enum Dificultad.
         * * @param dificultad Nombre descriptivo del nivel de dificultad.
         */

        Dificultad(String dificultad) {
            this.dificultad = dificultad;
        }

        /**
         * Obtiene el nombre descriptivo de la dificultad.
         * * @return Una cadena de texto con el nivel de dificultad.
         */

        public String getDificultad() {
            return dificultad;
        }
    }
    
    //Contructor por defecto

    /**
     * Constructor por defecto.
     * Inicializa el grupo turístico con valores predeterminados (horarios en 00:00,
     * sin destino, guía vacío, sin condición física requerida, valor 0 y dificultad Principiante).
     */
    
    public GrupoTuristico() {
        this.horaInicio = LocalTime.of(0, 0);
        this.horaTermino = LocalTime.of(0, 0);
        this.destino = "Sin destino asignado";
        this.guiaEncargado = new GuiaTuristico();
        this.condicionFisicaRequerida = false;
        this.valorTour = 0;
        this.dificultad = Dificultad.PRINCIPIANTE;
    }

    //Constructor con atributos

    /**
     * Constructor con parámetros para inicializar un grupo turístico completo.
     * * @param horaInicio             Hora de inicio del tour.
     * @param horaTermino              Hora de término del tour.
     * @param destino                  Lugar de destino del tour.
     * @param guiaEncargado            Objeto GuiaTuristico asignado al tour.
     * @param condicionFisicaRequerida Indica si el tour exige un estado físico particular.
     * @param valorTour                Costo monetario del tour.
     * @param dificultad               Nivel de dificultad del tour (Enum Dificultad).
     * @throws IllegalArgumentException Si el guía o la dificultad son nulos.
     */
    
    public GrupoTuristico(LocalTime horaInicio, LocalTime horaTermino, String destino, GuiaTuristico guiaEncargado,
                          boolean condicionFisicaRequerida,int valorTour,Dificultad dificultad) {
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.destino = destino;
        this.setGuiaEncargado(guiaEncargado);
        this.condicionFisicaRequerida = condicionFisicaRequerida;
        this.valorTour = valorTour;
        this.setDificultad(dificultad);
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
    

    //Metodos de [Horario]

    /**
     * Retorna una cadena de texto describiendo el bloque horario del tour.
     * * @return String con formato "Tour desde las HH:mm hrs hasta las HH:mm hrs", o "Horario por definir" si falta algún dato.
     */
    
    public String getHorario() {
        if (this.horaInicio == null || this.horaTermino == null) {
            return "Horario por definir";
        }
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        
        String inicioStr = this.horaInicio.format(formato);
        String terminoStr = this.horaTermino.format(formato);
        
        return "Tour desde las " + inicioStr + " hrs hasta las " + terminoStr + " hrs";
    }

    /**
     * Configura el horario de inicio y término del tour especificando horas y minutos.
     * * @param hInicio Hora de inicio (0-23).
     * @param mInicio Minuto de inicio (0-59).
     * @param hFin    Hora de término (0-23).
     * @param mFin    Minuto de término (0-59).
     * @throws IllegalArgumentException Si los valores de tiempo están fuera de los rangos válidos.
     */
    
    public void setHorarioTour(int hInicio, int mInicio, int hFin, int mFin) {
        try {
            LocalTime inicio = LocalTime.of(hInicio, mInicio);
            LocalTime fin = LocalTime.of(hFin, mFin);
            
            this.horaInicio = inicio;
            this.horaTermino = fin;
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Valores de tiempo inválidos. formato de 24 horas (0-23) y 60 minuitos (00-59)");
        }
    }
    
    //Metodos de [Destino]

    /**
     * Obtiene el destino del tour.
     * * @return El destino actual asignado.
     */
    
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del tour.
     * * @param destino Nombre del destino.
     */
    
    public void setDestino(String destino) {
        this.destino = destino;
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

    //Metodos de [Condicion fisica requerida]

    /**
     * Verifica si el tour exige una condición física particular.
     * * @return true si requiere buena condición física, false en caso contrario.
     */

    public boolean getCondicionFisicaRequerida() {
        return condicionFisicaRequerida;
    }

    /**
     * Establece si el tour exige una condición física particular.
     * * @param condicionFisicaRequerida Valor booleano indicando el requerimiento físico.
     */

    public void setCondicionFisicaRequerida(boolean condicionFisicaRequerida) {
        this.condicionFisicaRequerida = condicionFisicaRequerida;
    }

    //Metodos de [valor tour]

    /**
     * Obtiene el valor monetario del tour.
     * * @return Costo del tour.
     */

    public int getValorTour() {
        return valorTour;
    }

    /**
     * Establece el valor monetario del tour.
     * * @param valorTour Costo del tour.
     */

    public void setValorTour(int valorTour) {
        this.valorTour = valorTour;
    }


    //Metodos de [dificultad]

    /**
     * Obtiene el nivel de dificultad del tour.
     * * @return Enum Dificultad asignado.
     */

    public Dificultad getDificultad() {
        return dificultad;
    }

    /**
     * Asigna el nivel de dificultad del tour.
     * * @param dificultad Enum Dificultad a asignar.
     * @throws IllegalArgumentException Si la dificultad proporcionada es nula.
     */

    public void setDificultad(Dificultad dificultad) {
        if (dificultad == null) {
            throw new IllegalArgumentException("Dificultad no asignado para este tour");
        }
        this.dificultad = dificultad;
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
        
        resumen.append("=== Resumen del tour ===\n");
        resumen.append("Destino: ").append(this.destino).append("\n");
        resumen.append("Valor tour: $").append(this.valorTour).append("\n");
        resumen.append("Condicion fisica requerida: ").append((this.condicionFisicaRequerida ? "Se requiere buena condición fisica" : "Abierto a todo publico")).append("\n");
        resumen.append("Dificultad: ").append(this.dificultad).append("\n");
        
        resumen.append(this.getHorario()).append("\n");
        
        resumen.append("Guia encargado: ").append(this.getGuiaEncargado().getNombres()).append(" ").append(this.getGuiaEncargado().getApellidoPaterno()).append(" ").append(this.getGuiaEncargado().getApellidoMaterno()).append("\n");
        
        resumen.append("\n").append(this.getListadoParticipantes());
        
        return resumen.toString();
    }
    
}
