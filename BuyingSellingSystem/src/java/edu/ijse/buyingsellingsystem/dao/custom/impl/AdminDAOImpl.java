/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom.impl;

import edu.ijse.buyingsellingsystem.dao.custom.AdminDAO;
import edu.ijse.buyingsellingsystem.model.Admin;
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
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Serializable add(Admin model) {
        return sessionFactory.getCurrentSession().save(model);
    }

    @Override
    public int update(Admin model) {
        sessionFactory.getCurrentSession().update(model);
        return 1;
    }

    @Override
    public List<Object[]> search(String name) {
         return sessionFactory.getCurrentSession().createSQLQuery("select * from Admin where adminName='" + name + "'").list();
    }

    @Override
    public int delete(Admin model) {
        sessionFactory.getCurrentSession().delete(model);
        return 1;
    }

    @Override
    public ArrayList<Admin> getAll() {
        return (ArrayList<Admin>) sessionFactory.getCurrentSession().createQuery("from Admin").list();
    }
    @Override
    public List<Object[]>  customQuery(String query) {
          return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    public Admin searchById(String id) {
        return(Admin) sessionFactory.getCurrentSession().get(Admin.class, Integer.parseInt(id));
    }

    @Override
    public List<Object[]> searchByEmail(String email,String password) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Admin where email='" + email + "' and password='"+password+"'").list();
    }

    @Override
    public List<Object[]> sendMail(String email) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Admin where email='" + email + "'").list();
    }
}
