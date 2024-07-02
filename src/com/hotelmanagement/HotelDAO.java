package com.hotelmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    //Veritabanından bütün otelleri çekip listelediğimiz metot
    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotel";

        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("stars"),
                        rs.getString("features")
                );
                hotels.add(hotel);
            }
        }

        return hotels;
    }
    //Veritabanına otel eklediğimiz metot.
    public void addHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO hotel (name, address, email, phone, stars, features) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getAddress());
            pstmt.setString(3, hotel.getEmail());
            pstmt.setString(4, hotel.getPhone());
            pstmt.setInt(5, hotel.getStars());
            pstmt.setString(6, hotel.getFeatures());
            pstmt.executeUpdate();
        }
    }
    //Veritabanında otel güncellediğimiz metot.
    public void updateHotel(Hotel hotel) throws SQLException {
        String query = "UPDATE hotel SET name = ?, address = ?, email = ?, phone = ?, stars = ?, features = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getAddress());
            pstmt.setString(3, hotel.getEmail());
            pstmt.setString(4, hotel.getPhone());
            pstmt.setInt(5, hotel.getStars());
            pstmt.setString(6, hotel.getFeatures());
            pstmt.setInt(7, hotel.getId());
            pstmt.executeUpdate();
        }
    }
    //Veritabanından otel sildiğimiz metot.
    public void deleteHotel(int hotelId) throws SQLException {
        String query = "DELETE FROM hotel WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, hotelId);
            pstmt.executeUpdate();
        }
    }
}
