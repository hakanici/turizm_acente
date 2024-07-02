package com.hotelmanagement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    //Belirtilen tarih aralığında odanın boş olup olmadığını döndüren metot.
    public boolean isRoomAvailable(int roomId, LocalDate checkinDate, LocalDate checkoutDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM reservation WHERE room_id = ? AND (checkin_date < ? AND checkout_date > ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            pstmt.setDate(2, java.sql.Date.valueOf(checkoutDate));
            pstmt.setDate(3, java.sql.Date.valueOf(checkinDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }
    //Rezervasyon oluşturmamızı sağlayan metot.
    public void addReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservation (room_id, customer_name, customer_id_number, checkin_date, checkout_date, guest_count_adult, guest_count_child, total_price, customer_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setString(2, reservation.getCustomerName());
            pstmt.setString(3, reservation.getGuestID());
            pstmt.setDate(4, java.sql.Date.valueOf(reservation.getCheckinDate()));
            pstmt.setDate(5, java.sql.Date.valueOf(reservation.getCheckoutDate()));
            pstmt.setInt(6, reservation.getAdults());
            pstmt.setInt(7, reservation.getChildren());
            pstmt.setBigDecimal(8, reservation.getTotalPrice());
            pstmt.setString(9, reservation.getContactInfo());
            pstmt.executeUpdate();
        }
    }
    //Rezervasyon güncellememizi sağlayan metot.
    public void updateReservation(Reservation reservation) throws SQLException {
        String query = "UPDATE reservation SET room_id = ?, customer_name = ?, customer_id_number = ?, checkin_date = ?, checkout_date = ?, guest_count_adult = ?, guest_count_child = ?, total_price = ?, customer_phone = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, reservation.getRoomId());
            pstmt.setString(2, reservation.getCustomerName());
            pstmt.setString(3, reservation.getGuestID());
            pstmt.setDate(4, Date.valueOf(reservation.getCheckinDate()));
            pstmt.setDate(5, Date.valueOf(reservation.getCheckoutDate()));
            pstmt.setInt(6, reservation.getAdults());
            pstmt.setInt(7, reservation.getChildren());
            pstmt.setBigDecimal(8, reservation.getTotalPrice());
            pstmt.setString(9, reservation.getContactInfo());
            pstmt.setInt(10, reservation.getId());
            pstmt.executeUpdate();
        }
    }
    //Rezervasyon silmemizi sağlayan metot.
    public void deleteReservation(int id) throws SQLException {
        String query = "DELETE FROM reservation WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    //Bütün rezervasyonları veritabanından çekip, Listeleyen metot.
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_id_number"),
                        rs.getDate("checkin_date").toLocalDate(),
                        rs.getDate("checkout_date").toLocalDate(),
                        rs.getInt("guest_count_adult"),
                        rs.getInt("guest_count_child"),
                        rs.getBigDecimal("total_price"),
                        rs.getString("customer_phone")
                );
                reservations.add(reservation);
            }
        }
        return reservations;
    }


}
