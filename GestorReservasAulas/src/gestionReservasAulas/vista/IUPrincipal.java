package gestionReservasAulas.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class IUPrincipal{
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
                System.exit(0);
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
