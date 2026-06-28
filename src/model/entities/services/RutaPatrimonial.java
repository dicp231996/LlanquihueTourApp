package model.entities.services;

import model.core.ServicioTuristico;

import java.time.LocalTime;

public class RutaPatrimonial extends ServicioTuristico {
    private boolean incluyeDegustacion;
    private boolean restriccionEdad;
    private int paradasProgramadas;

    public RutaPatrimonial() {
        super();
        this.incluyeDegustacion = false;
        this.restriccionEdad = false;
        this.paradasProgramadas = 1;
    }

    public RutaPatrimonial(String nombre, int horaInicio, int minutoInicio, int horaTermino, int minutoTermino, int valorTour, Dificultad dificultad,
                           boolean incluyeDegustacion, boolean restriccionEdad, int paradasProgramadas) {
        super(nombre, horaInicio,minutoInicio, horaTermino,minutoTermino, valorTour, dificultad);
        this.incluyeDegustacion = incluyeDegustacion;
        this.restriccionEdad = restriccionEdad;
        this.paradasProgramadas = paradasProgramadas;
    }

    //Metodos de incluye degustacion

    public boolean isIncluyeDegustacion() {
        return incluyeDegustacion;
    }

    public void setIncluyeDegustacion(boolean incluyeDegustacion) {
        this.incluyeDegustacion = incluyeDegustacion;
    }

    //Metodos de restriccion de edad

    public boolean isRestriccionEdad() {
        return restriccionEdad;
    }

    public void setRestriccionEdad(boolean restriccionEdad) {
        this.restriccionEdad = restriccionEdad;
    }

    //Metodos de paradas programadas

    public int getParadasProgramadas() {
        return paradasProgramadas;
    }

    public void setParadasProgramadas(int paradasProgramadas) {
        this.paradasProgramadas = paradasProgramadas;
    }

    //Instancia de objetos

    @Override
    public String toString() {
        String datosGenerales = super.toString();

        StringBuilder datosTour = new StringBuilder();
        datosTour.append(datosGenerales).append("\n");
        datosTour.append("Incluye degustación: ").append(this.incluyeDegustacion ? "Tour incluye degustación":"Tour no incluye degustación").append("\n");
        datosTour.append("Tiene restricción de edad: ").append(this.restriccionEdad ? "El tour es para mayores de edad":"Tour apto para todo publico").append("\n");
        datosTour.append("Paradas programadas: ").append(this.paradasProgramadas).append("\n");

        return datosTour.toString();
    }
}
