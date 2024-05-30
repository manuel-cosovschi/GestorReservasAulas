// package controlador;

// import modelo.Aula;
// import modelo.Reserva;

// import java.util.ArrayList;
// import java.util.List;

// public class GestorReservas {
//     private List<Aula> aulas;

//     public GestorReservas() {
//         this.aulas = new ArrayList<>();
//     }

//     public void agregarAula(Aula aula) {
//         aulas.add(aula);
//     }

//     public List<Aula> obtenerAulas() {
//         return aulas;
//     }

//     public void registrarReserva(Aula aula, Reserva reserva) {
//         aula.agregarReserva(reserva);
//     }
// }

import java.util.TreeMap;

public class GestionReservas {
    private TreeMap<Integer, Aula> aulas;

    public GestionReservas() {
        this.aulas = new TreeMap<>();
    }

    public void registrarReserva(String tipo, String codigoEntidad, int aulaNumero, Date fecha, String rangoHorario) {
        // Implementación para registrar una reserva
        // Controlar disponibilidad y capacidades
    }

    public void cancelarReserva(int aulaNumero, int codigoReserva) {
        // Implementación para cancelar una reserva
    }

    public List<Aula> consultarAulas(int piso, String codigoEntidad) {
        // Implementación para consultar aulas con filtros
    }
}
