package model.entities.services;

import model.core.ServicioTuristico;

import java.time.LocalTime;

public class AvistamientoHumedales extends ServicioTuristico {
    private boolean incluyeBinoculares;
    private EstacionRecomendada estacionRecomendada;

    public enum EstacionRecomendada {
        VERANO("Verano"), OTONIO("Otoño"), INVIERNO("Invierno"), PRIMAVERA("Primavera");

        private String estacionRecomendada;
        EstacionRecomendada(String estacionRecomendada) {
            this.estacionRecomendada = estacionRecomendada;
        }

        public String getEstacionRecomendada() {
            return estacionRecomendada;
        }
    }

    //Constructor por defecto

    public AvistamientoHumedales() {
        super();
        this.incluyeBinoculares = false;
        this.estacionRecomendada = EstacionRecomendada.PRIMAVERA;
    }

    //Contructor con atributos

    public AvistamientoHumedales(String nombre, int horaInicio, int minutoInicio, int horaTermino, int minutoTermino, int valorTour, Dificultad dificultad,
                                 boolean incluyeBinoculares, EstacionRecomendada estacionRecomendada) {
        super(nombre, horaInicio,minutoInicio, horaTermino,minutoTermino, valorTour, dificultad);
        this.incluyeBinoculares = incluyeBinoculares;
        this.setEstacionRecomendada(estacionRecomendada);
    }

    //Metodos de incluye binoculares

    public boolean getIncluyeBinoculares() {
        return incluyeBinoculares;
    }

    public void setIncluyeBinoculares(boolean incluyeBinoculares) {
        this.incluyeBinoculares = incluyeBinoculares;
    }

    //Metodos de Estacion recomendada

    public EstacionRecomendada getEstacionRecomendada() {
        return estacionRecomendada;
    }

    public void setEstacionRecomendada(EstacionRecomendada estacionRecomendada) {
        this.estacionRecomendada = estacionRecomendada;
    }

    //Instancia de objeto

    @Override
    public String toString() {
        String datosGenerales = super.toString();

        StringBuilder datosTour = new StringBuilder();
        datosTour.append("").append(datosGenerales).append("\n");
        datosTour.append("Incluye binoculares: ").append(this.incluyeBinoculares ? "El tour incluye binoculares":"El tour no incluye binoculares").append("\n");
        datosTour.append("Estacion recomendada: ").append(this.getEstacionRecomendada());

        return datosTour.toString();
    }


}
