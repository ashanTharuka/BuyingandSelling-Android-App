/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service.impl;

import edu.ijse.buyingsellingsystem.dao.custom.ItemDAO;
import edu.ijse.buyingsellingsystem.dto.ItemDTO;
import edu.ijse.buyingsellingsystem.dto.SellDTO;
import edu.ijse.buyingsellingsystem.service.ItemService;
import edu.ijse.buyingsellingsystem.model.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemDAO itemDAO;
    
    @Override
    public String addItem(ItemDTO item) {
        
        String res = searchBrand(item.getBrandName());
        if (res.equalsIgnoreCase("yes")) {
            return "Name Already Exist";
        } else {
            Item it = new Item();
            it.setBrandName(item.getBrandName());
            it.setItemName(item.getItemName());
            
            Serializable ress = itemDAO.add(it);
            if (ress != null) {
                return "Added success";
            } else {
                return "Added fail";
            }
        }
    }
    
    @Override
    public ArrayList<ItemDTO> getAll() {
        
        ArrayList<Item> list = itemDAO.getAll();
        ArrayList<ItemDTO> itemList = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            ItemDTO l = new ItemDTO();
            l.setItemId(list.get(i).getItemId());
            l.setItemName(list.get(i).getItemName());
            l.setBrandName(list.get(i).getBrandName());
            
            itemList.add(l);
        }
        
        return itemList;
    }
    
    @Override
    public String deleteItem(String name) {
        Item item = itemDAO.searchById(name);
        
        int res = itemDAO.delete(item);
        return "Success";
    }
    
    @Override
    public ItemDTO searchItem(String name) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ItemDTO searchItemById(String id) {
        Item item = itemDAO.searchById(id);
        ItemDTO i = new ItemDTO();
        i.setItemId(item.getItemId());
        i.setItemName(item.getItemName());
        i.setBrandName(item.getBrandName());
        
        return i;
    }
    
    @Override
    public String updateItem(ItemDTO item) {
        Item it = new Item();
        it.setItemId(item.getItemId());
        it.setItemName(item.getItemName());
        it.setBrandName(item.getBrandName());
        int update = itemDAO.update(it);
        
        return "success";
    }
    
    @Override
    public ArrayList<ItemDTO> customQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String searchBrand(String brand) {
        List<Object[]> list = itemDAO.search(brand);
        
        String item = "";
        
        for (int i = 0; i < list.size(); i++) {
            Object[] get = list.get(i);
            item = get[1].toString();
        }
        
        if (item.equalsIgnoreCase(brand)) {
            return "yes";
        } else {
            return "no";
        }
    }
    
    @Override
    public ArrayList<ItemDTO> getAllMainCategory() {
        List<String> list = itemDAO.getAllMainCategory();
        ArrayList<ItemDTO> newlist = new ArrayList<>();
        
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ItemDTO item = new ItemDTO();
                String ii = list.get(i);
                item.setItemName(ii);
                newlist.add(item);
            }
        }
        return newlist;
    }
    
    @Override
    public ArrayList<ItemDTO> getBrandByMainCategory(String mainName) {
        
        List<Object[]> list = itemDAO.getBrandByMainCategory(mainName);
        ArrayList<ItemDTO> newlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] ar = list.get(i);
            ItemDTO it = new ItemDTO();
            it.setItemId(Integer.parseInt(ar[0].toString()));
            it.setBrandName(ar[1].toString());
            newlist.add(it);
        }
        
        return newlist;
    }
    
    @Override
    public ArrayList<SellDTO> getModelsByBrand(String brand) {
        List<Object[]> list = itemDAO.getModeByBrand(brand);
        ArrayList<SellDTO> newlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] ar = list.get(i);
            SellDTO it = new SellDTO();
            it.setSellId(Integer.parseInt(ar[0].toString()));
            it.setModelName(ar[2].toString());
            it.setPrice(Double.parseDouble(ar[1].toString()));
            
            newlist.add(it);
        }
        
        return newlist;
        
    }
    
}
