package com.hotelmanagement;

import java.math.BigDecimal;

public class Room {
    private int id;
    private int hotelId;
    private int seasonId;
    private int pensionTypeId;
    private String type;
    private BigDecimal pricePerNightAdult;
    private BigDecimal pricePerNightChild;
    private int stock;
    private int bedCount;
    private int sizeSqm;
    private boolean hasTv;
    private boolean hasMinibar;
    private boolean hasGameConsole;
    private boolean hasSafe;
    private boolean hasProjector;

    // Constructor
    public Room(int id, int hotelId, int seasonId, int pensionTypeId, String type, BigDecimal pricePerNightAdult,
                BigDecimal pricePerNightChild, int stock, int bedCount, int sizeSqm, boolean hasTv, boolean hasMinibar,
                boolean hasGameConsole, boolean hasSafe, boolean hasProjector) {
        this.id = id;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.pensionTypeId = pensionTypeId;
        this.type = type;
        this.pricePerNightAdult = pricePerNightAdult;
        this.pricePerNightChild = pricePerNightChild;
        this.stock = stock;
        this.bedCount = bedCount;
        this.sizeSqm = sizeSqm;
        this.hasTv = hasTv;
        this.hasMinibar = hasMinibar;
        this.hasGameConsole = hasGameConsole;
        this.hasSafe = hasSafe;
        this.hasProjector = hasProjector;
    }

    public Room(int hotelId, int seasonId, int pensionTypeId, String type, BigDecimal pricePerNightAdult,
                BigDecimal pricePerNightChild, int stock, int bedCount, int sizeSqm, boolean hasTv, boolean hasMinibar,
                boolean hasGameConsole, boolean hasSafe, boolean hasProjector) {
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.pensionTypeId = pensionTypeId;
        this.type = type;
        this.pricePerNightAdult = pricePerNightAdult;
        this.pricePerNightChild = pricePerNightChild;
        this.stock = stock;
        this.bedCount = bedCount;
        this.sizeSqm = sizeSqm;
        this.hasTv = hasTv;
        this.hasMinibar = hasMinibar;
        this.hasGameConsole = hasGameConsole;
        this.hasSafe = hasSafe;
        this.hasProjector = hasProjector;
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

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getPensionTypeId() {
        return pensionTypeId;
    }

    public void setPensionTypeId(int pensionTypeId) {
        this.pensionTypeId = pensionTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePerNightAdult() {
        return pricePerNightAdult;
    }

    public void setPricePerNightAdult(BigDecimal pricePerNightAdult) {
        this.pricePerNightAdult = pricePerNightAdult;
    }

    public BigDecimal getPricePerNightChild() {
        return pricePerNightChild;
    }

    public void setPricePerNightChild(BigDecimal pricePerNightChild) {
        this.pricePerNightChild = pricePerNightChild;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public int getSizeSqm() {
        return sizeSqm;
    }

    public void setSizeSqm(int sizeSqm) {
        this.sizeSqm = sizeSqm;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public void setHasTv(boolean hasTv) {
        this.hasTv = hasTv;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public boolean isHasGameConsole() {
        return hasGameConsole;
    }

    public void setHasGameConsole(boolean hasGameConsole) {
        this.hasGameConsole = hasGameConsole;
    }

    public boolean isHasSafe() {
        return hasSafe;
    }

    public void setHasSafe(boolean hasSafe) {
        this.hasSafe = hasSafe;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }
}

