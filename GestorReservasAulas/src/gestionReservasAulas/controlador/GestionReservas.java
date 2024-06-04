package gestionReservasAulas.controlador;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.TreeSet;

import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.dominio.Reserva;
import gestionReservasAulas.excepciones.ReservaException;

public class GestionReservas {
    private TreeSet<Aula> aulas;
    private TreeSet<Curso> cursos;

    public GestionReservas() {

        this.aulas = new TreeSet<>();
        this.cursos = new TreeSet<>();

    }

    public void registrarReserva(String tipo, String codigoEntidad, int aulaNumero, LocalDateTime HoraInicio, LocalDateTime HoraFin) throws ReservaException {
        Aula aula = buscarAula(aulaNumero);
        if (aula == null) {
            throw new ReservaException("El aula con número " + aulaNumero + " no existe.");
        }

        Curso curso = buscarCurso(codigoEntidad);
        if (curso == null) {
            throw new ReservaException("El curso con código " + codigoEntidad + " no existe.");
        }

        // Controlar capacidad del aula
        if (curso.getCantidadAlumnos() > aula.getCapacidad()) {
            throw new ReservaException("La cantidad de alumnos supera la capacidad del aula.");
        }

        Reserva nuevaReserva = new Reserva(HoraInicio, HoraFin, curso);
        // Falta controlar la disponibilidad horaria en el rango entre horaIni y horaFin.
        

        aula.agregarReserva(nuevaReserva);
    }

    public void cancelarReserva(int aulaNumero, int codigoReserva) {
        // Implementación para cancelar una reserva
    }

    public TreeSet<Aula> consultarAulas(int piso, String codigoEntidad) throws ReservaException {
        TreeSet<Aula> aulasFiltradas = new TreeSet<>();
    
        for (Aula aula : this.aulas) {
            boolean pisoCoincide = (piso == 0) || (aula.getNumero() / 100 == piso);
            boolean tieneReservaEntidad = codigoEntidad.isEmpty() || aula.tieneReserva(codigoEntidad);
    
            if (pisoCoincide && tieneReservaEntidad) {
                aulasFiltradas.add(aula);
            }
        }

        if (aulasFiltradas.isEmpty()) {
            throw new ReservaException("No se encontraron aulas para los filtros proporcionados.");
        }
    
        return aulasFiltradas;
    }

    private Aula buscarAula(int numero) {
        for (Aula aula : aulas) {
            if (aula.getNumero() == numero) {
                return aula;
            }
        }
        return null;
    }

    private Curso buscarCurso(String codigo) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        return null;
    }

    public void agregarAula(Aula aula) {
        aulas.add(aula);
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }
    
}
