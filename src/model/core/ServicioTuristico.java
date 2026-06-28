package model.core;

import model.valueobjects.GrupoTuristico;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class ServicioTuristico {
    private String destino;
    private LocalTime horaInicio;
    private LocalTime horaTermino;
    private int valorTour;
    private Dificultad dificultad;


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

    public ServicioTuristico() {
        this.destino = "";
        this.horaInicio = LocalTime.of(0,0);
        this.horaTermino = LocalTime.of(0,0);
        this.valorTour = 0;
        this.dificultad = Dificultad.PRINCIPIANTE;
    }

    public ServicioTuristico(String destino, int horaInicio,int minutoInicio, int horaTermino, int minutoTermino, int valorTour, Dificultad dificultad) {
        this.destino = destino;
        this.setHorarioTour(horaInicio,minutoInicio,horaTermino,minutoTermino);
        this.valorTour = valorTour;
        this.setDificultad(dificultad);
    }

    //Metodos de nombre

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    @Override
    public String toString() {
        StringBuilder resumen = new StringBuilder();

        resumen.append("=== Datos tour ===\n");
        resumen.append("Nombre: ").append(this.destino).append("\n");
        resumen.append("Valor tour: $").append(this.valorTour).append("\n");
        resumen.append("Dificultad: ").append(this.dificultad).append("\n");

        resumen.append(this.getHorario()).append("\n");

        return resumen.toString();
    }
}
