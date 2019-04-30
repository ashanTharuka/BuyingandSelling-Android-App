/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service.impl;

import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dao.custom.AdminDAO;
import edu.ijse.buyingsellingsystem.dao.custom.CustomerDAO;
import edu.ijse.buyingsellingsystem.dto.AdminDTO;
import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import edu.ijse.buyingsellingsystem.dto.LocationDTO;
import edu.ijse.buyingsellingsystem.model.Admin;
import edu.ijse.buyingsellingsystem.service.AdminService;
import edu.ijse.buyingsellingsystem.service.CustomerService;
import edu.ijse.buyingsellingsystem.util.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private CustomerService customerService;

    @Override
    public String addAdmin(AdminDTO admin) {
        Admin ad = new Admin();
        ad.setAdminName(admin.getAdminName());
        ad.setEmail(admin.getEmail());
        ad.setPassword(admin.getPassword());

        Serializable res = adminDAO.add(ad);
        if (res != null) {
            return "success";
        } else {
            return "fail";
        }
    }

    @Override
    public ArrayList<AdminDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteAdmin(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdminDTO searchAdmin(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdminDTO searchAdminById(String id) {
        Admin admin = adminDAO.searchById(id);
        AdminDTO ad = new AdminDTO();
        ad.setAdminId(Integer.toString(admin.getAdminId()));
        ad.setAdminName(admin.getAdminName());
        ad.setEmail(admin.getEmail());
        ad.setPassword(admin.getPassword());
        return ad;
    }

    @Override
    public String updateAdmin(AdminDTO admin) {
        Admin ad = new Admin();
        ad.setAdminId(Integer.parseInt(admin.getAdminId()));
        ad.setAdminName(admin.getAdminName());
        ad.setEmail(admin.getEmail());
        ad.setPassword(admin.getPassword());

        adminDAO.update(ad);
        return "SUCCESS";
    }

    @Override
    public ArrayList<AdminDTO> customQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String checkUser(String email, String password) {

        List<Object[]> list = adminDAO.searchByEmail(email, password);
        CustomerDTO cus = customerService.searchByEmail(email, password);
        if (list.size() > 0) {
            AdminDTO admin = new AdminDTO();
            for (int i = 0; i < list.size(); i++) {
                Object[] get = list.get(i);
                admin.setAdminId(get[0].toString());
                admin.setAdminName(get[1].toString());
                admin.setEmail(get[2].toString());
                admin.setPassword(get[3].toString());
            }

            String ar[] = {admin.getAdminId(), "admin"};
            return new Gson().toJson(ar);
        } else if (cus != null) {
            String ar[] = {Integer.toString(cus.getCustomerId()), "customer"};
            return new Gson().toJson(ar);
        } else {
            String ar[] = {"0", "noone"};
            return new Gson().toJson(ar);
        }
    }

    @Override
    public String sendMail(String email) {
        List<Object[]> list = adminDAO.sendMail(email);
        CustomerDTO cus = customerService.sendMail(email);
        if (list.size() > 0) {
            AdminDTO admin = new AdminDTO();
            for (int i = 0; i < list.size(); i++) {
                Object[] get = list.get(i);
                admin.setAdminId(get[0].toString());
                admin.setAdminName(get[1].toString());
                admin.setEmail(get[2].toString());
                admin.setPassword(get[3].toString());
            }

            try {
                return Email.generateAndSendEmail(email, admin.getPassword());
            } catch (MessagingException ex) {
                Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cus != null) {

            try {
                return Email.generateAndSendEmail(email, cus.getPassword());
            } catch (MessagingException ex) {
                Logger.getLogger(AdminServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "Oops !!!";

    }

}
