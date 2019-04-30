/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom.impl;

import edu.ijse.buyingsellingsystem.dao.custom.LocationDAO;
import edu.ijse.buyingsellingsystem.model.Location;
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
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Serializable add(Location model) {
        return sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public int update(Location model) {
        sessionFactory.getCurrentSession().update(model);
        return 1;
    }

    @Override
    public List<Object[]> search(String name) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Location where locationName='" + name + "'").list();
    }

    @Override
    public ArrayList<Location> getAll() {
        return (ArrayList<Location>) sessionFactory.getCurrentSession().createQuery("from Location").list();
    }

    @Override
    public List<Object[]>  customQuery(String query) {
        return  sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    public int delete(Location model) {
        sessionFactory.getCurrentSession().delete(model);
        return 1;
    }

    @Override
    public Location searchById(String id) {
        return (Location) sessionFactory.getCurrentSession().get(Location.class, Integer.parseInt(id));
    }

}
