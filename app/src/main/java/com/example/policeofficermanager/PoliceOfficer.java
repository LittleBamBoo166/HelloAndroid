package com.example.policeofficermanager;

public class PoliceOfficer {
    private String imageUrl, name, level, country, starNumber, workPlace;

    public PoliceOfficer() {
    }

    public PoliceOfficer(String imageUrl, String name, String level, String country, String starNumber, String workPlace) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.level = level;
        this.country = country;
        this.starNumber = starNumber;
        this.workPlace = workPlace;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(String starNumber) {
        this.starNumber = starNumber;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
