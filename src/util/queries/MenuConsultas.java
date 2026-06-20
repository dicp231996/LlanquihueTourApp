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

public class MenuConsultas {

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

    private static <T> void imprimirResultado(ArrayList<T> resultado) {
        if (resultado.isEmpty()) {
            System.out.println("No hay resultado para ese criterio de busqueda");
            return;
        }

        System.out.println("\n====== Resultados de la busqueda ======");
        for (T registro : resultado) {
            System.out.println(registro.toString());
        }
    }
}
