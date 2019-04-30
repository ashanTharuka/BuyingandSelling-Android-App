/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom.impl;

import edu.ijse.buyingsellingsystem.dao.custom.ItemDAO;
import edu.ijse.buyingsellingsystem.model.Customer;
import edu.ijse.buyingsellingsystem.model.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DELL
 */
@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Serializable add(Item model) {
        return sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public int update(Item model) {
        sessionFactory.getCurrentSession().update(model);
        return 1;
    }

    @Override
    public List<Object[]> search(String name) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Item where brandName='" + name + "'").list();
    }

    @Override
    public ArrayList<Item> getAll() {
        return (ArrayList<Item>) sessionFactory.getCurrentSession().createQuery("from Item").list();
    }

    @Override
    public List<Object[]>  customQuery(String query) {
        return  sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    public int delete(Item model) {
        sessionFactory.getCurrentSession().delete(model);
        return 1;
    }

    @Override
    public Item searchById(String id) {
        return (Item) sessionFactory.getCurrentSession().get(Item.class, Integer.parseInt(id));
    }

    @Override
    public List<String> getAllMainCategory() {
        return sessionFactory.getCurrentSession().createSQLQuery(" select distinct itemname from item").list();
    }

    @Override
    public List<Object[]> getBrandByMainCategory(String mainName) {
          return sessionFactory.getCurrentSession().createSQLQuery("select itemid,brandname from item where itemname='"+mainName+"'").list();
    }

    @Override
    public List<Object[]> getModeByBrand(String brand) {
        System.out.println("***************************************************************");
        System.out.println("***************************************************************");
        System.out.println("***************************************************************");
      return sessionFactory.getCurrentSession().createSQLQuery("select b.sellId,price,modelName from sell s,buyingsellingdetail b,item i where s.sellId=b.sellId and s.item_Id=i.itemId and status='sell' and brandName='"+brand+"'").list();
    }

}
