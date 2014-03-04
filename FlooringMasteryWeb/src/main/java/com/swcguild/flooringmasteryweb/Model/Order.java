/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swcguild.flooringmasteryweb.Model;

/**
 *
 * @author apprentice
 */
public class Order {
    private int orderNumber = 0;
    private String date = "";
    private String customerName ="";
    private String State ="";
    private float taxRate =0;
    private String productType = "";
    private float area = 0;
    private float cPSQFT = 0; 
    private float lCPSQFT= 0;
    private float materialCost=0;
    private float laborCost = 0; 
    private float tax = 0; 
    private float total= 0; 

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getcPSQFT() {
        return cPSQFT;
    }

    public void setcPSQFT(float cPSQFT) {
        this.cPSQFT = cPSQFT;
    }

    public float getlCPSQFT() {
        return lCPSQFT;
    }

    public void setlCPSQFT(float lCPSQFT) {
        this.lCPSQFT = lCPSQFT;
    }

    public float getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(float materialCost) {
        this.materialCost = materialCost;
    }

    public float getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(float laborCost) {
        this.laborCost = laborCost;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
    
    
}
