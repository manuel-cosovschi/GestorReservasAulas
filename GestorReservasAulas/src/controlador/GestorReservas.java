package controlador;

import modelo.Aula;
import modelo.Reserva;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Aula> aulas;

    public GestorReservas() {
        this.aulas = new ArrayList<>();
    }

    public void agregarAula(Aula aula) {
        aulas.add(aula);
    }

    public List<Aula> obtenerAulas() {
        return aulas;
    }

    public void registrarReserva(Aula aula, Reserva reserva) {
        aula.agregarReserva(reserva);
    }
    
}

