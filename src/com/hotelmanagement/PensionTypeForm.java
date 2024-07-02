package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PensionTypeForm extends JFrame {
    private JTextField hotelIdField, typeField;
    private JButton saveButton, cancelButton;
    private PensionTypeDAO pensionTypeDAO;
    private PensionType pensionType;
    //PensionTypeForm'un oluşturulduğu kısım.
    public PensionTypeForm(PensionTypeDAO pensionTypeDAO, PensionType pensionType) {
        this.pensionTypeDAO = pensionTypeDAO;
        this.pensionType = pensionType;

        setTitle(pensionType == null ? "Add Pension Type" : "Edit Pension Type");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel hotelIdLabel = new JLabel("Hotel ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(hotelIdLabel, gbc);

        hotelIdField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(hotelIdField, gbc);

        JLabel typeLabel = new JLabel("Type:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(typeLabel, gbc);

        typeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(typeField, gbc);

        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveButton, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(cancelButton, gbc);

        if (pensionType != null) {
            hotelIdField.setText(String.valueOf(pensionType.getHotelId()));
            typeField.setText(pensionType.getType());
        }

        saveButton.addActionListener(e -> savePensionType());
        cancelButton.addActionListener(e -> dispose());

        pack();
        WindowUtils.centerWindow(this);
        setVisible(true);
    }
    //PensionType eklendiği metot.
    private void savePensionType() {
        int hotelId = Integer.parseInt(hotelIdField.getText());
        String type = typeField.getText();

        if (pensionType == null) {
            pensionType = new PensionType(hotelId, type);
            try {
                pensionTypeDAO.addPensionType(pensionType);
                JOptionPane.showMessageDialog(this, "Pension Type added successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding pension type: " + e.getMessage());
            }
        } else {
            pensionType.setHotelId(hotelId);
            pensionType.setType(type);
            try {
                pensionTypeDAO.updatePensionType(pensionType);
                JOptionPane.showMessageDialog(this, "Pension Type updated successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating pension type: " + e.getMessage());
            }
        }
    }
}

