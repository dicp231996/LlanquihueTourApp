package model.entities;

import model.core.Persona;
import model.valueobjects.Direccion;
import model.valueobjects.Rut;

/**
 * Clase que representa a un Guía Turístico, la cual hereda de la clase base {@link Persona}.
 * Administra la información profesional y las competencias técnicas del guía,
 * tales como su nivel de inglés, especialidad y certificaciones de emergencia.
 * @author Daniel Campos
 * @version 1.0.0
 */

public class GuiaTuristico extends Persona {
    
    private NivelDeIngles nivelDeIngles;
    private String especialidad;
    private boolean certificadoPrimerosAuxilios;
    private boolean capacitacionRescate;

    /**
     * Enumerador que define los niveles de dominio del idioma inglés,
     * alineados generalmente con el Marco Común Europeo de Referencia (MCER).
     */
    
    public enum NivelDeIngles {
        A1("Principiante"),A2("Básico"),B1("Intermedio"),B2("Intermedio alto"),C1("Avanzado"),C2("Maestría / Nativo");
        
        private String nivelDeIngles;

        /**
         * Constructor de la enumeración NivelDeIngles.
         *
         * @param nivelDeIngles La descripción en texto del nivel de competencia.
         */
        
        NivelDeIngles (String nivelDeIngles){
            this.nivelDeIngles = nivelDeIngles;
        }

        /**
         * Obtiene la descripción textual del nivel de inglés.
         *
         * @return El nivel de dominio del idioma como cadena de caracteres.
         */
        
        public String getNivelDeIngles() {
            return nivelDeIngles;
        }
    }
    //Constructor por defecto

    /**
     * Constructor por defecto.
     * Invoca al constructor de la clase padre e inicializa al guía con un nivel de inglés A1 (Principiante), especialidad como "Guia general",
     * y establece que no posee certificaciones de primeros auxilios ni de rescate por defecto.
     */
    
    public GuiaTuristico() {
        this.nivelDeIngles = NivelDeIngles.A1;
        this.especialidad = "Guia general";
        this.certificadoPrimerosAuxilios = false;
        this.capacitacionRescate = false;
    }

    //Constructor con atributos

    /**
     * Constructor que inicializa un guía turístico con todos sus atributos personales (heredados) y profesionales.
     *
     * @param nombres                     Los nombres del guía turístico.
     * @param apellidoPaterno             El apellido paterno del guía.
     * @param apellidoMaterno             El apellido materno del guía.
     * @param nacionalidad                La nacionalidad de origen.
     * @param rut                         El documento de identidad (RUT) asociado al guía.
     * @param direccion                   La dirección de residencia del guía.
     * @param nivelDeIngles               El nivel de dominio del idioma inglés (Enum NivelDeIngles).
     * @param especialidad                El área de especialización profesional del guía.
     * @param certificadoPrimerosAuxilios Indica si el guía posee un certificado válido en primeros auxilios (true/false).
     * @param capacitacionRescate         Indica si el guía cuenta con capacitación formal para rescates (true/false).
     */
    
    public GuiaTuristico(String nombres, String apellidoPaterno, String apellidoMaterno, String nacionalidad, Rut rut, Direccion direccion, NivelDeIngles nivelDeIngles, String especialidad, boolean certificadoPrimerosAuxilios, boolean capacitacionRescate) {
         super(nombres, apellidoPaterno, apellidoMaterno, nacionalidad, rut, direccion);
         this.setNivelDeIngles(nivelDeIngles);
         this.setEspecialidad(especialidad);
         this.setCertificadoPrimerosAuxilios(certificadoPrimerosAuxilios);
         this.setCapacitacionRescate(capacitacionRescate);
     }
    
    //Metodos para el atributo [nivel de ingles]

    /**
     * Obtiene el nivel de inglés actual del guía turístico.
     *
     * @return El nivel de inglés asignado (Enum NivelDeIngles).
     */
    
    public NivelDeIngles getNivelDeIngles() {
        return nivelDeIngles;
    }

    /**
     * Establece el nivel de inglés del guía turístico.
     *
     * @param capacidadComunicativa El nivel de inglés a asignar.
     * @throws IllegalArgumentException si el valor proporcionado es nulo.
     */
    
    public void setNivelDeIngles(NivelDeIngles capacidadComunicativa) {
        if(capacidadComunicativa == null) {
            throw new IllegalArgumentException("NIvel de inglés sin declarar");            
        }
        this.nivelDeIngles = capacidadComunicativa;
    }
    
    //Metodos para el atributo [especialidad]

    /**
     * Obtiene la especialidad profesional del guía.
     *
     * @return El área de especialidad como cadena de caracteres.
     */
    
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece el área de especialidad del guía turístico.
     *
     * @param especialidad La especialidad a asignar.
     * @throws IllegalArgumentException si la especialidad es null o está en blanco.
     */
    
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("Sin especialidad declarada");             
        }
        this.especialidad = especialidad;
    }
    
    //Metodos para el atributo [Certificado de primeros auxilios]

    /**
     * Verifica si el guía posee certificación en primeros auxilios.
     *
     * @return {@code true} si está certificado, {@code false} en caso contrario.
     */
    
    public boolean getCertificadoPrimerosAuxilios() {
        return certificadoPrimerosAuxilios;
    }

    /**
     * Establece si el guía turístico cuenta con certificación en primeros auxilios.
     *
     * @param certificadoPrimerosAuxilios {@code true} para indicar que posee el certificado, {@code false} para indicar lo contrario.
     */
    
    public void setCertificadoPrimerosAuxilios(boolean certificadoPrimerosAuxilios) {
        this.certificadoPrimerosAuxilios = certificadoPrimerosAuxilios;
    }
    
    //Metodos para el atributo [Capacitacion de rescate]

    /**
     * Verifica si el guía tiene capacitación en operaciones de rescate.
     *
     * @return {@code true} si está capacitado, {@code false} en caso contrario.
     */
    
    public boolean getCapacitacionRescate() {
        return capacitacionRescate;
    }

    /**
     * Establece si el guía turístico cuenta con capacitación para rescate.
     *
     * @param capacitacionRescate {@code true} para indicar que está capacitado, {@code false} para indicar lo contrario.
     */
    
    public void setCapacitacionRescate(boolean capacitacionRescate) {
        this.capacitacionRescate = capacitacionRescate;
    }
    
    //Instancia de objeto

    /**
     * Retorna una representación en formato de texto con los datos personales y de formación del guía turístico.
     * Combina la información provista por la clase padre (Persona) con los detalles profesionales
     * específicos de esta clase.
     *
     * @return Una cadena de caracteres estructurada con la ficha completa del guía turístico.
     */
    
    @Override
    public String toString(){
        String datosPersonales = super.toString();
        
        StringBuilder reporte = new StringBuilder();
        reporte.append(datosPersonales).append("\n");
        reporte.append("=== Datos de formación ===\n");
        
        reporte.append("Nivel de inglés: ").append(this.nivelDeIngles.nivelDeIngles).append("\n");
        reporte.append("Especialidad: ").append(this.especialidad).append("\n");
        reporte.append("Certificado de primeros auxilios: ").append((this.certificadoPrimerosAuxilios ? "Cuenta con certificación" : "No cuenta con certificación")).append("\n");
        reporte.append("Esta capacitado para rescate: ").append((this.capacitacionRescate ? "Cuenta con certificación" : "No cuenta con certificación"));
        
        return reporte.toString();
    }

}
