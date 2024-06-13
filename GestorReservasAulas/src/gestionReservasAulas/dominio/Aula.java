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

    /**
     * Retorna TRUE si el curso indicado por parametro tiene alguna reserva en aula
     * @param codigoEntidad
     * @return
     */
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

    /**
     * Busca y retorna una Reserva o NULL si no la encuentra
     * @param codigoReserva
     * @return
     */
    public Reserva buscarReserva(String codigoReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo().equals(codigoReserva)) {
                return reserva;
            }
        }
        return null;
    }

    /**
     * Busca y elimina una reserva. Retorna TRUE si la elimina.
     * @param codigoReserva
     * @return
     */
    public boolean cancelarReserva(String codigoReserva) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva != null){
            reservas.remove(reserva);
            return true;
        }
        return false;
    }

    /**
     * Busca las reservas del aula que coinciden con el diaReserva y verifica que el horario de reserva no se solape
     * con el horaInicio y el horaFin de la nueva reserva. Retorna TRUE si la nueva reserva no se solapa con ninguna
     * reserva existente en el aula.
     * @param diaReserva
     * @param horaInicio
     * @param horaFin
     * @return
     */
    public boolean ValidarDisponibilidadReserva(LocalDate diaReserva, LocalTime horaInicio, LocalTime horaFin) {
        for (Reserva reserva : reservas) {
            if (reserva.getFechaInicio().equals(diaReserva)) {
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



