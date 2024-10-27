package dev.gustavorh.gestioninventario;

import dev.gustavorh.gestioninventario.domain.models.Category;
import dev.gustavorh.gestioninventario.domain.models.Product;
import dev.gustavorh.gestioninventario.infrastructure.persistence.DbContext;
import dev.gustavorh.gestioninventario.infrastructure.repositories.GenericRepository;
import dev.gustavorh.gestioninventario.infrastructure.repositories.ProductRepository;

import java.sql.Connection;
import java.sql.SQLException;

// TODO: Add Service Layer and Factory Pattern.
public class GestionInventario {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DbContext.getInstance()) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                GenericRepository<Product> productRepository = new ProductRepository();

                System.out.println("========== [ LISTAR TODO ] ==========");
                // productRepository.findAll().forEach(System.out::println);

                System.out.println("========== [ LISTAR POR ID ] ==========");
                // System.out.println(productRepository.findById(2L));

                System.out.println("========== [ INSERTAR ] ==========");
                Product product = new Product();
                product.setName("MacBook Pro M2");
                product.setDescription("Apple MacBook");
                product.setPrice(1000000.0);
                product.setStock(25);
                Category category = new Category();
                category.setId(2L);
                product.setCategory(category);
                productRepository.save(product);
                System.out.println("Producto guardado con éxito!");

                System.out.println("========== [ ACTUALIZAR ] ==========");
                Product updateProduct = new Product();
                updateProduct.setId(9L);
                updateProduct.setName("Servidor Dell R720");
                updateProduct.setDescription("Apple MacBook");
                updateProduct.setPrice(1500000.0);
                updateProduct.setStock(10);
                Category category2 = new Category();
                category2.setId(4L);
                updateProduct.setCategory(category2);
                //productRepository.save(updateProduct);
                System.out.println("Producto actualizado con éxito!");

                System.out.println("========== [ ELIMINAR ] ==========");
                //productRepository.delete(7L);
                System.out.println("Producto eliminado con éxito!");

                productRepository.findAll().forEach(System.out::println);

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        }
    }
}
