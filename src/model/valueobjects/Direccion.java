package model.valueobjects;

/**
 * Clase que representa una dirección como un (Value Object).
 * Encapsula los componentes para formar una direccion chilena basica, incluye calle, número,
 * comuna y región.
 * @author Daniel Campos
 * @version 1.0.0
 */

public class Direccion {
    
    private String calle;
    private String numero;
    private String comuna;
    private Region region;

    /**
     * Enumerador que representa las regiones territoriales de Chile.
     * El enumerador entrega como resultado el nombre oficial de la región correspondiente.
     */
    
    public enum Region {
        ARICA("Arica y Parinacota"),TARAPACA("Iquique"),ANTOFAGASTA("Antofagasta"),ATACAMA("Copiapo"),
        LA_SERENA("Coquimbo"),VALPARAISO("Valparaíso"),SANTIAGO("Metropolitana de Santiago"),O_HIGGINS("Rancagua"),
        MAULE("Talca"),NUBLE("Chillan"),CONCEPCION("Biobío"),TEMUCO("La Araucanía"),
        VALDIVIA("Los Ríos"),PUERTO_MONTT("Los Lagos"),COYHAIQUE("Aysén del General Carlos Ibáñez del Campo"),PUNTA_ARENAS("Magallanes y de la Antártica Chilena");
        
        private String region;

        /**
         * Constructor del enumerador de regiónes
         * @param region el nombre oficial de la región
         */
        
        Region(String region) {
            this.region = region;
        }

        /**
         * Obtiene un string que contiene el nombre oficial de la región
         * @return el nombre de la region como cadena de caracteres.
         */
        
        public String getRegion() {
            return region;
        }
    }
    
    //Constructor por defecto

    /**
     * Constructor de la instancia por defecto.
     * Inicializa la calle, número y comuna como cadenas de caracteres vacías.
     * Establece la región por defecto en SANTIAGO.
     */
    
    public Direccion() {
        this.calle = "";
        this.numero = "";
        this.comuna = "";
        this.region = Region.SANTIAGO;        
    }
    
    //Constructor con atributos

    /**
     * Constructor que crea una instancia de la clase con los atributos proporcionados.
     *
     * @param calle El nombre de la calle como cadena de caracteres
     * @param numero El número de la calle como cadena de caracteres
     * @param comuna El nombre de la comuna como cadena de caracteres
     * @param region La región territorial del país asignada por medio de un enumerador (Enum Region).
     */
    
    public Direccion(String calle, String numero, String comuna,Region region) {

        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;  
        this.setRegion(region);        
    }
    

    //Metodos de [calle]

    /**
     * Obtiene el nombre de la calle de la dirección.
     *
     * @return El nombre de la calle.
     *
     */
    
    public String getCalle() {
        return calle;
    }

    /**
     *Establece el nombre de la calle de la dirección.
     *
     * @param calle El nombre de la calle a asignar.
     */
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    //Metodos de [numero]

    /**
     * Obtiene el número de la dirección.
     *
     * @return El número de la dirección.
     */
    
    public String getNumero() {
        return numero;
    }

    /**
     *Establece el número de la dirección.
     *
     * @param numero El número de loteo de la dirección a asignar.
     */
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    //Metodos de [comuna]

    /**
     * Obtiene la comuna de la dirección.
     *
     * @return El nombre de la comuna.
     */
    
    public String getComuna() {
        return comuna;
    }

    /**
     * Establece la comuna de la direccion.
     *
     * @param comuna La comuna de la direccion a asignar.
     */
    
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
        
    //Metodos de [region]

    /**
     * Obtiene la región territorial de la dirección.
     *
     * @return La región territorial (Enum Region)
     */
    
    public Region getRegion() {
        return region;        
    }

    /**
     * Establece la región de la dirección con su respectiva validación.
     *
     * @param region La región territorial de la dirección asignada.
     * @throws IllegalArgumentException si el valor de region es null.
     */
    
    public void setRegion(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Región no definida");
        }
        this.region = region;
    }

    //Instancia de objeto

    /**
     * Retorna una representación en formato de texto de la dirección completa.
     * El formato devuelto es: "[calle] [número], [comuna], [nombre oficial de la región]".
     *
     * @return Una cadena de texto que representa la dirección formateada.
     */
    
    @Override
    public String toString() {
        StringBuilder direccionFormato = new StringBuilder();
        
        direccionFormato.append(this.calle).append(" ");
        direccionFormato.append(this.numero).append(", ");
        direccionFormato.append(this.comuna).append(", ");
        direccionFormato.append(this.region.getRegion());
        
        return direccionFormato.toString();
    }
        
}