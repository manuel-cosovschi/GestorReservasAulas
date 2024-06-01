package gestionReservasAulas.dominio;

import java.util.Date;

public class Evento extends Curso {
    private Date dia;
    private String horaInicio;
    private String horaFin;

    public Evento(String codigo, String nombre, int cantidadAlumnos, Date dia, String horaInicio, String horaFin) {
        super(codigo,nombre,cantidadAlumnos);
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

}

