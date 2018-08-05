/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author garto
 */
public class OrderDTO {

    private String orderId;
    private String userId;
    private Calendar orderDate;
    private Date delDate;
    private List<WineOrdersDTO> wines;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String userId, Calendar orderDate, Date delDate, List<WineOrdersDTO> wines) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.delDate = delDate;
        this.wines = wines;
    }
    
    public OrderDTO(Calendar orderDate, Date delDate, List<WineOrdersDTO> wines) {
        this.orderDate = orderDate;
        this.delDate = delDate;
        this.wines = wines;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public List<WineOrdersDTO> getWines() {
        return wines;
    }

    public void setWines(List<WineOrdersDTO> wines) {
        this.wines = wines;
    }
}
