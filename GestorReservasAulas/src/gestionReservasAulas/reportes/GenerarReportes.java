package gestionReservasAulas.reportes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.CursoExtension;
import gestionReservasAulas.dominio.EventoExterno;
import gestionReservasAulas.dominio.Reserva;
import gestionReservasAulas.excepciones.ReservaException;

public class GenerarReportes {
    private GestionReservas gestionReservas;

    public GenerarReportes(GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
    }

    public void generarReporteMontos() {
        try {
            // Consulta todas las aulas sin aplicar ningún filtro. Esto puede lanzar una ReservaException.
            TreeSet<Aula> aulas = gestionReservas.consultarAulas(-1, null);
    
            // Crea un mapa para almacenar el monto recaudado por cada piso.
            Map<Integer, Double> montoPorPiso = new HashMap<>();
    
            // Inicializa una variable para almacenar el monto total recaudado por la institución.
            double montoTotalInstitucion = 0;
    
            // Utiliza un FileWriter para escribir el reporte en el archivo "reporte_montos.txt".
            try (FileWriter writer = new FileWriter("reporte_montos.txt")) {
                // Escribe la cabecera del reporte en el archivo.
                writer.write("Monto recaudado por aula:\n");
    
                // Itera sobre todas las aulas obtenidas.
                for (Aula aula : aulas) {
                    // Inicializa una variable para almacenar el monto recaudado por cada aula.
                    double montoAula = 0;
    
                    // Itera sobre todas las reservas de cada aula.
                    for (Reserva reserva : aula.getReservas()) {
                        // Comprueba si la reserva fue hecha por un evento externo.
                        if (reserva.getQuienHizoReserva() instanceof EventoExterno) {
                            // Convierte la reserva a un objeto EventoExterno.
                            EventoExterno evento = (EventoExterno) reserva.getQuienHizoReserva();
                            // Suma el costo del alquiler del evento al monto recaudado por el aula.
                            montoAula += evento.getCostoAlquiler();
                        } 
                        // Comprueba si la reserva fue hecha por un curso de extensión.
                        else if (reserva.getQuienHizoReserva() instanceof CursoExtension) {
                            // Convierte la reserva a un objeto CursoExtension.
                            CursoExtension curso = (CursoExtension) reserva.getQuienHizoReserva();
                            // Suma el monto recaudado por el curso al monto recaudado por el aula.
                            montoAula += curso.getCostoPorAlumno() * curso.getCantidadAlumnos();
                        }
                    }
    
                    // Escribe el monto recaudado por el aula en el archivo.
                    writer.write("Aula " + aula.getNumero() + ": $" + montoAula + "\n");
    
                    // Calcula el piso del aula a partir de su número, dividiendo el número del aula por 100.
                    int piso = aula.getNumero() / 100;
                    // Actualiza el monto recaudado por piso, sumando el monto del aula al monto existente (si lo hay).
                    montoPorPiso.put(piso, montoPorPiso.getOrDefault(piso, 0.0) + montoAula);
                    // Suma el monto recaudado por el aula al monto total recaudado por la institución.
                    montoTotalInstitucion += montoAula;
                }
    
                // Escribe la cabecera del monto recaudado por piso en el archivo.
                writer.write("\nMonto recaudado por piso:\n");
                // Itera sobre las entradas del mapa montoPorPiso.
                for (Map.Entry<Integer, Double> entry : montoPorPiso.entrySet()) {
                    // Escribe el monto recaudado por cada piso en el archivo.
                    writer.write("Piso " + entry.getKey() + ": $" + entry.getValue() + "\n");
                }
    
                // Escribe el monto total recaudado por la institución en el archivo.
                writer.write("\nMonto total recaudado por la institución: $" + montoTotalInstitucion + "\n");
    
            } catch (IOException e) {
                // Captura cualquier excepción IOException que ocurra al escribir en el archivo.
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } catch (ReservaException e) {
            // Captura cualquier excepción ReservaException que ocurra al consultar las aulas.
            System.out.println("Error al consultar aulas: " + e.getMessage());
        }
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
