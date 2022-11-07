package curso.java.jdbc.repositorio;

import java.util.List;

public interface RepositorioAdvanced<T> extends Repositorio<T> {
	
	List<T> busquedaPorUsuario (String user);

}
