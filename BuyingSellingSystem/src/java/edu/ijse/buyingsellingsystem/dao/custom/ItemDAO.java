/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom;

import edu.ijse.buyingsellingsystem.dao.SuperDAO;
import edu.ijse.buyingsellingsystem.model.Item;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ItemDAO extends SuperDAO<Item>{
      public List<String>  getAllMainCategory();
      public List<Object[]>  getBrandByMainCategory(String mainName);
      public List<Object[]>  getModeByBrand(String brand);
      
}
