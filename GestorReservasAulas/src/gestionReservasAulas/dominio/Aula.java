package gestionReservasAulas.dominio;

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

    

    // To do:   // Métodos para agregar, eliminar y listar reservas
                // Agregar los @overrride de Comparable (compareTo, equals) y toString
}

