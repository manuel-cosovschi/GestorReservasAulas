package gestionReservasAulas.dominio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reserva implements Comparable<Reserva> {
    private static int contador = 0;
    private String codigo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Curso quienHizoReserva;

    public Reserva(LocalDateTime fechaInicio, LocalDateTime fechaFin, Curso quienHizoReserva) {
        this.codigo = "R" + (++contador);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quienHizoReserva = quienHizoReserva;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Reserva o) {
        return this.codigo.compareTo(o.codigo);
    }

    public Curso getQuienHizoReserva() {
        return quienHizoReserva;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio.toLocalDate();
    }

    public LocalDate getFechaFin() {
        return fechaFin.toLocalDate();
    }

    public LocalTime getHoraInicio() {
        return fechaInicio.toLocalTime();
    }

    public LocalTime getHoraFin() {
        return fechaFin.toLocalTime();
    }
}

