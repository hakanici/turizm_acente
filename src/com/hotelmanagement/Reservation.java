package com.hotelmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private int id;
    private int roomId;
    private String customerName;
    private String guestID;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int adults;
    private int children;
    private BigDecimal totalPrice;
    private String contactInfo;

    //Constructor

    public Reservation(int id,int roomId, String customerName, String guestID, LocalDate checkinDate, LocalDate checkoutDate, int adults, int children, BigDecimal totalPrice, String contactInfo) {
        this.id=id;
        this.roomId = roomId;
        this.customerName = customerName;
        this.guestID = guestID;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.adults = adults;
        this.children = children;
        this.totalPrice = totalPrice;
        this.contactInfo = contactInfo;
    }

    public Reservation(int roomId, String customerName, String guestID, LocalDate checkinDate, LocalDate checkoutDate, int adults, int children, BigDecimal totalPrice, String contactInfo) {
        this.roomId = roomId;
        this.customerName = customerName;
        this.guestID = guestID;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.adults = adults;
        this.children = children;
        this.totalPrice = totalPrice;
        this.contactInfo = contactInfo;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
