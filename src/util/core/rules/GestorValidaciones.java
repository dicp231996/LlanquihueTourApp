package util.core.rules;

import model.entities.services.AvistamientoHumedales;
import model.valueobjects.Direccion.Region;
import model.core.ServicioTuristico.Dificultad;
import model.entities.services.AvistamientoHumedales.EstacionRecomendada;
import model.entities.actors.GuiaTuristico.NivelDeIngles;
import util.core.metadata.TipoDato;

/**
 * Motor central de validaciones del sistema.
 * Esta clase utilitaria se encarga de evaluar las cadenas de texto crudas capturadas
 * desde la interfaz de usuario, garantizando que cumplan estrictamente con los formatos,
 * expresiones regulares y estructuras lógicas dictadas por el esquema de metadatos
 * antes de permitir su ingreso a la base de datos.
 */

public class GestorValidaciones {

    /**
     * Evalúa una cadena de texto contra una regla de validación específica.
     * <p>
     * El método ejecuta una evaluación de dos fases: primero, un control de nulidad y
     * cadenas vacías. Segundo, un enrutamiento mediante un bloque {@code switch} que aplica
     * expresiones regulares (Regex) para tipos primitivos (como RUT, horas o números), o
     * intenta el mapeo directo mediante {@code valueOf()} para los enumeradores complejos,
     * capturando la excepción {@link IllegalArgumentException} para retornar un fallo controlado.
     * </p>
     *
     * @param tipo    La constante del enumerador {@link TipoDato} que define la regla estricta a aplicar.
     * @param entrada La cadena de texto ingresada por el usuario que será sometida a evaluación.
     * @return {@code true} si la entrada coincide exactamente con el patrón o enumerador esperado;
     * {@code false} si la entrada es nula, está vacía o rompe la regla de formato.
     */

    public static boolean datoValido(TipoDato tipo, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return false;
        }

        switch (tipo) {
            case TEXTO_LIBRE:
                return entrada.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\d\\s]+$");
            case RUT:
                return entrada.matches("^\\d{1,3}\\.\\d{3}\\.\\d{3}-[0-9Kk]$");
            case BOOLEAN:
                return entrada.equalsIgnoreCase("true") || entrada.equalsIgnoreCase("false");
            case NUMERO_ENTERO:
                return entrada.matches("^\\d+$");
            case HORA:
                return entrada.matches("^(1?[0-9]|2[0-3])$");
            case MINUTO:
                return entrada.matches("^[0-5]?[0-9]$");
            case REGION:
                try {
                    Region.valueOf(entrada.toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            case NIVEL_INGLES:
                try {
                    NivelDeIngles.valueOf(entrada.toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            case DIFICULTAD:
                try {
                    Dificultad.valueOf(entrada.toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            case ESTACION_RECOMENDADA:
                try {
                    EstacionRecomendada.valueOf(entrada.toUpperCase());
                    return true;
                } catch (IllegalArgumentException e) {
                    return false;
                }
            default:
                return false;
        }
    }
}
