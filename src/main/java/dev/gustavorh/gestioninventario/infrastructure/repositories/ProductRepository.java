package dev.gustavorh.gestioninventario.infrastructure.repositories;

import dev.gustavorh.gestioninventario.domain.models.Product;
import dev.gustavorh.gestioninventario.infrastructure.persistence.DbContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements GenericRepository<Product> {
    private Connection getConnection() throws SQLException {
        return DbContext.getInstance();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM productos")) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("nombre"));
                product.setDescription(rs.getString("descripcion"));
                product.setPrice(rs.getDouble("precio"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void save(Product entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
