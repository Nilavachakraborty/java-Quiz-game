/*
 * Smart Inventory Management System - Java Console Application
 * Author: Nilava Chakraborty (Assumed)
 * Description: A simple inventory system using Core Java and MySQL.
 */

import java.sql.*;
import java.util.Scanner;

public class InventoryApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    static final String USER = "root";
    static final String PASS = "yourpassword"; // Replace with your MySQL password

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            int choice;
            do {
                System.out.println("\nSmart Inventory Management System");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product Quantity");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> viewProducts();
                    case 3 -> updateProduct();
                    case 4 -> deleteProduct();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 5);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addProduct() throws SQLException {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, quantity);
        stmt.setDouble(3, price);
        stmt.executeUpdate();
        System.out.println("Product added successfully.");
    }

    static void viewProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\nID\tName\t\tQuantity\tPrice");
        while (rs.next()) {
            System.out.printf("%d\t%s\t\t%d\t\t%.2f\n", rs.getInt("id"), rs.getString("name"),
                    rs.getInt("quantity"), rs.getDouble("price"));
        }
    }

    static void updateProduct() throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();

        String sql = "UPDATE products SET quantity = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, quantity);
        stmt.setInt(2, id);
        int rows = stmt.executeUpdate();
        if (rows > 0)
            System.out.println("Product updated successfully.");
        else
            System.out.println("Product not found.");
    }

    static void deleteProduct() throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();
        if (rows > 0)
            System.out.println("Product deleted successfully.");
        else
            System.out.println("Product not found.");
    }
}
