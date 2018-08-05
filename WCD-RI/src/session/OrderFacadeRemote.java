/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItem;
import entity.OrderItem;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author garto
 */
@Remote
public interface OrderFacadeRemote {

    boolean createOrder(List<CartItem> cart, int custId);

    List<OrderItem> findOrders(int userId);
    
}
