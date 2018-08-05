/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItem;
import entity.OrderItem;
import entity.WcdCustomers;
import entity.WcdOrders;
import entity.WcdWineOrders;
import entity.WcdWineOrdersPK;
import entity.WineDTO;
import entity.WineOrdersDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author garto
 */
@Stateless
public class OrderFacade implements OrderFacadeRemote {

    @EJB
    private InventoryFacadeLocal inventoryFacade;

    @PersistenceContext(unitName = "WCD-ejbPU")
    private EntityManager em;

    @Override
    public List<OrderItem> findOrders(int userId) {
        try {
            WcdCustomers cust = findCustomer(userId);
            Collection<WcdOrders> orders = cust.getWcdOrdersCollection();
            return this.orderDAO2OrderItemList(orders);
        } catch (Exception ex) {
            return null;
        }
    }

    private WcdCustomers findCustomer(int custId) {
        return em.find(WcdCustomers.class, custId);
    }

    private List<OrderItem> orderDAO2OrderItemList(Collection<WcdOrders> orders) {
        List<OrderItem> result = new ArrayList<>();
        
        // iterate list of orders
        for (WcdOrders ord: orders) {
            List<WineOrdersDTO> wol = new ArrayList<>();
            
            // iterate list of wines in each order
            for (WcdWineOrders wineOrder: ord.getWcdWineOrdersCollection()) {
                WineOrdersDTO wineOrderDTO = new WineOrdersDTO();
                
                //get wine
                WineDTO wine = inventoryFacade.wineDAO2WineDTO(wineOrder.getWcdWines());
                wineOrderDTO.setWine(wine);
                
                //get attributes
                wineOrderDTO.setQty(wineOrder.getQty());
                wineOrderDTO.setUnitPrice(wineOrder.getUnitPrice());
                
                wol.add(wineOrderDTO);
            }
            
            OrderItem orderItem = new OrderItem(ord.getOrderDate(), ord.getDelDate(), wol);
            
            result.add(orderItem);
        }
        return result;
    }

    @Override
    public boolean createOrder(List<CartItem> cart, int custId) {
        WcdOrders order = createOrderDAO(custId);
        try {

            persistOrder(order);
        } catch (Exception ex) {
        }

        int orderId = order.getOrderId();

        try {
            for (CartItem ci : cart) {
                WcdWineOrders wineOrder = this.cartItem2WineOrderDAO(ci, orderId);
                this.persistWineOrder(wineOrder);
                this.inventoryFacade.changeStockLevel(ci.getWine().getWineId(), ci.getQtyOrdered());
            }
            return true;
        } catch (Exception ex) {

        }
        return false;
    }

    private void persistOrder(WcdOrders order) {
        em.persist(order);
        em.flush();
    }

    private void persistWineOrder(WcdWineOrders wineOrder) {
        em.persist(wineOrder);
        em.flush();
    }

    private WcdWineOrders cartItem2WineOrderDAO(CartItem ci, int orderId) {
        WcdWineOrders wineOrder = new WcdWineOrders();
        wineOrder.setQty(ci.getQtyOrdered());
        wineOrder.setUnitPrice(ci.getCurrentPrice());

        WcdWineOrdersPK pk = new WcdWineOrdersPK();
        pk.setOrderId(orderId);
        pk.setWineId(ci.getWine().getWineId());
        wineOrder.setWcdWineOrdersPK(pk);

//        WcdOrders order = new WcdOrders(orderId);
//        wineOrder.setWcdOrders(order);
//        WcdWines wine = new WcdWines(ci.getWine().getWineId());
//        wineOrder.setWcdWines(wine);
        return wineOrder;
    }

    private WcdOrders createOrderDAO(int userId) {
        WcdOrders result = new WcdOrders();
        TimeZone tz = TimeZone.getTimeZone("Australia/Melbourne");
        Calendar orderDate = Calendar.getInstance(tz);
        result.setOrderDate(orderDate);

        Calendar delDate = Calendar.getInstance(tz);
        // allow 5 days for delivery
        delDate.add(Calendar.DATE, 5);

        // if proposed del date is weekend, make it two days later.
        int dayOfWeek = orderDate.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.SATURDAY:
                // Saturday so skip to Monday
                delDate.add(Calendar.DATE, 2);
                break;
            case Calendar.SUNDAY:
                // Sunday so skip to Tuesday
                delDate.add(Calendar.DATE, 2);
                break;
        }

        result.setDelDate(delDate.getTime());
        WcdCustomers cust = new WcdCustomers(userId);
        result.setUser(cust);

        return result;
    }
}
