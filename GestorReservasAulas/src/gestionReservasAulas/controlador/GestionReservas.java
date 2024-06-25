package gestionReservasAulas.controlador;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TreeSet;

import gestionReservasAulas.dominio.*;
import gestionReservasAulas.excepciones.ReservaException;

public class GestionReservas {
    private TreeSet<Aula> aulas;
    private TreeSet<Curso> cursos;
    private static GestionReservas gestionReservas;

    public GestionReservas() {
        this.aulas = new TreeSet<>();
        this.cursos = new TreeSet<>();
    }

    /**
     * Singleton. Evito mas de una instancia del sistema
     * @return
     */
    public static GestionReservas getSingletonInstance() {
        if (gestionReservas == null) {
            gestionReservas = new GestionReservas();
        }
        return gestionReservas;
    }

    /**
     * Registra las reservas de una Asignatura.
     * @param codigoAsignatura
     * @param aulaNumero
     * @throws ReservaException
     */
    public void registrarReserva(String codigoAsignatura, int aulaNumero) throws ReservaException {
        int cantidadClasesAsignatura;
        long sumaDias;
        LocalDate diaAsignatura;
        LocalDateTime inicioAsignatura;
        LocalDateTime finAsignatura;

        // Controlar existencia del aula
        Aula aula = buscarAula(aulaNumero);
        if (aula == null) {
            throw new ReservaException("El aula con número " + aulaNumero + " no existe.");
        }

        // Controlar existencia de la asignatura
        Curso asignatura = buscarCurso(codigoAsignatura);
        if (asignatura == null) {
            throw new ReservaException("El curso con código " + codigoAsignatura + " no existe.");
        }

        // Controlar capacidad del aula
        if (asignatura.getCantidadAlumnos() > aula.getCapacidad()) {
            throw new ReservaException("La cantidad de alumnos supera la capacidad del aula.");
        }

        // Validar si se pueden reservar todos los días de una asignatura
        cantidadClasesAsignatura = asignatura.getCantidadClases();
        diaAsignatura = asignatura.getFechaInicio();
        while (cantidadClasesAsignatura > 0 && aula.ValidarDisponibilidadReserva(diaAsignatura, asignatura.getHoraInicio(),asignatura.getHoraFin())) {
            diaAsignatura = diaAsignatura.plusDays(7);
            cantidadClasesAsignatura--;
        }

        // Si cantidadClasesAsignatura NO llegó a 0, significa que almenos 1 día no se puede reservar
        if (cantidadClasesAsignatura > 0) {
            throw new ReservaException("El aula no está disponible en el horario solicitado");
        }

        // Creo y agrego todas las reservas de la Asignatura
        inicioAsignatura = LocalDateTime.of(asignatura.getFechaInicio(),asignatura.getHoraInicio());
        finAsignatura = LocalDateTime.of(asignatura.getFechaInicio(), asignatura.getHoraFin());

        for (int i = 0; i < asignatura.getCantidadClases(); i++) {
            sumaDias = i * 7L; //Parece que la "L" del final es un "Cast to Long"
            aula.agregarReserva(new Reserva(inicioAsignatura.plusDays(sumaDias), finAsignatura.plusDays(sumaDias), asignatura));
        }
    }

    /**
     * Registra las reservas de un Curso de Extensión.
     * @param codigoCursoExt
     * @param aulaNumero
     * @param fechaInicio
     * @param horaInicio
     * @param horaFin
     * @throws ReservaException
     */
    public void registrarReserva(String codigoCursoExt, int aulaNumero, LocalDate fechaInicio, LocalTime horaInicio, LocalTime horaFin) throws ReservaException {
        int cantidadClasesCursoExt;
        LocalDate diaCursoExt;
        LocalDateTime inicioCursoExt;
        LocalDateTime finCursoExt;
        long sumaDias;

        // Controlar existencia del aula
        Aula aula = buscarAula(aulaNumero);
        if (aula == null) {
            throw new ReservaException("El aula con número " + aulaNumero + " no existe.");
        }

        // Controlar existencia del Curso de Extención
        Curso cursoExt = buscarCurso(codigoCursoExt);
        if (cursoExt == null) {
            throw new ReservaException("El curso con código " + codigoCursoExt + " no existe.");
        }

        // Controlar capacidad del aula
        if (cursoExt.getCantidadAlumnos() > aula.getCapacidad()) {
            throw new ReservaException("La cantidad de alumnos supera la capacidad del aula.");
        }

        // Validar si se pueden reservar todos los días de un curso de extensión
        cantidadClasesCursoExt = cursoExt.getCantidadClases();
        diaCursoExt = fechaInicio;
        while (cantidadClasesCursoExt > 0 && aula.ValidarDisponibilidadReserva(diaCursoExt, horaInicio, horaFin)) {
            diaCursoExt = diaCursoExt.plusDays(7);
            cantidadClasesCursoExt--;
        }

        // Si cantidadClasesCursoExt NO llegó a 0, significa que almenos 1 día no se puede reservar
        if (cantidadClasesCursoExt > 0) {
            throw new ReservaException("El aula no está disponible en el horario solicitado");
        }

        // Creo y agrego todas las reservas del Curso de Extensión
        inicioCursoExt = LocalDateTime.of(fechaInicio,horaInicio);
        finCursoExt = LocalDateTime.of(fechaInicio, horaFin);

        for (int i = 0; i < cursoExt.getCantidadClases(); i++) {
            sumaDias = i * 7L; //Parece que la "L" del final es un "Cast to Long"
            aula.agregarReserva(new Reserva(inicioCursoExt.plusDays(sumaDias), finCursoExt.plusDays(sumaDias), cursoExt));
        }
    }

