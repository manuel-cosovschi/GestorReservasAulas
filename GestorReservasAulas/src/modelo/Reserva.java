package modelo;

import java.time.LocalDateTime;

public class Reserva {
    private static int contador = 0;
    private int codigo;
    private LocalDateTime fecha;
    private String rangoHorario;
    private String quienHizoReserva;

    public Reserva(LocalDateTime fecha, String rangoHorario, String quienHizoReserva) {
        this.codigo = ++contador;
        this.fecha = fecha;
        this.rangoHorario = rangoHorario;
        this.quienHizoReserva = quienHizoReserva;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getRangoHorario() {
        return rangoHorario;
    }

    public void setRangoHorario(String rangoHorario) {
        this.rangoHorario = rangoHorario;
    }

    public String getQuienHizoReserva() {
        return quienHizoReserva;
    }

    public void setQuienHizoReserva(String quienHizoReserva) {
        this.quienHizoReserva = quienHizoReserva;
    }
}

