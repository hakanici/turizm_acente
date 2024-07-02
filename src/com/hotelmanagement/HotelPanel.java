package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
//HotelPanel'in oluşturulduğu kısım.
public class HotelPanel extends JPanel {
    private JTable hotelTable;
    private JButton addHotelButton, editHotelButton, deleteHotelButton;
    private HotelDAO hotelDAO;

    public HotelPanel() {
        hotelDAO = new HotelDAO();
        setLayout(new BorderLayout());

        hotelTable = new JTable();
        loadHotels();

        addHotelButton = new JButton("Add Hotel");
        editHotelButton = new JButton("Edit Hotel");
        deleteHotelButton = new JButton("Delete Hotel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addHotelButton);
        buttonPanel.add(editHotelButton);
        buttonPanel.add(deleteHotelButton);

        add(new JScrollPane(hotelTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addHotelButton.addActionListener(e -> showHotelForm(null));
        editHotelButton.addActionListener(e -> showHotelForm(getSelectedHotel()));
        deleteHotelButton.addActionListener(e -> deleteHotel());
    }
    //Hotelleri alıp, listeleyen metot.
    private void loadHotels() {
        try {
            List<Hotel> hotels = hotelDAO.getAllHotels();
            String[] columnNames = {"ID", "Name", "Address", "Email", "Phone", "Stars", "Features"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Hotel hotel : hotels) {
                Object[] row = {
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getAddress(),
                        hotel.getEmail(),
                        hotel.getPhone(),
                        hotel.getStars(),
                        hotel.getFeatures()
                };
                model.addRow(row);
            }

            hotelTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showHotelForm(Hotel hotel) {
        HotelForm hotelForm = new HotelForm(hotelDAO, hotel);
        hotelForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadHotels();
            }
        });
    }
    //Hotel seçmemizi sağlayan metot.
    private Hotel getSelectedHotel() {
        int selectedRow = hotelTable.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = (int) hotelTable.getValueAt(selectedRow, 0);
            try {
                List<Hotel> hotels = hotelDAO.getAllHotels();
                for (Hotel hotel : hotels) {
                    if (hotel.getId() == hotelId) {
                        return hotel;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Seçilen Otelin silindiği metot.
    private void deleteHotel() {
        int selectedRow = hotelTable.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = (int) hotelTable.getValueAt(selectedRow, 0);
            try {
                hotelDAO.deleteHotel(hotelId);
                loadHotels();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a hotel to delete.");
        }
    }
}
