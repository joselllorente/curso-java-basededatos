package curso.java.hibernate.ejercicio;

import static curso.java.hibernate.util.UtilsBaseDeDatos.buscarPorId;
import static curso.java.hibernate.util.UtilsBaseDeDatos.cargaInicial;
import static curso.java.hibernate.util.UtilsBaseDeDatos.listarAlumnos;
import static curso.java.hibernate.util.UtilsBaseDeDatos.buscarPorDni;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.hibernate.entity.Alumno;


public class HibernateEjercicio {
	private static final Logger logger = LogManager.getLogger(HibernateEjercicio.class);
	public static void main(String[] args) {
		logger.debug("Empezando");
        
		cargaInicial();
		
		listarAlumnos().forEach(alumno->logger.info(alumno));
		
		Alumno alumno = buscarPorId(2L);
		logger.info(alumno);
		
		Alumno alumnoDni = buscarPorDni("DNI4");
		logger.info(alumnoDni);
		
		logger.debug("Termina");
	}
	
	

}
