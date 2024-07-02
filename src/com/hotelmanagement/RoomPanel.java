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
//RoomPanel'in oluşturulduğu kısım.
public class RoomPanel extends JPanel {
    private JTable roomTable;
    private JButton addRoomButton, editRoomButton, deleteRoomButton;
    private RoomDAO roomDAO;

    public RoomPanel() {
        roomDAO = new RoomDAO();
        setLayout(new BorderLayout());

        roomTable = new JTable();
        loadRooms();

        addRoomButton = new JButton("Add Room");
        editRoomButton = new JButton("Edit Room");
        deleteRoomButton = new JButton("Delete Room");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addRoomButton);
        buttonPanel.add(editRoomButton);
        buttonPanel.add(deleteRoomButton);

        add(new JScrollPane(roomTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addRoomButton.addActionListener(e -> showRoomForm(null));
        editRoomButton.addActionListener(e -> showRoomForm(getSelectedRoom()));
        deleteRoomButton.addActionListener(e -> deleteRoom());
    }
    //Bütün Roomların seçilip, listelendiği metot.
    void loadRooms() {
        try {
            List<Room> rooms = roomDAO.getAllRooms();
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

            roomTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showRoomForm(Room room) {
        RoomForm roomForm = new RoomForm(roomDAO, room);
        roomForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadRooms();
            }
        });

        roomForm.pack();
        WindowUtils.centerWindow(roomForm);
        roomForm.setVisible(true);
    }
    //Room seçmemizi sağlayan metot.
    private Room getSelectedRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            int roomId = (int) roomTable.getValueAt(selectedRow, 0);
            try {
                List<Room> rooms = roomDAO.getAllRooms();
                for (Room room : rooms) {
                    if (room.getId() == roomId) {
                        return room;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Room silmemizi sağlayan metot.
    private void deleteRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            int roomId = (int) roomTable.getValueAt(selectedRow, 0);
            try {
                roomDAO.deleteRoom(roomId);
                loadRooms();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a room to delete.");
        }
    }
}
