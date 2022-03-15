package com.revature.dao;

import com.revature.model.Client;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public Client createClient(Client client) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "INSERT INTO clients (first_name, last_name, age) VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setInt(3, client.getAge());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt(1); // 1st column of the ResultSet

            return new Client(generatedId, client.getFirstName(), client.getLastName(), client.getAge());
        }
    }

    public static Client getClientById(int id) throws SQLException{
        try(Connection con = ConnectionUtility.getConnection()){
            String sql = "SELECT * clients WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");

                return new Client(id, firstName, lastName, age);
            }
        }
        return null;
    }

    public List<Client> getAllClients() throws SQLException{
        List<Client> clients = new ArrayList<>();

        try(Connection con = ConnectionUtility.getConnection()){
            String sql = "SELECT * FROM clients";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("email");

                clients.add(new Client (id, firstName, lastName, age));
            }
        }

        return clients;
    }

    public static Client updateClient(Client client) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "UPDATE clients " +
                    "SET first_name = ?, " +
                    "last_name = ?, " +
                    "age = ? " +
                    "WHERE id = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setInt(3, client.getAge());
            pstmt.setInt(4, client.getId());

            pstmt.executeUpdate();
        }

        return client;
    }

    public static boolean deleteClientById(int id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "DELETE FROM clients WHERE id = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, id);

            int numberOfRecordsDeleted = pstmt.executeUpdate(); // executeUpdate() is used with INSERT, UPDATE, DELETE

            if (numberOfRecordsDeleted == 1) {
                return true;
            }
        }

        return false;
    }
}
