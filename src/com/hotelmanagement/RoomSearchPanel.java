package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RoomSearchPanel extends JPanel {
    private JTextField cityField, hotelNameField, checkinDateField, checkoutDateField;
    private JButton searchButton;
    private JTable searchResultsTable;
    private RoomDAO roomDAO;

    public RoomSearchPanel() {
        roomDAO = new RoomDAO();
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel(new GridLayout(5, 2));
        searchPanel.add(new JLabel("City:"));
        cityField = new JTextField();
        searchPanel.add(cityField);

        searchPanel.add(new JLabel("Hotel Name:"));
        hotelNameField = new JTextField();
        searchPanel.add(hotelNameField);

        searchPanel.add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        checkinDateField = new JTextField();
        searchPanel.add(checkinDateField);

        searchPanel.add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        checkoutDateField = new JTextField();
        searchPanel.add(checkoutDateField);

        searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        searchResultsTable = new JTable();
        add(new JScrollPane(searchResultsTable), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchRooms());
    }

    private void searchRooms() {
        String city = cityField.getText();
        String hotelName = hotelNameField.getText();
        LocalDate checkinDate = LocalDate.parse(checkinDateField.getText(), DateTimeFormatter.ISO_DATE);
        LocalDate checkoutDate = LocalDate.parse(checkoutDateField.getText(), DateTimeFormatter.ISO_DATE);

        try {
            List<Room> rooms = roomDAO.searchRooms(city, hotelName, checkinDate, checkoutDate);
            String[] columnNames = {"ID", "Hotel ID", "Season ID", "Pension Type ID", "Type", "Price Per Night (Adult)", "Price Per Night (Child)", "Stock", "Bed Count", "Size (sqm)", "TV", "Minibar", "Game Console", "Safe", "Projector"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Room room : rooms) {
                Object[] row = {
                        room.getId(),
                        room.getHotelId(),
                        room.getSeasonId(),
                        room.getPensionTypeId(),
                        room.getType(),
                        room.getPricePerNightAdult(),
                        room.getPricePerNightChild(),
                        room.getStock(),
                        room.getBedCount(),
                        room.getSizeSqm(),
                        room.isHasTv(),
                        room.isHasMinibar(),
                        room.isHasGameConsole(),
                        room.isHasSafe(),
                        room.isHasProjector()
                };
                model.addRow(row);
            }

            searchResultsTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
