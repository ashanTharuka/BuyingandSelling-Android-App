/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service;

import edu.ijse.buyingsellingsystem.dto.AdminDTO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface AdminService {

    public String addAdmin(AdminDTO admin);

    public ArrayList<AdminDTO> getAll();

    public String deleteAdmin(String name);

    public AdminDTO searchAdmin(String name);

    public AdminDTO searchAdminById(String id);

    public String updateAdmin(AdminDTO admin);

    public ArrayList<AdminDTO> customQuery();
    public String checkUser(String email,String password);
    
    public String sendMail(String email);
}
