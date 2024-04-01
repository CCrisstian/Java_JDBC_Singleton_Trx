package org.CCristian.Java.JDBC.Util;

import org.CCristian.Java.JDBC.Models.Categoria;
import org.CCristian.Java.JDBC.Models.Producto;
import org.CCristian.Java.JDBC.Repositorio.ProductoRepositorio_Implementacion;
import org.CCristian.Java.JDBC.Repositorio.Repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Ejemplo_JDBC_Trx {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = Conexion_BaseDeDatos_SINGLETON.getInstance()) {
            if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }
            try {
                Repositorio<Producto> repositorio = new ProductoRepositorio_Implementacion();

                System.out.println("\nInsertar nuevo producto");
                Producto producto = new Producto();
                producto.setNombre("Teclado IBM mecánico");
                producto.setPrecio(1550);
                producto.setFecha_registro(new Date());
                Categoria categoria = new Categoria();
                categoria.setId(3L);
                producto.setSku("abcde12345");
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Producto guardado con éxito");

                System.out.println("\nModificar producto");
                producto = new Producto();
                producto.setId(5L);
                producto.setNombre("Teclado Corsair k95 mecánico");
                producto.setPrecio(1000);
                producto.setSku("abcdef1234");
                categoria = new Categoria();
                categoria.setId(2L);
                producto.setCategoria(categoria);
                repositorio.guardar(producto);
                System.out.println("Update");
                repositorio.listar().forEach(System.out::println);

                connection.commit();                                /*COMMIT*/
            }catch (SQLException exception){
                connection.rollback();                              /*ROLLBACK*/
                throw new RuntimeException(exception);
            }
        }
    }
}
