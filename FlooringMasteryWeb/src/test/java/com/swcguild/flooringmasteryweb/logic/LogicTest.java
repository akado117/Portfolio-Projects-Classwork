/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryweb.logic;

import com.swcguild.flooringmasteryweb.Dao.ProductDAO;
import com.swcguild.flooringmasteryweb.Dao.TaxesDAO;
import com.swcguild.flooringmasteryweb.Model.UserInput;
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


/**
 *
 * @author apprentice
 */
public class LogicTest {
    
    TaxesDAO taxs;
    ProductDAO prod;
    
    public LogicTest() {
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
       this.taxs = (TaxesDAO) ctx.getBean("TaxesDAO");
       this.prod = (ProductDAO) ctx.getBean("ProductDAO");
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculate method, of class Logic.
     */
    @Test
    public void testCalculate_UserInput() throws IOException {
        System.out.println("calculate");
        //create a new user input object
        UserInput user = new UserInput();
        user.setDate("01282014");
        user.setCustomerName("Jacob");
        user.setState("MI");
        user.setProductType("wood");
        user.setArea(20);
        
        //create a new logic object
        Logic logic = new Logic(taxs,prod);
        
        Order result = logic.calculate(user);
        
        
        //create the expected result from above inputs
        Order expResult = new Order();
        expResult.setCustomerName("Jacob");
        expResult.setState("MI");
        expResult.setProductType("wood");
        expResult.setArea(20);
        expResult.setTaxRate((float)5.00);
        expResult.setcPSQFT((float)5.00);
        expResult.setlCPSQFT((float)4.00);
        expResult.setMaterialCost((float)(20*5.00));
        expResult.setLaborCost((float)(20*4.00));
        expResult.setTax((float)(((20*5.00)+(20*4.00))*5.00/100));
        expResult.setTotal((float)(20*5.00)+(float)(20*4.00)+(float)(((20*5.00)+(20*4.00))*5.00/100));
        
        assertEquals(expResult.getCustomerName(), result.getCustomerName());
        assertEquals(expResult.getState(), result.getState());
        assertEquals(expResult.getProductType(), result.getProductType());
        assertEquals(expResult.getArea(), result.getArea(),.01);
        assertEquals(expResult.getTaxRate(), result.getTaxRate(),.01);
        assertEquals(expResult.getcPSQFT(), result.getcPSQFT(),.01);
        assertEquals(expResult.getlCPSQFT(), result.getlCPSQFT(),.01);
        assertEquals(expResult.getTax(), result.getTax(),.01);
        assertEquals(expResult.getMaterialCost(), result.getMaterialCost(),.01);
        assertEquals(expResult.getLaborCost(), result.getLaborCost(),.01);
        assertEquals(expResult.getTotal(), result.getTotal(),.01);
        
        
        
        
        
        
        
        
     
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of calculate method, of class Logic.
     */
    @Test
    public void testCalculate_Order() throws Exception {
       System.out.println("calculate");
        //create a new user input object
        Order user = new Order();
        
        user.setCustomerName("Jacob");
        user.setState("MI");
        user.setProductType("wood");
        user.setArea(20);
        
        //create a new logic object
        Logic logic =new Logic(taxs,prod);
        
        
       
        
        Order result = logic.calculate(user);
        
        
        //create the expected result from above inputs
        Order expResult = new Order();
        expResult.setCustomerName("Jacob");
        expResult.setState("MI");
        expResult.setProductType("wood");
        expResult.setArea(20);
        expResult.setTaxRate((float)5.00);
        expResult.setcPSQFT((float)5.00);
        expResult.setlCPSQFT((float)4.00);
        expResult.setMaterialCost((float)(20*5.00));
        expResult.setLaborCost((float)(20*4.00));
        expResult.setTax((float)(((20*5.00)+(20*4.00))*5.00/100));
        expResult.setTotal((float)(20*5.00)+(float)(20*4.00)+(float)(((20*5.00)+(20*4.00))*5.00/100));
        
        assertEquals(expResult.getCustomerName(), result.getCustomerName());
        assertEquals(expResult.getState(), result.getState());
        assertEquals(expResult.getProductType(), result.getProductType());
        assertEquals(expResult.getArea(), result.getArea(),.01);
        assertEquals(expResult.getTaxRate(), result.getTaxRate(),.01);
        assertEquals(expResult.getcPSQFT(), result.getcPSQFT(),.01);
        assertEquals(expResult.getlCPSQFT(), result.getlCPSQFT(),.01);
        assertEquals(expResult.getTax(), result.getTax(),.01);
        assertEquals(expResult.getMaterialCost(), result.getMaterialCost(),.01);
        assertEquals(expResult.getLaborCost(), result.getLaborCost(),.01);
        assertEquals(expResult.getTotal(), result.getTotal(),.01);
    }
    
}
