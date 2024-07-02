package com.hotelmanagement;

import java.time.LocalDate;

public class Season {
    private int id;
    private int hotelId;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor
    public Season(int id, int hotelId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Season(int hotelId, LocalDate startDate, LocalDate endDate) {
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

