package gestionReservasAulas.util;
import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CargaDatos {

    /**
     * Realiza la carga de datos del sistema en base al Archivo indicado por parámetro
     * @param archivo
     * @throws Exception
     */
    public void cargarDatos(File archivo) throws Exception {
        if (!archivo.exists()) {
            throw new Exception("Error: El archivo no existe - " + archivo.getAbsolutePath());
        }
        if (!archivo.canRead()) {
            throw new Exception("Error: No se puede leer el archivo" + archivo.getAbsolutePath());
        }

        List<String> errores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Aula aula;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                try {
                    switch (datos[0]) {
                        case "Aula":
                            aula = new Aula(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
                            GestionReservas.getSingletonInstance().agregarAula(aula);
                            break;
                        case "Asignatura":
                            Asignatura asignatura = new Asignatura(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalDate.parse(datos[5]), datos[6],
                                    LocalTime.parse(datos[7]), LocalTime.parse(datos[8]));
                            GestionReservas.getSingletonInstance().agregarCurso(asignatura);
                            break;
                        case "CursoExtension":
                            CursoExtension cursoExt = new CursoExtension(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]), Float.parseFloat(datos[5]));
                            GestionReservas.getSingletonInstance().agregarCurso(cursoExt);
                            break;
                        case "Evento":
                            Evento evento = new Evento(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalTime.parse(datos[5]), LocalTime.parse(datos[6]));
                            GestionReservas.getSingletonInstance().agregarCurso(evento);
                            break;
                        case "EventoExterno":
                            EventoExterno eventoExt = new EventoExterno(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalTime.parse(datos[5]), LocalTime.parse(datos[6]));
                            GestionReservas.getSingletonInstance().agregarCurso(eventoExt);
                            break;
                        case "Reserva":
                            Reserva reserva;
                            Curso curso;
                            int cantidadAulas = 0;
                            if (datos[1].length() != 5) {
                                switch (datos[3].substring(0, 2)) {
                                    case "AS": case "CE":
                                        aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(datos[4]));
                                        curso = GestionReservas.getSingletonInstance().buscarCurso(datos[3]);
                                        reserva = new Reserva(LocalDateTime.parse(datos[1]), LocalDateTime.parse(datos[2]), curso);
                                        aula.agregarReserva(reserva);
                                        break;
                                    case "EI":
                                        cantidadAulas = datos.length - 4;
                                        curso = GestionReservas.getSingletonInstance().buscarCurso(datos[3]);
                                        for (int i = 0; i < cantidadAulas; i++) {
                                            aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(datos[i + 4]));
                                            reserva = new Reserva(LocalDateTime.parse(datos[1]), LocalDateTime.parse(datos[2]), curso);
                                            aula.agregarReserva(reserva);
                                        }
                                        break;
                                    case "EE":
                                        cantidadAulas = datos.length - 6;
                                        curso = GestionReservas.getSingletonInstance().buscarCurso(datos[3]);
                                        curso.setNombreOrganizacion(datos[4]);
                                        curso.setCostoAlquiler(Float.parseFloat(datos[5]));
                                        for (int i = 0; i < cantidadAulas; i++) {
                                            aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(datos[i + 6]));
                                            reserva = new Reserva(LocalDateTime.parse(datos[1]), LocalDateTime.parse(datos[2]), curso);
                                            aula.agregarReserva(reserva);
                                        }
                                        break;
                                    default:
                                        errores.add("Tipo de dato desconocido: " + datos[3]);
                                }
                                break;
                            }
                            else{
                                switch (datos[4].substring(0, 2)) {
                                    case "AS": case "CE": case "EI":
                                        aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(datos[5]));
                                        curso = GestionReservas.getSingletonInstance().buscarCurso(datos[4]);
                                        reserva = new Reserva(LocalDateTime.parse(datos[2]), LocalDateTime.parse(datos[3]), curso, datos[1]);
                                        aula.agregarReserva(reserva);
                                    break;
                                    case "EE":
                                        curso = GestionReservas.getSingletonInstance().buscarCurso(datos[4]);
                                        curso.setNombreOrganizacion(datos[5]);
                                        curso.setCostoAlquiler(Float.parseFloat(datos[6]));
                                        aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(datos[ 7]));
                                        reserva = new Reserva(LocalDateTime.parse(datos[2]), LocalDateTime.parse(datos[3]), curso, datos[1]);
                                        aula.agregarReserva(reserva);
                                        break;
                                    default:
                                        errores.add("Tipo de dato desconocido: " + datos[4]);
                                }
                                break;
                            }
                        default:
                            errores.add("Tipo de dato desconocido: " + datos[0]);
                    }
                } catch (Exception e) {
                    errores.add("Error en la línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            errores.add("Error al leer el archivo: " + e.getMessage());
        }

        if (!errores.isEmpty()) {
            System.err.println("Errores encontrados:");
            for (String error : errores) {
                System.err.println(error);
                throw new Exception(error);
            }
        } else {
            System.out.println("Datos cargados exitosamente.");
        }
    }
    
}