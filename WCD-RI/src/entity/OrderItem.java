/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author garto
 */
public class OrderItem extends entity.OrderDTO {

    String orderDateStr;
    String delDateStr;
    BigDecimal totalPrice;

    public OrderItem(Calendar orderDate, Date delDate, List<WineOrdersDTO> wines) {
        super(orderDate, delDate, wines);
        totalPrice = BigDecimal.valueOf(0);
        Date d = orderDate.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            orderDateStr = format.format(d);
            delDateStr = format.format(delDate);
        } catch (Exception ex) {

        }
    }

    public boolean initialize() {

        try {
            for (WineOrdersDTO wineOrd : this.getWines()) {
                BigDecimal qty = BigDecimal.valueOf(wineOrd.getQty());
                BigDecimal winePrice = qty.multiply(wineOrd.getUnitPrice());
                totalPrice = totalPrice.add(winePrice);
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }

    public String getDelDateStr() {
        return delDateStr;
    }

    public void setDelDateStr(String delDateStr) {
        this.delDateStr = delDateStr;
    }
}
