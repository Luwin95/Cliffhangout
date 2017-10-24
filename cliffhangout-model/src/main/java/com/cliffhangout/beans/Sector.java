package com.cliffhangout.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sector {

    private int id;
    private String name;
    private String description;
    private Site site;
    private List<Way> ways = new ArrayList<Way>();

    public Sector(Site site){
        this.site = site;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Way> getWays() {
        return ways;
    }

    public void setWays(List<Way> ways) {
        this.ways = ways;
    }

    public void addWay(Way way){
        this.ways.add(way);
    }

    public void removeWay(Way way){
        this.ways.remove(way);
    }
}
