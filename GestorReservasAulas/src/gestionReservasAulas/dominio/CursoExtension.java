package gestionReservasAulas.dominio;

public class CursoExtension extends Curso {
    private int cantidadClases;
    private float costoPorAlumno;

    public CursoExtension(String nombre, String descripcion, int cantidadAlumnos, int cantidadClases, float costoPorAlumno) {
        super(nombre,descripcion,cantidadAlumnos);
        this.cantidadClases = cantidadClases;
        this.costoPorAlumno = costoPorAlumno;
    }


    public int getCantidadClases() {
        return this.cantidadClases;
    }

    public void setCantidadClases(int cantidadClases) {
        this.cantidadClases = cantidadClases;
    }

    public float getCostoPorAlumno() {
        return this.costoPorAlumno;
    }

    public void setCostoPorAlumno(float costoPorAlumno) {
        this.costoPorAlumno = costoPorAlumno;
    }


}

