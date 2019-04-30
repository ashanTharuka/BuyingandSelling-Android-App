/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dto;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class LocationDTO implements Serializable {

    private int locationId;
    private String locationName;

    public LocationDTO() {
    }

    public LocationDTO(int locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    /**
     * @return the locationId
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * @param locationId the locationId to set
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName the locationName to set
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
