package com.hotelmanagement;

public class PensionType {
    private int id;
    private int hotelId;
    private String type;

    // Constructor
    public PensionType(int id, int hotelId, String type) {
        this.id = id;
        this.hotelId = hotelId;
        this.type = type;
    }

    public PensionType(int hotelId, String type) {
        this.hotelId = hotelId;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

