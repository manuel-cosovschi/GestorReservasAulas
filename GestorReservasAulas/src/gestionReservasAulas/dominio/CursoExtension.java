package gestionReservasAulas.dominio;

public class CursoExtension extends Curso {
    private int cantidadClases;
    private double costoPorAlumno;

    public CursoExtension(String nombre, String descripcion, int cantidadAlumnos, int cantidadClases, double costoPorAlumno) {
        super(nombre,descripcion,cantidadAlumnos);
        this.cantidadClases = cantidadClases;
        this.costoPorAlumno = costoPorAlumno;
    }

}

