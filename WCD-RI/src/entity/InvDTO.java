/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author garto
 */
public class InvDTO implements Serializable {

    private int stockQty;
    private Integer qtySold;
    private BigDecimal currentPrice;
    private WineDTO wine;

    public InvDTO() {
    }

    public InvDTO(int stockQty, Integer qtySold, BigDecimal currentPrice, WineDTO wine) {
        this.stockQty = stockQty;
        this.qtySold = qtySold;
        this.currentPrice = currentPrice;
        this.wine = wine;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public Integer getQtySold() {
        return qtySold;
    }

    public void setQtySold(Integer qtySold) {
        this.qtySold = qtySold;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public WineDTO getWine() {
        return wine;
    }

    public void setWine(WineDTO wine) {
        this.wine = wine;
    }
}
