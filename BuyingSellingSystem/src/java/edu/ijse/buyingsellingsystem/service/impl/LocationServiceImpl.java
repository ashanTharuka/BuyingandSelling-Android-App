/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service.impl;

import edu.ijse.buyingsellingsystem.dao.custom.LocationDAO;
import edu.ijse.buyingsellingsystem.dto.LocationDTO;
import edu.ijse.buyingsellingsystem.model.Location;
import edu.ijse.buyingsellingsystem.service.LocationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO locationDAO;

    @Override
    public String addLocation(LocationDTO location) {
        LocationDTO loc = searchLocation(location.getLocationName());
        if (loc.getLocationName() != null) {
            return "Location Already Exist !";
        } else {
            Location lo = new Location();
            lo.setLocationId(location.getLocationId());
            lo.setLocationName(location.getLocationName());
            Serializable add = locationDAO.add(lo);
            if (add != null) {
                return "Location Added Success";
            } else {
                return "Location Added Fail";
            }
        }

    }

    @Override
    public ArrayList<LocationDTO> getAll() {
        ArrayList<Location> list = locationDAO.getAll();
        ArrayList<LocationDTO> newlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LocationDTO l = new LocationDTO();
            l.setLocationId(list.get(i).getLocationId());
            l.setLocationName(list.get(i).getLocationName());
            newlist.add(l);
        }
        return newlist;
    }

    @Override
    public String deleteLocation(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocationDTO searchLocation(String name) {

        List<Object[]> list = locationDAO.search(name);
        LocationDTO locationDTO = new LocationDTO();
        for (int i = 0; i < list.size(); i++) {
            Object[] get = list.get(i);
            locationDTO.setLocationId(Integer.parseInt(get[0].toString()));
            locationDTO.setLocationName(get[1].toString());
        }
        return locationDTO;
    }

    @Override
    public LocationDTO searchLocationById(String id) {
        Location location = locationDAO.searchById(id);

        LocationDTO loc = new LocationDTO();
        loc.setLocationId(location.getLocationId());
        loc.setLocationName(location.getLocationName());

        return loc;
    }

    @Override
    public String updateLocation(LocationDTO location) {
        Location loc = new Location();
        loc.setLocationId(location.getLocationId());
        loc.setLocationName(location.getLocationName());
        int update = locationDAO.update(loc);
        return "SUCCESS";
    }

    @Override
    public ArrayList<LocationDTO> customQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
