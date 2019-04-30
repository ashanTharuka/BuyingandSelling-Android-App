/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.controller;

import com.google.gson.Gson;
import edu.ijse.buyingsellingsystem.dto.SellDTO;
import edu.ijse.buyingsellingsystem.service.SellService;
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
public class SellController {

    @Autowired
    private SellService sellService;

    @RequestMapping(value = "/addSell", method = RequestMethod.POST)
    public @ResponseBody
    String addSell(@RequestParam(value = "price") String price,
            @RequestParam(value = "modelName") String modelName,
            @RequestParam(value = "cusId") String cusId,
            @RequestParam(value = "itemid") String itemid) {
        SellDTO sell = new SellDTO();
        sell.setPrice(Double.parseDouble(price));
        sell.setModelName(modelName);
        sell.setCustomerId(cusId);
        sell.setBrandName(itemid);//item id
        return sellService.addSell(sell);
    }

    @RequestMapping(value = "/findMySells", method = RequestMethod.POST)
    public @ResponseBody
    String findMySells(@RequestParam(value = "id") String id) {

        return new Gson().toJson(sellService.findMySells(id));
    }

    @RequestMapping(value = "/findMybuy", method = RequestMethod.POST)
    public @ResponseBody
    String findMybuy(@RequestParam(value = "id") String id) {

        return new Gson().toJson(sellService.findMybuy(id));
    }
    @RequestMapping(value = "/addBuy", method = RequestMethod.POST)
    public @ResponseBody
    String addBuy(@RequestParam(value = "customerId") String customerId,@RequestParam(value = "itemId") String itemId) {

        return sellService.addBuy(customerId, itemId);
    }

    @RequestMapping(value = "/getSellCount", method = RequestMethod.GET)
    public @ResponseBody String getSellCount() {

        return new Gson().toJson(sellService.getSellCount());
    }

    @RequestMapping(value = "/getBuyCount", method = RequestMethod.GET)
    public @ResponseBody String getBuyCount() {

         return new Gson().toJson(sellService.getBuyCount());
    }

}
