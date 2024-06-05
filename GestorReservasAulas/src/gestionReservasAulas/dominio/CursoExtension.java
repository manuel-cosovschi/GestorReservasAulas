package gestionReservasAulas.dominio;

public class CursoExtension extends Curso {
    private int cantidadClases;
    private float costoPorAlumno;

    public CursoExtension(String nombre, String descripcion, int cantidadAlumnos, int cantidadClases, float costoPorAlumno) {
        super(nombre,descripcion,cantidadAlumnos);
        this.cantidadClases = cantidadClases;
        this.costoPorAlumno = costoPorAlumno;
    }

}

