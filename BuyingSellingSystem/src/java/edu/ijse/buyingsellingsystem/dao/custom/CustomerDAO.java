/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom;

import edu.ijse.buyingsellingsystem.dao.SuperDAO;
import edu.ijse.buyingsellingsystem.model.Customer;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CustomerDAO extends SuperDAO<Customer> {

    public List<Object[]> searchByEmail(String email, String password);

    public List<Object[]> sendMail(String email);

    public List<String> getCustomerCount();
}
