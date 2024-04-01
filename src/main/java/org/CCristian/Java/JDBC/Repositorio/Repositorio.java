package org.CCristian.Java.JDBC.Repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio <T>{

/*------------------MÉTODOS------------------*/
    List<T> listar() throws SQLException;

    T buscarPorId(Long id) throws SQLException;

    void guardar(T t) throws SQLException;

    void eliminar(Long id) throws SQLException;
}
