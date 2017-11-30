package com.cliffhangout.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Way {
    private int id;
    private String name;
    private double height;
    private Quotation quotation;
    private int pointsNb;
    private int sectorId;
    private String heightString;
    private List<Length> lengths = new ArrayList<Length>();


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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHeightString() {
        return heightString;
    }

    public void setHeightString(String heightString) {
        this.heightString = heightString;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public int getPointsNb() {
        return pointsNb;
    }

    public void setPointsNb(int pointsNb) {
        this.pointsNb = pointsNb;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public List<Length> getLengths() {
        return lengths;
    }

    public void setLengths(List<Length> lengths) {
        this.lengths = lengths;
    }

    public void addLength(Length length){
        this.lengths.add(length);
    }

    public void removeLength(Length length){
        this.lengths.remove(length);
    }
}
