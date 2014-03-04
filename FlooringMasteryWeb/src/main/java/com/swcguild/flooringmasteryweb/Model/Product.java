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
public class Product {
    private int prodId;
    private String product;
    private float laborRate;
    private float materialCost;

    
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
    
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getLaborRate() {
        return laborRate;
    }

    public void setLaborRate(float laborRate) {
        this.laborRate = laborRate;
    }

    public float getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(float materialCost) {
        this.materialCost = materialCost;
    }
    
    
    
}
