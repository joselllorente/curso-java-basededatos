package curso.java.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Alumno;
import curso.java.jdbc.repositorio.AlumnoRepositorioImpl;
import curso.java.jdbc.repositorio.RepositorioAdvanced;
import curso.java.jdbc.util.UtilsFicheros;

public class EjercicioAlumnoJdbc {
	private static final Logger logger = LogManager.getLogger(EjercicioAlumnoJdbc.class);
	
	public static void main(String[] args) {
		logger.debug("Iniciando aplicacion");
		
		RepositorioAdvanced<Alumno> repositorio = new AlumnoRepositorioImpl();
		
		Alumno alumno1 = new Alumno("Nombre1","Apellido1","1111A","admin");
		Alumno alumno2 = new Alumno("Nombre2","Apellido2","2222B","admin");
		Alumno alumno3 = new Alumno("Nombre3","Apellido3","3333C","system");
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos.add(alumno3);
		alumnos.add(alumno2);
		alumnos.add(alumno1);
		alumnos.forEach(alumno -> repositorio.guardar(alumno));
		
//		for (Alumno alumno : alumnos) {
//			repositorio.guardar(alumno);
//		}
		
		//List<Alumno> alumnosConsulta = repositorio.listar();
		List<Alumno> alumnosConsulta = repositorio.busquedaPorUsuario("admin");
		alumnosConsulta.forEach(alumno -> logger.debug(alumno.getNombre()) );

		UtilsFicheros.crearArchivoAlumnos("./src/main/resources/alumnos.txt", alumnosConsulta);
		
		logger.debug("Termina aplicacion");
	}

}

