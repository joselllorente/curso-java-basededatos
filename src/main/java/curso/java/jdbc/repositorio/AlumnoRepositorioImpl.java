package curso.java.jdbc.repositorio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Alumno;

public class AlumnoRepositorioImpl implements Repositorio<Alumno> {
	private static final Logger logger = LogManager.getLogger(AlumnoRepositorioImpl.class);
	
	
	@Override
	public List<Alumno> listar() {
		logger.debug("Entrando en el método listar de la clase AlumnoRepositorioImpl");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno porId(Long id) {
		Alumno alumno=null;
		logger.debug("Entrando con id "+id );
		if (id == null) {
			logger.warn("id de alumno proporcionado llega vacio");
		}

		//Localizar al alumno por su id
		
		if (alumno!=null) {
			logger.info("Alumo encontrado "+alumno.getNombre());
		}else {
			logger.warn("Alumo con id "+id+" no encontrado ");
		}
		
		logger.debug("Sale");
		return null;
	}

	@Override
	public void guardar(Alumno t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
