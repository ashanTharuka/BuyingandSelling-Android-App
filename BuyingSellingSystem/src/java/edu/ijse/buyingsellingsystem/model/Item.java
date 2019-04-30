/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author DELL
 */
@Entity
public class Item extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private String itemName;
    private String brandName;
    
    @OneToMany(mappedBy = "item")
    private Set<Sell>sells=new HashSet<>();

    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

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
     * @return the sells
     */
    public Set<Sell> getSells() {
        return sells;
    }

    /**
     * @param sells the sells to set
     */
    public void setSells(Set<Sell> sells) {
        this.sells = sells;
    }

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", itemName=" + itemName + ", brandName=" + brandName + ", sells=" + sells + '}';
    }
 
    
}
