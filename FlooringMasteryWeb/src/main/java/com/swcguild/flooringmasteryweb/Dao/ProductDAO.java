/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryweb.Dao;


import com.swcguild.flooringmasteryweb.Model.Product;
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
public class ProductDAO {
     
    private static final String  SQL_SELECT_PRODUCT =
           "SELECT * FROM taxes WHERE prod_type = ?";
    
    private static final String SQL_SELECT_ALL_PRODUCTS =
           "SELECT * FROM products";
    
    private static final String SQL_SELECT_PRODUCT_TYPE=
            "SELECT prod_type FROM products";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
          this.jdbcTemplate = jdbcTemplate;
    }
    
  
    //returns 
    public Product returnProduct(String product){
        try{
            Product nc= jdbcTemplate.queryForObject(SQL_SELECT_PRODUCT,new ProductMapper(), product);
            return nc;
        }catch(EmptyResultDataAccessException ex){
            //there were null results
            //wee just want to return null in this cass
            return null;
        }
        
        
    }
    //returns an array of all the product types
    public Product[] listProducts(){
        List<Product> cList = jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS,new ProductMapper());
        return cList.toArray(new Product[0]);
        
    }
    //returns an array containing all the product types available
    public String[] listProductTypes(){
        List<String> cList = jdbcTemplate.query(SQL_SELECT_PRODUCT_TYPE,new ProductTypeMapper());
        return cList.toArray(new String[0]);
    }
    
    private static final class ProductMapper implements ParameterizedRowMapper<Product>{

        //used to parse database results into a contact and returns a contact
      public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
          Product prod = new Product();
          prod.setProdId(rs.getInt("prod_id"));//the column name 
          prod.setProduct(rs.getString("prod_type"));//the column name 
          prod.setLaborRate(rs.getInt("prod_labor_cost"));//the column name 
           prod.setMaterialCost(rs.getInt("prod_cost"));//the column name 
          
          return prod;
      }        
    }
    
     private static final class ProductTypeMapper implements ParameterizedRowMapper<String>{

        //used to parse database results into a contact and returns a contact
      public String mapRow(ResultSet rs, int rowNum) throws SQLException {
          return rs.getString("prod_type");
      }        
    }
    
}
