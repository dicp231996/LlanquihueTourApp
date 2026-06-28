package model.entities.actors;

import model.core.Persona;
import model.core.ServicioTuristico;
import model.valueobjects.Direccion;
import model.valueobjects.GrupoTuristico;
import model.valueobjects.Rut;

/**
 * Clase que representa a un Turista, la cual hereda de la clase base {@link Persona}.
 * Administra la información relacionada con el alojamiento del turista y
 * el grupo turístico al que se registró.
 * @author Daniel Campos
 * @version 1.0.0
 */


public class Turista extends Persona {
    
    private String habitacionReservada;
    private int diasReserva;
    private ServicioTuristico tourContratado;
    
    //Constructor por defecto

    /**
     * Constructor por defecto.
     * Invoca al constructor de la clase padre e inicializa la habitación como "Sin reservación",
     * los días de reserva en 0, y asigna un nuevo grupo turístico por defecto.
     */
    
    public Turista() {
        super();
        this.habitacionReservada = "Sin reservación";
        this.diasReserva = 0;
        this.tourContratado = null;
    }
    
    //Constructor con atributos

    /**
     * Constructor que inicializa un turista con todos sus atributos personales (heredados),
     * de alojamiento y de tour.
     *
     * @param nombres             Los nombres del turista.
     * @param apellidoPaterno     El apellido paterno del turista.
     * @param apellidoMaterno     El apellido materno del turista.
     * @param nacionalidad        La nacionalidad del turista.
     * @param rut                 El documento de identidad (RUT) del turista.
     * @param direccion           La dirección de procedencia del turista.
     * @param habitacionReservada El número o identificador de la habitación reservada.
     * @param diasReserva         La cantidad de días de alojamiento reservados.
     * @param servicioTuristico      El grupo turístico al que se integra el turista.
     */
    
    public Turista(String nombres, String apellidoPaterno, String apellidoMaterno,String nacionalidad, Rut rut, Direccion direccion,
                   String habitacionReservada, int diasReserva, ServicioTuristico tourContratado) {
        super(nombres, apellidoPaterno, apellidoMaterno, nacionalidad, rut, direccion);
        this.setHabitacionReservada(habitacionReservada);
        this.setDiasReserva(diasReserva);
        this.setTourContratado(tourContratado);
    }
    
    //Metodos para el atributo [Habitación reservada]

    /**
     * Obtiene la habitación reservada por el turista.
     *
     * @return El identificador de la habitación.
     */


    public String getHabitacionReservada() {
        return habitacionReservada;
    }

    /**
     * Establece la habitación reservada para el turista.
     *
     * @param habitacionReservada El número o nombre de la habitación a asignar.
     * @throws IllegalArgumentException si la habitación proporcionada es null o está en blanco.
     */
    
    public void setHabitacionReservada(String habitacionReservada) {
        if (habitacionReservada == null || habitacionReservada.isBlank()) {
            throw new IllegalArgumentException("Habitación sin asignar");
        }
        this.habitacionReservada = habitacionReservada;
    }
    
    //Metodos para el atributo [Días reserva]

    /**
     * Obtiene la cantidad de días que el turista tiene reservados.
     *
     * @return El número de días de reserva.
     */
    
    public int getDiasReserva() {
        return diasReserva;
    }

    /**
     * Establece la cantidad de días de reserva del turista.
     *
     * @param diasReserva El número de días a asignar.
     * @throws IllegalArgumentException si la cantidad de días es menor o igual a 0.
     */

    public void setDiasReserva(int diasReserva) {
        if (diasReserva <= 0) {
            throw new IllegalArgumentException("La reserva debe ser de al menos 1 día");
        }
        this.diasReserva = diasReserva;
    }
    
    //Metodos para el [Grupo turistico]

    /**
     * Obtiene el grupo turístico al que pertenece el turista.
     *
     * @return El objeto {@link GrupoTuristico} asociado.
     */
    
    public ServicioTuristico getTourContratado() {
        return tourContratado;
    }

    /**
     * Asigna un grupo turístico al turista.
     *
     * @param grupo El grupo turístico a asignar.
     * @throws IllegalArgumentException si el grupo proporcionado es nulo.
     */
    
    public void setTourContratado(ServicioTuristico tourContratado) {
        this.tourContratado = tourContratado;
    }

    public void vincularGrupo(GrupoTuristico grupoTuristico) {
        if (grupoTuristico == null) {
            throw new IllegalArgumentException("Grupo no puede asignar");
        }
        grupoTuristico.inscribirParticipante(this);
        this.tourContratado = grupoTuristico.getActividad();
    }
    
    //Instancia de objeto

    /**
     * Retorna una representación en formato de texto con los datos personales,
     * información de alojamiento y el destino del tour contratado por el turista.
     * Combina la información provista por la clase padre (Persona) con los
     * detalles específicos de esta clase.
     *
     * @return Una cadena de texto estructurada con la ficha completa del turista.
     */
    
    @Override
    public String toString(){
        String datosPersonales = super.toString();
        
        StringBuilder reporte = new StringBuilder();
        reporte.append(datosPersonales).append("\n");
        
        reporte.append("=== Datos de alojamiento ===\n");
        reporte.append("Habitación: ").append(this.habitacionReservada).append("\n");
        reporte.append("Días de reservavión: ").append(this.diasReserva).append("\n");

        if(this.tourContratado != null){
            reporte.append("\n").append(this.tourContratado.toString()).append("\n");
        }
        else {
            reporte.append("Destino del tour contratado: [Sin tour contratado]\n");
        }
        return reporte.toString();
    }
}
