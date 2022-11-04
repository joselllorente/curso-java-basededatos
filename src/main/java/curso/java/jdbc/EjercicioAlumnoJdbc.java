package curso.java.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Alumno;
import curso.java.jdbc.repositorio.AlumnoRepositorioImpl;
import curso.java.jdbc.repositorio.Repositorio;

public class EjercicioAlumnoJdbc {
	private static final Logger logger = LogManager.getLogger(EjercicioAlumnoJdbc.class);
	
	public static void main(String[] args) {
		logger.debug("Iniciando aplicacion");
		
		Repositorio<Alumno> repositorio = new AlumnoRepositorioImpl();
		repositorio.porId(1L);
		
		logger.debug("Termina aplicacion");
	}

}
