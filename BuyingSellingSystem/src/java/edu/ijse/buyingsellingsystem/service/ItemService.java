/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service;

import edu.ijse.buyingsellingsystem.dto.ItemDTO;
import edu.ijse.buyingsellingsystem.dto.SellDTO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface ItemService {

    public String addItem(ItemDTO item);

    public ArrayList<ItemDTO> getAll();

    public String deleteItem(String name);

    public ItemDTO searchItem(String name);

    public String searchBrand(String brand);

    public ItemDTO searchItemById(String id);

    public String updateItem(ItemDTO item);

    public ArrayList<ItemDTO> customQuery();

    public ArrayList<ItemDTO> getAllMainCategory();

    public ArrayList<ItemDTO> getBrandByMainCategory(String mainName);

    public ArrayList<SellDTO> getModelsByBrand(String brand);

}
