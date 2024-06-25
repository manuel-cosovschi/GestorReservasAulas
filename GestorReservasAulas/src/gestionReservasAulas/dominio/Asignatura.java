package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Asignatura extends Curso {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String diaSemana;
    private LocalTime horaInicio; //String horaInicio = DateTime.Now.ToString("HH:mm");
    private LocalTime horaFin;

    public Asignatura(String codigo, String nombre, int cantidadAlumnos,LocalDate Fechainicio, LocalDate fechaFin, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo, nombre, cantidadAlumnos);
        this.fechaInicio = Fechainicio;
        this.fechaFin = fechaFin;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     * Retorna la fecha de inicio de la asignatura.
     * @return
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Retorna la fecha de finalización de la asignatura.
     * @return
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Retorna la hora de inicio de la asignatura.
     * @return
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Retorna la hora de finalización de la asignatura.
     * @return
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Retorna la cantidad de clases totales de la asignatura.
     * @return
     */
    @Override
    public int getCantidadClases() {
        return (fechaFin.getDayOfYear() - fechaInicio.getDayOfYear()) / 7;
    }

    /**
     * Retorna 0 (la asignatura no tiene costo por alumno).
     * @return
     */
    @Override
    public float getCostoPorAlumno() {
        return 0;
    }

    /**
     * Retorna vacio (la asignatura no tiene organización)
     * @return
     */
    @Override
    public String getOrganizacion() {
        return "";
    }

    /**
     * Retorna el día de la semana de cursada de la asignatura
     * @return
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * No hace nada. La asignatura no tiene costo.
     * @param costoAlquiler
     */
    @Override
    public void setCostoAlquiler(float costoAlquiler) {

    }

    /**
     * no hace nada. La asignatura no tiene organización
     * @param nombreOrganizacion
     */
    @Override
    public void setNombreOrganizacion(String nombreOrganizacion) {

    }
}
