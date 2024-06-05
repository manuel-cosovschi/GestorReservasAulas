package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Asignatura extends Curso {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String diaSemana;
    private LocalTime horaInicio; //String horaInicio = DateTime.Now.ToString("HH:mm");
    private LocalTime horaFin;

    public Asignatura(String codigo, String nombre, int cantidadAlumnos,LocalDate Fechainicio, LocalDate fechaFin, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo, nombre, cantidadAlumnos);
        this.fechaInicio = Fechainicio;
        this.fechaFin = fechaFin;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public LocalTime getHoraFin() {
        return horaFin;
    }
}
