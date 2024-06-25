package gestionReservasAulas.vista;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.reportes.GenerarReportes;
import gestionReservasAulas.util.GuardarDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class IUPrincipal{
    private IUAltaReserva iuAltaReserva;
    private IUConsultarAula iuConsultarAula;
    private IUCancelarReserva iuCancelarReserva;
    private IUListados iuListado;
    private IUReportes iuReportes;
    private JFrame frameAltaReserva;
    private JFrame frameConsultarAula;
    private JFrame frameCancelarReserva;
    private JFrame frameListado;
    private JFrame frameReportes;
    private JPanel panel1;
    private JButton crearReservaButton;
    private JButton cancelarReservaButton;
    private JButton listarButton;
    private JButton generarReportesButton;
    private JButton consultarAulaButton;
    private JButton salirButton;

    public IUPrincipal() {

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GuardarDatos.guardarDatosTxt(GestionReservas.getSingletonInstance());
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Error al guardar datos");
                }
                finally {
                    System.exit(0);
                }
            }
        });

        crearReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iuAltaReserva != null)
                    iuAltaReserva = null;

                iuAltaReserva = new IUAltaReserva();
                frameAltaReserva = new JFrame();

                frameAltaReserva.setContentPane(iuAltaReserva.getAltaReserva());
                frameAltaReserva.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frameAltaReserva.setResizable(false); //No se puede maximizar
                frameAltaReserva.setSize(625, 375); //Ancho y alto de la ventana
                frameAltaReserva.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)

                frameAltaReserva.setVisible(true);
            }
        });

        consultarAulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iuConsultarAula != null)
                    iuConsultarAula = null;

                iuConsultarAula = new IUConsultarAula();
                frameConsultarAula = new JFrame();

                frameConsultarAula.setContentPane(iuConsultarAula.getConsultarAula());
                frameConsultarAula.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frameConsultarAula.setResizable(false); //No se puede maximizar
                frameConsultarAula.setSize(450, 375); //Ancho y alto de la ventana
                frameConsultarAula.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)

                frameConsultarAula.setVisible(true);
            }
        });
        cancelarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iuCancelarReserva != null)
                    iuCancelarReserva = null;

                iuCancelarReserva = new IUCancelarReserva();
                frameCancelarReserva = new JFrame();

                frameCancelarReserva.setContentPane(iuCancelarReserva.getCancelarReserva());
                frameCancelarReserva.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frameCancelarReserva.setResizable(false); //No se puede maximizar
                frameCancelarReserva.setSize(450, 250); //Ancho y alto de la ventana
                frameCancelarReserva.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)

                frameCancelarReserva.setVisible(true);
            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iuListado != null)
                    iuListado = null;

                iuListado = new IUListados();
                frameListado = new JFrame();

                frameListado.setContentPane(iuListado.getListado());
                frameListado.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frameListado.setResizable(false); //No se puede maximizar
                frameListado.setSize(625, 700); //Ancho y alto de la ventana
                frameListado.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)

                frameListado.setVisible(true);
            }
        });
        generarReportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GenerarReportes reportes = new GenerarReportes();
                    reportes.generarReportePromedioReservas();
                    reportes.generarReporteMontos();
                    JOptionPane.showMessageDialog(null,"Confirmacion");

                    if (iuReportes != null)
                        iuReportes = null;

                    iuReportes = new IUReportes();
                    frameReportes = new JFrame();

                    frameReportes.setContentPane(iuReportes.getReportes());
                    frameReportes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frameReportes.setResizable(false); //No se puede maximizar
                    frameReportes.setSize(625, 700); //Ancho y alto de la ventana
                    frameReportes.setLocationRelativeTo(null); //Posicion inicial de la ventan (centro)

                    frameReportes.setVisible(true);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
