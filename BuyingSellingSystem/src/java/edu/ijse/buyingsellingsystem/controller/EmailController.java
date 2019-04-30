/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;

import edu.ijse.buyingsellingsystem.service.AdminService;
import edu.ijse.buyingsellingsystem.util.Email;
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
public class EmailController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public @ResponseBody
    String sendMail(@RequestParam(value = "email") String email) {

        return "" ;
    }

}
