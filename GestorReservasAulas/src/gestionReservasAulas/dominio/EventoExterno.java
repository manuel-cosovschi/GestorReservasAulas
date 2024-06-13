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

    //PARA CONSULTAR
    // No estoy seguro si es necesario o no, pero en el Registrar Reserva se informan Organización y Costo Alquiler. Supongo que es para setearlo en esta clase
    public EventoExterno(String codigo, String nombre, int cantidadAlumnos, LocalDate dia, LocalTime horaInicio, LocalTime horaFin) {
        super(codigo,nombre,cantidadAlumnos,dia,horaInicio,horaFin);
    }

    //Los vamos a necesitar
    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public double getCostoAlquiler() {
        return costoAlquiler;
    }

}
