package org.example;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static Connection connect() throws SQLException {
        File dbFile = new File("database.db");
        System.out.println("Database path: " + dbFile.getAbsolutePath());
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Transports (" +
                "brand TEXT," +
                "speed DOUBLE," +
                "weight DOUBLE," +
                "color TEXT," +
                "type TEXT," +
                "wingSpan DOUBLE," +      // Для самолетов
                "maxAltitude DOUBLE" +   // Для самолетов
                ")";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'Transports' is ready.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void insertTransports(List<Transport> transports) {
        String sql = "INSERT INTO Transports (brand, speed, weight, color, type, wingSpan, maxAltitude) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Transport transport : transports) {
                pstmt.setString(1, transport.getBrand());
                pstmt.setDouble(2, transport.getSpeed());
                pstmt.setDouble(3, transport.getWeight());
                pstmt.setString(4, transport.getColor());
                pstmt.setString(5, transport.getClass().getSimpleName());

                if (transport instanceof Airplane) {
                    pstmt.setDouble(6, ((Airplane) transport).getWingSpan());
                    pstmt.setDouble(7, ((Airplane) transport).getMaxAltitude());
                } else {
                    pstmt.setNull(6, Types.DOUBLE);
                    pstmt.setNull(7, Types.DOUBLE);
                }

                pstmt.addBatch();
                System.out.println("Preparing to insert: " + transport);
            }
            pstmt.executeBatch();
            conn.commit();
            System.out.println("Data successfully inserted into the database.");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    public static List<Transport> getTransports() {
        List<Transport> transports = new ArrayList<>();
        String sql = "SELECT * FROM Transports";
        try (Connection conn = connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String brand = rs.getString("brand");
                double speed = rs.getDouble("speed");
                double weight = rs.getDouble("weight");
                String color = rs.getString("color");
                String type = rs.getString("type");
                double wingSpan = rs.getDouble("wingSpan");
                double maxAltitude = rs.getDouble("maxAltitude");

                if (type.equals("Airplane")) {
                    transports.add(new Airplane(brand, speed, weight, color, wingSpan, maxAltitude));
                } else if (type.equals("Car")) {
                    transports.add(new Car(brand, speed, weight, color));
                }
            }
            System.out.println("Data successfully retrieved from the database.");
        } catch (SQLException e) {
            System.out.println("Error retrieving data: " + e.getMessage());
        }
        return transports;
    }
}
