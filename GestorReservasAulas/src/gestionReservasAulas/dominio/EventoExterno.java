package gestionReservasAulas.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoExterno extends Evento {
    private String nombreOrganizacion;
    private float costoAlquiler;

    public EventoExterno(String codigo, String nombre, int cantidadAlumnos, LocalDate dia, LocalTime horaInicio, LocalTime horaFin, String nombreOrganizacion, float costoAlquiler) {
        super(codigo,nombre,cantidadAlumnos,dia,horaInicio,horaFin);
        this.nombreOrganizacion = nombreOrganizacion;
        this.costoAlquiler = costoAlquiler;
    }

    public EventoExterno(String codigo, String nombre, int cantidadAlumnos, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo,nombre,cantidadAlumnos,dia,horaInicio,horaFin);
    }

    /**
     * Retorna el nombre de la organización responsable del Evento Externo.
     * @return
     */
    @Override
    public String getOrganizacion() {
        return nombreOrganizacion;
    }

    /**
     * Registra el nombre de la organización responsable de la ultima reserva realizada sobre el Evento Externo.
     * @param nombreOrganizacion
     */
    @Override
    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    /**
     * registra el costo de alquiler del Evento Externo.
     * @param costoAlquiler
     */
    @Override
    public void setCostoAlquiler(float costoAlquiler) {
        this.costoAlquiler = costoAlquiler;
    }

    /**
     * Retorna el costo de alquiler del Evento Externo.
     * @return
     */
    @Override
    public float getCostoPorAlumno(){
        return costoAlquiler;
    }

}
