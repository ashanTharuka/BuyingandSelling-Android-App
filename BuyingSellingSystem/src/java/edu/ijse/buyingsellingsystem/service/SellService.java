/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service;

import edu.ijse.buyingsellingsystem.dto.SellDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface SellService {

    public String addSell(SellDTO sell);

    public ArrayList<SellDTO> getAll();

    public String deleteSell(String name);

    public SellDTO searchSell(String name);

    public SellDTO searchSellById(String id);

    public String updateSell(SellDTO sell);

    public ArrayList<SellDTO> customQuery();

    public ArrayList<String> findMySells(String id);

    public ArrayList<String> findMybuy(String id);

    public List<String> getBuyCount();

    public List<String> getSellCount();
    
    public String addBuy(String customerId,String itemId);
    
    

}
