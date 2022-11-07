package curso.java.jdbc.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Alumno;
import curso.java.jdbc.util.ConexionBaseDatos;

public class AlumnoRepositorioImpl implements RepositorioAdvanced<Alumno> {
	private static final Logger logger = LogManager.getLogger(AlumnoRepositorioImpl.class);
	
	@Override
	public List<Alumno> listar() {
		Alumno alumno=null;
		logger.debug("Entrando en listar alumnos");
		
		//Localizar al alumno por su id
		List<Alumno> alumnos = new ArrayList<>();

        try (Statement stmt = ConexionBaseDatos.getInstance().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos")) {
        	
            while (rs.next()) {
                alumno = crearAlumno(rs);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		if (alumnos.size()!=0) {
			logger.info("Encontrados "+alumnos.size()+" alumos");
		}else {
			logger.warn("No se han encontrado alumnos");
		}
		
		logger.debug("Sale");
		return alumnos;
	}

	@Override
	public Alumno porId(Long id) {
		logger.debug("Entrando con id "+id );
		if (id == null) {
			logger.warn("id de alumno proporcionado llega vacio");
		}

		//Localizar al alumno por su id
		Alumno alumno=null;

        try {
        	PreparedStatement pstmt = ConexionBaseDatos.getInstance().prepareStatement("SELECT * FROM alumnos where id=?");
        	pstmt.setLong(1, id);
        	
        	ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                alumno = crearAlumno(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		if (alumno!=null) {
			logger.info("Alumo encontrado "+alumno.getNombre());
		}else {
			logger.warn("Alumo con id "+id+" no encontrado ");
		}
		
		logger.debug("Sale");
		return alumno;
	}

	@Override
	public void guardar(Alumno alumno) {
		logger.debug("Insertando alumno "+alumno);
		String sql = "INSERT INTO alumnos(nombre, apellidos, dni, user) VALUES(?,?,?,?)";
        
        try (PreparedStatement stmt = ConexionBaseDatos.getInstance().prepareStatement(sql)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApellidos());
            stmt.setString(3, alumno.getDni());
            stmt.setString(4, alumno.getUser());

            stmt.executeUpdate();
            logger.debug("Alumno insertado");
        } catch (SQLException throwables) {
        	logger.error("Error al insertar el alumno "+ alumno.getNombre());
            throwables.printStackTrace();
        }
        
        logger.debug("Finaliza inserción alumno");
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Alumno> busquedaPorUsuario(String user) {
		Alumno alumno=null;
		logger.debug("Entrando en listar alumnos buscando por el usuario "+user);
		
		//Localizar al alumno por su id
		List<Alumno> alumnos = new ArrayList<>();

		try {
        	PreparedStatement pstmt = ConexionBaseDatos.getInstance().prepareStatement("SELECT * FROM alumnos where user=?");
        	pstmt.setString(1, user);
        	ResultSet rs = pstmt.executeQuery();
        	
            while (rs.next()) {
                alumno = crearAlumno(rs);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		if (alumnos.size()!=0) {
			logger.info("Encontrados "+alumnos.size()+" alumos");
		}else {
			logger.warn("No se han encontrado alumnos");
		}
		
		logger.debug("Sale");
		return alumnos;
	}

	
	private Alumno crearAlumno(ResultSet rs) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setId(rs.getLong("id"));
        alumno.setNombre(rs.getString("nombre"));
        alumno.setApellidos(rs.getString("apellidos"));
        alumno.setDni(rs.getString("dni"));
        alumno.setUser(rs.getString("user"));

        return alumno;
    }
}
