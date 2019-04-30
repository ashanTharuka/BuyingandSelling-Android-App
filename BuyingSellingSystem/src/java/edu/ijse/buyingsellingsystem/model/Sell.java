package edu.ijse.buyingsellingsystem.model;

import edu.ijse.buyingsellingsystem.model.SuperModel;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
@Entity
public class Sell extends SuperModel {

    /**
     * @return the buyingSellingDetails
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sellId;
    private double price;
    private String modelName;
 

    @ManyToOne
    @JoinColumn(name = "item_Id")
    private Item item;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sell")
    private Set<BuyingSellingDetail> buyingSellingDetails = new HashSet<>();

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
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
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

}
