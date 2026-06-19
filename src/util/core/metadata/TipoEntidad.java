package util.core.metadata;

public enum TipoEntidad {

    TURISTA("resource/base_datos_turistas.txt", new Campo[]{
            new Campo("Nombres", TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Paterno",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Materno",TipoDato.TEXTO_LIBRE),
            new Campo("Nacionalidad",TipoDato.TEXTO_LIBRE),
            new Campo("Rut (ej. 10.000.000-0)",TipoDato.RUT),
            new Campo("Nombre calle",TipoDato.TEXTO_LIBRE),
            new Campo("Número domicilio",TipoDato.TEXTO_LIBRE),
            new Campo("Comuna",TipoDato.TEXTO_LIBRE),
            new Campo("Región",TipoDato.REGION),
            new Campo("Habitacion reservada",TipoDato.TEXTO_LIBRE),
            new Campo("Días de reserva",TipoDato.NUMERO_ENTERO)
    }),

    GUIA("resource/base_datos_guias_turisticos.txt", new Campo[]{
            new Campo("Nombres",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Paterno",TipoDato.TEXTO_LIBRE),
            new Campo("Apellido Materno",TipoDato.TEXTO_LIBRE),
            new Campo("Nacionalidad",TipoDato.TEXTO_LIBRE),
            new Campo("Rut (ej. 10.000.000-0)",TipoDato.RUT),
            new Campo("Nombre calle",TipoDato.TEXTO_LIBRE),
            new Campo("Número domicilio",TipoDato.TEXTO_LIBRE),
            new Campo("Comuna",TipoDato.TEXTO_LIBRE),
            new Campo("Región",TipoDato.REGION),
            new Campo("Nivel de Inglés (A1, A2, B1, B2, C1, C2)",TipoDato.NIVEL_INGLES),
            new Campo("Capacitado para prestar primeros auxilios",TipoDato.BOOLEAN),
            new Campo("Capacitado para realizar rescate",TipoDato.BOOLEAN)
    }),

    GRUPO("resource/base_datos_tours.txt", new Campo[]{
            new Campo("Hora inicio (entre 0 a 23)",TipoDato.HORA),
            new Campo("Minuto inicio (entre 0 a 59)",TipoDato.MINUTO),
            new Campo("Hora termino (entre 0 a 23)",TipoDato.HORA),
            new Campo("Minuto termino (entre 0 a 59)",TipoDato.MINUTO),
            new Campo("Destino",TipoDato.TEXTO_LIBRE),
            new Campo("Rut del guia asignado(ej. 10.000.000-0)",TipoDato.RUT),
            new Campo("Condicion física requerida",TipoDato.BOOLEAN),
            new Campo("Valor del tour",TipoDato.NUMERO_ENTERO),
            new Campo("Dificultad del recorrido",TipoDato.DIFICULTAD)
    });

    public static String getRutaArchivo;
    private final String rutaArchivo;
    private final Campo[] esquemaDatos;

    TipoEntidad(String rutaArchivo, Campo[] esquemaDatos) {
        this.rutaArchivo = rutaArchivo;
        this.esquemaDatos = esquemaDatos;
    }
    public String getRutaArchivo() {
        return rutaArchivo;
    }
    public Campo[] getEsquemaDatos() {
        return esquemaDatos;
    }
}
