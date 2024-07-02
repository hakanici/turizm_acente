package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class RoomForm extends JFrame {
    private JComboBox<Integer> hotelComboBox, seasonComboBox, pensionTypeComboBox;
    private JComboBox<String> typeComboBox;
    private JTextField pricePerNightAdultField, pricePerNightChildField, stockField, bedCountField, sizeField;
    private JCheckBox tvCheckBox, minibarCheckBox, gameConsoleCheckBox, safeCheckBox, projectorCheckBox;
    private JButton saveButton, cancelButton;
    private RoomDAO roomDAO;
    private Room room;

    //RoomFormun oluşturulduğu kısım
    public RoomForm(RoomDAO roomDAO, Room room) {
        this.roomDAO = roomDAO;
        this.room = room;

        setTitle(room == null ? "Add Room" : "Edit Room");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel hotelLabel = new JLabel("Hotel ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(hotelLabel, gbc);

        hotelComboBox = new JComboBox<>();
        loadHotels();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(hotelComboBox, gbc);

        JLabel seasonLabel = new JLabel("Season ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(seasonLabel, gbc);

        seasonComboBox = new JComboBox<>();
        loadSeasons();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(seasonComboBox, gbc);

        JLabel pensionTypeLabel = new JLabel("Pension Type ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(pensionTypeLabel, gbc);

        pensionTypeComboBox = new JComboBox<>();
        loadPensionTypes();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(pensionTypeComboBox, gbc);

        JLabel typeLabel = new JLabel("Type:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(typeLabel, gbc);

        typeComboBox = new JComboBox<>(new String[]{"Single Room", "Double Room", "Junior Suite", "Suite"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(typeComboBox, gbc);

        JLabel pricePerNightAdultLabel = new JLabel("Price Per Night (Adult):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(pricePerNightAdultLabel, gbc);

        pricePerNightAdultField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(pricePerNightAdultField, gbc);

        JLabel pricePerNightChildLabel = new JLabel("Price Per Night (Child):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(pricePerNightChildLabel, gbc);

        pricePerNightChildField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(pricePerNightChildField, gbc);

        JLabel stockLabel = new JLabel("Stock:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        add(stockLabel, gbc);

        stockField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(stockField, gbc);

        JLabel bedCountLabel = new JLabel("Bed Count:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        add(bedCountLabel, gbc);

        bedCountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        add(bedCountField, gbc);

        JLabel sizeLabel = new JLabel("Size (sqm):");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        add(sizeLabel, gbc);

        sizeField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        add(sizeField, gbc);

        JLabel tvLabel = new JLabel("TV:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        add(tvLabel, gbc);

        tvCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        add(tvCheckBox, gbc);

        JLabel minibarLabel = new JLabel("Minibar:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.EAST;
        add(minibarLabel, gbc);

        minibarCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        add(minibarCheckBox, gbc);

        JLabel gameConsoleLabel = new JLabel("Game Console:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;
        add(gameConsoleLabel, gbc);

        gameConsoleCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        add(gameConsoleCheckBox, gbc);

        JLabel safeLabel = new JLabel("Safe:");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.EAST;
        add(safeLabel, gbc);

        safeCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        add(safeCheckBox, gbc);

        JLabel projectorLabel = new JLabel("Projector:");
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.EAST;
        add(projectorLabel, gbc);

        projectorCheckBox = new JCheckBox();
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.WEST;
        add(projectorCheckBox, gbc);

        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveButton, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.anchor = GridBagConstraints.WEST;
        add(cancelButton, gbc);

        if (room != null) {
            hotelComboBox.setSelectedItem(room.getHotelId());
            seasonComboBox.setSelectedItem(room.getSeasonId());
            pensionTypeComboBox.setSelectedItem(room.getPensionTypeId());
            typeComboBox.setSelectedItem(room.getType());
            pricePerNightAdultField.setText(room.getPricePerNightAdult().toString());
            pricePerNightChildField.setText(room.getPricePerNightChild().toString());
            stockField.setText(String.valueOf(room.getStock()));
            bedCountField.setText(String.valueOf(room.getBedCount()));
            sizeField.setText(String.valueOf(room.getSizeSqm()));
            tvCheckBox.setSelected(room.isHasTv());
            minibarCheckBox.setSelected(room.isHasMinibar());
            gameConsoleCheckBox.setSelected(room.isHasGameConsole());
            safeCheckBox.setSelected(room.isHasSafe());
            projectorCheckBox.setSelected(room.isHasProjector());
        }

        saveButton.addActionListener(e -> saveRoom());
        cancelButton.addActionListener(e -> dispose());

        pack();
        WindowUtils.centerWindow(this);
        setVisible(true);
    }

    //getAllHotels metodundan otelleri çekip listeleyen metot
    private void loadHotels() {
        try {
            List<Hotel> hotels = new HotelDAO().getAllHotels();
            for (Hotel hotel : hotels) {
                hotelComboBox.addItem(hotel.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //getAllSeason metoduyla bütün seasonsları alıp listeleyen metot
    private void loadSeasons() {
        try {
            List<Season> seasons = new SeasonDAO().getAllSeasons();
            for (Season season : seasons) {
                seasonComboBox.addItem(season.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //getAllPensionTypes metoduyla bütün PensionType'leri alıp listeleyen metot
    private void loadPensionTypes() {
        try {
            List<PensionType> pensionTypes = new PensionTypeDAO().getAllPensionTypes();
            for (PensionType pensionType : pensionTypes) {
                pensionTypeComboBox.addItem(pensionType.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ComboBox, Field, Checkbox, ve textboxlardaki verileri değişkenlere atıyoruz.
    private void saveRoom() {
        if (hotelComboBox.getItemCount() == 0 || seasonComboBox.getItemCount() == 0 || pensionTypeComboBox.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid option for all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int hotelId = (int) hotelComboBox.getSelectedItem();
        int seasonId = (int) seasonComboBox.getSelectedItem();
        int pensionTypeId = (int) pensionTypeComboBox.getSelectedItem();
        String type = (String) typeComboBox.getSelectedItem();
        BigDecimal pricePerNightAdult = new BigDecimal(pricePerNightAdultField.getText());
        BigDecimal pricePerNightChild = new BigDecimal(pricePerNightChildField.getText());
        int stock = Integer.parseInt(stockField.getText());
        int bedCount = Integer.parseInt(bedCountField.getText());
        int sizeSqm = Integer.parseInt(sizeField.getText());
        boolean hasTv = tvCheckBox.isSelected();
        boolean hasMinibar = minibarCheckBox.isSelected();
        boolean hasGameConsole = gameConsoleCheckBox.isSelected();
        boolean hasSafe = safeCheckBox.isSelected();
        boolean hasProjector = projectorCheckBox.isSelected();

        //Eğer room nesnesi oluşturulduysa setter metoduyla alıyoruz.
        if (room == null) {
            room = new Room(hotelId, seasonId, pensionTypeId, type, pricePerNightAdult, pricePerNightChild, stock, bedCount, sizeSqm, hasTv, hasMinibar, hasGameConsole, hasSafe, hasProjector);
            try {
                roomDAO.addRoom(room);
                JOptionPane.showMessageDialog(this, "Room added successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding room: " + e.getMessage());
            }
        } else {
            room.setHotelId(hotelId);
            room.setSeasonId(seasonId);
            room.setPensionTypeId(pensionTypeId);
            room.setType(type);
            room.setPricePerNightAdult(pricePerNightAdult);
            room.setPricePerNightChild(pricePerNightChild);
            room.setStock(stock);
            room.setBedCount(bedCount);
            room.setSizeSqm(sizeSqm);
            room.setHasTv(hasTv);
            room.setHasMinibar(hasMinibar);
            room.setHasGameConsole(hasGameConsole);
            room.setHasSafe(hasSafe);
            room.setHasProjector(hasProjector);
            try {
                roomDAO.updateRoom(room);
                JOptionPane.showMessageDialog(this, "Room updated successfully.");
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating room: " + e.getMessage());
            }
        }
    }
}
