package com.pluralsight.db;

import com.pluralsight.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO vehicles (make, model, year) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, vehicle.getMake());
                statement.setString(2, vehicle.getModel());
                statement.setInt(3, vehicle.getYear());
                System.out.println("The " + vehicle + " Has been added successfully");
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM vehicles WHERE VIN = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, VIN);
                statement.executeUpdate();
                System.out.println("Vehicle with VIN " + VIN + " removed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, minPrice);
                statement.setDouble(2, maxPrice);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        double minPrice1 = resultSet.getDouble("minPrice");
                        double maxPrice1 = resultSet.getDouble("maxPrice");


                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (logging, error messages, etc.)
        }
        return new ArrayList<>();
    }

    // Other methods and classes related to your application





    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        return new ArrayList<>();
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}

