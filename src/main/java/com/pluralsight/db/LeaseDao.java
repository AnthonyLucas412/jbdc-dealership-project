package com.pluralsight.db;

import com.pluralsight.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO lease_contracts (contract_ID, vehicle_vin, lease_start, lease_end) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, leaseContract.getContractId());
                statement.setString(2, leaseContract.getVin());
                statement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
                statement.setDate(4, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



