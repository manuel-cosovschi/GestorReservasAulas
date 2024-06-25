package gestionReservasAulas.vista;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.dominio.Reserva;
import gestionReservasAulas.excepciones.ReservaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IUCancelarReserva {
    private JPanel jpCancelarReserva;
    private JLabel lbCancelarReserva;
    private JComboBox cboReservas;
    private JComboBox cboAulas;
    private JButton btnConfirmar;
    private JButton btnCerrar;
    private JLabel lbFijoAula;
    private JLabel lbFijoReserva;
    private JLabel lbFijoDatosReserva;
    private JLabel lbFijoResponsable;
    private JLabel lbFijoFecha;
    private JLabel lbFijoHoraIni;
    private JLabel lbFijoHoraFin;
    private JLabel lbResponsable;
    private JLabel lbFecha;
    private JLabel lbHoraIni;
    private JLabel lbHoraFin;

    public IUCancelarReserva() {

        cargarComboBoxAulas();

        lbResponsable.setText("");
        lbFecha.setText("");
        lbHoraIni.setText("");
        lbHoraFin.setText("");

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component button = (Component) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });

        cboAulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int aulaIndex = cboAulas.getSelectedIndex();
                String aulaNro = "";
                if (aulaIndex > -1) {
                    aulaNro = cboAulas.getSelectedItem().toString();
                    cargarComboBoxReservas(aulaNro);
                }
            }
        });

        cboReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reservaIndex = cboReservas.getSelectedIndex();
                int aulaIndex = cboAulas.getSelectedIndex();
                String reservaCodigo = "";
                Reserva reserva = null;
                if (reservaIndex > -1 && aulaIndex > -1) {
                    reservaCodigo = cboReservas.getSelectedItem().toString();
                    reserva = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(cboAulas.getSelectedItem().toString())).buscarReserva(reservaCodigo);

                    lbResponsable.setText(reserva.getQuienHizoReserva().getCodigo() + " - " + reserva.getQuienHizoReserva().getNombre());
                    lbFecha.setText(reserva.getFechaInicio().toString());
                    lbHoraIni.setText(reserva.getHoraInicio().toString());
                    lbHoraFin.setText(reserva.getHoraFin().toString());
                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reservaIndex = cboReservas.getSelectedIndex();
                int aulaIndex = cboAulas.getSelectedIndex();
                if (reservaIndex > -1 && aulaIndex > -1) {
                    try {
                        if (GestionReservas.getSingletonInstance().cancelarReserva(Integer.parseInt(cboAulas.getSelectedItem().toString()),cboReservas.getSelectedItem().toString())){
                            cargarComboBoxReservas(cboAulas.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null,"Confirmacion");
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"No Confirmacion");
                        }
                    } catch (ReservaException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    private void cargarComboBoxAulas() {
        String aulaNro = "";
        for(Aula aula : GestionReservas.getSingletonInstance().getAulas()) {
            aulaNro = "000" + String.valueOf(aula.getNumero());
            aulaNro = aulaNro.substring(aulaNro.length() - 3);
            cboAulas.addItem(aulaNro);
        }
    }

    private void cargarComboBoxReservas(String aulaNro){
        cboReservas.removeAllItems();
        Aula aula = GestionReservas.getSingletonInstance().buscarAula(Integer.parseInt(aulaNro));

        for (Reserva reserva : aula.getReservas()) {
            cboReservas.addItem(reserva.getCodigo().toString());
        }
    }

    public Container getCancelarReserva() {
        return jpCancelarReserva;
    }
}
