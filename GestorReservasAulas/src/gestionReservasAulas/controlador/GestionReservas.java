package gestionReservasAulas.controlador;
import java.sql.Date;
import java.util.TreeSet;

import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;

public class GestionReservas {
    private TreeSet<Aula> aulas;
    private TreeSet<Curso> cursos;

    public GestionReservas() {

        this.aulas = new TreeSet<>();
        this.cursos = new TreeSet<>();

    }

    public void registrarReserva(String tipo, String codigoEntidad, int aulaNumero, Date fecha, String rangoHorario) {
        // Implementación para registrar una reserva
        // Controlar disponibilidad y capacidades
    }

    public void cancelarReserva(int aulaNumero, int codigoReserva) {
        // Implementación para cancelar una reserva
    }

    public TreeSet<Aula> consultarAulas(int piso, String codigoEntidad) {
        return null;
        // Implementación para consultar aulas con filtros
    }
}
