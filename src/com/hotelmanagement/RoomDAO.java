package com.hotelmanagement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    //Veritabanından bütün Odaları listeleyen metot.
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room";

        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("season_id"),
                        rs.getInt("pension_type_id"),
                        rs.getString("type"),
                        rs.getBigDecimal("price_per_night_adult"),
                        rs.getBigDecimal("price_per_night_child"),
                        rs.getInt("stock"),
                        rs.getInt("bed_count"),
                        rs.getInt("size_sqm"),
                        rs.getBoolean("has_tv"),
                        rs.getBoolean("has_minibar"),
                        rs.getBoolean("has_game_console"),
                        rs.getBoolean("has_safe"),
                        rs.getBoolean("has_projector")
                );
                rooms.add(room);
            }
        }

        return rooms;
    }
    //Oda stoğunu eksilttiğimiz metot.
    public boolean decreaseRoomStock(int roomId) throws SQLException {
        String query = "UPDATE room SET stock = stock - 1 WHERE id = ? AND stock > 0 RETURNING stock";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int newStock = rs.getInt("stock");
                    return newStock >= 0;
                }
            }
        }
        return false;
    }

    //Odaları id ye göre alan metot.
    public Room getRoomById(int id) throws SQLException {
        String query = "SELECT * FROM room WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getInt("id"),
                            rs.getInt("hotel_id"),
                            rs.getInt("season_id"),
                            rs.getInt("pension_type_id"),
                            rs.getString("type"),
                            rs.getBigDecimal("price_per_night_adult"),
                            rs.getBigDecimal("price_per_night_child"),
                            rs.getInt("stock"),
                            rs.getInt("bed_count"),
                            rs.getInt("size_sqm"),
                            rs.getBoolean("has_tv"),
                            rs.getBoolean("has_minibar"),
                            rs.getBoolean("has_game_console"),
                            rs.getBoolean("has_safe"),
                            rs.getBoolean("has_projector")
                    );
                }
            }
        }
        return null;
    }

    //Oda eklememizi sağlayan metot.
    public void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO room (hotel_id, season_id, pension_type_id, type, price_per_night_adult, price_per_night_child, stock, bed_count, size_sqm, has_tv, has_minibar, has_game_console, has_safe, has_projector) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getType());
            pstmt.setBigDecimal(5, room.getPricePerNightAdult());
            pstmt.setBigDecimal(6, room.getPricePerNightChild());
            pstmt.setInt(7, room.getStock());
            pstmt.setInt(8, room.getBedCount());
            pstmt.setInt(9, room.getSizeSqm());
            pstmt.setBoolean(10, room.isHasTv());
            pstmt.setBoolean(11, room.isHasMinibar());
            pstmt.setBoolean(12, room.isHasGameConsole());
            pstmt.setBoolean(13, room.isHasSafe());
            pstmt.setBoolean(14, room.isHasProjector());
            pstmt.executeUpdate();
        }
    }
    //Oda güncellememizi sağlayan metot.
    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE room SET hotel_id = ?, season_id = ?, pension_type_id = ?, type = ?, price_per_night_adult = ?, price_per_night_child = ?, stock = ?, bed_count = ?, size_sqm = ?, has_tv = ?, has_minibar = ?, has_game_console = ?, has_safe = ?, has_projector = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, room.getHotelId());
            pstmt.setInt(2, room.getSeasonId());
            pstmt.setInt(3, room.getPensionTypeId());
            pstmt.setString(4, room.getType());
            pstmt.setBigDecimal(5, room.getPricePerNightAdult());
            pstmt.setBigDecimal(6, room.getPricePerNightChild());
            pstmt.setInt(7, room.getStock());
            pstmt.setInt(8, room.getBedCount());
            pstmt.setInt(9, room.getSizeSqm());
            pstmt.setBoolean(10, room.isHasTv());
            pstmt.setBoolean(11, room.isHasMinibar());
            pstmt.setBoolean(12, room.isHasGameConsole());
            pstmt.setBoolean(13, room.isHasSafe());
            pstmt.setBoolean(14, room.isHasProjector());
            pstmt.setInt(15, room.getId());
            pstmt.executeUpdate();
        }
    }
    //Oda silmemizi sağlayan metot.
    public void deleteRoom(int roomId) throws SQLException {
        String query = "DELETE FROM room WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();
        }
    }

    public List<Room> searchRooms(String city, String hotelName, LocalDate checkinDate, LocalDate checkoutDate) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT r.* FROM room r JOIN hotel h ON r.hotel_id = h.id WHERE 1=1");

        if (city != null && !city.isEmpty()) {
            query.append(" AND h.address LIKE ?");
        }
        if (hotelName != null && !hotelName.isEmpty()) {
            query.append(" AND h.name LIKE ?");
        }
        query.append(" AND EXISTS (SELECT 1 FROM season s WHERE s.id = r.season_id AND s.start_date <= ? AND s.end_date >= ?)");

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query.toString())) {

            int paramIndex = 1;
            if (city != null && !city.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + city + "%");
            }
            if (hotelName != null && !hotelName.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + hotelName + "%");
            }
            pstmt.setDate(paramIndex++, Date.valueOf(checkinDate));
            pstmt.setDate(paramIndex++, Date.valueOf(checkoutDate));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room(
                            rs.getInt("id"),
                            rs.getInt("hotel_id"),
                            rs.getInt("season_id"),
                            rs.getInt("pension_type_id"),
                            rs.getString("type"),
                            rs.getBigDecimal("price_per_night_adult"),
                            rs.getBigDecimal("price_per_night_child"),
                            rs.getInt("stock"),
                            rs.getInt("bed_count"),
                            rs.getInt("size_sqm"),
                            rs.getBoolean("has_tv"),
                            rs.getBoolean("has_minibar"),
                            rs.getBoolean("has_game_console"),
                            rs.getBoolean("has_safe"),
                            rs.getBoolean("has_projector")
                    );
                    rooms.add(room);
                }
            }
        }

        return rooms;
    }
}
