package gestionReservasAulas.reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.excepciones.ReservaException;

public class GenerarReportes {
    private GestionReservas gestionReservas;

    public GenerarReportes(GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
    }

    public void generarReporteMontos() {
        // Implementación para generar reporte de montos recaudados
    }

    public void generarListadoCompletoAulas() {
        try {
            TreeSet<Aula> aulas = gestionReservas.consultarAulas(-1, null);
            try (FileWriter writer = new FileWriter("reporte_aulas.txt")) {
                writer.write("Listado completo de aulas:\n");
                for (Aula aula : aulas) {
                    writer.write(aula.toString() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo de reporte: " + e.getMessage());
            }
        } catch (ReservaException e) {
            System.out.println("Error al consultar aulas: " + e.getMessage());
        }
    }
}
