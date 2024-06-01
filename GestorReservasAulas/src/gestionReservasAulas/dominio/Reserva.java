package gestionReservasAulas.dominio;

import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private static int contador = 0;
    private int codigo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Curso quienHizoReserva;

    public Reserva(LocalDateTime fechaInicio, LocalDateTime fechaFin, Curso quienHizoReserva) {
        this.codigo = ++contador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.quienHizoReserva = quienHizoReserva;
    }

    @Override
    public int compareTo(Reserva o) {
        return 0;
    }

}

