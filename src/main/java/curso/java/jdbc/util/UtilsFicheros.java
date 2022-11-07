package curso.java.jdbc.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Alumno;

public class UtilsFicheros {
	private static final Logger logger = LogManager.getLogger(UtilsFicheros.class);
	
	public static void crearArchivoAlumnos(String nombre, List<Alumno> alumnos){
		logger.debug("Creando el fichero "+ nombre + "con " + alumnos.size() + " alumnos");
        File archivo = new File(nombre);
        
        try (PrintWriter buffer = new PrintWriter(new FileWriter(archivo))){

        	for (Alumno alumno : alumnos) {
        		buffer.print(alumno.getId());
        		buffer.print("|");
        		buffer.print(alumno.getNombre());
        		buffer.print("|");
        		buffer.print(alumno.getApellidos());
        		buffer.print("|");
        		buffer.print(alumno.getDni());
        		buffer.print("|");
        		buffer.println(alumno.getUser());
        		
			}
            
            // buffer.close();
            logger.debug("El archivo "+nombre+"  se ha creado con éxito!");
        } catch (IOException e) {
        	logger.error("Error creando el fichero" + nombre + ": " + e.getMessage());
//            e.printStackTrace();
        }
    }
	
	
}
