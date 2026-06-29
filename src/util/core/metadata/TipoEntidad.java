package util.core.metadata;
/**
 * Define las entidades principales manejadas por el sistema y actúa como un registro central de metadatos.
 * Cada constante del enumerador encapsula la configuración estructural de una entidad, vinculando
 * su representación física en el disco duro (ruta del archivo) con su representación lógica en la memoria
 * (esquema de campos y validaciones).
 */

public enum TipoEntidad {
    /**
     * Representa la entidad Turista.
     * Su esquema de datos está configurado para capturar información personal,
     * ubicación geográfica y detalles específicos de la reserva hotelera.
     */

    TURISTA("resources/base_datos_turistas.txt", new Campo[]{
            new Campo("Nombres: ", TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Paterno: ",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Materno: ",TipoDato.TEXTO_LIBRE),
            new Campo("Nacionalidad: ",TipoDato.TEXTO_LIBRE),
            new Campo("Rut (ej. 10.000.000-0): ",TipoDato.RUT),
            new Campo("Nombre calle: ",TipoDato.TEXTO_LIBRE),
            new Campo("Número domicilio: ",TipoDato.TEXTO_LIBRE),
            new Campo("Comuna: ",TipoDato.TEXTO_LIBRE),
            new Campo("Región: ",TipoDato.REGION),
            new Campo("Habitacion reservada: ",TipoDato.TEXTO_LIBRE),
            new Campo("Días de reserva: ",TipoDato.NUMERO_ENTERO),
            new Campo("ID del grupo deseado: ",TipoDato.SELECCION_TOUR_INTERACTIVO)
    }),
    /**
     * Representa la entidad Guía Turístico.
     * Su esquema de datos incluye información personal básica y añade campos específicos
     * de evaluación profesional, como certificaciones de rescate y nivel de dominio de idiomas.
     */
    GUIA("resources/base_datos_guias_turisticos.txt", new Campo[]{
            new Campo("Nombres:",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Paterno:",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Materno:",TipoDato.TEXTO_LIBRE),
            new Campo("Nacionalidad:",TipoDato.TEXTO_LIBRE),
            new Campo("Rut (ej. 10.000.000-0):",TipoDato.RUT),
            new Campo("Nombre calle:",TipoDato.TEXTO_LIBRE),
            new Campo("Número domicilio:",TipoDato.TEXTO_LIBRE),
            new Campo("Comuna:",TipoDato.TEXTO_LIBRE),
            new Campo("Región:",TipoDato.REGION),
            new Campo("Nivel de Inglés (A1, A2, B1, B2, C1, C2):",TipoDato.NIVEL_INGLES),
            new Campo("Capacitado para prestar primeros auxilios:",TipoDato.BOOLEAN),
            new Campo("Capacitado para realizar rescate:",TipoDato.BOOLEAN)
    }),
    /**
     * Representa la entidad Grupo Turístico (Tour).
     * Su esquema está diseñado para estructurar la logística del recorrido, capturando
     * métricas de tiempo (horas y minutos), destino, costos, requerimientos físicos.
     * Rut guía asignado es una idea descartada, eventualmente se descartará para asociarlo a un número unico de empleado
     */
    GRUPO("resources/base_datos_tours.txt", new Campo[]{
            new Campo("ID del grupo: ",TipoDato.TEXTO_LIBRE),
            new Campo("N° de empleado del guía asignado: ",TipoDato.TEXTO_LIBRE),
            new Campo("N° de servicio: ",TipoDato.TEXTO_LIBRE)
    }),
    PASEO_LACUSTRE("resources/base_datos_paseos_lacustres.txt", new Campo[]{
            new Campo("ID de servicio: ", TipoDato.TEXTO_LIBRE),
            new Campo("Destino:",TipoDato.TEXTO_LIBRE),
            new Campo("Hora inicio (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto inicio (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Hora termino (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto termino (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Valor del tour: ",TipoDato.NUMERO_ENTERO),
            new Campo("Dificultad del recorrido:",TipoDato.DIFICULTAD),
            new Campo("Tipo de embarcacion: ",TipoDato.TEXTO_LIBRE),
            new Campo("Capacidad de embarcacion:",TipoDato.NUMERO_ENTERO),
            new Campo("Salvavidas:",TipoDato.TEXTO_LIBRE)
    }),
    RUTA_PATRIMONIAL("resources/base_datos_ruta_patrimonial.txt", new Campo[]{
            new Campo("ID de servicio: ", TipoDato.TEXTO_LIBRE),
            new Campo("Destino:",TipoDato.TEXTO_LIBRE),
            new Campo("Hora inicio (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto inicio (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Hora termino (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto termino (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Valor del tour: ",TipoDato.NUMERO_ENTERO),
            new Campo("Dificultad del recorrido:",TipoDato.DIFICULTAD),
            new Campo("¿Incluye degustación?:",TipoDato.BOOLEAN),
            new Campo("¿Tiene restriccion de edad?: ",TipoDato.BOOLEAN),
            new Campo("Paradas programadas: ",TipoDato.NUMERO_ENTERO)
    }),
    TREKKING("resources/base_datos_trekking.txt", new Campo[]{
            new Campo("ID de servicio: ", TipoDato.TEXTO_LIBRE),
            new Campo("Destino:",TipoDato.TEXTO_LIBRE),
            new Campo("Hora inicio (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto inicio (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Hora termino (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto termino (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Valor del tour: ",TipoDato.NUMERO_ENTERO),
            new Campo("Dificultad del recorrido:",TipoDato.DIFICULTAD),
            new Campo("Altitud maxima: ",TipoDato.NUMERO_ENTERO),
            new Campo("¿Incluye equipo tecnico?: ",TipoDato.BOOLEAN)
    }),
    AVISTAMIENTO_HUMEDAL("resources/base_datos_avistamiento.txt", new Campo[]{
            new Campo("ID de servicio: ", TipoDato.TEXTO_LIBRE),
            new Campo("Destino:",TipoDato.TEXTO_LIBRE),
            new Campo("Hora inicio (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto inicio (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Hora termino (entre 0 a 23):",TipoDato.HORA),
            new Campo("Minuto termino (entre 0 a 59):",TipoDato.MINUTO),
            new Campo("Valor del tour: ",TipoDato.NUMERO_ENTERO),
            new Campo("Dificultad del recorrido:",TipoDato.DIFICULTAD),
            new Campo("Incluye binoculares: ",TipoDato.BOOLEAN),
            new Campo("Estacion recomendada para el avistamiento:",TipoDato.ESTACION_RECOMENDADA)
    });
    /**
     * Variable estática global reservada para el almacenamiento temporal o acceso estático a la ruta del archivo.
     */
    public static String getRutaArchivo;
    private final String rutaArchivo;
    private final Campo[] esquemaDatos;
    /**
     * Construye una nueva instancia del enumerador con su configuración física y lógica.
     *
     * @param rutaArchivo  La ruta relativa en el sistema de archivos donde se persistirán y leerán los registros de esta entidad.
     * @param esquemaDatos Un arreglo de objetos {@link Campo} que define el orden, la instrucción
     * y la validación de cada dato asociado a la entidad.
     */
    TipoEntidad(String rutaArchivo, Campo[] esquemaDatos) {
        this.rutaArchivo = rutaArchivo;
        this.esquemaDatos = esquemaDatos;
    }
    /**
     * Recupera la ubicación física del archivo de texto asociado a esta entidad.
     *
     * @return Una cadena de texto con la ruta del archivo (ej. "resources/base_datos_turistas.txt").
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }
    /**
     * Recupera la estructura de metadatos (campos y reglas) asignada a esta entidad.
     *
     * @return Un arreglo de objetos {@link Campo} que representa el esquema de la base de datos.
     */
    public Campo[] getEsquemaDatos() {
        return esquemaDatos;
    }
}
