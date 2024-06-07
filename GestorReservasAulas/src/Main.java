import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.util.CargaDatos;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase GestionReservas
        GestionReservas gestionReservas = new GestionReservas();
        // Crear una instancia de la clase CargaDatos pasando gestionReservas
        CargaDatos cargaDatosTxt = new CargaDatos(gestionReservas);
        // Cargar datos desde un archivo
        File archivo = new File("GestorReservasAulas/archivoAula.txt");
        cargaDatosTxt.cargarDatos(archivo);
    }
}

// Cambiar el nombre de la clase abstracta "Curso" por uno mas representativo.
// cantReservas = (asignatura.getFechaFin().getDayOfYear() - asignatura.getFechaInicio().getDayOfYear()) / 7; Consultar si dias totales asignaturas es multiplo de 7
