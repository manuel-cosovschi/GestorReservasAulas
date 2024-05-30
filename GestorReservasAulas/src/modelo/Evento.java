package modelo;

public class Evento {
    private String codigo;
    private String descripcion;
    private int cantidadMaximaParticipantes;
    private boolean esExterno;
    private String nombreOrganizacion;
    private double costoAlquiler;

    public Evento(String codigo, String descripcion, int cantidadMaximaParticipantes, boolean esExterno) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidadMaximaParticipantes = cantidadMaximaParticipantes;
        this.esExterno = esExterno;
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

    public int getCantidadMaximaParticipantes() {
        return cantidadMaximaParticipantes;
    }

    public void setCantidadMaximaParticipantes(int cantidadMaximaParticipantes) {
        this.cantidadMaximaParticipantes = cantidadMaximaParticipantes;
    }

    public boolean isEsExterno() {
        return esExterno;
    }

    public void setEsExterno(boolean esExterno) {
        this.esExterno = esExterno;
    }
}

