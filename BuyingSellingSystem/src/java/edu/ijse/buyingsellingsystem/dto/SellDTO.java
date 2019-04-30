/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dto;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class SellDTO implements Serializable {

    private int sellId;
    private double price;
    private String modelName;
    private String customerId;

    private String brandName;

    public SellDTO() {
    }

    public SellDTO(int sellId, double price, String modelName, String brandName) {
        this.sellId = sellId;
        this.price = price;
        this.modelName = modelName;
        this.brandName = brandName;
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
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the status
     */
   

    

    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
