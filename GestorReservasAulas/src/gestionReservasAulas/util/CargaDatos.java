package gestionReservasAulas.util;
import gestionReservasAulas.controlador.GestionReservas;

import java.io.*;
import java.text.SimpleDateFormat;

public class CargaDatos {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private GestionReservas gestionReservas;

    // constructor
    public CargaDatos(GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
    }

    public void cargarDatos(File archivo) {

        if (!archivo.exists()) {
            System.err.println("Error: El archivo no existe - " + archivo.getAbsolutePath());
            return;
        }
        if (!archivo.canRead()) {
            System.err.println("Error: No se puede leer el archivo - " + archivo.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            System.out.println("br.readLine(): " + br.readLine());
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                switch (datos[0]) {
                    case "Aula":
                        System.out.println("El aula es: " + datos[0]);
                        break;
                    case "Asignatura":
                        System.out.println("El asignatura es: " + datos[0]);
                        break;
                    case "CursoExtension":
                        System.out.println("El Curso Extension es: " + datos[0]);
                        break;
                    case "Evento":
                        System.out.println("El evento es: " + datos[0]);
                        break;
                    case "EventoExterno":
                        System.out.println("El evento es: " + datos[0]);
                        break;
                    default:
                        System.out.println("Tipo de dato desconocido: " + datos[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException");
            throw new RuntimeException(e);
        }
    }

    // private void validarDatos(List<String> datos) {
    // }

}

//A PARTIR DE ACA VERSION ACTUALIZADA DE LO DE ARRIBA A REVISAR
/*public class CargaDatos {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private GestionReservas gestionReservas;

    public CargaDatos(GestionReservas gestionReservas) {
        this.gestionReservas = gestionReservas;
    }

    public void cargarDatos(File archivo) {
        if (!archivo.exists()) {
            System.err.println("Error: El archivo no existe - " + archivo.getAbsolutePath());
            return;
        }
        if (!archivo.canRead()) {
            System.err.println("Error: No se puede leer el archivo - " + archivo.getAbsolutePath());
            return;
        }

        List<String> errores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                try {
                    switch (datos[0]) {
                        case "Aula":
                            Aula aula = new Aula(Integer.parseInt(datos[1]), Integer.parseInt(datos[2]));
                            gestionReservas.agregarAula(aula);
                            break;
                        case "Asignatura":
                            Asignatura asignatura = new Asignatura(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalDate.parse(datos[5]), datos[6],
                                    LocalTime.parse(datos[7]), LocalTime.parse(datos[8]));
                            gestionReservas.agregarCurso(asignatura);
                            break;
                        case "CursoExtension":
                            CursoExtension cursoExt = new CursoExtension(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    Integer.parseInt(datos[4]), Float.parseFloat(datos[5]));
                            gestionReservas.agregarCurso(cursoExt);
                            break;
                        case "Evento":
                            Evento evento = new Evento(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalTime.parse(datos[5]), LocalTime.parse(datos[6]));
                            gestionReservas.agregarCurso(evento);
                            break;
                        case "EventoExterno":
                            EventoExterno eventoExt = new EventoExterno(datos[1], datos[2], Integer.parseInt(datos[3]),
                                    LocalDate.parse(datos[4]), LocalTime.parse(datos[5]), LocalTime.parse(datos[6]),
                                    datos[7], Float.parseFloat(datos[8]));
                            gestionReservas.agregarCurso(eventoExt);
                            break;
                        default:
                            errores.add("Tipo de dato desconocido: " + datos[0]);
                    }
                } catch (Exception e) {
                    errores.add("Error en la línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            errores.add("Error al leer el archivo: " + e.getMessage());
        }

        if (!errores.isEmpty()) {
            System.err.println("Errores encontrados:");
            for (String error : errores) {
                System.err.println(error);
            }
        } else {
            System.out.println("Datos cargados exitosamente.");
        }
    }
    
}
    */