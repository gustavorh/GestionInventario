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

            System.out.println("========== [ LISTAR TODO ] ==========");
            productRepository.findAll().forEach(System.out::println);

            System.out.println("========== [ LISTAR POR ID ] ==========");
            System.out.println(productRepository.findById(2L));

            System.out.println("========== [ INSERTAR ] ==========");
            Product product = new Product();
            product.setName("MacBook Air M4");
            product.setDescription("Apple MacBook");
            product.setPrice(1000000.0);
            product.setStock(25);
            productRepository.save(product);
            System.out.println("Producto guardado con éxito!");
            productRepository.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
