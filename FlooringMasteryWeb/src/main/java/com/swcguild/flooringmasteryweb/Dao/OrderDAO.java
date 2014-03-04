/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasteryweb.Dao;

import com.swcguild.flooringmasteryweb.Model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author apprentice
 */
public class OrderDAO {

    private static final String SQL_INSERT_ORDER
            = "INSERT INTO orders (name, date, state, tax_rate,product_type,area,cpsqft,lcpsqft,material_cost,labor_cost,tax,total) "
            + "values (?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_DELETE_ORDER
            = "DELETE FROM orders WHERE order_id= ?";

    private static final String SQL_SELECT_ORDER
            = "SELECT * FROM orders WHERE order_id = ?";

    private static final String SQL_UPDATE_ORDER
            = "UPDATE orders SET name = ?, date = ?, state =?, tax_rate = ?, product_type = ?, area = ?, cpsqft = ?,"
            + " lcpsqft = ?, material_cost = ?, labor_cost = ?, tax =?, total = ? WHERE order_id = ?";

    private static final String SQL_SELECT_ALL_ORDERS
            = "SELECT * FROM orders WHERE date = ?";

    private static final String SQL_SELECT_ALL_DATES
            = "SELECT MIN(date) AS date FROM orders GROUP BY date";

    //required for spring framework to function
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     
    
      //returns an array containing all the product types available
    public String[] listDates(){
        List<String> cList = jdbcTemplate.query(SQL_SELECT_ALL_DATES,new DateMapper());
        return cList.toArray(new String[0]);
    }
    

    //determines the latest order number in the database
    public int highestOrderNumber() {

        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

    }

    //adds an order to the list
    public void addOrder(Order order) {
        jdbcTemplate.update(SQL_INSERT_ORDER,
                order.getCustomerName(),
                order.getDate(),
                order.getState(),
                order.getTaxRate(),
                order.getProductType(),
                order.getArea(),
                order.getcPSQFT(),
                order.getlCPSQFT(),
                order.getMaterialCost(),
                order.getLaborCost(),
                order.getTax(),
                order.getTotal());

        //add the order id to the order
        order.setOrderNumber(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));
    }

    //removes order from the list
    public void deleteOrder(int orderNum) {
        jdbcTemplate.update(SQL_DELETE_ORDER, orderNum);
    }

    //returns an order
    public Order getOrder(int orderNum) {
        try {

            Order nc = jdbcTemplate.queryForObject(SQL_SELECT_ORDER, new OrderMapper(), orderNum);

            //if the value doesn't exist null is returned instead
            if (nc.getCustomerName().equals("") || nc == null) {
                return null;
            }
            return nc;
        } catch (EmptyResultDataAccessException ex) {
            //there were null results
            //wee just want to return null in this cass
            return null;
        }
    }

    //converts all orders which are part of this OrderDAO object to a readable array of Order
    public Order[] listOrders(String date) {

        List<Order> list = jdbcTemplate.query(SQL_SELECT_ALL_ORDERS, new OrderMapper(), date);
        return list.toArray(new Order[0]);
    }

    public void updateOrder(Order order) {

        jdbcTemplate.update(SQL_UPDATE_ORDER,
                order.getCustomerName(),
                order.getDate(),
                order.getState(),
                order.getTaxRate(),
                order.getProductType(),
                order.getArea(),
                order.getcPSQFT(),
                order.getlCPSQFT(),
                order.getMaterialCost(),
                order.getLaborCost(),
                order.getTax(),
                order.getTotal(),
                order.getOrderNumber());

    }

    private static final class OrderMapper implements ParameterizedRowMapper<Order> {

        //used to parse database results into an order and returns an order
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order ord = new Order();
            ord.setOrderNumber(rs.getInt("order_id"));
            ord.setDate(rs.getString("date"));
            ord.setCustomerName(rs.getString("name"));
            ord.setState(rs.getString("state"));
            ord.setTaxRate(rs.getFloat("tax_rate"));
            ord.setProductType(rs.getString("product_type"));
            ord.setArea(rs.getFloat("area"));
            ord.setcPSQFT(rs.getFloat("cpsqft"));
            ord.setlCPSQFT(rs.getFloat("lcpsqft"));
            ord.setMaterialCost(rs.getFloat("material_cost"));
            ord.setLaborCost(rs.getFloat("labor_cost"));
            ord.setTax(rs.getFloat("tax"));
            ord.setTotal(rs.getFloat("total"));

            return ord;
        }
    }
    
    
    
   

    private static final class DateMapper implements ParameterizedRowMapper<String> {

        //used to parse database results into a contact and returns a contact
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String date = new String();
            return  rs.getString("date");

        }

    }

//    public void clearMap(){
//        
//        orders.clear();
//    } 
//    public String getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(String orderDate) {
//        this.orderDate = orderDate;
//    }
    //    //test to see if file exists 
//    public boolean doesFileExist() {
//        File f = new File("Orders_" + orderDate + ".txt");
//
//        if (f.exists()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    //gets highest order number-if no orders it returns 1 else it returns the highest order number
//    public int getHighestOrderNumber() {
//        Iterator<Integer> itr = null;
//        int highest = 0;
//        Set<Integer> set = orders.keySet();
//        if (!set.isEmpty()) {
//            itr = set.iterator();//will enable the ability to iterate through all the keys
//            while (itr.hasNext()) {
//                int holder = itr.next();
//                if (holder > highest) {//if highest is smaller than the iterator highest becomes the iterator
//                    highest = holder;
//                }
//            }
//            return highest;
//        } else {
//            return 1;
//        }
//
//    }
}
