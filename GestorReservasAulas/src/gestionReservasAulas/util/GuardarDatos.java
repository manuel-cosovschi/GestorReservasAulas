package gestionReservasAulas.util;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.dominio.Reserva;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class GuardarDatos {
    /**
     * Guarda en el archivo Serializable.txt todos los datos del sistema.
     * @param gestor
     */
    public static void guardarDatosTxt(GestionReservas gestor) {
        try (FileWriter writer = new FileWriter("GestorReservasAulas/Serializable.txt")) {
            String linea = "";

            //Aulas
            for(Aula aula : gestor.getAulas()) {
                linea = "Aula";
                linea = linea + "," + aula.getNumero() + ',' + aula.getCapacidad();

                writer.write(linea + "\n");
            }

            //Cursos
            for(Curso curso : gestor.getCursos()){
                switch (curso.getCodigo().substring(0,2)){
                    case "AS":
                        linea = "Asignatura";
                        linea = linea + "," + curso.getCodigo() + "," + curso.getNombre() + "," + curso.getCantidadAlumnos();
                        linea = linea + "," + curso.getFechaInicio() + "," + curso.getFechaFin() + "," + curso.getDiaSemana();
                        linea = linea + "," + curso.getHoraInicio() + "," + curso.getHoraFin();
                        break;
                    case "CE":
                        linea = "CursoExtension";
                        linea = linea + "," + curso.getCodigo() + "," + curso.getNombre() + "," + curso.getCantidadAlumnos();
                        linea = linea + "," + curso.getCantidadClases() + "," + curso.getCostoPorAlumno();
                        break;
                    case "EI":
                        linea = "Evento";
                        linea = linea + "," + curso.getCodigo() + "," + curso.getNombre() + "," + curso.getCantidadAlumnos();
                        linea = linea + "," + curso.getFechaInicio() + "," + curso.getHoraInicio() + "," + curso.getHoraFin();
                        break;
                    case "EE":
                        linea = "EventoExterno";
                        linea = linea + "," + curso.getCodigo() + "," + curso.getNombre() + "," + curso.getCantidadAlumnos();
                        linea = linea + "," + curso.getFechaInicio() + "," + curso.getHoraInicio() + "," + curso.getHoraFin();
                        linea = linea + "," + curso.getOrganizacion() + "," + curso.getCostoPorAlumno();
                        break;
                    default:
                        linea = "";
                        break;
                }
                writer.write(linea + "\n");
            }

            //Reservas
            for(Aula aula : gestor.getAulas()) {
                for (Reserva reserva : aula.getReservas()) {
                    linea = "Reserva";
                    linea = linea  + "," + reserva.getCodigo();
                    linea = linea + "," + LocalDateTime.of(reserva.getFechaInicio(),reserva.getHoraInicio()).toString();
                    linea = linea + "," + LocalDateTime.of(reserva.getFechaFin(),reserva.getHoraFin()).toString();
                    linea = linea + "," + reserva.getQuienHizoReserva().getCodigo();

                    switch (reserva.getQuienHizoReserva().getCodigo().substring(0,2)){
                        case "AS": case "CE": case "EI":
                            linea = linea + "," + aula.getNumero();
                            break;
                        case "EE":
                            linea = linea + "," + reserva.getQuienHizoReserva().getOrganizacion();
                            linea = linea + "," + reserva.getQuienHizoReserva().getCostoPorAlumno();
                            linea = linea + "," + aula.getNumero();
                            break;
                        default:
                            break;
                    }
                    writer.write(linea + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}