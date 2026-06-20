package util.core.metadata;

/**
 * Representa la estructura de metadatos de un campo individual dentro del esquema de una entidad.
 * Encapsula la pregunta o instrucción que se mostrará al usuario en la interfaz de consola,
 * junto con la regla de validación estricta que debe cumplir el dato ingresado.
 */

public class Campo {
    private String pregunta;
    private TipoDato tipoRegla;

    /**
     * Construye una nueva instancia de un campo de metadatos.
     *
     * @param pregunta  La cadena de texto que sirve como instrucción o pregunta para el usuario (ej. "Ingrese el nombre").
     * @param tipoRegla El enumerador {@link TipoDato} que define el formato lógico y estructural que se aplicará a la entrada.
     */

    public Campo(String pregunta, TipoDato tipoRegla) {
        this.pregunta = pregunta;
        this.tipoRegla = tipoRegla;
    }

    /**
     * Recupera la pregunta configurada para este campo.
     *
     * @return Un {@code String} con la pregunta o instrucción del campo.
     */

    public String getPregunta() {
        return pregunta;
    }

    /**
     * Recupera la regla de validación asignada a este campo.
     *
     * @return Una instancia del enumerador {@link TipoDato} asociada a este campo específico.
     */

    public TipoDato getTipoRegla() {
        return tipoRegla;
    }
}
