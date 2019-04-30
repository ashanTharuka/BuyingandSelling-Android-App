/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom;

import edu.ijse.buyingsellingsystem.dao.SuperDAO;
import edu.ijse.buyingsellingsystem.model.BuyingSellingDetail;
import edu.ijse.buyingsellingsystem.model.Sell;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface SellDAO extends SuperDAO<Sell> {

    public int addd(Sell model);

    public Serializable add(BuyingSellingDetail b);

    public List<String> findMySells(String id);

    public List<String> findMybuys(String id);

    public List<String> getBuyCount();

    public List<String> getSellCount();
    
    public String buy(String cusId,String sellId);
    
    public String update(String shellId);
}
