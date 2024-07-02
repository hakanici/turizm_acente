package com.hotelmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //Bütün userları alıp listelediğimiz metot.
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM \"user\"";

        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(user);
            }
        }

        return users;
    }
    //Userları Rolüne göre listelediğimiz metot.
    public List<User> getUsersByRole(String role) throws SQLException {
        String query = "SELECT * FROM \"user\" WHERE role = ?";
        List<User> users = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, role);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }
    //User eklememizi sağlayan metot.
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO \"user\" (username, password, role) VALUES (?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.executeUpdate();
        }
    }
    //User güncellememizi sağlayan metot.
    public void updateUser(User user) throws SQLException {
        String query = "UPDATE \"user\" SET username = ?, password = ?, role = ? WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();
        }
    }
    //User silmemizi sağlayan metot.
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM \"user\" WHERE id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
    //Giriş yaparken veritabanıyla eşleşip eşleşmediğini kontrol eden metot.
    public User authenticateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM \"user\" WHERE username = ? AND password = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }
        }
        return null;
    }
}
