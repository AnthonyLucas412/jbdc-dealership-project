package com.pluralsight.db;

import com.pluralsight.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO sales_contracts (contract_ID, vehicle_vin, sale_date, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, salesContract.getContractId());
                statement.setString(2, salesContract.getVin());
                statement.setDate(3, java.sql.Date.valueOf(salesContract.getSaleDate()));
                statement.setDouble(4, salesContract.getPrice());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
