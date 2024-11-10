package utils;

import models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private Connection conn;

    public void connect() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/Lab6DB";
        String user = "root";
        String password = "1234";
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Database connected successfully.");
        conn.setAutoCommit(true);
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

public void insertProduct(Product product) {
    if (conn == null) {
        System.out.println("Unable to establish a database connection.");
        return;
    }

    String query = "INSERT INTO Products (name, price) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, product.getName());
        stmt.setDouble(2, product.getPrice());
        stmt.executeUpdate();
        System.out.println("Product inserted successfully.");
    } catch (SQLException e) {
        System.out.println("Error inserting product: " + e.getMessage());
    }
}

    public void updateProduct(Product product) {
        String query = "UPDATE Products SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getId());
            stmt.executeUpdate();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM Products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
    
    public ResultSet fetchProducts() {
    String query = "SELECT * FROM Products";
    try {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    } catch (SQLException e) {
        System.out.println("Error fetching products: " + e.getMessage());
        return null;
    }
}

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }
    public static void main(String[] args) {
    DatabaseUtils dbUtils = new DatabaseUtils();
    try {
        dbUtils.connect();
        Product testProduct = new Product(0, "Test Product", 19.99);
        dbUtils.insertProduct(testProduct);
        dbUtils.close();
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
}
