package util.core.rules;

import model.valueobjects.Direccion.Region;
import model.valueobjects.GrupoTuristico.Dificultad;
import model.entities.GuiaTuristico.NivelDeIngles;
import util.core.metadata.TipoDato;

public class GestorValidaciones {


    public static boolean datoValido(TipoDato tipo, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return false;
        }

        switch (tipo) {
            case TEXTO_LIBRE:
                return entrada.matches("^[a-zA-ZáéíóúÁÉÍÓÚnÑ\\d\\s]+$");
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
            default:
                return false;
        }
    }
}
