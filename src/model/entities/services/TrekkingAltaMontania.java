package model.entities.services;

import model.core.ServicioTuristico;

import java.time.LocalTime;

public class TrekkingAltaMontania extends ServicioTuristico {

    private int altitudMaxima;
    private boolean incluyeEquipoTecnico;


    public TrekkingAltaMontania() {
        super();
        this.altitudMaxima = 0;
        this.incluyeEquipoTecnico = false;
    }

    public TrekkingAltaMontania(String nombre, int horaInicio, int minutoInicio, int horaTermino, int minutoTermino, int valorTour, Dificultad dificultad,
                                int altitudMaxima, boolean incluyeEquipoTecnico) {
        super(nombre, horaInicio,minutoInicio, horaTermino,minutoTermino, valorTour, dificultad);
        this.altitudMaxima = altitudMaxima;
        this.incluyeEquipoTecnico = incluyeEquipoTecnico;

    }

    //Metodos de altitud maxima

    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        if (altitudMaxima < 0) {
            throw new IllegalArgumentException("Altura debe ser mayor a 0 mts");
        }
        this.altitudMaxima = altitudMaxima;
    }

    //Metodos de incluye equipo tecnico

    public boolean isIncluyeEquipoTecnico() {
        return incluyeEquipoTecnico;
    }

    public void setIncluyeEquipoTecnico(boolean incluyeEquipoTecnico) {
        this.incluyeEquipoTecnico = incluyeEquipoTecnico;
    }


    //Instancia de objeto
    @Override
    public String toString() {
        String datosGenerales = super.toString();

        StringBuilder datosTour = new StringBuilder();
        datosTour.append(datosGenerales).append("\n");
        datosTour.append("Altura Maxima de ascenso: ").append(this.altitudMaxima).append("\n");
        datosTour.append("Necesita equipo especializado: ").append(this.incluyeEquipoTecnico ? "Se incluye equipo tecnico para el recorrido":"No se incluye equipo tecnico para el recorrido").append("\n");

        return datosTour.toString();
    }
}
