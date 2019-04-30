/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service;

import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CustomerService {

    public String addCustomer(CustomerDTO customer);

    public ArrayList<CustomerDTO> getAll();

    public String deleteCustomer(String name);

    public CustomerDTO searchCustomer(String name);

    public CustomerDTO searchCustomerById(String id);

    public String updateCustomer(CustomerDTO customer);

    public ArrayList<CustomerDTO> customQuery();
    
    public CustomerDTO searchByEmail(String email,String password);
    public CustomerDTO sendMail(String email);
    
    public List<String> getCustomerCount();
}
