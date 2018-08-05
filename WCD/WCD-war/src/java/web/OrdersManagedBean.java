/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.OrderItem;
import entity.WineOrdersDTO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import session.CustomerFacadeRemote;
import session.OrderFacadeRemote;

/**
 *
 * @author garto
 */
@Named(value = "ordersManagedBean")
@SessionScoped
public class OrdersManagedBean implements Serializable {

    @EJB
    private CustomerFacadeRemote customerFacade;

    @EJB
    private OrderFacadeRemote orderFacade;

    // for orders.xhtml
    List<OrderItem> pastOrders;
    List<OrderItem> pendOrders;

    //for orderDetails
    List<WineOrdersDTO> currItem;

    /**
     * Creates a new instance of OrdersManagedBean
     */
    public OrdersManagedBean() {
        pastOrders = new ArrayList<>();
        pendOrders = new ArrayList<>();
        currItem = new ArrayList<>();
    }

    public String showOrders() {
        String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        int userId = customerFacade.email2UserId(email);
        if (userId != 0) {
            pendOrders = orderFacade.findOrders(userId);
            for (OrderItem oi : pastOrders) {
                oi.initialize();
            }
            for (OrderItem oi : pendOrders) {
                oi.initialize();
            }
        }
        return "orders";
    }

    public String showOrderDetails(OrderItem item) {
        currItem = item.getWines();
        for (WineOrdersDTO wine : currItem) {
            wine.initialize();
        }
        return "orderDetails";
    }

    public List<WineOrdersDTO> getCurrItem() {
        return currItem;
    }

    public void setCurrItem(List<WineOrdersDTO> currItem) {
        this.currItem = currItem;
    }

    public List<OrderItem> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(List<OrderItem> pastOrders) {
        this.pastOrders = pastOrders;
    }

    public List<OrderItem> getPendOrders() {
        return pendOrders;
    }

    public void setPendOrders(List<OrderItem> pendOrders) {
        this.pendOrders = pendOrders;
    }

}
