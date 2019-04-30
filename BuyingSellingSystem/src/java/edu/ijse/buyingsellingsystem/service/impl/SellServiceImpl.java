/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.service.impl;

import edu.ijse.buyingsellingsystem.dao.custom.SellDAO;
import edu.ijse.buyingsellingsystem.dto.CustomerDTO;
import edu.ijse.buyingsellingsystem.dto.ItemDTO;
import edu.ijse.buyingsellingsystem.dto.SellDTO;
import edu.ijse.buyingsellingsystem.model.BuyingSellingDetail;
import edu.ijse.buyingsellingsystem.model.BuyingSellingId;
import edu.ijse.buyingsellingsystem.model.Customer;
import edu.ijse.buyingsellingsystem.model.Item;
import edu.ijse.buyingsellingsystem.model.Sell;
import edu.ijse.buyingsellingsystem.service.CustomerService;
import edu.ijse.buyingsellingsystem.service.ItemService;
import edu.ijse.buyingsellingsystem.service.SellService;
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
public class SellServiceImpl implements SellService {

    @Autowired
    private SellDAO sellDAO;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Override
    public String addSell(SellDTO sell) {
        ItemDTO it = itemService.searchItemById(sell.getBrandName());
        CustomerDTO cus = customerService.searchCustomerById(sell.getCustomerId());

        Customer cu = new Customer();
        cu.setCustomerId(cus.getCustomerId());
        cu.setEmail(cus.getEmail());
        cu.setFirstName(cus.getFirstName());
        cu.setLastName(cus.getLastName());
        cu.setPassword(cus.getPassword());
        cu.setPhoneNumber(cus.getPhoneNumber());

        Item item = new Item();
        item.setBrandName(it.getBrandName());
        item.setItemId(it.getItemId());
        item.setItemName(it.getItemName());

        Sell se = new Sell();
        se.setItem(item);
        se.setModelName(sell.getModelName());
        se.setPrice(sell.getPrice());

        int add = sellDAO.addd(se);
        BuyingSellingId b = new BuyingSellingId();
        b.setSellId(add);
        b.setCustomerId(Integer.parseInt(sell.getCustomerId()));
        BuyingSellingDetail bb = new BuyingSellingDetail();
        bb.setBuyingSellingId(b);
        bb.setSell(se);
        bb.setStatus("sell");
        bb.setCustomer(cu);
        Serializable add1 = sellDAO.add(bb);
        if (add1 != null) {
            return " Success";
        }

        return " Fail";

    }

    @Override
    public ArrayList<SellDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteSell(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SellDTO searchSell(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SellDTO searchSellById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateSell(SellDTO sell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SellDTO> customQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> findMySells(String id) {

        ArrayList<String> list = (ArrayList<String>) sellDAO.findMySells(id);
        return list;
    }

    @Override
    public ArrayList<String> findMybuy(String id) {
        ArrayList<String> list = (ArrayList<String>) sellDAO.findMybuys(id);
        return list;
    }

    @Override
    public List<String> getBuyCount() {
        return sellDAO.getBuyCount();
    }

    @Override
    public List<String> getSellCount() {
        return sellDAO.getSellCount();
    }

    @Override
    public String addBuy(String customerId, String itemId) {
       
       return sellDAO.buy(customerId, itemId);
    }

}
