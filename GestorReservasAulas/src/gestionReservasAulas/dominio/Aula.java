package gestionReservasAulas.dominio;

import java.util.TreeSet;

public class Aula implements Comparable{
    private int numero;
    private int capacidad;
    private TreeSet<Reserva> reservas;

    public Aula(int numero, int capacidad) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.reservas = new TreeSet<>();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    // To do:   // Métodos para agregar, eliminar y listar reservas
                // Agregar los @overrride de Comparable (compareTo, equals) y toString
}

