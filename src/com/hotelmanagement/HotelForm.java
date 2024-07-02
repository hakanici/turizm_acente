package com.hotelmanagement;

//import DAO.HotelDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class HotelForm extends JFrame {
    //HotelForm'un oluştuğu kısım.
    private JTextField nameField, addressField, emailField, phoneField;
    private JComboBox<Integer> starsComboBox;
    private JTextArea featuresTextArea;
    private JButton saveButton, cancelButton;
    private HotelDAO hotelDAO;
    private Hotel hotel;

    public HotelForm(HotelDAO hotelDAO, Hotel hotel) {
        this.hotelDAO = hotelDAO;
        this.hotel = hotel;

        setTitle(hotel == null ? "Add Hotel" : "Edit Hotel");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Stars:"));
        starsComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        add(starsComboBox);

        add(new JLabel("Features:"));
        featuresTextArea = new JTextArea();
        add(new JScrollPane(featuresTextArea));

        saveButton = new JButton("Save");
        add(saveButton);

        cancelButton = new JButton("Cancel");
        add(cancelButton);
        //Eğer otel nesnesi boş değilse setter metoduyla bilgileri aldığımız kısım.
        if (hotel != null) {
            nameField.setText(hotel.getName());
            addressField.setText(hotel.getAddress());
            emailField.setText(hotel.getEmail());
            phoneField.setText(hotel.getPhone());
            starsComboBox.setSelectedItem(hotel.getStars());
            featuresTextArea.setText(hotel.getFeatures());
        }

        saveButton.addActionListener(e -> saveHotel());
        cancelButton.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    //Hotel bilgilerini değişkenlere attığımız metot.
    private void saveHotel() {
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        int stars = (int) starsComboBox.getSelectedItem();
        String features = featuresTextArea.getText();
    //Boş alanları tespit edip ekrana uygun mesajı verdiğimiz kısım.
        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }
    //Otel ekleyip, güncellediğimiz kısım.
        if (hotel == null) {
            hotel = new Hotel(name, address, email, phone, stars, features);
            try {
                hotelDAO.addHotel(hotel);
                JOptionPane.showMessageDialog(this, "Hotel added successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding hotel: " + e.getMessage());
            }
        } else {
            hotel.setName(name);
            hotel.setAddress(address);
            hotel.setEmail(email);
            hotel.setPhone(phone);
            hotel.setStars(stars);
            hotel.setFeatures(features);
            try {
                hotelDAO.updateHotel(hotel);
                JOptionPane.showMessageDialog(this, "Hotel updated successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating hotel: " + e.getMessage());
            }
        }
    }
}
