package com.hotelmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
//ReservationPanel'in oluşturulduğu kısım.
public class ReservationPanel extends JPanel {
    private JTable reservationTable;
    private JButton addReservationButton, editReservationButton, deleteReservationButton;
    private ReservationDAO reservationDAO;

    public ReservationPanel() {
        reservationDAO = new ReservationDAO();
        setLayout(new BorderLayout());

        reservationTable = new JTable();
        loadReservations();

        addReservationButton = new JButton("Add Reservation");
        editReservationButton = new JButton("Edit Reservation");
        deleteReservationButton = new JButton("Delete Reservation");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addReservationButton);
        buttonPanel.add(editReservationButton);
        buttonPanel.add(deleteReservationButton);

        add(new JScrollPane(reservationTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addReservationButton.addActionListener(e -> showReservationForm(null));
        editReservationButton.addActionListener(e -> showReservationForm(getSelectedReservation()));
        deleteReservationButton.addActionListener(e -> deleteReservation());
    }
    //Bütün rezervasyonları alıp, listeleyen metot.
    private void loadReservations() {
        try {
            List<Reservation> reservations = reservationDAO.getAllReservations();
            String[] columnNames = {"id", "room id", "customer Name", "customer_id_number", "check-in Date", "check-out Date", "guest_count_adult", "guest_count_child", "total Price", "customer_phone"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Reservation reservation : reservations) {
                Object[] row = {
                        reservation.getId(),
                        reservation.getRoomId(),
                        reservation.getCustomerName(),
                        reservation.getGuestID(),
                        reservation.getCheckinDate(),
                        reservation.getCheckoutDate(),
                        reservation.getAdults(),
                        reservation.getChildren(),
                        reservation.getTotalPrice(),
                        reservation.getContactInfo()
                };
                model.addRow(row);
            }

            reservationTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showReservationForm(Reservation reservation) {
        ReservationForm reservationForm = new ReservationForm(reservationDAO, reservation);
        reservationForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                loadReservations();
            }
        });

        reservationForm.pack();
        WindowUtils.centerWindow(reservationForm);
        reservationForm.setVisible(true);
    }
    //Rezervasyon seçmemizi sağlayan metot.
    private Reservation getSelectedReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
            try {
                List<Reservation> reservations = reservationDAO.getAllReservations();
                for (Reservation reservation : reservations) {
                    if (reservation.getId() == reservationId) {
                        return reservation;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Rezervasyon silmemizi sağlayan metot.
    private void deleteReservation() {
        int selectedRow = reservationTable.getSelectedRow();
        if (selectedRow != -1) {
            int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
            try {
                reservationDAO.deleteReservation(reservationId);
                loadReservations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a reservation to delete.");
        }
    }
}
