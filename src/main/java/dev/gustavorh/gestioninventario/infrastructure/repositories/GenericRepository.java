package dev.gustavorh.gestioninventario.infrastructure.repositories;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    void save(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
}
