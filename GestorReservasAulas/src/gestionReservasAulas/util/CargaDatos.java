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