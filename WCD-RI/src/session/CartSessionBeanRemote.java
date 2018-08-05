/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItem;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author garto
 */
@Remote
public interface CartSessionBeanRemote {

    boolean AddItemToCart(CartItem item);

    List<CartItem> getCart();

    boolean deleteCartItem(CartItem item);

    void clearCart();
    
}
