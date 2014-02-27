/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookfortheweb;


import com.swcguild.addressbookfortheweb.dao.AddressDao;
import com.swcguild.addressbookfortheweb.model.Address;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class AddressDaoTest {

    AddressDao dao;

    public AddressDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (AddressDao) ctx.getBean("AddressDao");
        
        JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("DELETE FROM address");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class AddressDao.
     */
    @Test
    public void testAddGetRemove() {
        System.out.println("add");
        Address addr = new Address();
        addr.setFirst("Alex");
        addr.setLast("Kaidan");
        addr.setStreet("1446 Annandale Ct");
        addr.setCity("Ann Arbor");
        addr.setState("MI");
        addr.setZip("48108");

        dao.add(addr);

        System.out.println("get");

        Address addr2 = dao.get(addr.getAddressId());

        assertEquals(addr.getAddressId(), addr2.getAddressId());
        assertEquals(addr.getFirst(), addr2.getFirst());
        assertEquals(addr.getLast(), addr2.getLast());
        assertEquals(addr.getState(), addr2.getState());
        assertEquals(addr.getStreet(), addr2.getStreet());
        assertEquals(addr.getZip(), addr2.getZip());
        assertEquals(addr.getCity(), addr2.getCity());

        System.out.println("Remove");

        dao.remove(addr.getAddressId());

        assertNull(dao.get(addr.getAddressId()));

    }

    /**
     * Test of update method, of class AddressDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

        Address addr = new Address();
        addr.setFirst("Alex");
        addr.setLast("Kaidan");
        addr.setStreet("1446 Annandale Ct");
        addr.setCity("Ann Arbor");
        addr.setState("MI");
        addr.setZip("48108");

        dao.add(addr);
        //change a few values of the address
        addr.setFirst("Al");
        addr.setLast("Kai");
        addr.setStreet("1337 st");
        addr.setCity("Query Town");
        addr.setState("LS");
        addr.setZip("90210");
        //update the database
        dao.update(addr);
        //get the address from the database
        Address addr2 = dao.get(addr.getAddressId());
        //check if update worked
        assertEquals(addr.getFirst(), addr2.getFirst());
        assertEquals(addr.getLast(), addr2.getLast());
        assertEquals(addr.getState(), addr2.getState());
        assertEquals(addr.getStreet(), addr2.getStreet());
        assertEquals(addr.getZip(), addr2.getZip());
        assertEquals(addr.getCity(), addr2.getCity());

    }

    /**
     * Test of getAll method, of class AddressDao.
     */
    @Test
    public void testGetAll() {
        
        Address addr = new Address();
        addr.setFirst("Alex");
        addr.setLast("Kaidan");
        addr.setStreet("1446 Annandale Ct");
        addr.setCity("Ann Arbor");
        addr.setState("MI");
        addr.setZip("48108");

        dao.add(addr);
               
        //there should only be one value in the database
        assertEquals(dao.getAll().length, 1);

    }

    /**
     * Test of size method, of class AddressDao.
     */
    @Test
    public void testSize() {

        assertEquals(dao.size(), 0);

    }

}
