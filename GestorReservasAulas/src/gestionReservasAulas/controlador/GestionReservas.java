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

    public GestionReservas() {

        this.aulas = new TreeSet<>();
        this.cursos = new TreeSet<>();

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
        Asignatura asignatura = buscarAsignatura(codigoAsignatura);
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
        CursoExtension cursoExt = buscarCursoExtencion(codigoCursoExt);
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
    public void registrarReserva(String codigoEvento, String organizacion, float costoAlquiler, int... aulaNumero) throws ReservaException {
        Aula aula = null;
        Evento evento;
        int capacidadTotalAulas = 0;
        int cantidadAulas = aulaNumero.length;
        LocalDateTime inicioEvento;
        LocalDateTime finEvento;

        // Controlar existencia del Evento
        if (!organizacion.isEmpty() || costoAlquiler != 0) {
            evento = buscarEventoExterno(codigoEvento);
            if (evento != null) {
                ((EventoExterno) evento).setNombreOrganizacion(organizacion);
                ((EventoExterno) evento).setCostoAlquiler(costoAlquiler);
            }
        }
        else
            evento = buscarEventoInterno(codigoEvento);

        if (evento == null) {
            throw new ReservaException("El evento con código " + codigoEvento + " no existe.");
        }

        // Controlar existencia de las aulas y sumo la capacidad total de todas
        for (int i = 0; i < cantidadAulas; i++) {
            aula = buscarAula(aulaNumero[i]);
            if (aula == null) {
                throw new ReservaException("El aula con número " + aulaNumero[i] + " no existe.");
            }
            capacidadTotalAulas = capacidadTotalAulas + aula.getCapacidad();

            // Valido disponibilidad horaria en este punto para evitar recorrer y buscar las aulas nuevamente
            if (!aula.ValidarDisponibilidadReserva(evento.getDia(), evento.getHoraInicio(), evento.getHoraFin())){
                throw new ReservaException("El aula no está disponible en el horario solicitado");
            }
        }

        // Controlar capacidad de las aulas
        if (evento.getCantidadAlumnos() > capacidadTotalAulas) {
            throw new ReservaException("La cantidad de alumnos supera la capacidad total de las aulas.");
        }

        // Creo y agrego todas las reservas del Evento
        inicioEvento = LocalDateTime.of(evento.getDia(), evento.getHoraInicio());
        finEvento = LocalDateTime.of(evento.getDia(), evento.getHoraFin());
        for (int i = 0; i < cantidadAulas; i++) {
            aula = buscarAula(i);
            aula.agregarReserva(new Reserva(inicioEvento, finEvento, evento));
        }
    }

    /**
     * Busca y retorna un Curso Asignatura o NULL si no lo encuentra
     * @param codigoAsignatura
     * @return
     */
    private Asignatura buscarAsignatura(String codigoAsignatura) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigoAsignatura)) {
                return (Asignatura)curso; //verificar que devuelve
            }
        }
        return null;
    }

    /**
     * Busca y retorna un Curso CursoExtención o NULL si no lo encuentra
     * @param codigoCursoExt
     * @return
     */
    private CursoExtension buscarCursoExtencion(String codigoCursoExt) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigoCursoExt)) {
                return (CursoExtension)curso;
            }
        }
        return null;
    }

    /**
     * Busca y retorna un Curso Evento o NULL si no lo encuentra
     * @param codigoEvento
     * @return
     */
    private Evento buscarEventoInterno(String codigoEvento) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigoEvento)) {
                return (Evento)curso;
            }
        }
        return null;
    }

    /**
     * Busca y retorna un Curso EventoExterno o NULL si no lo encuentra
     * @param codigoEvento
     * @return
     */
    private EventoExterno buscarEventoExterno(String codigoEvento) {
        for (Curso curso : cursos) {
            if (curso.getCodigo().equals(codigoEvento)) {
                return (EventoExterno)curso;
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

    /**
     * Busca y retorna el aula acorde al numero ingresado por parametro. Returna NULL si no la encuentra
     * @param numero
     * @return
     */
    private Aula buscarAula(int numero) {
        for (Aula aula : aulas) {
            if (aula.getNumero() == numero) {
                return aula;
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
