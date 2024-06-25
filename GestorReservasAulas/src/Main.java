import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.util.CargaDatos;
import gestionReservasAulas.vista.IUPrincipal;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        GestionReservas gestor = GestionReservas.getSingletonInstance();

        try {
            CargaDatos cargaDatosTxt = new CargaDatos();
            File archivo = new File("GestorReservasAulas/Serializable.txt");
            cargaDatosTxt.cargarDatos(archivo);
        }
        catch (Exception e) {
            //Carga inicial de datos por txt
            CargaDatos cargaDatosTxt = new CargaDatos();
            File archivo = new File("GestorReservasAulas/archivoAula.txt");
            cargaDatosTxt.cargarDatos(archivo);
        }
        finally{
            //Instancia de IUPrincipal y configuración
            IUPrincipal iuPrincipal = new IUPrincipal();
            JFrame framePrincipal = new JFrame();
            iniciarIU(iuPrincipal, framePrincipal);

            if (!framePrincipal.isVisible())
                framePrincipal.setVisible(true);
        }
    }

    /**
     * Define las configuraciónes de la IUPrincipal
     * @param iuPrincipal
     * @param framePrincipal
     */
    private static void iniciarIU(IUPrincipal iuPrincipal, JFrame framePrincipal) {
        framePrincipal.setContentPane(iuPrincipal.getPanel1());
        framePrincipal.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //La "X" no cierra la ventana
        framePrincipal.setResizable(false); //No se puede maximizar
        framePrincipal.setSize(500, 300); //Ancho y alto de la ventana
        framePrincipal.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)
    }
}