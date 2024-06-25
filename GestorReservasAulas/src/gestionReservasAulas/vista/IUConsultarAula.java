package gestionReservasAulas.vista;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.dominio.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class IUConsultarAula {
    private JPanel jpIUConsultarAula;
    private JList lstAula;
    private JComboBox cboPiso;
    private JComboBox cboCursos;
    private JButton btnMostrar;
    private JButton btnCerrar;
    private JLabel lbFijoPiso;
    private JLabel lbFijoCurso;
    private JScrollPane jspScroll;

    public IUConsultarAula() {
        cargarComboBoxPisoAulas();
        cboPiso.setSelectedIndex(-1);

        cargarComboBoxCursos();
        cboCursos.setSelectedIndex(-1);

        jspScroll.setViewportView(lstAula);
        lstAula.setVisible(false);

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component button = (Component) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidadReservas;
                String pisoSeleccionado = "";
                String cursosSeleccionado = "";

                DefaultListModel listModelAula = new DefaultListModel();
                lstAula.setModel(listModelAula);

                if (cboPiso.getSelectedIndex() != -1 && cboCursos.getSelectedIndex() != -1) {
                    pisoSeleccionado = cboPiso.getSelectedItem().toString();
                    if (pisoSeleccionado.equals("Planta baja")) {
                        pisoSeleccionado = "0";
                    }
                    cursosSeleccionado = cboCursos.getSelectedItem().toString();
                    for (Aula aula : GestionReservas.getSingletonInstance().getAulas()) {
                        if(aula.getNumeroPiso().equals(pisoSeleccionado)){
                            listModelAula.addElement("Nro de aula: " + aula.getNumeroAula());
                            listModelAula.addElement("Capacidad máxima: " + aula.getCapacidad());
                            cantidadReservas = 0;
                            for (Reserva reserva : aula.getReservas()){
                                if(reserva.getQuienHizoReserva().getCodigo().equals(cursosSeleccionado)){
                                    cantidadReservas += 1;
                                }
                            }
                            listModelAula.addElement("Cantidad de reservas: " + cantidadReservas);
                            if (cantidadReservas > 0) {
                                listModelAula.addElement("RESERVAS:");
                                for (Reserva reserva : aula.getReservas()) {
                                    if (reserva.getQuienHizoReserva().getCodigo().equals(cursosSeleccionado)) {
                                        listModelAula.addElement("      Código: " + reserva.getCodigo());
                                        listModelAula.addElement("      Día de la reserva: " + reserva.getFechaInicio());
                                        listModelAula.addElement("      Inicia a las " + reserva.getHoraInicio() + "hs ");
                                        listModelAula.addElement("      Termina a las " + reserva.getHoraFin() + "hs");
                                        listModelAula.addElement("      ");
                                    }
                                }
                            }
                            listModelAula.addElement("------------------------------------------------------------------");
                        }
                    }
                }
                lstAula.setVisible(false);
                lstAula.setVisible(true);
            }
        });
    }

    private void cargarComboBoxPisoAulas() {
        TreeSet<String> aulaPisos = new TreeSet<>();
        String aulaPiso;
        for (Aula aula : GestionReservas.getSingletonInstance().getAulas()) {
            aulaPiso = aula.getNumeroPiso();
            if (aulaPiso.equals("0"))
                aulaPiso = "Planta baja";
            aulaPisos.add(aulaPiso);
        }
        for (String piso : aulaPisos) {
            cboPiso.addItem(piso);
        }
    }

    private void cargarComboBoxCursos() {
        for (Curso curso : GestionReservas.getSingletonInstance().getCursos()){
            cboCursos.addItem(curso.getCodigo());
        }
    }

    public Container getConsultarAula() {
        return jpIUConsultarAula;
    }
}
