package dev.gustavorh.gestioninventario;

import dev.gustavorh.gestioninventario.domain.models.Product;
import dev.gustavorh.gestioninventario.infrastructure.persistence.DbContext;
import dev.gustavorh.gestioninventario.infrastructure.repositories.GenericRepository;
import dev.gustavorh.gestioninventario.infrastructure.repositories.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class GestionInventario {
    public static void main(String[] args) {
        try (Connection connection = DbContext.getInstance()) {
            GenericRepository<Product> productRepository = new ProductRepository();
            productRepository.findAll().forEach(p -> System.out.println(p.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
