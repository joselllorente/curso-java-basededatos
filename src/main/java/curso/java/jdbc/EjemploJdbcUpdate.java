package curso.java.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import curso.java.jdbc.modelo.Producto;
import curso.java.jdbc.repositorio.ProductoRepositorioImpl;
import curso.java.jdbc.repositorio.Repositorio;
import curso.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {
        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            System.out.println("============= listar =============");
            repositorio.listar().forEach(System.out::println);

            System.out.println("============= obtener por id =============");
            System.out.println(repositorio.porId(1L));

            System.out.println("============= editar producto =============");
            Producto producto = new Producto();
            producto.setId(3L);
            producto.setNombre("Teclado Razer mecánico");
            producto.setPrecio(700);
            repositorio.guardar(producto);
            System.out.println("Producto editado con éxito");
            repositorio.listar().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
