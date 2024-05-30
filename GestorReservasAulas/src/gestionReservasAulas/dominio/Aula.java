package modelo;

import java.util.ArrayList;
import java.util.List;

public class Aula {
    private int numero;
    private int capacidad;
    // private List<Reserva> reservas;
    private ArrayList<Reserva> reservas;

    public Aula(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.reservas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // To do: // Métodos para agregar, eliminar y listar reservas
}

