/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryweb.Dao;


import com.swcguild.flooringmasteryweb.Model.Tax;
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
public class TaxesDAO {
    
    
    private static final String  SQL_SELECT_TAX_RATE =
           "SELECT tax_rate FROM taxes WHERE tax_state = ?";
    
    private static final String SQL_SELECT_ALL_TAXES =
           "SELECT * FROM taxes";
    
    private static final String SQL_SELECT_TAX_STATES=
            "SELECT tax_state FROM taxes";
    
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
          this.jdbcTemplate = jdbcTemplate;
    }
    
    
    
    
    
    //returns 
    public double getTaxRate(String state){
        try{
            Tax nc= jdbcTemplate.queryForObject(SQL_SELECT_TAX_RATE,new TaxMapper(), state);
            return nc.getTaxRate();
        }catch(EmptyResultDataAccessException ex){
            //there were null results
            //wee just want to return null in this cass
            return -1;
        }
        
    }
    
    public String[] getTaxStates(){
        List<String> cList = jdbcTemplate.query(SQL_SELECT_TAX_STATES,new TaxStateMapper());
        return cList.toArray(new String[0]);
    }

    public Tax[] getTaxes() {
        List<Tax> cList = jdbcTemplate.query(SQL_SELECT_ALL_TAXES,new TaxMapper());
        return cList.toArray(new Tax[0]);
        
    }

    //unneeded
//    public void setTaxes(HashMap<String,Float> taxes) {
//        this.taxes = taxes;
//    }
    
    private static final class TaxMapper implements ParameterizedRowMapper<Tax>{

        //used to parse database results into a contact and returns a contact
      public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
          Tax tax = new Tax();
          tax.setTaxId(rs.getInt("tax_id"));//the column name 
          tax.setTaxState(rs.getString("tax_state"));//the column name 
          tax.setTaxRate(rs.getInt("tax_rate"));//the column name 
          
          return tax;
      }        
    }
     private static final class TaxStateMapper implements ParameterizedRowMapper<String>{

        //used to parse database results into a contact and returns a contact
      public String mapRow(ResultSet rs, int rowNum) throws SQLException {
          return rs.getString("tax_state");
      }        
    }
    
}
