package curso.java.hibernate.util;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.hibernate.entity.Alumno;
import curso.java.hibernate.enums.AlumnoEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class UtilsBaseDeDatos {
	private static final Logger logger = LogManager.getLogger(UtilsBaseDeDatos.class);
	private static EntityManager em = JpaUtil.getEntityManager();

	public static void cargaInicial() {
		logger.debug("Empezando carga inicial de datos");

		AlumnoEnum[] alumnosEnum = AlumnoEnum.values();
		try {
			em.getTransaction().begin();
			for (AlumnoEnum alumnoEnum : alumnosEnum) {
				Alumno alumno = new Alumno(alumnoEnum.getNombre(), alumnoEnum.getApellidos(), alumnoEnum.getDni(),
						alumnoEnum.getUser());
				em.persist(alumno);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			logger.error("Error al insertar alumnos " + e.getMessage());
			em.getTransaction().rollback();
		}

		logger.debug("Termina");
	}

	public static List<Alumno> listarAlumnos() {
		logger.debug("Listando alumnos");
		List<Alumno> alumnos = em.createQuery("from Alumno", Alumno.class).getResultList();
		alumnos.forEach(alumno -> logger.trace(alumno));// Método Referencia

		logger.debug("Numero de alumnos" + alumnos.size());
		return alumnos;
	}

	public static Alumno buscarPorId(Long id) {
		logger.debug("buscarPorId " + id);
		Alumno alumno = em.find(Alumno.class, id);

		logger.debug("Alumno encontrado " + alumno);
		return alumno;
	}

	public static Alumno buscarPorDni(String dni) {
		logger.debug("buscarPorDni " + dni);
		Query query = em.createQuery("from Alumno a where a.dni=?1", Alumno.class);
		query.setParameter(1, dni);

		Alumno alumno = null;
		try {
			alumno = (Alumno) query.getSingleResult();
		}catch(NoResultException nre) {
			logger.error("No se ha econtrado al alumno con Dni "+ dni);
		}
		
		return alumno;

	}

}
