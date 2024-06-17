import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.util.CargaDatos;
import gestionReservasAulas.vista.IUPrincipal;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase GestionReservas
        GestionReservas gestionReservas = new GestionReservas();
        // Crear una instancia de la clase CargaDatos pasando gestionReservas
        CargaDatos cargaDatosTxt = new CargaDatos(gestionReservas);
        // Cargar datos desde un archivo
        File archivo = new File("GestorReservasAulas/archivoAula.txt");
        cargaDatosTxt.cargarDatos(archivo);

        IUPrincipal iuPrincipal = new IUPrincipal();

        JFrame framePrincipal = new JFrame();
        framePrincipal.setContentPane(iuPrincipal.getPanel1());
        //framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        framePrincipal.setResizable(false);
        framePrincipal.setSize(500, 300);
        framePrincipal.setLocationRelativeTo(null);
        //framePrincipal.setUndecorated(true);
        //framePrincipal.pack();
        framePrincipal.setVisible(true);
    }
}

// Cambiar el nombre de la clase abstracta "Curso" por uno mas representativo.