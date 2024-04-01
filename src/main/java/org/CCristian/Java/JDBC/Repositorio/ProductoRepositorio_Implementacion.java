package org.CCristian.Java.JDBC.Repositorio;

import org.CCristian.Java.JDBC.Models.Categoria;
import org.CCristian.Java.JDBC.Models.Producto;
import org.CCristian.Java.JDBC.Util.Conexion_BaseDeDatos_SINGLETON;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio_Implementacion implements Repositorio<Producto> {

/*------------------MÉTODOS------------------*/
    public Connection getConnection() throws SQLException {
        return Conexion_BaseDeDatos_SINGLETON.getInstance();
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement
                     .executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p "
                             +"inner join categorias as c ON (p.categoria_id = c.id)"))
        {
            while (resultSet.next()){
                Producto producto = crearProducto(resultSet);
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public Producto buscarPorId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement preparedSt = getConnection().
                prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p "
                        +"inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?"))
        {
            preparedSt.setLong(1, id);
            try (ResultSet resultSet = preparedSt.executeQuery()) {
                if (resultSet.next()) {
                    producto = crearProducto(resultSet);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;  /*Plantilla*/
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=?, sku=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, sku, fecha_registro) VALUES(?,?,?,?,?)";
        }
        try (PreparedStatement preparedSt = getConnection().prepareStatement(sql)){
            preparedSt.setString(1, producto.getNombre());
            preparedSt.setLong(2, producto.getPrecio());
            preparedSt.setLong(3, producto.getCategoria().getId());
            preparedSt.setString(4, producto.getSku());
            if (producto.getId() != null && producto.getId() > 0) { /*UPDATE*/
                preparedSt.setLong(5, producto.getId());
            } else {    /*INSERT*/
                preparedSt.setDate(5, new Date(producto.getFecha_registro().getTime()));
            }
            preparedSt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement preparedSt = getConnection().prepareStatement("DELETE FROM productos WHERE id=?")){
            preparedSt.setLong(1, id);
            preparedSt.executeUpdate();
        }
    }

    private static Producto crearProducto(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setFecha_registro(resultSet.getDate("fecha_registro"));
        producto.setSku(resultSet.getNString("sku"));
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("categoria_id"));
        categoria.setNombre(resultSet.getString("categoria"));
        producto.setCategoria(categoria);
        return producto;
    }
}
