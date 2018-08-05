/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author garto
 */
public class CartItem extends entity.InvDTO {

    private int qtyOrdered;

    public CartItem() {
    }
    
    public CartItem(InvDTO invDTO) {
        super.setWine(invDTO.getWine());
        super.setStockQty(invDTO.getStockQty());
        if (invDTO.getQtySold() != null) {
            super.setQtySold(invDTO.getQtySold());
        }
        super.setCurrentPrice(invDTO.getCurrentPrice());
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(int qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }
}
