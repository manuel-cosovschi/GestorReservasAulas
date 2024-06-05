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

}
