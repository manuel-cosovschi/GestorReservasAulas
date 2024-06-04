package gestionReservasAulas.excepciones;

public class ReservaException extends Exception {
    private static final long serialVersionUID = 1L;

    public ReservaException(String mensaje) {
        super(mensaje);
    }

    // Constructor que acepta un mensaje de error y una causa
    public ReservaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    // Constructor que acepta una causa
    public ReservaException(Throwable causa) {
        super(causa);
    }

    // Constructor predeterminado
    public ReservaException() {
        super("Error en la gestión de reservas.");
    }
}

