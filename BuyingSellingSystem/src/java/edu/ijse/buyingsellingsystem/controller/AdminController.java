/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;

import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dto.AdminDTO;
import edu.ijse.buyingsellingsystem.service.AdminService;
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
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String addAdmin() {
        return "woow";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public @ResponseBody
    String addAdmin(@RequestParam(value = "adminName") String adminName, @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        AdminDTO admin = new AdminDTO();
        admin.setAdminName(adminName);
        admin.setEmail(email);
        admin.setPassword(password);
        return adminService.addAdmin(admin);
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public @ResponseBody
    String checkUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

        return adminService.checkUser(email, password);
    }

    @RequestMapping(value = "/seachAdminById", method = RequestMethod.POST)
    public @ResponseBody
    String seachAdminById(@RequestParam(value = "id") String id) {
        return new Gson().toJson(adminService.searchAdminById(id));
    }

    @RequestMapping(value = "/sendEMail", method = RequestMethod.POST)
    public @ResponseBody
    String sendEMail(@RequestParam(value = "email") String email) {
        System.out.println("///////////////////   :   Emil Send Call :: "+email);
        return adminService.sendMail(email);
    }

    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public @ResponseBody
    String updateAdmin(@RequestParam(value = "id") String id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        AdminDTO admin = new AdminDTO(id, name, email, password);
        adminService.updateAdmin(admin);
        return seachAdminById(id);
    }

}
