package dev.gustavorh.gestioninventario.infrastructure.repositories;

import dev.gustavorh.gestioninventario.domain.models.Product;
import dev.gustavorh.gestioninventario.infrastructure.persistence.DbContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                Product product = getProduct(rs);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT * FROM productos WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) { // RS auto-close.
                if (rs.next()) {
                    product = getProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void save(Product entity) {
        String query;
        if (entity.getId() != null && entity.getId() > 0) {
            query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ? WHERE id = ?";
        } else {
            query = "INSERT INTO productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setDouble(3, entity.getPrice());
            stmt.setInt(4, entity.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Product getProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("nombre"));
        product.setDescription(rs.getString("descripcion"));
        product.setPrice(rs.getDouble("precio"));
        product.setStock(rs.getInt("stock"));
        return product;
    }
}
