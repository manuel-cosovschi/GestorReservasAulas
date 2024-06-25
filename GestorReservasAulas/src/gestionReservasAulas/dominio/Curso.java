package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Curso implements Comparable<Curso>{
    private String codigo;
    private String nombre;
    private int cantidadAlumnos;

    public Curso(String codigo, String nombre, int cantidadAlumnos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadAlumnos = cantidadAlumnos;
    }

    /**
     * Retorna el código de la Asignatura/Curso de extención/Evento.
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre de la Asignatura/Curso de extención/Evento.
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Eetorna la cantidad de alumnos registrados en la Asignatura/Curso de extención/Evento.
     * @return
     */
    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public abstract void setNombreOrganizacion(String nombreOrganizacion);
    public abstract void setCostoAlquiler(float costoAlquiler);

    @Override
    public int compareTo(Curso o) {
        return this.codigo.compareTo(o.codigo);
    }

    public abstract LocalDate getFechaInicio();
    public abstract LocalDate getFechaFin();
    public abstract String getDiaSemana();
    public abstract LocalTime getHoraInicio();
    public abstract LocalTime getHoraFin();
    public abstract int getCantidadClases();
    public abstract float getCostoPorAlumno();
    public abstract String getOrganizacion();
}
