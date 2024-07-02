package com.hotelmanagement;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private int stars;
    private String features;

    // Constructor
    public Hotel(int id, String name, String address, String email, String phone, int stars, String features) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.stars = stars;
        this.features = features;
    }
    public Hotel(){

    }

    public Hotel(String name, String address, String email, String phone, int stars, String features) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.stars = stars;
        this.features = features;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
