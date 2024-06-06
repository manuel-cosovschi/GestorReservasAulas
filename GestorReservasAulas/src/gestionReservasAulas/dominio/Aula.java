package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeSet;

public class Aula implements Comparable<Aula>{
    private int numero;
    private int capacidad;
    private TreeSet<Reserva> reservas;

    public Aula(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.reservas = new TreeSet<>();
    }
    
    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public TreeSet<Reserva> getReservas() {
        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }


    public boolean tieneReservaConEntidad(String codigoEntidad) {
        for (Reserva reserva : reservas) {
            if (reserva.getQuienHizoReserva().getCodigo().equals(codigoEntidad)) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneReserva(String codigoEntidad) {
        for (Reserva reserva : reservas) {
            if (reserva.getQuienHizoReserva().getCodigo().equals(codigoEntidad)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Aula o) {
        return Integer.compare(this.numero, o.getNumero());
    }


    public Reserva buscarReserva(String codigoReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo().equals(codigoReserva)) {
                return reserva;
            }
        }
        return null;
    }

    public boolean cancelarReserva(String codigoReserva) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva != null){
            reservas.remove(reserva);
            return true;
        }
        return false;
    }
    public boolean ValidarDisponibilidadReserva(int diaDelAnio, LocalTime horaInicio, LocalTime horaFin) {
        for (Reserva reserva : reservas) {
            if (reserva.getFechaInicio().getDayOfYear() == diaDelAnio) {
                if (reserva.getHoraInicio().isAfter(horaFin) || reserva.getHoraFin().isBefore(horaInicio)){
                    return true;
                }
            }
        }
        return false;
    }

    // To do:   // Métodos para agregar, eliminar y listar reservas
                // Agregar los @overrride de Comparable (compareTo, equals) y toString
}



