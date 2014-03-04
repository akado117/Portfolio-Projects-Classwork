package com.swcguild.flooringmasteryweb.controller;

import com.swcguild.flooringmasteryweb.Dao.OrderDAO;
import com.swcguild.flooringmasteryweb.Dao.ProductDAO;
import com.swcguild.flooringmasteryweb.Dao.TaxesDAO;
import com.swcguild.flooringmasteryweb.Model.Order;
import com.swcguild.flooringmasteryweb.Model.UserInput;
import com.swcguild.flooringmasteryweb.logic.Logic;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.aspectj.weaver.MemberImpl.method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class OrderController {

    OrderDAO ordDao;
    TaxesDAO taxDao;
    ProductDAO prodDao;
    String date = "01252014";

    public OrderController() {
    }

    @Inject
    public OrderController(OrderDAO oDao, ProductDAO pDao, TaxesDAO tDao) {
        this.ordDao = oDao;
        this.prodDao = pDao;
        this.taxDao = tDao;
    }

    @RequestMapping(value = "/displayOrders", method = RequestMethod.GET)
    public String displayOrders(Map<String, Object> model) {

        //put variablse for products, taxes, orders, and a date into the model for later access
        model.put("states", taxDao.getTaxStates());
        model.put("productTypes", prodDao.listProductTypes());
        model.put("date", date);
        model.put("dates", ordDao.listDates());
        model.put("orders", ordDao.listOrders(date));
        return "displayorder";
    }

    //add order to the database
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public String addOrder(Map<String, Object> model,
            HttpServletRequest req,//gets the response from the form in the form req.namefield
            HttpServletResponse res) throws IOException {

        Logic logic = new Logic(taxDao, prodDao);

        String name = req.getParameter("addName");
        int area = Integer.parseInt(req.getParameter("addAArea"));
        String date = req.getParameter("addDate");
        String state = req.getParameter("addState");
        String prodType = req.getParameter("addProductType");

        //add everything that is required to user
        UserInput user = new UserInput();
        user.setCustomerName(name);
        user.setArea(area);
        user.setDate(date);
        user.setProductType(prodType);
        user.setState(state);

        //make a new order after running through the logic class
        Order order = logic.calculate(user);

        //add order to the database
        ordDao.addOrder(order);

        //return and reload orders for the desired date        
        return "redirect:displayOrders";
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
    public String searchDate(Map<String, Object> model,
            HttpServletRequest req,//gets the response from the form in the form req.namefield
            HttpServletResponse res) {

        this.date = req.getParameter("addDateSearch");

        return "redirect:displayOrders";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam("orderId") String id,
            Map<String, Object> model) {

        //delete the order number from memory
        ordDao.deleteOrder(Integer.parseInt(id));
        //needs to reset the orders that are in memory, so redirect is used
        return "redirect:displayOrders";
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public String updateOrderFormDisplay(@RequestParam("orderId") String id,
            Map<String, Object> model) {

        
        String[] states = taxDao.getTaxStates();
        String[] prods = prodDao.listProductTypes();
        
        //get the order number from the database and put it into the order object in the model map
        model.put("order", ordDao.getOrder(Integer.parseInt(id)));
        model.put("states",states);
        model.put("productTypes", prods);

        //bring up the edit order form
        return "editOrder";
    }
    
    @RequestMapping(value="/updateOrder", method=RequestMethod.POST)
    public String updateOrder(@ModelAttribute("order") Order order,
            Map<String,Object> model) throws IOException{
        
        Logic logic = new Logic(taxDao, prodDao);
        
        order = logic.calculate(order);
        //puts the order from the form into the database using the updateOrder method from the Dao
        ordDao.updateOrder(order);
        
        return "redirect:displayOrders";
    }

}
