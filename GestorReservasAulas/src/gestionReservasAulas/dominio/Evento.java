package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento extends Curso {
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Evento(String codigo, String nombre, int cantidadAlumnos, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo,nombre,cantidadAlumnos);
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     * No hace nada (el Evento Interno no tiene costo por alumno).
     * @param costoAlquiler
     */
    @Override
    public void setCostoAlquiler(float costoAlquiler) {

    }

    /**
     * Retorna la fecha/día de inicio del Evento Interno.
     * @return
     */
    @Override
    public LocalDate getFechaInicio() {
        return dia;
    }

    /**
     * Retorna la fecha/día de finalización del Evento Interno (solo dura 1 dia, así que es igual a la fecha inicio).
     * @return
     */
    @Override
    public LocalDate getFechaFin() {
        return dia;
    }

    /**
     * Retorna vacio (no se define un día de semana para los eventos, aunque se podría calcular).
     * @return
     */
    @Override
    public String getDiaSemana() {
        return "";
    }

    /**
     * Retorna la hora de inicio del Evento Interno.
     * @return
     */
    @Override
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Retorna la hora de finalización del Evento Interno.
     * @return
     */
    @Override
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Retorna 1 (solo dura 1 día)
     * @return
     */
    @Override
    public int getCantidadClases() {
        return 1;
    }

    /**
     * Retorna 0 (el Evento Interno no tiene costo por alumno)
     * @return
     */
    @Override
    public float getCostoPorAlumno() {
        return 0;
    }

    /**
     * Retorna vacio (el Evento Interno no tiene organización)
     * @return
     */
    @Override
    public String getOrganizacion() {
        return "";
    }

    /**
     * No hace nada (el Evento Interno no tiene organización)
     * @param nombreOrganizacion
     */
    @Override
    public void setNombreOrganizacion(String nombreOrganizacion) {

    }
}

