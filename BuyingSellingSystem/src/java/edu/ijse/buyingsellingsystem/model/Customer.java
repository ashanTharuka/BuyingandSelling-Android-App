/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author DELL
 */
@Entity
public class Customer extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "location_Id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<BuyingSellingDetail> buyingSellingDetails = new HashSet<>();

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the buyingSellingDetails
     */
    public Set<BuyingSellingDetail> getBuyingSellingDetails() {
        return buyingSellingDetails;
    }

    /**
     * @param buyingSellingDetails the buyingSellingDetails to set
     */
    public void setBuyingSellingDetails(Set<BuyingSellingDetail> buyingSellingDetails) {
        this.buyingSellingDetails = buyingSellingDetails;
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

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + ", location=" + location + ", buyingSellingDetails=" + buyingSellingDetails + '}';
    }
    
    
}

