package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserForm extends JFrame {
    private JTextField usernameField, passwordField;
    private JComboBox<String> roleComboBox;
    private JButton saveButton, cancelButton;
    private UserDAO userDAO;
    private User user;
    //UserForm'un oluşturulduğu kısım.
    public UserForm(UserDAO userDAO, User user) {
        this.userDAO = userDAO;
        this.user = user;

        setTitle(user == null ? "Add User" : "Edit User");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        passwordField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        JLabel roleLabel = new JLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(roleLabel, gbc);

        roleComboBox = new JComboBox<>(new String[]{"admin", "personel"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(roleComboBox, gbc);

        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveButton, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(cancelButton, gbc);

        if (user != null) {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            roleComboBox.setSelectedItem(user.getRole());
        }

        saveButton.addActionListener(e -> saveUser());
        cancelButton.addActionListener(e -> dispose());

        pack();
        WindowUtils.centerWindow(this);
        setVisible(true);
    }


    private void saveUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = (String) roleComboBox.getSelectedItem();

        if (user == null) {
            user = new User(username, password, role);
            try {
                userDAO.addUser(user);
                JOptionPane.showMessageDialog(this, "User added successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
            }
        } else {
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            try {
                userDAO.updateUser(user);
                JOptionPane.showMessageDialog(this, "User updated successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage());
            }
        }
    }


}
