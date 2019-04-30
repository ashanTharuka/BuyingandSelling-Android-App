/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao.custom.impl;
 
import edu.ijse.buyingsellingsystem.dao.custom.CustomerDAO;
import edu.ijse.buyingsellingsystem.model.Customer;
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
public class CustomerDAOImpl implements CustomerDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Serializable add(Customer model) {
        return sessionFactory.getCurrentSession().save(model);
    }
    
    @Override
    public int update(Customer model) {
        sessionFactory.getCurrentSession().update(model);
        return 1;
    }
    
    @Override
    public List<Object[]> search(String name) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Customer where firstName='" + name + "'").list();
    }
    
   
    
    @Override
    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) sessionFactory.getCurrentSession().createQuery("from Customer").list();
    }
    
    @Override
    public List<Object[]>  customQuery(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @Override
    public int delete(Customer model) {
         sessionFactory.getCurrentSession().delete(model);
        return 1;
    }

    @Override
    public Customer searchById(String id) {
         return(Customer) sessionFactory.getCurrentSession().get(Customer.class, Integer.parseInt(id));
    }

    @Override
    public List<Object[]> searchByEmail(String email,String password) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Customer where email='" + email + "' and password='"+password+"'").list();
    }

    @Override
    public List<Object[]> sendMail(String email) {
        return sessionFactory.getCurrentSession().createSQLQuery("select * from Customer where email='" + email + "'").list();
    }

    @Override
    public List<String> getCustomerCount() {
         return sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(customerId) from customer").list();
    }
    
}
