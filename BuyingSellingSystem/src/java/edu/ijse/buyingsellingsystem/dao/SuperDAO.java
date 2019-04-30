/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.buyingsellingsystem.dao;


import edu.ijse.buyingsellingsystem.model.SuperModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashan Tharuka
 * @param <T>
 */
public interface SuperDAO<T extends SuperModel> {

    public Serializable add(T model);
  

    public int update(T model);

    public List<Object[]> search(String name);

    public T searchById(String id);

    public int delete(T model);

    public ArrayList<T> getAll();

    public List<Object[]>  customQuery(String query);
}
