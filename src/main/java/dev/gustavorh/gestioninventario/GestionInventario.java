package dev.gustavorh.gestioninventario;

import dev.gustavorh.gestioninventario.domain.models.Product;
import dev.gustavorh.gestioninventario.infrastructure.persistence.DbContext;
import dev.gustavorh.gestioninventario.infrastructure.repositories.GenericRepository;
import dev.gustavorh.gestioninventario.infrastructure.repositories.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;

// TODO: Add Service Layer and Factory Pattern.
public class GestionInventario {
    public static void main(String[] args) {
        try (Connection connection = DbContext.getInstance()) {
            GenericRepository<Product> productRepository = new ProductRepository();

            System.out.println("========== [ LISTAR TODO ] ==========");
            productRepository.findAll().forEach(System.out::println);

            System.out.println("========== [ LISTAR POR ID ] ==========");
            System.out.println(productRepository.findById(2L));

            System.out.println("========== [ INSERTAR ] ==========");
            // Product product = new Product();
            // product.setName("MacBook Air M4");
            // product.setDescription("Apple MacBook");
            // product.setPrice(1000000.0);
            // product.setStock(25);
            // productRepository.save(product);
            // System.out.println("Producto guardado con éxito!");
            // productRepository.findAll().forEach(System.out::println);

            System.out.println("========== [ ACTUALIZAR ] ==========");
//            Product updateProduct = new Product();
//            updateProduct.setId(6L);
//            updateProduct.setName("MacBook Air M1");
//            updateProduct.setDescription("Apple MacBook");
//            updateProduct.setPrice(1500000.0);
//            updateProduct.setStock(10);
//            productRepository.save(updateProduct);
//            System.out.println("Producto actualizado con éxito!");
//            productRepository.findAll().forEach(System.out::println);

            System.out.println("========== [ ELIMINAR ] ==========");
            productRepository.delete(7L);
            System.out.println("Producto eliminado con éxito!");
            productRepository.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
