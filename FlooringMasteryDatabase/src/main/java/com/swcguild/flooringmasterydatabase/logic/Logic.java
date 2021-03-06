/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterydatabase.logic;

import com.swcguild.flooringmasterydatabase.Dao.OrderDAO;
import com.swcguild.flooringmasterydatabase.Dao.ProductDAO;
import com.swcguild.flooringmasterydatabase.Dao.TaxesDAO;
import com.swcguild.flooringmasterydatabase.model.Order;
import com.swcguild.flooringmasterydatabase.model.Product;
import com.swcguild.flooringmasterydatabase.model.Tax;
import com.swcguild.flooringmasterydatabase.model.UserInput;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class Logic {

    TaxesDAO taxs = new TaxesDAO();
    ProductDAO prod = new ProductDAO();
    HashMap<String, Tax> taxMap = new HashMap<String, Tax>();
    HashMap<String, Product> prodMap = new HashMap<String, Product>();
    Order ord = new Order();

    public Logic() throws IOException {//load from file when logic object is created
        
        //initialize the beans
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        taxs = (TaxesDAO) ctx.getBean("TaxesDAO");
        prod = (ProductDAO) ctx.getBean("ProductDAO");
        
        
        
        //fill the hashmap with the products
        Product[] prodArray = prod.listProducts();
        for (Product product : prodArray) {
            prodMap.put(product.getProduct(), product);

        }

        Tax[] taxArray = taxs.getTaxes();

        //add the taxes to a hash map to make them easier to find
        for (Tax tax : taxArray) {
            taxMap.put(tax.getTaxState(), tax);
        }

    }

    public Order calculate(UserInput user) {

        //sets user defined variables
        ord.setCustomerName(user.getCustomerName());
        ord.setProductType(user.getProductType());
        ord.setArea(user.getArea());
        ord.setState(user.getState());
        ord.setDate(user.getDate());

        //get information about products and taxes from file
        //product cost section
        Product curProd = prodMap.get(ord.getProductType()); //grabs the product type we want
        ord.setcPSQFT(curProd.getMaterialCost()); //sets rate based upon what was drawn from the file
        ord.setlCPSQFT(curProd.getLaborRate());
        ord.setMaterialCost(ord.getArea() * ord.getcPSQFT());
        ord.setLaborCost(ord.getArea() * ord.getlCPSQFT());

//taxes section
        ord.setTaxRate((float) taxMap.get(ord.getState()).getTaxRate());
        ord.setTax((ord.getLaborCost() + ord.getMaterialCost()) * ord.getTaxRate() / 100);

        ord.setTotal(ord.getLaborCost() + ord.getMaterialCost() + ord.getTax()); //calculates total 

        return ord;

    }

    //if the user enters an order it will just used the already defined parameters and overwrite all 
    public Order calculate(Order order) throws IOException {
        ord = order;

        //get information about products and taxes from file
        //product cost section
        Product curProd = prodMap.get(ord.getProductType()); //grabs the product type we want
        ord.setcPSQFT(curProd.getMaterialCost()); //sets rate based upon what was drawn from the file
        ord.setlCPSQFT(curProd.getLaborRate());
        ord.setMaterialCost(ord.getArea() * ord.getcPSQFT());
        ord.setLaborCost(ord.getArea() * ord.getlCPSQFT());

//taxes section
        ord.setTaxRate((float) taxMap.get(ord.getState()).getTaxRate());
        ord.setTax((ord.getLaborCost() + ord.getMaterialCost()) * ord.getTaxRate() / 100);

        ord.setTotal(ord.getLaborCost() + ord.getMaterialCost() + ord.getTax()); //calculates total 

        return ord;

    }

}
