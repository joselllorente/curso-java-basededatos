package curso.java.jdbc;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.java.jdbc.modelo.Producto;
import curso.java.jdbc.repositorio.ProductoRepositorioImpl;
import curso.java.jdbc.repositorio.Repositorio;

public class EjemploJdbc {
	
	//Encargado de mostrar las trazas
	private static final Logger logger = LogManager.getLogger(EjemploJdbc.class);
	
    public static void main(String[] args) {
        try {
        	logger.trace("Empezando trace");
        	logger.debug("Empezando debug");
        	logger.info("Empezando info");
        	logger.warn("Empezando warning");
        	
        	
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
//            System.out.println("============= listar =============");
//            repositorio.listar().forEach(producto->System.out.println(producto));
//            //repositorio.listar().forEach(System.out::println);
//
//            System.out.println("============= obtener por id =============");
//            System.out.println(repositorio.porId(1L));

//            System.out.println("============= insertar nuevo producto =============");
            Producto producto = new Producto();
            producto.setNombre("Teclado mecánico");
            producto.setPrecio(500);
            producto.setFechaRegistro(new Date());
            repositorio.guardar(producto);
            
            System.out.println("Producto guardado con éxito");
            repositorio.listar().forEach(System.out::println);

        } catch (Exception e) {
        	logger.error("Empezando error " +e.getMessage());
        	logger.fatal("Empezando fatal");
//            e.printStackTrace();
        }
    }
}
