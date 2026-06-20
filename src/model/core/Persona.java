package model.core;

import model.valueobjects.Direccion;
import model.valueobjects.Rut;

/**
 * Clase abstracta que representa a una Persona de forma genérica.
 * Centraliza los atributos y comportamientos comunes de cualquier persona en el sistema,
 * como su nombre completo, nacionalidad, documento de identidad (RUT) y dirección.
 * @author Daniel Campos
 * @version 1.0.0
 */

public abstract class Persona {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nacionalidad;
    private Rut rut;
    private Direccion direccion;
    
    //Constructor por defecto

    /**
     * Constructor por defecto.
     * Inicializa los datos personales y nacionalidad como "Sin registro",
     * e instancia un RUT y una Dirección con el constructor por defecto.
     */
    
    public Persona() {
        this.nombres = "Sin registro";
        this.apellidoPaterno = "Sin registro";
        this.apellidoMaterno = "Sin registro";
        this.nacionalidad = "Sin registro";
        this.rut = new Rut();
        this.direccion = new Direccion();
    }
    
    //Constructor con atributos

    /**
     * Constructor que inicializa a la persona con todos sus atributos específicos.
     *
     * @param nombres         Los nombres de la persona.
     * @param apellidoPaterno El apellido paterno de la persona.
     * @param apellidoMaterno El apellido materno de la persona.
     * @param nacionalidad    La nacionalidad de la persona.
     * @param rut             El documento de identidad (RUT) asociado a la persona.
     * @param direccion       La dirección de residencia de la persona.
     */
    
    public Persona(String nombres, String apellidoPaterno, String apellidoMaterno, String nacionalidad, Rut rut, Direccion direccion) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.setNacionalidad(nacionalidad);
        this.rut = rut;
        this.direccion = direccion;
    }
    
    //Metodos para el argumento [nombres]

    /**
     * Obtiene los nombres de la persona.
     *
     * @return Los nombres como cadena de caracteres.
     */

    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres de la persona.
     *
     * @param nombres Los nombres a asignar.
     */

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    //Metodos para el argumento [Apellido paterno]

    /**
     * Obtiene el apellido paterno de la persona.
     *
     * @return El apellido paterno como cadena de caracteres.
     */
    
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno de la persona.
     *
     * @param apellidoPaterno El apellido paterno a asignar.
     */
    
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    
    //Metodos para el argumento [Apellido materno]

    /**
     * Obtiene el apellido materno de la persona.
     *
     * @return El apellido materno como cadena de caracteres.
     */
    
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno de la persona.
     *
     * @param apellidoMaterno El apellido materno a asignar.
     */
    
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    //Metodos para el argumento [Nacionalidad]

    /**
     * Obtiene la nacionalidad de la persona.
     *
     * @return La nacionalidad como cadena de caracteres.
     */
    
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad de la persona.
     *
     * @param nacionalidad La nacionalidad a asignar.
     * @throws IllegalArgumentException si la nacionalidad es nula o está en blanco.
     */


    public void setNacionalidad(String nacionalidad) {
        if (nacionalidad == null || nacionalidad.isBlank()) {
            throw new IllegalArgumentException("Sin nacionalidad declarada");
        }
        this.nacionalidad = nacionalidad;
    }
    
    //Metodos para el argumento [Rut]

    /**
     * Obtiene el documento de identidad (RUT) de la persona.
     *
     * @return El objeto {@link Rut} asociado.
     */
    
    public Rut getRut() {
        return rut;
    }

    /**
     * Establece el documento de identidad (RUT) de la persona.
     *
     * @param rut El objeto {@link Rut} a asignar.
     */
    
    public void setRut(Rut rut) {
        this.rut = rut;
    }

    //Metodos para el argumento [Dirección]

    /**
     * Obtiene la dirección de la persona.
     *
     * @return El objeto {@link Direccion} asociado.
     */
    
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de la persona.
     *
     * @param direccion El objeto {@link Direccion} a asignar.
     */
    
    public void setDireccion() {
        this.direccion = direccion;
    }
    
    //Instancia de objeto

    /**
     * Retorna una representación en formato de texto con los datos básicos de la persona.
     *
     * @return Una cadena de caracteres estructurada con el nombre completo, nacionalidad, RUT y dirección.
     */
    
    @Override
    public String toString() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("\n=== Datos personales ===\n");
        reporte.append("Nombre completo: ").append(this.nombres).append(" ").append(this.apellidoPaterno).append(" ").append(this.apellidoMaterno).append("\n");
        reporte.append("Nacionalidad: ").append(this.nacionalidad).append("\n");
        reporte.append("Rut: ").append(this.rut).append("\n");
        reporte.append("Dirección: ").append(this.direccion).append("\n");
        
        return reporte.toString();
    }
}
