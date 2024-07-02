package com.hotelmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PensionTypeDAO {
    //Pension Typelerin alınıp, listelendiği metot.
    public List<PensionType> getAllPensionTypes() throws SQLException {
        List<PensionType> pensionTypes = new ArrayList<>();
        String query = "SELECT * FROM pension_type";

        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PensionType pensionType = new PensionType(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getString("type")
                );
                pensionTypes.add(pensionType);
            }
        }

        return pensionTypes;
    }
    //Pension Type sql ile eklendiği metot.
    public void addPensionType(PensionType pensionType) throws SQLException {
        String query = "INSERT INTO pension_type (hotel_id, type) VALUES (?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pensionType.getHotelId());
            pstmt.setString(2, pensionType.getType());
            pstmt.executeUpdate();
        }
    }
    //Pensin Type sql ile güncellendiği metot.
    public void updatePensionType(PensionType pensionType) throws SQLException {
        String query = "UPDATE pension_type SET hotel_id = ?, type = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pensionType.getHotelId());
            pstmt.setString(2, pensionType.getType());
            pstmt.setInt(3, pensionType.getId());
            pstmt.executeUpdate();
        }
    }
    //Pension Type sql ile silindiği metot.
    public void deletePensionType(int pensionTypeId) throws SQLException {
        String query = "DELETE FROM pension_type WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pensionTypeId);
            pstmt.executeUpdate();
        }
    }
}
