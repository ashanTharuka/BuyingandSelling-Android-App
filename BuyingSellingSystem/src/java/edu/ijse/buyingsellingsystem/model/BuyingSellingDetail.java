/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
@Entity
public class BuyingSellingDetail extends SuperModel{
    
    @EmbeddedId
    private BuyingSellingId buyingSellingId;
    
    @JoinColumn(name = "sellId", referencedColumnName = "sellId", insertable = false, updatable = false)
    @ManyToOne
    private Sell sell;

    @JoinColumn(name = "customerId", referencedColumnName = "customerId", insertable = false, updatable = false)
    @ManyToOne
    private Customer customer;
    
    
    private String status;

    /**
     * @return the buyingSellingId
     */
    public BuyingSellingId getBuyingSellingId() {
        return buyingSellingId;
    }

    /**
     * @param buyingSellingId the buyingSellingId to set
     */
    public void setBuyingSellingId(BuyingSellingId buyingSellingId) {
        this.buyingSellingId = buyingSellingId;
    }

    /**
     * @return the sell
     */
    public Sell getSell() {
        return sell;
    }

    /**
     * @param sell the sell to set
     */
    public void setSell(Sell sell) {
        this.sell = sell;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

   
}
