/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DELL
 */
@Entity
public class Admin extends SuperModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
    private String adminName;
    private String email;
    private String password;

    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * @return the adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName the adminName to set
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
