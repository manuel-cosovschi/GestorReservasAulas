package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento extends Curso {
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Evento(String codigo, String nombre, int cantidadAlumnos, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo,nombre,cantidadAlumnos);
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public LocalDate getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}

