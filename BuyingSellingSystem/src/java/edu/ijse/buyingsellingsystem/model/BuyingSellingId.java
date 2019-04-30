/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.model;

import javax.persistence.Embeddable;

/**
 *
 * @author DELL
 */
@Embeddable
public class BuyingSellingId extends SuperModel {

    private int sellId;
    private int customerId;

    public BuyingSellingId() {
    }

    public BuyingSellingId(int sellId, int customerId) {
        this.sellId = sellId;
        this.customerId = customerId;
    }

    /**
     * @return the sellId
     */
    public int getSellId() {
        return sellId;
    }

    /**
     * @param sellId the sellId to set
     */
    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

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
    
}
