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
        String codigo = "0000" + (++this.contador);
        this.codigo = "R" + codigo.substring(codigo.length()-4);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quienHizoReserva = quienHizoReserva;
    }

    public Reserva(LocalDateTime fechaInicio, LocalDateTime fechaFin, Curso quienHizoReserva, String codigo) {
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quienHizoReserva = quienHizoReserva;
        this.contador = Integer.parseInt(codigo.substring(1));
    }

    /**
     * Retorna el código de la reserva.
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Reserva o) {
        return this.codigo.compareTo(o.codigo);
    }

    /**
     * Retorna la Asignatura/Curso de Extención/Evento que realizó la reserva.
     * @return
     */
    public Curso getQuienHizoReserva() {
        return quienHizoReserva;
    }

    /**
     * Retorna la fecha de inicio de la reserva (no la hora)
     * @return
     */
    public LocalDate getFechaInicio() {
        return fechaInicio.toLocalDate();
    }

    /**
     * Retorna la fecha de finalización de la reserva (no la hora)
     * @return
     */
    public LocalDate getFechaFin() {
        return fechaFin.toLocalDate();
    }

    /**
     * Retorna la hora de inicio de la reserva (no la fecha)
     * @return
     */
    public LocalTime getHoraInicio() {
        return fechaInicio.toLocalTime();
    }

    /**
     * Retorna la hora de finalización de la reserva (no la fecha)
     * @return
     */
    public LocalTime getHoraFin() {
        return fechaFin.toLocalTime();
    }
}

