package com.pluralsight.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO vehicles (vin, dealership_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vin);
                statement.setInt(2, dealershipId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory

        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM vehicles WHERE vin = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vin);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (logging, error messages, etc.)
        }
    }
}


