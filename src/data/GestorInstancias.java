package data;

import model.entities.GuiaTuristico;
import model.entities.Turista;
import model.valueobjects.Direccion;
import model.valueobjects.GrupoTuristico;
import model.valueobjects.Rut;
import util.core.metadata.TipoEntidad;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class  GestorInstancias {

    public static ArrayList<GuiaTuristico> ensamblarGuiasTuristas () {
        var lineasGuiasTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GUIA.getRutaArchivo());
        var datosGuiasTuristicos = GestorDatos.registroInstancias(lineasGuiasTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = new ArrayList<>();

        for (var partes:datosGuiasTuristicos) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7], Direccion.Region.valueOf(partes[8]));

            GuiaTuristico nuevoGuia = new GuiaTuristico(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    GuiaTuristico.NivelDeIngles.valueOf(partes[9]),partes[10],Boolean.parseBoolean(partes[11]),Boolean.parseBoolean(partes[12]));

            baseDatosGuias.add(nuevoGuia);
        }
        return baseDatosGuias;
    }

    public static ArrayList<GrupoTuristico> ensamblarGruposTuristicos () {
        var lineasGruposTuristicosDB = GestorDatos.cargarDatos(TipoEntidad.GRUPO.getRutaArchivo());
        var datosGruposTours = GestorDatos.registroInstancias(lineasGruposTuristicosDB);

        ArrayList<GuiaTuristico> baseDatosGuias = ensamblarGuiasTuristas();
        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = new ArrayList<>();
        int contador = 0;
        for (var partes:datosGruposTours) {

            GrupoTuristico nuevoGrupo = new GrupoTuristico (LocalTime.of(Integer.parseInt(partes[0]),(Integer.parseInt(partes[1]))),
                    LocalTime.of(Integer.parseInt(partes[2]),(Integer.parseInt(partes[3]))),partes[4],baseDatosGuias.get(contador),Boolean.parseBoolean(partes[6]),
                    Integer.parseInt(partes[7]),GrupoTuristico.Dificultad.valueOf(partes[9]));
            baseDatosGruposTuristicos.add(nuevoGrupo);
            contador++;
        }
        return baseDatosGruposTuristicos;
    }

    public static ArrayList<Turista> ensamblarTuristas () {
        var lineasTuristasDB = GestorDatos.cargarDatos(TipoEntidad.TURISTA.getRutaArchivo());
        var DatosTuristas = GestorDatos.registroInstancias(lineasTuristasDB);

        ArrayList<GrupoTuristico> baseDatosGruposTuristicos = ensamblarGruposTuristicos();
        ArrayList<Turista> baseDatosTuristas = new ArrayList<>();
        Random contadorAleatorio = new Random();

        for (var partes:DatosTuristas) {
            Rut rut = new Rut(partes[4]);
            Direccion direccion = new Direccion(partes[5],partes[6],partes[7], Direccion.Region.valueOf(partes[8]));

            int indiceAleatorio = contadorAleatorio.nextInt(baseDatosGruposTuristicos.size());
            GrupoTuristico tourElegido = baseDatosGruposTuristicos.get(indiceAleatorio);

            Turista nuevoTurista = new Turista(partes[0],partes[1],partes[2],partes[3],rut,direccion,
                    partes[9],Integer.parseInt(partes[10]),tourElegido);

            baseDatosTuristas.add(nuevoTurista);
        }

        for (Turista turistaInscrito : baseDatosTuristas) {
            turistaInscrito.getGrupoTuristico().inscribirParticipante(turistaInscrito);
        }
        return baseDatosTuristas;
    }
}
