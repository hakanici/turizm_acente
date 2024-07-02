package com.hotelmanagement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO {
    //Bütün Seasonları alıp listeleyen metot.
    public List<Season> getAllSeasons() throws SQLException {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM season";

        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Season season = new Season(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                );
                seasons.add(season);
            }
        }

        return seasons;
    }
    //Season eklememizi sağlayan metot.
    public void addSeason(Season season) throws SQLException {
        String query = "INSERT INTO season (hotel_id, start_date, end_date) VALUES (?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, season.getHotelId());
            pstmt.setDate(2, Date.valueOf(season.getStartDate()));
            pstmt.setDate(3, Date.valueOf(season.getEndDate()));
            pstmt.executeUpdate();
        }
    }
    //Season güncellememizi sağlayan metot.
    public void updateSeason(Season season) throws SQLException {
        String query = "UPDATE season SET hotel_id = ?, start_date = ?, end_date = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, season.getHotelId());
            pstmt.setDate(2, Date.valueOf(season.getStartDate()));
            pstmt.setDate(3, Date.valueOf(season.getEndDate()));
            pstmt.setInt(4, season.getId());
            pstmt.executeUpdate();
        }
    }
    //Season silmemizi sağlayan metot
    public void deleteSeason(int seasonId) throws SQLException {
        String query = "DELETE FROM season WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, seasonId);
            pstmt.executeUpdate();
        }
    }
}
