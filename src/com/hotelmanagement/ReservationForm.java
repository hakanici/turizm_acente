package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationForm extends JFrame {
    private JTextField roomIdField, customerNameField, guestIDField, checkinDateField, checkoutDateField, adultsField, childrenField, totalPriceField, contactInfoField;
    private JButton saveButton, cancelButton, calculateButton;
    private ReservationDAO reservationDAO;
    private RoomDAO roomDAO;
    private Reservation reservation;
    private JComboBox<String> roomIdCb;
    private RoomPanel roomPanel;

    //RezervationForm'un oluşturulduğu kısım.
    public ReservationForm(ReservationDAO reservationDAO, Reservation reservation) {
        this.reservationDAO = reservationDAO;
        this.reservation = reservation;
        this.roomDAO = new RoomDAO();
        initComponents();
        setTitle(reservation == null ? "Add Reservation" : "Edit Reservation");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel roomIdLabel = new JLabel("Room ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(roomIdLabel, gbc);


        roomIdCb = new JComboBox<>();
        loadRoomId();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(roomIdCb,gbc);


        JLabel customerNameLabel = new JLabel("Customer Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(customerNameLabel, gbc);

        customerNameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(customerNameField, gbc);

        JLabel guestIDLabel = new JLabel("Guest ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(guestIDLabel, gbc);

        guestIDField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(guestIDField, gbc);

        JLabel checkinDateLabel = new JLabel("Check-in Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(checkinDateLabel, gbc);

        checkinDateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(checkinDateField, gbc);

        JLabel checkoutDateLabel = new JLabel("Check-out Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(checkoutDateLabel, gbc);

        checkoutDateField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(checkoutDateField, gbc);

        JLabel adultsLabel = new JLabel("Adults:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(adultsLabel, gbc);

        adultsField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(adultsField, gbc);

        JLabel childrenLabel = new JLabel("Children:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        add(childrenLabel, gbc);

        childrenField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(childrenField, gbc);

        JLabel contactInfoLabel = new JLabel("Contact Info:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        add(contactInfoLabel, gbc);

        contactInfoField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        add(contactInfoField, gbc);

        calculateButton = new JButton("Toplam Tutarı Hesapla");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        add(calculateButton, gbc);

        totalPriceField = new JTextField(10);
        totalPriceField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        add(totalPriceField, gbc);

        saveButton = new JButton("Kaydet");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        add(saveButton, gbc);

        cancelButton = new JButton("İptal et");
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        add(cancelButton, gbc);
        //Eğer rezervasyon nesnesi oluştuysa, setter metoduyla aldığımız kısım.
        if (reservation != null) {
            roomIdField.setText(String.valueOf(reservation.getRoomId()));
            customerNameField.setText(reservation.getCustomerName());
            guestIDField.setText(reservation.getGuestID());
            checkinDateField.setText(reservation.getCheckinDate().toString());
            checkoutDateField.setText(reservation.getCheckoutDate().toString());
            adultsField.setText(String.valueOf(reservation.getAdults()));
            childrenField.setText(String.valueOf(reservation.getChildren()));
            totalPriceField.setText(reservation.getTotalPrice().toString());
            contactInfoField.setText(reservation.getContactInfo());
        }
        calculateButton.addActionListener(e -> calculateTotalPrice());
        saveButton.addActionListener(e -> saveReservation());
        cancelButton.addActionListener(e -> dispose());

        pack();
        WindowUtils.centerWindow(this);
        setVisible(true);
    }

    private void initComponents() {
        roomIdField = new JTextField(10);
        customerNameField = new JTextField(10);
        guestIDField = new JTextField(10);
        checkinDateField = new JTextField(10);
        checkoutDateField = new JTextField(10);
        adultsField = new JTextField(10);
        childrenField = new JTextField(10);
        totalPriceField = new JTextField(10);
        totalPriceField.setEditable(false);
        contactInfoField = new JTextField(10);
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        calculateButton = new JButton("Calculate Total Price");
    }
    //Toplam Fiyatı hesaplayan metot.
    private void calculateTotalPrice() {
        try {
            int roomId = Integer.parseInt(roomIdCb.getItemAt(0));
            Room room = roomDAO.getRoomById(roomId);

            if (room != null) {
                int adults = Integer.parseInt(adultsField.getText());
                int children = Integer.parseInt(childrenField.getText());
                LocalDate checkinDate = LocalDate.parse(checkinDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate checkoutDate = LocalDate.parse(checkoutDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                long daysStayed = ChronoUnit.DAYS.between(checkinDate, checkoutDate);

                BigDecimal totalPrice = room.getPricePerNightAdult().multiply(BigDecimal.valueOf(adults)).multiply(BigDecimal.valueOf(daysStayed))
                        .add(room.getPricePerNightChild().multiply(BigDecimal.valueOf(children)).multiply(BigDecimal.valueOf(daysStayed)));
                totalPriceField.setText(totalPrice.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Room not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving room information: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for room ID, adults, and children.");
        } catch (DateTimeException e){
            JOptionPane.showMessageDialog(this,"Please enter valid dates for check-in and check-out in YYYY-MM-DD format");
        }
    }
    //Roomları id'ye göre alıp listeleyen metot.
    private void loadRoomId() {
        try {
            List<Room> roomdaos = new RoomDAO().getAllRooms();
            for (Room room : roomdaos) {
                roomIdCb.addItem(String.valueOf(room.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Rezervasyonu oluşturduğumuz metot.
    private void saveReservation() {
        try {
            int roomId;
            try {
                roomId = Integer.parseInt(roomIdCb.getItemAt(0));
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Invalid Room ID.");
                return;
            }

            String customerName = customerNameField.getText();
            String guestIDNumber = guestIDField.getText();


            LocalDate checkinDate;
            try {
                checkinDate = LocalDate.parse(checkinDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(this,"Invalid Check-in Date.");
                return;
            }
            LocalDate checkoutDate;
            try {
                checkoutDate = LocalDate.parse(checkoutDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(this,"Invalid Check-out Date.");
                return;
            }

            int adults;
            try {
                adults = Integer.parseInt(adultsField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number of Adults.");
                return;
            }

            int children;
            try {
                children = Integer.parseInt(childrenField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number of Children.");
                return;
            }


            BigDecimal totalPrice;

            if (totalPriceField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please calculate the total price first.");
                return;
            } else {
                try {
                    totalPrice = new BigDecimal(totalPriceField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid total price value.");
                    return;
                }
            }

            String contactInfo = contactInfoField.getText();

            if (reservationDAO.isRoomAvailable(roomId, checkinDate, checkoutDate)) {
                if(roomDAO.decreaseRoomStock(roomId)) {
                    if (reservation == null) {
                        reservation = new Reservation(roomId, customerName, guestIDNumber, checkinDate, checkoutDate, adults, children, totalPrice, contactInfo);
                        reservationDAO.addReservation(reservation);
                        JOptionPane.showMessageDialog(this, "Reservation added successfully.");
                    } else {
                        reservation.setRoomId(roomId);
                        reservation.setCustomerName(customerName);
                        reservation.setGuestID(guestIDNumber);
                        reservation.setCheckinDate(checkinDate);
                        reservation.setCheckoutDate(checkoutDate);
                        reservation.setAdults(adults);
                        reservation.setChildren(children);
                        reservation.setTotalPrice(totalPrice);
                        reservation.setContactInfo(contactInfo);
                        reservationDAO.updateReservation(reservation);
                        JOptionPane.showMessageDialog(this, "Reservation updated successfully.");
                    }
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(this,"The room is out of stock.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "The room is not available for the selected dates.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving reservation: " + e.getMessage());
        } //catch (NumberFormatException e) {
            //JOptionPane.showMessageDialog(this, "Please enter valid numbers for room ID, adults, children, and total price.");
        }
    }
