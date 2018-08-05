/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author garto
 */
public class WineOrdersDTO {

    private WineDTO wine;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal totalCost;

    public WineOrdersDTO() {
        totalCost = BigDecimal.valueOf(0);
    }

    public WineOrdersDTO(WineDTO wine, int qty, BigDecimal unitPrice) {
        this.wine = wine;
        this.qty = qty;
        this.unitPrice = unitPrice;
        totalCost = BigDecimal.valueOf(0);
    }

    public boolean initialize() {
        try {
            if (totalCost.doubleValue() == 0) {
                BigDecimal q = BigDecimal.valueOf(this.qty);
                BigDecimal winePrice = q.multiply(this.unitPrice);
                totalCost = totalCost.add(winePrice);
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public WineDTO getWine() {
        return wine;
    }

    public void setWine(WineDTO wine) {
        this.wine = wine;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
