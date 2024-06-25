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

    /**
     * Retorna el numero/codigo del aula.
     * @return
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna la capacidad máxima de alumnos del aula.
     * @return
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Agrega una reserva a la coleccion de reservas.
     * @param reserva
     */
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public int compareTo(Aula o) {
        Aula aula = (Aula) o;
        return Integer.compare(this.numero, o.numero);
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
                if (!(reserva.getHoraInicio().isAfter(horaFin) || reserva.getHoraFin().isBefore(horaInicio))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retorna la colección de reservas del aula
     * @return
     */
    public TreeSet<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Retorna el número de piso en donde se encuentra el aula.
     * @return
     */
    public String getNumeroPiso() {
        return Integer.toString(numero / 100);
    }

    /**
     * Retorna el número del aula.
     * @return
     */
    public String getNumeroAula() {
        return Integer.toString(numero % 100);
    }

    /**
     * Retorna la cantidad total de reservas registradas del aula.
     * @return
     */
    public int getCantidadReservasTotal() {
        return reservas.size();
    }
}



