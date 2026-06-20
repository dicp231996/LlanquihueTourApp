package util.queries;

import data.GestionFiltro;
import data.GestorInstancias;
import model.entities.GuiaTuristico;
import model.entities.Turista;
import model.valueobjects.GrupoTuristico;
import util.core.metadata.TipoEntidad;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
/**
 * Actúa como la capa de presentación y orquestación del motor de búsqueda del sistema.
 * Su responsabilidad principal es interactuar con el usuario para capturar los criterios de filtrado,
 * traducir dichas selecciones en expresiones lambda ({@link Predicate}), solicitar la instanciación
 * de los datos puros a la fábrica ({@link GestorInstancias}) y delegar el procesamiento lógico
 * al motor de filtrado ({@link GestionFiltro}).
 */
public class MenuConsultas {
    /**
     * Enrutador principal del módulo de consultas.
     * Recibe la entidad seleccionada por el usuario y deriva el flujo de ejecución hacia el
     * submenú especializado correspondiente para construir los predicados matemáticos.
     *
     * @param entidad La constante del enumerador {@link TipoEntidad} que define el contexto de la búsqueda.
     * @param teclado La instancia de {@code Scanner} para capturar la entrada por consola.
     */
    public static void iniciarConsulta(TipoEntidad entidad, Scanner teclado) {
        System.out.println("\n====== Motor de busqueda: " + entidad.name() + "======");

        if (entidad == TipoEntidad.GUIA) {
            ejecutarFiltroGuiasTuristicos(teclado);
        }
        else if (entidad == TipoEntidad.GRUPO) {
            ejecutarFiltroGruposTuristicos(teclado);
        }
        else if (entidad == TipoEntidad.TURISTA) {
            ejecutarFiltroTuristas(teclado);
        }
        else {
            System.out.println("No hay criterios de busqueda para esta entidad");
        }
    }
    /**
     * Despliega el menú de filtrado específico para la entidad Guía Turístico.
     * Construye un {@code Predicate<GuiaTuristico>} evaluando atributos fuertemente tipados
     * como enumeradores (Nivel de Inglés) o valores booleanos (Certificación).
     * Tras instanciar la base de datos en RAM, inyecta el predicado en el motor de filtrado.
     *
     * @param teclado La instancia de {@code Scanner} para capturar la opción deseada.
     */
    public static void ejecutarFiltroGuiasTuristicos(Scanner teclado) {
        System.out.println("\t1.- Mostrar guías con inglés avanzado (C2)");
        System.out.println("\t2.- Mostrar guías con certificación de rescate");
        System.out.println("Selecciona uno de los siguientes criterios");

        String opcion = teclado.nextLine().trim();
        Predicate<GuiaTuristico> criterio = null;

        if (opcion.equals("1")) {
            criterio = guia -> guia.getNivelDeIngles() == GuiaTuristico.NivelDeIngles.C2;
        }
        else if (opcion.equals("2")) {
            criterio = guia -> guia.getCertificadoPrimerosAuxilios() == true;
        }
        else  {
            System.out.println("Opcion invalida");
            return;
        }

        ArrayList<GuiaTuristico> baseDatosGuiasTuristicos = GestorInstancias.ensamblarGuiasTuristas();
        ArrayList<GuiaTuristico> filtro = GestionFiltro.filtrar(baseDatosGuiasTuristicos, criterio);

        imprimirResultado(filtro);
    }
    /**
     * Despliega el menú de filtrado específico para la entidad Grupo Turístico (Tours).
     * Construye un {@code Predicate<GrupoTuristico>} para evaluar condiciones logísticas
     * y de dificultad. Ejecuta el ciclo de extracción, filtrado e impresión polimórfica.
     *
     * @param teclado La instancia de {@code Scanner} para capturar la opción deseada.
     */
    public static void ejecutarFiltroGruposTuristicos(Scanner teclado) {
        System.out.println("\t1.- Mostrar tours con dificultad Avanzada");
        System.out.println("\t2.- Mostrar tours con condiciòn física requerida");
        System.out.println("Selecciona uno de los siguientes criterios");

        String opcion = teclado.nextLine().trim();
        Predicate<GrupoTuristico> criterio = null;

        if (opcion.equals("1")) {
            criterio = grupo -> grupo.getDificultad() == GrupoTuristico.Dificultad.AVANZADO;
        }
        else if (opcion.equals("2")) {
            criterio = grupo -> grupo.getCondicionFisicaRequerida() == true;
        }
        else  {
            System.out.println("Opcion invalida");
            return;
        }

        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = GestorInstancias.ensamblarGruposTuristicos();
        ArrayList<GrupoTuristico> filtro = GestionFiltro.filtrar(baseDatosGruposTuristicos, criterio);

        imprimirResultado(filtro);
    }
    /**
     * Despliega el menú de filtrado específico para la entidad Turista.
     * Construye un {@code Predicate<Turista>} evaluando métricas numéricas o aplicando
     * negación lógica y evaluación estricta de cadenas de texto ({@code .equals()})
     * para el análisis de nacionalidad.
     *
     * @param teclado La instancia de {@code Scanner} para capturar la opción deseada.
     */
    public static void ejecutarFiltroTuristas(Scanner teclado) {
        System.out.println("\t1.- Mostrar turistas extranjeros");
        System.out.println("\t2.- Mostrar turistas que reservaron más de 10 días");
        System.out.println("Selecciona uno de los siguientes criterios");

        String opcion = teclado.nextLine().trim();
        Predicate<Turista> criterio = null;

        if (opcion.equals("1")) {
            criterio = turista -> !turista.getNacionalidad().equals("Chilena");
        }
        else if (opcion.equals("2")) {
            criterio = turista -> turista.getDiasReserva() > 10;
        }
        else  {
            System.out.println("Opcion invalida");
            return;
        }

        ArrayList<Turista> baseDatosTuristas = GestorInstancias.ensamblarTuristas();
        ArrayList<Turista> filtro = GestionFiltro.filtrar(baseDatosTuristas, criterio);

        imprimirResultado(filtro);
    }
    /**
     * Método genérico para la renderización de resultados en la consola de comandos.
     * Utiliza el polimorfismo de Java para iterar sobre cualquier colección tipada ({@code <T>})
     * e invocar dinámicamente el método {@code .toString()} sobrescrito en las clases del modelo de dominio.
     *
     * @param resultado La colección filtrada de objetos que se mostrará al usuario.
     * @param <T>       El tipo de entidad genérica contenida en la lista (ej. {@code Turista}, {@code GuiaTuristico}).
     */
    private static <T> void imprimirResultado(ArrayList<T> resultado) {
        if (resultado.isEmpty()) {
            System.out.println("No hay resultado para ese criterio de busqueda");
            return;
        }

        System.out.println("\n====== Resultados de la busqueda ======\n");
        for (T registro : resultado) {
            System.out.println(registro.toString());
            System.out.println("\n---------------------------------\n");
        }
    }
}
