/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookfortheweb.dao;


import com.swcguild.addressbookfortheweb.model.Address;

/**
 *
 * @author apprentice
 */
public interface AddressDao {

    public void add(Address address);

    public void remove(int indexoflastName);

    public Address get(int indexoflastName);

    public Address[] getAll();
    
    public Address[] search(Address address);

    public void update(Address address);
    
    public int size();

}
