package dev.gustavorh.gestioninventario.infrastructure.repositories;

import dev.gustavorh.gestioninventario.domain.models.Category;
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
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p INNER JOIN categorias AS c ON (p.categoria_id = c.id)")) {
            while (rs.next()) {
                Product product = getProduct(rs);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT p.*, c.nombre AS categoria FROM productos AS p INNER JOIN categorias AS c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) { // RS auto-close.
                if (rs.next()) {
                    product = getProduct(rs);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product entity) throws SQLException {
        String query;
        if (entity.getId() != null && entity.getId() > 0) {
            query = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ? WHERE id = ?";
        } else {
            query = "INSERT INTO productos (nombre, descripcion, precio, stock, categoria_id) VALUES (?, ?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setDouble(3, entity.getPrice());
            stmt.setInt(4, entity.getStock());
            stmt.setLong(5, entity.getCategory().getId()); // Relación
            if (entity.getId() != null && entity.getId() > 0) {
                stmt.setLong(6, entity.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Product getProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("nombre"));
        product.setDescription(rs.getString("descripcion"));
        product.setPrice(rs.getDouble("precio"));
        product.setStock(rs.getInt("stock"));
        Category category = new Category(); // Crear la relación.
        category.setId(rs.getLong("categoria_id"));
        category.setName(rs.getString("categoria"));
        product.setCategory(category);
        return product;
    }
}
