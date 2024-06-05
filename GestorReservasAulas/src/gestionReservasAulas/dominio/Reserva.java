package gestionReservasAulas.dominio;

import java.sql.Date;
import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private static int contador = 0;
    private String codigo;
    private LocalDateTime horaInicio; // se necesita la fecha y el tiempo
    private LocalDateTime horaFin;
    private Curso quienHizoReserva;

    public Reserva(LocalDateTime horaInicio2, LocalDateTime horaFin2, Curso quienHizoReserva) {
        this.codigo = generarCodigo();
        this.horaInicio = horaInicio2;
        this.horaFin = horaFin2;
        this.quienHizoReserva = quienHizoReserva;
    }

    private String generarCodigo() {
        return "RES" + (++contador);
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


    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }
}

