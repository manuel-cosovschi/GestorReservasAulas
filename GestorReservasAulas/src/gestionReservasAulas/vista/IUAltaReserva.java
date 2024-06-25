package gestionReservasAulas.vista;

import gestionReservasAulas.controlador.GestionReservas;
import gestionReservasAulas.dominio.Aula;
import gestionReservasAulas.dominio.Curso;
import gestionReservasAulas.excepciones.ReservaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class IUAltaReserva {
    private JRadioButton rbAsignatura;
    private JRadioButton rbCursoExtencion;
    private JRadioButton rbEventoInterno;
    private JRadioButton rbEventoExterno;
    private JPanel jpTipoCurso;
    private JPanel jpAsignatura;
    private JPanel jpConfirmarCancelar;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JLabel lbFijoCurso;
    private JLabel lbTipoCurso;
    private JLabel lbAltaReserva;
    private JPanel jpAltaReserva;
    private JComboBox cboAsignaturas;
    private JComboBox cboAulas;
    private JLabel lbNombre;
    private JLabel lbFechaInicio;
    private JLabel lbFechaFin;
    private JLabel lbDiaSemana;
    private JLabel lbHoraInicio;
    private JLabel lbHorafin;
    private JLabel lbFijoNombre;
    private JLabel lbFijoFechaInicio;
    private JLabel lbFijoFechaFin;
    private JLabel lbFijoDiaSemana;
    private JLabel lbFijoHoraInicio;
    private JLabel lbFijoHorafin;
    private JLabel lbFijoAula;
    private JLabel lbFijoCantAlumnos;
    private JLabel lbCantAlumnos;
    private JLabel lbFijoCEFechaIni;
    private JLabel lbFijoHoraDyH;
    private JLabel lbFijoOrganizacion;
    private JLabel lbFijoPrecio;
    private JTextField txtFecha;
    private JTextField txtHoraIni;
    private JTextField txtOrganizacion;
    private JTextField txtPrecio;
    private JTextField txtHoraFin;
    private JLabel lbFijoHasta;
    private JButton btnAgregar;
    private JButton btnBorrar;

    public IUAltaReserva() {
        ButtonGroup btnGroupAsignaturas = new ButtonGroup();
        btnGroupAsignaturas.add(rbAsignatura);
        btnGroupAsignaturas.add(rbCursoExtencion);
        btnGroupAsignaturas.add(rbEventoInterno);
        btnGroupAsignaturas.add(rbEventoExterno);
        cargarComboBoxAulas();

        rbAsignatura.setSelected(true);
        lbFijoCurso.setText("Asignatura: ");
        lbFijoNombre.setText("Nombre: ");
        lbFijoFechaInicio.setText("Inicia el día: ");
        lbFijoFechaFin.setText("Finaliza el día: ");
        lbFijoDiaSemana.setText("Dictada los días: ");
        lbFijoHoraInicio.setText("Hora de inicio: ");
        lbFijoHorafin.setText("Hora de finalización: ");
        lbFijoCantAlumnos.setText("Alumnos inscriptos: ");
        lbFijoAula.setText("Aula");

        lbFijoCEFechaIni.setVisible(false);
        txtFecha.setVisible(false);

        lbFijoHoraDyH.setVisible(false);
        txtHoraIni.setVisible(false);
        lbFijoHasta.setVisible(false);
        txtHoraFin.setVisible(false);

        lbFijoOrganizacion.setVisible(false);
        txtOrganizacion.setVisible(false);

        lbFijoPrecio.setVisible(false);
        txtPrecio.setVisible(false);

        btnAgregar.setVisible(false);
        btnBorrar.setVisible(false);

        cargarComboBoxCursos("Asignatura");
        cboAulas.setSelectedIndex(-1);
        cboAsignaturas.setSelectedIndex(-1);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component button = (Component) e.getSource();
                SwingUtilities.getWindowAncestor(button).dispose();
            }
        });
        rbAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbFijoCurso.setText("Asignatura: ");
                lbFijoNombre.setText("Nombre: ");
                lbFijoFechaInicio.setText("Inicia el día: ");
                lbFijoFechaFin.setText("Finaliza el día: ");
                lbFijoDiaSemana.setText("Dictada los días: ");
                lbFijoHoraInicio.setText("Hora de inicio: ");
                lbFijoHorafin.setText("Hora de finalización: ");
                lbFijoCantAlumnos.setText("Alumnos inscriptos: ");

                lbFijoHoraInicio.setVisible(true);
                lbFijoHorafin.setVisible(true);
                lbFijoCantAlumnos.setVisible(true);

                lbFijoCEFechaIni.setVisible(false);
                txtFecha.setVisible(false);

                lbFijoHoraDyH.setVisible(false);
                txtHoraIni.setVisible(false);
                lbFijoHasta.setVisible(false);
                txtHoraFin.setVisible(false);

                lbFijoOrganizacion.setVisible(false);
                txtOrganizacion.setVisible(false);

                lbFijoPrecio.setVisible(false);
                txtPrecio.setVisible(false);

                lbFijoAula.setText("Aula");
                btnAgregar.setVisible(false);
                btnBorrar.setVisible(false);

                cargarComboBoxCursos("Asignatura");
                cboAulas.setSelectedIndex(-1);
                cboAsignaturas.setSelectedIndex(-1);
            }
        });
        rbCursoExtencion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbFijoCurso.setText("Curso Extención: ");
                lbFijoNombre.setText("Descripción: ");
                lbFijoFechaInicio.setText("Cantidad de Clases: ");
                lbFijoFechaFin.setText("Costo del curso: ");
                lbFijoDiaSemana.setText("Alumnos inscriptos: ");
                lbFijoHoraInicio.setVisible(false);
                lbFijoHorafin.setVisible(false);
                lbFijoCantAlumnos.setVisible(false);

                lbFijoCEFechaIni.setVisible(true);
                txtFecha.setVisible(true);

                lbFijoHoraDyH.setVisible(true);
                txtHoraIni.setVisible(true);
                lbFijoHasta.setVisible(true);
                txtHoraFin.setVisible(true);

                lbFijoOrganizacion.setVisible(false);
                txtOrganizacion.setVisible(false);

                lbFijoPrecio.setVisible(false);
                txtPrecio.setVisible(false);

                lbFijoAula.setText("Aula");
                btnAgregar.setVisible(false);
                btnBorrar.setVisible(false);

                cargarComboBoxCursos("CursoExt");
                cboAulas.setSelectedIndex(-1);
                cboAsignaturas.setSelectedIndex(-1);
            }
        });
        rbEventoInterno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbFijoCurso.setText("Evento Interno: ");
                lbFijoNombre.setText("Descripción: ");
                lbFijoFechaInicio.setText("Día del evento: ");
                lbFijoFechaFin.setText("Hora de inicio: ");
                lbFijoDiaSemana.setText("Hora de finalización: ");
                lbFijoHoraInicio.setText("Cupos máximos: ");
                lbFijoHorafin.setText("Aulas seleccionadas: ");
                lbFijoCantAlumnos.setVisible(false);

                lbFijoCEFechaIni.setVisible(false);
                txtFecha.setVisible(false);

                lbFijoHoraDyH.setVisible(false);
                txtHoraIni.setVisible(false);
                lbFijoHasta.setVisible(false);
                txtHoraFin.setVisible(false);

                lbFijoOrganizacion.setVisible(false);
                txtOrganizacion.setVisible(false);

                lbFijoPrecio.setVisible(false);
                txtPrecio.setVisible(false);

                lbFijoAula.setText("Aula");
                btnAgregar.setVisible(true);
                btnBorrar.setVisible(true);

                lbFijoHoraInicio.setVisible(true);
                lbFijoHorafin.setVisible(true);

                cargarComboBoxCursos("EventoInt");
                cboAulas.setSelectedIndex(-1);
                cboAsignaturas.setSelectedIndex(-1);
            }
        });
        rbEventoExterno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbFijoCurso.setText("Evento Externo: ");
                lbFijoNombre.setText("Descripción: ");
                lbFijoFechaInicio.setText("Día del evento: ");
                lbFijoFechaFin.setText("Hora de inicio: ");
                lbFijoDiaSemana.setText("Hora de finalización: ");
                lbFijoHoraInicio.setText("Cupos máximos: ");
                lbFijoHorafin.setText("Aulas seleccionadas: ");
                lbFijoCantAlumnos.setVisible(false);

                lbFijoHoraInicio.setVisible(true);
                lbFijoHorafin.setVisible(true);

                lbFijoCEFechaIni.setVisible(false);
                txtFecha.setVisible(false);

                lbFijoHoraDyH.setVisible(false);
                txtHoraIni.setVisible(false);
                lbFijoHasta.setVisible(false);
                txtHoraFin.setVisible(false);

                lbFijoOrganizacion.setVisible(true);
                txtOrganizacion.setVisible(true);
                txtOrganizacion.setText("");

                lbFijoPrecio.setVisible(true);
                txtPrecio.setVisible(true);
                txtPrecio.setText("");

                lbFijoAula.setText("Aula");
                btnAgregar.setVisible(true);
                btnBorrar.setVisible(true);

                cargarComboBoxCursos("EventoExt");
                cboAulas.setSelectedIndex(-1);
                cboAsignaturas.setSelectedIndex(-1);
            }
        });
        cboAsignaturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = cboAsignaturas.getSelectedIndex();
                if (cboAsignaturas.getSelectedIndex() != -1) {
                    Curso curso = GestionReservas.getSingletonInstance().buscarCurso(cboAsignaturas.getSelectedItem().toString());;
                    String cursoCodigo = cboAsignaturas.getSelectedItem().toString();
                    switch (cursoCodigo.substring(0,2)){
                        case "AS":
                            lbNombre.setText(curso.getNombre());
                            lbFechaInicio.setText(String.valueOf(curso.getFechaInicio()));
                            lbFechaFin.setText(String.valueOf(curso.getFechaFin()));
                            lbDiaSemana.setText(curso.getDiaSemana());
                            lbHoraInicio.setText(curso.getHoraInicio() + " hs");
                            lbHorafin.setText(curso.getHoraFin() + " hs");
                            lbCantAlumnos.setText(String.valueOf(curso.getCantidadAlumnos()));
                            break;
                        case "CE":
                            lbNombre.setText(curso.getNombre());
                            lbFechaInicio.setText(String.valueOf(curso.getCantidadClases()));
                            lbFechaFin.setText("$ " + curso.getCostoPorAlumno());
                            lbDiaSemana.setText(String.valueOf(curso.getCantidadAlumnos()));
                            break;
                        case "EI":
                            lbNombre.setText(curso.getNombre());
                            lbFechaInicio.setText(String.valueOf(curso.getFechaInicio()));
                            lbFechaFin.setText(curso.getHoraInicio() + " hs");
                            lbDiaSemana.setText(curso.getHoraFin() + " hs");
                            lbHoraInicio.setText(String.valueOf(curso.getCantidadAlumnos()));
                            break;
                        case "EE":
                            lbNombre.setText(curso.getNombre());
                            lbFechaInicio.setText(String.valueOf(curso.getFechaInicio()));
                            lbFechaFin.setText(curso.getHoraInicio() + " hs");
                            lbDiaSemana.setText(curso.getHoraFin() + " hs");
                            lbHoraInicio.setText(String.valueOf(curso.getCantidadAlumnos()));
                            txtOrganizacion.setText(curso.getOrganizacion());
                            txtPrecio.setText(String.valueOf(curso.getCostoPorAlumno()));
                            break;
                        default:
                            break;
                    }
                }
                else{
                    lbNombre.setText("");
                    lbFechaInicio.setText("");
                    lbFechaFin.setText("");
                    lbDiaSemana.setText("");
                    lbHoraInicio.setText("");
                    lbHorafin.setText("");
                    lbCantAlumnos.setText("");
                    txtOrganizacion.setText("");
                    txtPrecio.setText("");
                }
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curso = "";
                int aula = 0;
                LocalDate fechaInicio = null;
                LocalTime horaInicio = null;
                LocalTime horaFin = null;
                String organizacion = "";
                float precio = 0;
                String aulasSeleccionadas[];
                Integer aulas[];
                try{
                if (true){ //validar combos
                    curso = cboAsignaturas.getSelectedItem().toString();
                    aula = Integer.parseInt(cboAulas.getSelectedItem().toString());
                    if (rbAsignatura.isSelected()){
                        try {
                            GestionReservas.getSingletonInstance().registrarReserva(curso, aula);
                        } catch (ReservaException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else if (rbCursoExtencion.isSelected()) {
                        try {
                            if (true) {//validar fecha y hora en txt
                                fechaInicio = LocalDate.parse(txtFecha.getText());
                                horaInicio = LocalTime.parse(txtHoraIni.getText());
                                horaFin = LocalTime.parse(txtHoraFin.getText());
                                GestionReservas.getSingletonInstance().registrarReserva(curso, aula, fechaInicio, horaInicio, horaFin);
                            }
                        } catch (ReservaException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (rbEventoInterno.isSelected()) {
                        try {
                            if (true){ //validar aulas seleccionadas
                                aulasSeleccionadas = lbHorafin.getText().split(",");
                                aulas = new Integer[aulasSeleccionadas.length];
                                for (int i = 0; i < aulasSeleccionadas.length; i++) {
                                    aulas[i] = Integer.valueOf(aulasSeleccionadas[i]);
                                }
                                GestionReservas.getSingletonInstance().registrarReserva(curso,"",0,aulas);
                            }
                        } catch (ReservaException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (rbEventoExterno.isSelected()) {
                        try {
                            if (true){ //validar aulas seleccionadas, organizacion y precio
                                aulasSeleccionadas = lbHorafin.getText().split(",");
                                aulas = new Integer[aulasSeleccionadas.length];
                                for (int i = 0; i < aulasSeleccionadas.length; i++) {
                                    aulas[i] = Integer.valueOf(aulasSeleccionadas[i]);
                                }
                                organizacion = txtOrganizacion.getText();
                                precio = Integer.parseInt(txtPrecio.getText());
                                GestionReservas.getSingletonInstance().registrarReserva(curso,organizacion,precio,aulas);
                            }
                        } catch (ReservaException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    JOptionPane.showMessageDialog(null,"Confirmacion");
                }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cboAulas.getSelectedIndex() > -1) {
                    if (lbHorafin.getText().equals("")) {
                        lbHorafin.setText(cboAulas.getSelectedItem().toString());
                    }
                    else{
                        if(!lbHorafin.getText().contains(cboAulas.getSelectedItem().toString())) {
                            lbHorafin.setText(lbHorafin.getText() + "," + cboAulas.getSelectedItem().toString());
                        }
                    }
                }
            }
        });
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbHorafin.setText("");
            }
        });
    }

    public JPanel getAltaReserva() {
        return jpAltaReserva;
    }

    private void cargarComboBoxAulas() {
        String aulaNro = "";
        for(Aula aula : GestionReservas.getSingletonInstance().getAulas()) {
            aulaNro = "000" + String.valueOf(aula.getNumero());
            aulaNro = aulaNro.substring(aulaNro.length() - 3);
            cboAulas.addItem(aulaNro);
        }
    }

    private void cargarComboBoxCursos(String tipoCurso){
        cboAsignaturas.removeAllItems();
        switch(tipoCurso){
            case "Asignatura":
                for (Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if (curso.getClass().getSimpleName().equals("Asignatura")){
                        cboAsignaturas.addItem(curso.getCodigo());
                    }
                }
                break;
            case "CursoExt":
                for (Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if (curso.getClass().getSimpleName().equals("CursoExtension")){
                        cboAsignaturas.addItem(curso.getCodigo());
                    }
                }
                break;
            case "EventoInt":
                for (Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if (curso.getClass().getSimpleName().equals("Evento")){
                        cboAsignaturas.addItem(curso.getCodigo());
                    }
                }
                break;
            case "EventoExt":
                for (Curso curso : GestionReservas.getSingletonInstance().getCursos()){
                    if (curso.getClass().getSimpleName().equals("EventoExterno")){
                        cboAsignaturas.addItem(curso.getCodigo());
                    }
                }
            default:
                break;
        }
    }

}
