package gestionReservasAulas.dominio;

public abstract class Curso implements Comparable<Curso>{
    private String codigo;
    private String nombre;
    private int cantidadAlumnos;

    public Curso(String codigo, String nombre, int cantidadAlumnos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Curso o) {
        return 0;
    }

    public int getCantidadAlumnos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCantidadAlumnos'");
    }
}
