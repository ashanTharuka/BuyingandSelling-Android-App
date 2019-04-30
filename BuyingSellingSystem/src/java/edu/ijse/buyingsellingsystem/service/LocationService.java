/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service;

import edu.ijse.buyingsellingsystem.dto.LocationDTO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface LocationService {

    public String addLocation(LocationDTO location);

    public ArrayList<LocationDTO> getAll();

    public String deleteLocation(String name);

    public LocationDTO searchLocation(String name);

    public LocationDTO searchLocationById(String id);

    public String updateLocation(LocationDTO location);

    public ArrayList<LocationDTO> customQuery();
}
