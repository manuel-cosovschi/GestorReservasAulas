package gestionReservasAulas.dominio;

import java.time.LocalDate;
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

    public int getCapacidad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCapacidad'");
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
            if (reserva.getHoraInicio().getDayOfYear() == diaDelAnio) {
                //Existe una reserva en el mismo dia, valido el horario
                if (reserva.getHoraInicio().getHour() > horaFin.getHour() || reserva.getHoraFin().getHour() < horaInicio.getHour()){
                    //La asignatura termina antes que arranque la reservada o arranca después que termina la reservada
                    return true;
                }
            }
        }
        return false;
    }

    // To do:   // Métodos para agregar, eliminar y listar reservas
                // Agregar los @overrride de Comparable (compareTo, equals) y toString
}



