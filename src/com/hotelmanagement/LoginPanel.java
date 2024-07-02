package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
//Giriş Sayfasının oluşturulduğu kısım
public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserDAO userDAO;
    private JFrame parentFrame;

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        userDAO = new UserDAO();
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> login());
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            User user = userDAO.authenticateUser(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");

                parentFrame.setVisible(false);
                parentFrame.dispose();

                JFrame mainFrame = new JFrame("Hotel Management System");
                mainFrame.setSize(800, 600);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLayout(new BorderLayout());

                if ("admin".equals(user.getRole())) {
                    mainFrame.add(new AdminPanel(), BorderLayout.CENTER);
                } else {
                    mainFrame.add(new EmployeePanel(), BorderLayout.CENTER);
                }

                mainFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during authentication: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
