package gestionReservasAulas.vista;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.dominio.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IUListados {
    private JButton btnAula;
    private JButton btnAsignatura;
    private JButton btnCurso;
    private JButton btnEvento;
    private JButton btnReserva;
    private JList lstListado;
    private JScrollPane jspScroll;
    private JLabel lbListados;
    private JButton cerrarButton;
    private JPanel jpListado;

    public IUListados() {

        jspScroll.setViewportView(lstListado);
        lstListado.setVisible(false);

        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component button = (Component) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        btnAula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListado("Aula");
                refresh();
            }
        });
        btnAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListado("Asignatura");
                refresh();
            }
        });
        btnCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListado("Curso");
                refresh();
            }
        });
        btnEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListado("Evento");
                refresh();
            }
        });
        btnReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListado("Reserva");
                refresh();
            }
        });
    }

    private void cargarListado(String tipo) {
        DefaultListModel listModel = new DefaultListModel();
        lstListado.setModel(listModel);

        switch (tipo) {
            case "Aula":
                for(Aula aula : GestionReservas.getSingletonInstance().getAulas()){
                    if (aula.getNumeroPiso().equals("0"))
                        listModel.addElement("Número de piso: Planta baja");
                    else
                        listModel.addElement("Número de piso: " + aula.getNumeroPiso());
                    listModel.addElement("Número de aula: " + aula.getNumeroAula());
                    listModel.addElement("Capacidad máxima: " + aula.getCapacidad());
                    listModel.addElement("Cantidad TOTAL de reservas: " + aula.getCantidadReservasTotal());
                    listModel.addElement("----------------------------------------------------------------------");
                }
                break;
            case "Asignatura":
                for(Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if(curso.getCodigo().substring(0,2).equals("AS")) {
                        listModel.addElement("Código: " + curso.getCodigo());
                        listModel.addElement("Nombre: " + curso.getNombre());
                        listModel.addElement("Fecha de inicio: " + curso.getFechaInicio());
                        listModel.addElement("Fecha de finalización: " + curso.getFechaFin());
                        listModel.addElement("Hora de inicio: " + curso.getHoraInicio() + "hs");
                        listModel.addElement("Hora de finalización: " + curso.getHoraFin() + "hs");
                        listModel.addElement("Dictada los días: " + curso.getDiaSemana());
                        listModel.addElement("Total de inscriptos: " + curso.getCantidadAlumnos());
                        listModel.addElement("Total de clases: " + curso.getCantidadClases());
                        listModel.addElement("----------------------------------------------------------------------");
                    }
                }
                break;
            case "Curso":
                for(Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if(curso.getCodigo().substring(0,2).equals("CE")) {
                        listModel.addElement("Código: " + curso.getCodigo());
                        listModel.addElement("Nombre: " + curso.getNombre());
                        listModel.addElement("Total de inscriptos: " + curso.getCantidadAlumnos());
                        listModel.addElement("Total de clases: " + curso.getCantidadClases());
                        listModel.addElement("Costo por alumno: $" + curso.getCostoPorAlumno());
                        listModel.addElement("----------------------------------------------------------------------");
                    }
                }
                break;
            case "Evento":
                for(Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if(curso.getCodigo().substring(0,2).equals("EI") || curso.getCodigo().substring(0,2).equals("EE")) {
                        if (curso.getCodigo().substring(0,2).equals("EI"))
                            listModel.addElement("Código: " + curso.getCodigo() + " (Interno)");
                        else
                            listModel.addElement("Código: " + curso.getCodigo() + " (Externo)");

                        listModel.addElement("Nombre: " + curso.getNombre());
                        listModel.addElement("Día del evento: " + curso.getFechaInicio());
                        listModel.addElement("Hora de inicio: " + curso.getHoraInicio() + "hs");
                        listModel.addElement("Hora de finalización: " + curso.getHoraFin() + "hs");
                        listModel.addElement("Capacidad máxima: " + curso.getCantidadAlumnos());
                        if (curso.getCodigo().substring(0,2).equals("EE")){
                            listModel.addElement("Organización a cargo: " + curso.getOrganizacion());
                            listModel.addElement("Costo de alquiler: $" + curso.getCostoPorAlumno());
                        }
                        listModel.addElement("----------------------------------------------------------------------");
                    }
                }
                break;
            case "Reserva":
                for(Aula aula : GestionReservas.getSingletonInstance().getAulas()){
                    for (Reserva reserva : aula.getReservas()){
                        listModel.addElement("Código: " + reserva.getCodigo());
                        listModel.addElement("Fecha: " + reserva.getFechaInicio());
                        listModel.addElement("Hora de inicio: " + reserva.getHoraInicio() + "hs");
                        listModel.addElement("Hora de finalización: " + reserva.getHoraFin() + "hs");
                        listModel.addElement("Responsable: " + reserva.getQuienHizoReserva().getCodigo() + " - " + reserva.getQuienHizoReserva().getNombre());
                        listModel.addElement("----------------------------------------------------------------------");
                    }
                }
                break;
            default:
                break;
        }
    }

    public Container getListado() {
        return jpListado;
    }
    private void refresh(){
        lstListado.setVisible(false);
        lstListado.setVisible(true);
    }
}
