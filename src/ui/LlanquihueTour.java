package ui;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

import data.GestionFiltro;
import model.entities.GuiaTuristico;
import model.entities.Turista;
import model.entities.GuiaTuristico.NivelDeIngles;
import model.valueobjects.Direccion;
import model.valueobjects.Rut;
import model.valueobjects.Direccion.Region;
import model.valueobjects.GrupoTuristico;
import data.GestorDatos;

/**
 * Clase principal que actúa como punto de entrada de la aplicación LlanquihueTour.
 * Simula un sistema de gestión turística, instanciando guías, turistas y grupos de viaje
 * mediante datos de prueba ("Pseudo bases de datos"), para luego mostrar un resumen de los
 * grupos y sus participantes en consola.
 * @author Daniel Campos
 * @version 1.1.0
 */

public class LlanquihueTour {

    /**
     * Método principal que ejecuta la lógica de la aplicación.
     * Crea arreglos de datos simulados para generar objetos de dominio y muestra
     * la información resultante de los grupos turísticos configurados.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta implementación).
     */


    public static void main(String[] args) {

        //Creacion de instancias de guías turisticos

        var lineasGuiasTuristicosDB = GestorDatos.cargarDatos("base_datos_guias_turisticos.txt");
        var datosGuiasTuristicos = GestorDatos.registroInstancias(lineasGuiasTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = new ArrayList<>();

        for (var partes:datosGuiasTuristicos) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7],Region.valueOf(partes[8]));

            GuiaTuristico nuevoGuia = new GuiaTuristico(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    NivelDeIngles.valueOf(partes[9]),partes[10],Boolean.parseBoolean(partes[11]),Boolean.parseBoolean(partes[12]));

            baseDatosGuias.add(nuevoGuia);
        }

        //Creacion de instancias de grupos turisticos

        var lineasGruposTuristicosDB = GestorDatos.cargarDatos("base_datos_tours.txt");
        var datosGruposTours = GestorDatos.registroInstancias(lineasGruposTuristicosDB);
        
        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = new ArrayList<>();
        int contador = 0;
        for (var partes:datosGruposTours) {

            GrupoTuristico nuevoGrupo = new GrupoTuristico (LocalTime.of(Integer.parseInt(partes[0]),(Integer.parseInt(partes[1]))),
            LocalTime.of(Integer.parseInt(partes[2]),(Integer.parseInt(partes[3]))),partes[4],baseDatosGuias.get(contador),Boolean.parseBoolean(partes[6]),
            Integer.parseInt(partes[7]),GrupoTuristico.Dificultad.valueOf(partes[9]));
            baseDatosGruposTuristicos.add(nuevoGrupo);
            contador++;
        }

        //Creación de instancias de turistas

        var lineasTuristasDB = GestorDatos.cargarDatos("base_datos_turistas.txt");
        var DatosTuristas = GestorDatos.registroInstancias(lineasTuristasDB);
        
        ArrayList<Turista> baseDatosTuristas = new ArrayList<>();
        Random contadorAleatorio = new Random();
        
        for (var partes:DatosTuristas) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7],Region.valueOf(partes[8]));

            int indiceAleatorio = contadorAleatorio.nextInt(baseDatosGruposTuristicos.size());
            GrupoTuristico tourElegido = baseDatosGruposTuristicos.get(indiceAleatorio);
            
            Turista nuevoTurista = new Turista(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    partes[9],Integer.parseInt(partes[10]),tourElegido);

            baseDatosTuristas.add(nuevoTurista);
        }
        
        for (Turista turistaInscrito : baseDatosTuristas) {
            turistaInscrito.getGrupoTuristico().inscribirParticipante(turistaInscrito);
        }

        //Criterio para filtrar los grupos turisticos

        ArrayList<GrupoTuristico> tourAvanzado = GestionFiltro.filtrar(baseDatosGruposTuristicos,
                grupo -> grupo.getDificultad() == GrupoTuristico.Dificultad.AVANZADO);

        //Salida de datos

        System.out.println("==== Registro grupos turisticos ====\n");
        
        for (int i=0; i < tourAvanzado.size(); i++) {
            System.out.println("Grupo " + (i + 1));
            System.out.println(tourAvanzado.get(i).toString());
            System.out.println("----------------------------------------");
        }


    }
    
}
