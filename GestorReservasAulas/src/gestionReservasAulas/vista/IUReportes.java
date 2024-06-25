package gestionReservasAulas.vista;

import gestionReservasAulas.dominio.Aula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IUReportes {
    private JPanel jpReportes;
    private JButton btnCerrar;
    private JLabel lbReportes;
    private JPanel jpMontos;
    private JPanel jpAulas;
    private JScrollPane jspMontos;
    private JScrollPane jspAulas;
    private JList lstMontos;
    private JList lstAulas;

    public IUReportes() {

        jspMontos.setViewportView(lstMontos);
        lstMontos.setVisible(false);

        jspAulas.setViewportView(lstAulas);
        lstAulas.setVisible(false);

        cargarListas();

        refresh();

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component button = (Component) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
    }

    private void cargarListas() {
        DefaultListModel listModelMontos = new DefaultListModel();
        lstMontos.setModel(listModelMontos);

        DefaultListModel listModelAulas = new DefaultListModel();
        lstAulas.setModel(listModelAulas);

        File archivo = new File("GestorReservasAulas/reporte_montos.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    if (linea.equals(""))
                        listModelMontos.addElement(" ");
                    listModelMontos.addElement(linea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

        archivo = new File("GestorReservasAulas/reporte_promedio_reservas.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    if (linea.equals(""))
                        listModelAulas.addElement(" ");
                    listModelAulas.addElement(linea);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    public Container getReportes() {
        return jpReportes;
    }

    private void refresh(){
        lstMontos.setVisible(false);
        lstMontos.setVisible(true);

        lstAulas.setVisible(false);
        lstAulas.setVisible(true);
    }
}
