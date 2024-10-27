package dev.gustavorh.gestioninventario.infrastructure.repositories;

import java.util.List;

public interface GenericRepository<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T entity);
    void delete(Long id);
}
