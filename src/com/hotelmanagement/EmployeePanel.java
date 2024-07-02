package com.hotelmanagement;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {

    public EmployeePanel() {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        HotelPanel hotelPanel = new HotelPanel();
        tabbedPane.addTab("Hotel Management", hotelPanel);

        RoomPanel roomPanel = new RoomPanel();
        tabbedPane.addTab("Room Management", roomPanel);

        SeasonPanel seasonPanel = new SeasonPanel();
        tabbedPane.addTab("Season Management", seasonPanel);

        PensionTypePanel pensionTypePanel = new PensionTypePanel();
        tabbedPane.addTab("Pension Type Management", pensionTypePanel);

        ReservationPanel reservationPanel = new ReservationPanel();
        tabbedPane.addTab("Reservation Management", reservationPanel);


        add(tabbedPane, BorderLayout.CENTER);
    }
}
