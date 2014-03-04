/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryweb.Dao;

import com.swcguild.flooringmasteryweb.Model.Order;
import java.io.IOException;
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
public class OrderDAOTest {
    
    OrderDAO ordDao;
    Order ord = new Order();
    
    public OrderDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
            //initialize the beans
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
       this.ordDao = (OrderDAO) ctx.getBean("OrderDAO");
       
       
       JdbcTemplate cleaner = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        cleaner.execute("DELETE FROM orders");
       
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderDAO.
     */
    @Test
    public void testAddOrderandGetOrder() {
        System.out.println("addOrder");
        //create new order object
        
        
        
        ord.setCustomerName("Alex");
        ord.setState("MI");
        ord.setProductType("wood");
        ord.setArea(20);
        ord.setDate("12252013");
        ord.setcPSQFT(0);
        ord.setlCPSQFT(0);
        ord.setMaterialCost(0);
        ord.setLaborCost(0);
        ord.setTaxRate(0);
        ord.setTax(0);
        ord.setTotal(0);
        
        ordDao.addOrder(ord); //adds the order to memory
        
        assertTrue(null!=ordDao.getOrder(ord.getOrderNumber()).getCustomerName());
        
        ordDao.deleteOrder(ord.getOrderNumber());//deletes order
        
        
    }


    

    /**
     * Test of getHighestOrderNumber method, of class OrderDAO.
     */
    @Test
    public void testGetHighestOrderNumber() {
        System.out.println("get most recent order");
        
        
        ordDao.addOrder(ord); //adds the order to memory
        
        assertTrue(ordDao.highestOrderNumber()==ord.getOrderNumber());
    }



    /**
     * Test of listOrders method, of class OrderDAO.
     */
    @Test
    public void testListOrders() {
      
       
        
        assertTrue(ordDao.listOrders(ord.getDate())!=null);
        
        
        
    }
    //testing the date dao
    @Test
    public void testListDates(){
        
        
        ord.setDate("12252014");
        ordDao.addOrder(ord);
        ordDao.addOrder(ord);
        
        ord.setDate("12242014");
        
        ordDao.addOrder(ord);
        
        ord.setDate("12232014");
        ordDao.addOrder(ord);
        
        
        String[] dates = ordDao.listDates();
        
      
        
        assertEquals(dates[0],ord.getDate());
    }
    
    

}
