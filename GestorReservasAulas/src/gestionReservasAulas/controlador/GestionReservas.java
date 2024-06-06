package gestionReservasAulas.controlador;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeSet;

import gestionReservasAulas.dominio.Asignatura;
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

    /**
     * Reserva de Asignatura
     * @param codigoAsignatura
     * @param aulaNumero
     * @throws ReservaException
     */
     public void registrarReserva(Curso curso, int aulaNumero, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws ReservaException {
        Aula aula = buscarAula(aulaNumero);
        if (aula == null) {
            throw new ReservaException("El aula no existe");
        }

        int diaDelAnio = fechaInicio.getDayOfYear();
        LocalTime horaInicio = fechaInicio.toLocalTime();
        LocalTime horaFin = fechaFin.toLocalTime();

        if (!aula.ValidarDisponibilidadReserva(diaDelAnio, horaInicio, horaFin)) {
            throw new ReservaException("El aula no está disponible en el horario solicitado");
        }

        if (curso.getCantidadAlumnos() > aula.getCapacidad()) {
            throw new ReservaException("La capacidad del aula es insuficiente para la cantidad de alumnos");
        }

        Reserva reserva = new Reserva(fechaInicio, fechaFin, curso);
        aula.agregarReserva(reserva);
    }

    //falta agregar la sobrecarga para Curso y para Evento. La idea es sobrecargar el metodo registrarReserva();

    private Asignatura buscarAsignatura(String codigoAsignatura) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigoAsignatura)) {
                return (Asignatura)curso; //verificar que devuelve
            }
        }
        return null;
    }


    /**
     * Devuelve true si la reserva se pudo cancelar con exito
     * @param aulaNumero
     * @param codigoReserva
     * @return
     * @throws ReservaException
     */
    public boolean cancelarReserva(int aulaNumero, String codigoReserva) throws ReservaException {
        Aula aula = buscarAula(aulaNumero);
        if (aula == null) {
            throw new ReservaException("El aula con número " + aulaNumero + " no existe.");
        }

        if (!aula.cancelarReserva(codigoReserva)){
            throw new ReservaException("El codigo de reserva no existe en esa aula");
        }

        return true;
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
