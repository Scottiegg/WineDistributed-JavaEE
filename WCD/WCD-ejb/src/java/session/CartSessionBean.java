/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItem;
import entity.InvDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author garto
 */
@Stateful
public class CartSessionBean implements CartSessionBeanRemote {

    private List<CartItem> wines;

    public CartSessionBean() {
        wines = new ArrayList<>();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean AddItemToCart(CartItem item) {
        boolean result = false;
        try {
            for (InvDTO w : wines) {
                if (w.getWine().getWineId().equals(item.getWine().getWineId())) {
                    return false;
                }
            }
            result = wines.add(item);
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public void clearCart() {
        wines = null;
    }

    @Override
    public List<CartItem> getCart() {
        return wines;
    }

    @Remove
    public void remove() {
        wines = null;
    }

    @Override
    public boolean deleteCartItem(CartItem item) {
        try {
            for (int i = 0; i < wines.size(); i++) {
                if (wines.get(i).getWine().getWineId().equals(item.getWine().getWineId())) {
                    //item found, delete it
                    CartItem itemRemoved = wines.remove(i);

                    //return true if "item removed" has same wineId as "item to be removed (item)"
                    return itemRemoved.getWine().getWineId().equals(item.getWine().getWineId());
                }
            }
        } catch (Exception ex) {
        }

        return false;
    }

}
