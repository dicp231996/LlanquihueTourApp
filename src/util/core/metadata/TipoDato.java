package util.core.metadata;

/**
 * Define los identificadores de tipos de datos y reglas de validación disponibles en el sistema.
 * Estos enumeradores son consumidos por el motor de validación (GestorValidaciones) para determinar
 * el formato lógico y estructural que debe cumplir la entrada del usuario antes de ser aceptada y almacenada.
 */

public enum TipoDato {
    /**
     * Representa una cadena de texto estándar sin restricciones estrictas de formato.
     * Utilizada generalmente para capturar nombres, apellidos o descripciones generales.
     */
    TEXTO_LIBRE,
    /**
     * Representa el Rol Único Tributario (RUT).
     * Su regla asociada exige el cumplimiento de un formato específico y la validez matemática del dígito verificador.
     */
    RUT,
    /**
     * Representa un valor lógico de verdad (verdadero o falso).
     * Utilizado para evaluar estados binarios, como la tenencia de certificaciones o requerimientos físicos.
     */
    BOOLEAN,
    /**
     * Representa un número entero estricto.
     * Utilizado para cuantificar métricas discretas como los días de estadía o la cantidad de participantes.
     */
    NUMERO_ENTERO,
    /**
     * Representa la métrica de horas en un formato de reloj.
     * Su validación restringe numéricamente la entrada al rango válido de 0 a 23.
     */
    HORA,
    /**
     * Representa la métrica de minutos en un formato de reloj.
     * Su validación restringe numéricamente la entrada al rango válido de 0 a 59.
     */
    MINUTO,
    /**
     * Representa una zona geográfica específica.
     * La entrada debe ser convertible al enumerador de regiones definido en los objetos de valor del dominio.
     */
    REGION,
    /**
     * Representa el grado de certificación internacional del idioma inglés.
     * La entrada debe ser convertible al enumerador de niveles de inglés (ej. B1, C2) del modelo de datos.
     */
    NIVEL_INGLES,
    /**
     * Representa el nivel de exigencia técnica de una actividad.
     * La entrada debe ser convertible al enumerador de dificultad definido en los grupos turísticos.
     */
    DIFICULTAD

}
