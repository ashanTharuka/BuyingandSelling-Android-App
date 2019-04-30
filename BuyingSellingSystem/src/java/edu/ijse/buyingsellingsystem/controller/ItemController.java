/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;

import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import edu.ijse.buyingsellingsystem.dto.ItemDTO;
import edu.ijse.buyingsellingsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public @ResponseBody
    String addItem(@RequestParam(value = "itemName") String itemName, @RequestParam(value = "brandName") String brandName) {
        ItemDTO item = new ItemDTO();
        item.setItemName(itemName);
        item.setBrandName(brandName);
        return itemService.addItem(item);
    }
    
    @RequestMapping(value = "/seachItemById", method = RequestMethod.POST)
    public @ResponseBody
    String seachItemById(@RequestParam(value = "id") String id) {
        return new Gson().toJson(itemService.searchItemById(id));
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
    public @ResponseBody
    String deleteItem(@RequestParam(value = "id") String id) {
        return new Gson().toJson(itemService.deleteItem(id));
    }
    
    @RequestMapping(value = "/checkBrandName", method = RequestMethod.POST)
    public @ResponseBody
    String checkBrandName(@RequestParam(value = "brand") String brand) {
        return itemService.searchBrand(brand);
    }
    
    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public @ResponseBody
    String getAllItems() {
        return new Gson().toJson(itemService.getAll());
    }
    @RequestMapping(value = "/getMainCategory", method = RequestMethod.GET)
    public @ResponseBody
    String getMainCategory() {
        return new Gson().toJson(itemService.getAllMainCategory());
    }
    @RequestMapping(value = "/getBrandByMainCategory", method = RequestMethod.POST)
    public @ResponseBody
    String getBrandByMainCategory(@RequestParam(value = "mainName") String mainName) {
        return new Gson().toJson(itemService.getBrandByMainCategory(mainName));
    }
    
    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public @ResponseBody
    String updateItem(@RequestParam(value = "id") String id,
            @RequestParam(value = "itemName") String itemName,
            @RequestParam(value = "brandName") String brandName) {
        
        ItemDTO item = new ItemDTO();
        item.setItemId(Integer.parseInt(id));
        item.setItemName(itemName);
        item.setBrandName(brandName);
        return itemService.updateItem(item);
    }
    
    
    @RequestMapping(value = "/getModelsByBrand", method = RequestMethod.POST)
    public @ResponseBody
    String getModelsByBrand(@RequestParam(value = "brand") String brand) {
       
        return new Gson().toJson(itemService.getModelsByBrand(brand));
    }
    
}
