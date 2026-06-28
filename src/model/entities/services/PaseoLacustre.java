package model.entities.services;

import model.core.ServicioTuristico;

import java.time.LocalTime;

public class PaseoLacustre extends ServicioTuristico {
    private String tipoEmbarcacion;
    private int capacidadEmbarcacion;
    private String salvavidas;

    public PaseoLacustre() {
        super();
        this.tipoEmbarcacion = "Lancha a motor";
        this.capacidadEmbarcacion = 1;
        this.salvavidas = "Por normativa Chilena todo pasajero debe contar con salvavidas";
    }

    public PaseoLacustre(String destino, int horaInicio, int minutoInicio, int horaTermino, int minutoTermino, int valorTour, Dificultad dificultad,
                         String tipoEnvarcacion, int capacidadEnvarcacion, String requiereSalvavidas) {
        super(destino, horaInicio, minutoInicio, horaTermino, minutoTermino, valorTour, dificultad);
        this.tipoEmbarcacion = tipoEnvarcacion;
        this.capacidadEmbarcacion = capacidadEnvarcacion;
        this.salvavidas = requiereSalvavidas;
    }

    //Metodos de tipo de envarcacion

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    //Metodos de capacidad de envarcacion

    public int getCapacidadEmbarcacion() {
        return capacidadEmbarcacion;
    }

    public void setCapacidadEmbarcacion(int capacidadEmbarcacion) {
        this.capacidadEmbarcacion = capacidadEmbarcacion;
    }

    //Metodos de salvavidas

    public String getSalvavidas() {
        return salvavidas;
    }

    public void setSalvavidas(String salvavidas) {
        this.salvavidas = salvavidas;
    }

    //Instancia de objetos

    @Override
    public String toString() {
        String datosGenerales = super.toString();

        StringBuilder datosTour = new StringBuilder();
        datosTour.append(datosGenerales).append("\n");
        datosTour.append("Tipo de embarcacion: ").append(this.tipoEmbarcacion).append("\n");
        datosTour.append("Capacidad de envarcación").append(this.capacidadEmbarcacion).append("\n");
        datosTour.append("Salvavidas: ").append(this.salvavidas).append("\n");

        return datosTour.toString();
    }
}
