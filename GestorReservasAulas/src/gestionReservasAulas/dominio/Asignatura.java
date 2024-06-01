package gestionReservasAulas.dominio;

import java.sql.Date;

public class Asignatura extends Curso {
    private Date fechaInicio;
    private Date fechaFin;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;

    public Asignatura(String codigo, String nombre, int cantidadAlumnos,Date Fechainicio, Date fechaFin, String diaSemana, String horaInicio, String horaFin) {
        super(codigo, nombre, cantidadAlumnos);
        this.fechaInicio = Fechainicio;
        this.fechaFin = fechaFin;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

}
