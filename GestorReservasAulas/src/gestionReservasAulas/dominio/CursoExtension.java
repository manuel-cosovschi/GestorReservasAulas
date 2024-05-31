package gestionReservasAulas.dominio;

public class CursoExtension {
    private String codigo;
    private String descripcion;
    private int cantidadAlumnos;
    private int cantidadClases;
    private double costoPorAlumno;

    public CursoExtension(String codigo, String descripcion, int cantidadAlumnos, int cantidadClases, double costoPorAlumno) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidadAlumnos = cantidadAlumnos;
        this.cantidadClases = cantidadClases;
        this.costoPorAlumno = costoPorAlumno;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getCantidadClases() {
        return cantidadClases;
    }

    public void setCantidadClases(int cantidadClases) {
        this.cantidadClases = cantidadClases;
    }

    public double getCostoPorAlumno() {
        return costoPorAlumno;
    }

    public void setCostoPorAlumno(double costoPorAlumno) {
        this.costoPorAlumno = costoPorAlumno;
    }
}

