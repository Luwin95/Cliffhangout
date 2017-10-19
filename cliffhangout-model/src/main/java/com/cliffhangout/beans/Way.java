package com.cliffhangout.beans;

public class Way {
    private int id;
    private String name;
    private float height;
    private String quotation;
    private int pointsNb;
    private Sector sector;

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

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public int getPointsNb() {
        return pointsNb;
    }

    public void setPointsNb(int pointsNb) {
        this.pointsNb = pointsNb;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
}
