package modelo;

public class Asignatura {
    private String codigo;
    private String nombre;
    // private String rangoFechas;
    private Date fechaInicio;
    private Date fechaFin;
    private String diaSemana;
    private String horario;
    private int cantidadAlumnos;

    public Asignatura(String codigo, String nombre, String rangoFechas, String diaSemana, String horario, int cantidadAlumnos) {
        this.codigo = codigo;
        this.nombre = nombre;
        // this.rangoFechas = rangoFechas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diaSemana = diaSemana;
        this.horario = horario;
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // public String getRangoFechas() {
    //     return rangoFechas;
    // }

    // public void setRangoFechas(String rangoFechas) {
    //     this.rangoFechas = rangoFechas;
    // }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }
}
