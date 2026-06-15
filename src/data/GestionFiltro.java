package data;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Clase utilitaria encargada de aplicar filtros dinámicos a colecciones de datos.
 * Utiliza genéricos para ser compatible con cualquier entidad del sistema.
 */

public class GestionFiltro {

    /**
     * Evalúa una lista de elementos y extrae aquellos que cumplan con una condición específica.
     *
     * @param listaOriginal La lista que contiene el conjunto de datos total.
     * @param criterio      La expresión lógica (Predicado) que se debe evaluar.
     * @param <T>           El tipo de objeto que se está manejando (ej. Turista, GuiaTuristico).
     * @return Una nueva ArrayList con los elementos que cumplieron la condición.
     */

    public static <T> ArrayList<T> filtrar(ArrayList<T> listaOriginal, Predicate<T> criterio) {

        ArrayList<T> resultado = new ArrayList<>();

        for (T item : listaOriginal) {
            if (criterio.test(item)) {
                resultado.add(item);
            }
        }
        return resultado;
    }
}
