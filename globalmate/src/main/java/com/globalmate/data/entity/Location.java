package com.globalmate.data.entity;

import java.io.Serializable;

public class Location implements Serializable {
    private String id;

    private String countryregion;

    private String state;

    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCountryregion() {
        return countryregion;
    }

    public void setCountryregion(String countryregion) {
        this.countryregion = countryregion == null ? null : countryregion.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}