    /**
     * Registra las reservas de un Evento (interno o externo).
     * @param codigoEvento
     * @param organizacion
     * @param costoAlquiler
     * @param aulaNumero
     * @throws ReservaException
     */
    public void registrarReserva(String codigoEvento, String organizacion, float costoAlquiler, Integer aulaNumero[]) throws ReservaException {
        Aula aula = null;
        int capacidadTotalAulas = 0;
        int cantidadAulas = aulaNumero.length;
        LocalDateTime inicioEvento;
        LocalDateTime finEvento;

        // Controlar existencia del Evento
        Curso evento = buscarCurso(codigoEvento);
        if (evento == null) {
            throw new ReservaException("El evento con código " + codigoEvento + " no existe.");
        }

        // Controlar existencia de las aulas y sumo la capacidad total de todas
        for (int i = 0; i < cantidadAulas; i++) {
            aula = buscarAula(aulaNumero[i]);
            if (aula == null) {
                throw new ReservaException("El aula con número " + aulaNumero[i] + " no existe.");
            }
            capacidadTotalAulas += aula.getCapacidad();

            // Valido disponibilidad horaria en este punto para evitar recorrer y buscar las aulas nuevamente
            if (!aula.ValidarDisponibilidadReserva(evento.getFechaInicio(), evento.getHoraInicio(), evento.getHoraFin())){
                throw new ReservaException("El aula no está disponible en el horario solicitado");
            }
        }

        // Controlar capacidad de las aulas
        if (evento.getCantidadAlumnos() > capacidadTotalAulas) {
            throw new ReservaException("La cantidad de alumnos supera la capacidad total de las aulas.");
        }

        // Creo y agrego todas las reservas del Evento
        evento.setNombreOrganizacion(organizacion);
        evento.setCostoAlquiler(costoAlquiler);
        inicioEvento = LocalDateTime.of(evento.getFechaInicio(), evento.getHoraInicio());
        finEvento = LocalDateTime.of(evento.getFechaInicio(), evento.getHoraFin());
        for (int i = 0; i < cantidadAulas; i++) {
            aula = buscarAula(aulaNumero[i]);
            aula.agregarReserva(new Reserva(inicioEvento, finEvento, evento));
        }
    }

    /**
     * Busca y devuelve un curso. Retorna null si no lo encuentra.
     * @param codigoCurso
     * @return
     */
    public Curso buscarCurso(String codigoCurso){
        for (Curso curso : cursos)
            if (curso.getCodigo().equals(codigoCurso)) {
                return curso; //verificar que devuelve
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

    /**
     * Busca y retorna el aula acorde al numero ingresado por parametro. Returna NULL si no la encuentra
     * @param numero
     * @return
     */
    public Aula buscarAula(int numero) {
        for (Aula aula : aulas) {
            if (aula.getNumero() == numero) {
                return aula;
            }
        }
        return null;
    }

    /**
     * Agrega un Aula a la colección.
     * @param aula
     */
    public void agregarAula(Aula aula) {
        aulas.add(aula);
    }

    /**
     * Agrega un curso a la colección.
     * @param curso
     */
    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    /**
     * Retorna la lista completa de cursos.
     * @return
     */
    public TreeSet<Curso> getCursos() {
        return cursos;
    }

    /**
     * Retorna la lista completa de aulas.
     * @return
     */
    public TreeSet<Aula> getAulas() {
        return aulas;
    }
}
