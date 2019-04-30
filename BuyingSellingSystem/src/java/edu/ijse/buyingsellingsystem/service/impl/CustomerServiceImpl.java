/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service.impl;

import edu.ijse.buyingsellingsystem.dao.custom.CustomerDAO;
import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import edu.ijse.buyingsellingsystem.dto.LocationDTO;
import edu.ijse.buyingsellingsystem.service.CustomerService;
import edu.ijse.buyingsellingsystem.service.LocationService;
import edu.ijse.buyingsellingsystem.model.Customer;
import edu.ijse.buyingsellingsystem.model.Location;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private LocationService locationService;

    @Override
    public String addCustomer(CustomerDTO customer) {
        Customer cus = new Customer();
        cus.setEmail(customer.getEmail());
        cus.setFirstName(customer.getFirstName());
        cus.setLastName(customer.getLastName());
        cus.setPhoneNumber(customer.getPhoneNumber());
        cus.setPassword(customer.getPassword());

        LocationDTO loc = locationService.searchLocation(customer.getLocation());
        Location location = new Location();
        location.setLocationId(loc.getLocationId());
        location.setLocationName(loc.getLocationName());

        cus.setLocation(location);

        Serializable res = customerDAO.add(cus);
        if (res != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @Override
    public ArrayList<CustomerDTO> getAll() {
        ArrayList<Customer> list = customerDAO.getAll();

        ArrayList<CustomerDTO> newlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CustomerDTO l = new CustomerDTO();
            l.setCustomerId(list.get(i).getCustomerId());
            l.setFirstName(list.get(i).getFirstName());
            l.setLastName(list.get(i).getLastName());
            l.setEmail(list.get(i).getEmail());
            l.setPassword(list.get(i).getPassword());
            l.setPhoneNumber(list.get(i).getPhoneNumber());
            l.setLocation(list.get(i).getLocation().getLocationName());
            newlist.add(l);
        }
        return newlist;
    }

    @Override
    public String deleteCustomer(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDTO searchCustomer(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDTO searchCustomerById(String id) {
        Customer cus = customerDAO.searchById(id);
        CustomerDTO custom = new CustomerDTO();
        custom.setCustomerId(cus.getCustomerId());
        custom.setFirstName(cus.getFirstName());
        custom.setLastName(cus.getLastName());
        custom.setPhoneNumber(cus.getPhoneNumber());
        custom.setEmail(cus.getEmail());
        custom.setLocation(cus.getLocation().getLocationName());
        custom.setPassword(cus.getPassword());

        return custom;

    }

    @Override
    public String updateCustomer(CustomerDTO customer) {
        Customer cus = new Customer();
        cus.setCustomerId(customer.getCustomerId());
        cus.setFirstName(customer.getFirstName());
        cus.setLastName(customer.getLastName());
        cus.setPhoneNumber(customer.getPhoneNumber());
        cus.setEmail(customer.getEmail());
        cus.setPassword(customer.getPassword());

        LocationDTO loc = locationService.searchLocation(customer.getLocation());
        Location location = new Location();
        location.setLocationId(loc.getLocationId());
        location.setLocationName(loc.getLocationName());
        cus.setLocation(location);
        int update = customerDAO.update(cus);
        return "Success";
    }

    @Override
    public ArrayList<CustomerDTO> customQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDTO searchByEmail(String email, String password) {
        List<Object[]> list = customerDAO.searchByEmail(email, password);
        if (list.size() > 0) {
            CustomerDTO customerDTO = new CustomerDTO();
            for (int i = 0; i < list.size(); i++) {
                Object[] get = list.get(i);
                customerDTO.setCustomerId(Integer.parseInt(get[0].toString()));
                customerDTO.setEmail(get[1].toString());
                customerDTO.setFirstName(get[2].toString());
                customerDTO.setLastName(get[3].toString());
                customerDTO.setPassword(get[4].toString());
                customerDTO.setPhoneNumber(Integer.parseInt(get[5].toString()));

                LocationDTO loc = locationService.searchLocationById(get[6].toString());
                customerDTO.setLocation(loc.getLocationName());

            }
            return customerDTO;
        } else {
            return null;
        }
    }

    @Override
    public CustomerDTO sendMail(String email) {
        List<Object[]> list = customerDAO.sendMail(email);
        if (list.size() > 0) {
            CustomerDTO customerDTO = new CustomerDTO();
            for (int i = 0; i < list.size(); i++) {
                Object[] get = list.get(i);
                customerDTO.setCustomerId(Integer.parseInt(get[0].toString()));
                customerDTO.setEmail(get[1].toString());
                customerDTO.setFirstName(get[2].toString());
                customerDTO.setLastName(get[3].toString());
                customerDTO.setPassword(get[4].toString());
                customerDTO.setPhoneNumber(Integer.parseInt(get[5].toString()));

                LocationDTO loc = locationService.searchLocationById(get[6].toString());
                customerDTO.setLocation(loc.getLocationName());
            }
            return customerDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<String> getCustomerCount() {
        return customerDAO.getCustomerCount();
    }

}
