/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom.impl;

import edu.ijse.buyingsellingsystem.dao.custom.SellDAO;
import edu.ijse.buyingsellingsystem.model.BuyingSellingDetail;
import edu.ijse.buyingsellingsystem.model.Location;
import edu.ijse.buyingsellingsystem.model.Sell;
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
public class SellDAOImpl implements SellDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int update(Sell model) {
        sessionFactory.getCurrentSession().update(model);
        return 1;
    }

    @Override
    public List<Object[]> search(String name) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Sell where itemName='" + name + "'").list();
    }

    @Override
    public ArrayList<Sell> getAll() {
        return (ArrayList<Sell>) sessionFactory.getCurrentSession().createQuery("from Sell").list();

    }

    @Override
    public List<Object[]> customQuery(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    public int delete(Sell model) {
        sessionFactory.getCurrentSession().delete(model);
        return 1;
    }

    @Override
    public Sell searchById(String id) {
        return (Sell) sessionFactory.getCurrentSession().get(Sell.class, Integer.parseInt(id));
    }

    @Override
    public int addd(Sell model) {
        return (Integer) sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public Serializable add(Sell model) {
        return sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public Serializable add(BuyingSellingDetail b) {
        return sessionFactory.getCurrentSession().save(b);
    }

    @Override
    public List<String> findMySells(String id) {
        return sessionFactory.getCurrentSession().createSQLQuery(" select s.modelName from buyingsellingdetail b,sell s,customer c where s.sellId=b.sellId and b.customerId=c.customerId and b.status='sell' and c.customerId='" + id + "'").list();
    }

    @Override
    public List<String> findMybuys(String id) {
        return sessionFactory.getCurrentSession().createSQLQuery(" select s.modelName from buyingsellingdetail b,sell s,customer c where s.sellId=b.sellId and b.customerId=c.customerId and b.status='buy' and c.customerId='" + id + "'").list();
    }

    @Override
    public List<String> getBuyCount() {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(customerId) from buyingsellingdetail where status='buy'").list();
    }

    @Override
    public List<String> getSellCount() {
        return sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(customerId) from buyingsellingdetail where status='sell'").list();
    }

    @Override
    public String buy(String cusId, String sellId) {
        update(sellId);
        sessionFactory.getCurrentSession().createSQLQuery("insert into buyingsellingdetail values('" + cusId + "','" + sellId + "','buy')").executeUpdate();
        return "Success";
    }

    @Override
    public String update(String shellId) {
        sessionFactory.getCurrentSession().createSQLQuery("update buyingsellingdetail set status='none' where sellId='" + shellId + "'").executeUpdate();
        return "success";
    }
}
