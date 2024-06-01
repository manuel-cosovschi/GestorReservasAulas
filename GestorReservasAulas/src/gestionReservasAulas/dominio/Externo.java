package gestionReservasAulas.dominio;

import java.util.Date;

public class Externo extends Evento {
    private String nombreOrganizacion;
    private double costoAlquiler;

    public Externo(String codigo, String nombre, int cantidadAlumnos, Date dia, String horaInicio, String horaFin, String nombreOrganizacion, double costoAlquiler) {
        super(codigo,nombre,cantidadAlumnos,dia,horaInicio,horaFin);
        this.nombreOrganizacion = nombreOrganizacion;
        this.costoAlquiler = costoAlquiler;
    }

}
