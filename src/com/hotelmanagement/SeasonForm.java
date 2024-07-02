package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
//SeasonForm'un oluşturulduğu metot.
public class SeasonForm extends JFrame {
    private JTextField hotelIdField, startDateField, endDateField;
    private JButton saveButton, cancelButton;
    private SeasonDAO seasonDAO;
    private Season season;

    public SeasonForm(SeasonDAO seasonDAO, Season season) {
        this.seasonDAO = seasonDAO;
        this.season = season;

        setTitle(season == null ? "Add Season" : "Edit Season");
        setSize(300, 200);
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

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(startDateLabel, gbc);

        startDateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(startDateField, gbc);

        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(endDateLabel, gbc);

        endDateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(endDateField, gbc);

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

        if (season != null) {
            hotelIdField.setText(String.valueOf(season.getHotelId()));
            startDateField.setText(season.getStartDate().toString());
            endDateField.setText(season.getEndDate().toString());
        }

        saveButton.addActionListener(e -> saveSeason());
        cancelButton.addActionListener(e -> dispose());

        pack();
        WindowUtils.centerWindow(this);
        setVisible(true);
    }

    private void saveSeason() {
        int hotelId = Integer.parseInt(hotelIdField.getText());
        LocalDate startDate = LocalDate.parse(startDateField.getText());
        LocalDate endDate = LocalDate.parse(endDateField.getText());

        if (season == null) {
            season = new Season(hotelId, startDate, endDate);
            try {
                seasonDAO.addSeason(season);
                JOptionPane.showMessageDialog(this, "Season added successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding season: " + e.getMessage());
            }
        } else {
            season.setHotelId(hotelId);
            season.setStartDate(startDate);
            season.setEndDate(endDate);
            try {
                seasonDAO.updateSeason(season);
                JOptionPane.showMessageDialog(this, "Season updated successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating season: " + e.getMessage());
            }
        }
    }
}
