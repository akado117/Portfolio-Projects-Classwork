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
public class Tax {
    private int taxId;
    private String taxState;
    private float taxRate;

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public String getTaxState() {
        return taxState;
    }

    public void setTaxState(String taxState) {
        this.taxState = taxState;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }
    
}
