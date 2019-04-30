/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;

import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import edu.ijse.buyingsellingsystem.service.CustomerService;
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
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public @ResponseBody
    String addCustomer(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        CustomerDTO cus = new CustomerDTO();
        cus.setFirstName(firstName);
        cus.setLastName(lastName);
        cus.setPhoneNumber(Integer.parseInt(phoneNumber));
        cus.setLocation(location);
        cus.setEmail(email);
        cus.setPassword(password);

        return customerService.addCustomer(cus);
    }

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public @ResponseBody
    String getAllCustomers() {
        return new Gson().toJson(customerService.getAll());
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public @ResponseBody
    String updateCustomer(@RequestParam(value = "id") String id,
            @RequestParam(value = "fname") String fname,
            @RequestParam(value = "lname") String lname,
            @RequestParam(value = "number") String number,
            @RequestParam(value = "location") String location,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {

        System.out.println("update Called >>>>>>>>>>>>>>>>>");
        CustomerDTO cus = new CustomerDTO();
        cus.setCustomerId(Integer.parseInt(id));
        cus.setFirstName(fname);
        cus.setLastName(lname);
        cus.setPhoneNumber(Integer.parseInt(number));
        cus.setLocation(location);
        cus.setEmail(email);
        cus.setPassword(password);

        customerService.updateCustomer(cus);
        return seachCustomerById(id);
    }

    @RequestMapping(value = "/seachCustomerById", method = RequestMethod.POST)
    public @ResponseBody
    String seachCustomerById(@RequestParam(value = "id") String id) {
        CustomerDTO cus = customerService.searchCustomerById(id);
        return new Gson().toJson(cus);
    }

    @RequestMapping(value = "/getCustomerCount", method = RequestMethod.GET)
    public @ResponseBody String getCustomerCount() {

       return new Gson().toJson(customerService.getCustomerCount());
    }

}
