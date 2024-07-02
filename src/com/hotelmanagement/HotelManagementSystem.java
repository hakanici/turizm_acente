package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;

public class HotelManagementSystem {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HotelManagementSystem() {
        initializeGUI();
    }
    //Main sayfanın oluşturulduğu kısım.
    private void initializeGUI() {
        frame = new JFrame("Hotel Management System");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel adminPanel = new AdminPanel();
        JPanel employeePanel = new JPanel(new BorderLayout());
        HotelPanel hotelPanel = new HotelPanel();
        employeePanel.add(hotelPanel, BorderLayout.CENTER);

        mainPanel.add(adminPanel, "Admin");
        mainPanel.add(employeePanel, "Employee");

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout.show(mainPanel, "Admin");  // İlk açılışta admin panelini göster
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("Login");
            loginFrame.setSize(400, 200);
            WindowUtils.centerWindow(loginFrame);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            loginFrame.setLayout(new BorderLayout());

            LoginPanel loginPanel = new LoginPanel(loginFrame);
            loginFrame.add(loginPanel, BorderLayout.CENTER);

            loginFrame.setVisible(true);
        });
    }

}





