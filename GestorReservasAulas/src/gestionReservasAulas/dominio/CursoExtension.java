package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class CursoExtension extends Curso {
    private int cantidadClases;
    private float costoPorAlumno;

    public CursoExtension(String nombre, String descripcion, int cantidadAlumnos, int cantidadClases, float costoPorAlumno) {
        super(nombre,descripcion,cantidadAlumnos);
        this.cantidadClases = cantidadClases;
        this.costoPorAlumno = costoPorAlumno;
    }

    /**
     * Retorna la cantidad de clases totales del Curso de Extención.
     * @return
     */
    public int getCantidadClases() {
        return this.cantidadClases;
    }

    /**
     * Retorna el costo por alumno del Curso de Extención.
     * @return
     */
    @Override
    public float getCostoPorAlumno() {
        return this.costoPorAlumno;
    }

    /**
     * Retorna vacio (el Curso de Extención no tiene organización)
     * @return
     */
    @Override
    public String getOrganizacion() {
        return "";
    }

    /**
     * Registra el costo por alumno del Curso de Extención.
     * @param costoAlquiler
     */
    @Override
    public void setCostoAlquiler(float costoAlquiler) {
        this.costoPorAlumno = costoAlquiler;
    }

    /**
     * Retorna NULL (la fecha de inicio del Curso de Extención se determina al reservar un aula)
     * @return
     */
    @Override
    public LocalDate getFechaInicio() {
        return null;
    }

    /**
     * Retorna NULL (la fecha de finalización del Curso de Extención se determina al reservar un aula)
     * @return
     */
    @Override
    public LocalDate getFechaFin() {
        return null;
    }

    /**
     * Retorna NULL (el día de cursada del Curso de Extención se determina al reservar un aula)
     * @return
     */
    @Override
    public String getDiaSemana() {
        return null;
    }

    /**
     * Retorna NULL (la hora de inicio del Curso de Extención se determina al reservar un aula)
     * @return
     */
    @Override
    public LocalTime getHoraInicio() {
        return null;
    }

    /**
     * Retorna NULL (la hora de finalización del Curso de Extención se determina al reservar un aula)
     * @return
     */
    @Override
    public LocalTime getHoraFin() {
        return null;
    }

    /**
     * No hace nada (el Curso de Extención no tiene organización)
     * @param nombreOrganizacion
     */
    @Override
    public void setNombreOrganizacion(String nombreOrganizacion) {

    }
}

