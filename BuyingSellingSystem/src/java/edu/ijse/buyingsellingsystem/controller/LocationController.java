/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;


import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dto.LocationDTO;
import edu.ijse.buyingsellingsystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
public class LocationController {
    
    @Autowired
    private LocationService locationService;
    
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public @ResponseBody
    String addLocation(@RequestParam(value = "locationName") String locationName) {
        LocationDTO location=new LocationDTO();
        location.setLocationName(locationName);
        return locationService.addLocation(location);
    }
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public @ResponseBody
    String updateLocation(@RequestParam(value = "id") String id,@RequestParam(value = "locationName") String locationName) {
        LocationDTO location=new LocationDTO();
        location.setLocationId(Integer.parseInt(id));
        location.setLocationName(locationName);
        return locationService.updateLocation(location);
    }
    @RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
    public @ResponseBody String getAll() {
        return new Gson().toJson(locationService.getAll());
    }
    
    
}
