/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CartItem;
import entity.InvDTO;
import entity.WineDTO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import session.CartSessionBeanRemote;
import session.CustomerFacadeRemote;
import session.InventoryFacadeRemote;
import session.OrderFacadeRemote;

/**
 *
 * @author garto
 */
@Named(value = "winesManagedBean")
@SessionScoped
public class WinesManagedBean implements Serializable {

    @EJB
    private CustomerFacadeRemote customerFacade;

    @EJB
    private OrderFacadeRemote orderFacade;

    @EJB
    private CartSessionBeanRemote cartSessionBean;

    @EJB
    private InventoryFacadeRemote inventoryFacade;

    private String name;
    private String variety;
    private String vineyard;
    private String vintage;
    private String region;
    private String country_origin;

    // for searchWines.xhtml
    private List<InvDTO> wines;

    //for cart.xhtml
    private List<CartItem> cart;

    private String resultString;

    /**
     * Creates a new instance of WinesManagedBean
     */
    public WinesManagedBean() {
        wines = new ArrayList<>();
    }

    public String searchWines() {
        WineDTO wine = new WineDTO();
        wine.setName(name);
        wine.setVariety(variety);
        wine.setVineyard(vineyard);
        wine.setVintage(vintage);
        wine.setRegion(region);
        wine.setCountryOrigin(country_origin);
        wines = inventoryFacade.findByWine(wine);

        return "searchWines";
    }

    public String doCheckout() {
        if (cart.isEmpty()) {
            resultString = "Cart must have item(s) - return to menu instead";
            return "cart";
        }

        for (CartItem ci : cart) {
            try {
                if (ci.getQtyOrdered() > ci.getStockQty()) {
                    resultString = "Order quantity cannot exceed stock quantity";
                    return "cart";
                }
                if (ci.getQtyOrdered() <= 0) {
                    resultString = "Orders must have at least one item each - remove item if necessary";
                    return "cart";
                }
            } catch (NumberFormatException ex) {
                return "cart";
            }
        }
        String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        int userId = this.customerFacade.email2UserId(email);
        if (userId != 0) {
            if (!orderFacade.createOrder(cart, userId)) {
                resultString = "Error: Order cannot be created. Contact WCD directly.";
                return "cart";
            } else {
                //success clear cart
                cartSessionBean.clearCart();
            }

        } else {
            resultString = "Error: User cannot be found in system. Contact WCD directly.";
            return "cart";
        }

        return "checkoutResult";
    }

    public String addToCart(InvDTO item) {
        //String result = "failure";
        resultString = "Item could not be added. Item may be already in cart.";

        CartItem ci = new CartItem(item);

        if (cartSessionBean.AddItemToCart(ci)) {
            //result = "success";
            resultString = "Item has been added.";
        }

        return "searchWinesResult";
    }

    public String showCart() {
        cart = cartSessionBean.getCart();
        resultString = null;
        return "cart";
    }

    public String deleteItem(CartItem item) {
        cartSessionBean.deleteCartItem(item);

        return showCart();
    }

    public void validateStockQty(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String orderAmt = (String) newValue;

        UIInput component = (UIInput) componentToValidate.getAttributes().get("stockQty");
        String maxAmt = (String) component.getSubmittedValue();

        try {
            int orderAmtInt = Integer.parseInt(orderAmt);
            int maxAmtInt = Integer.parseInt(maxAmt);
            if (orderAmtInt > maxAmtInt) {
                FacesMessage message = new FacesMessage(
                        "Order cannot exceed stock left quantity");
                throw new ValidatorException(message);
            }
        } catch (NumberFormatException ex) {
            FacesMessage message = new FacesMessage(
                    "Order quantity must be a whole number");
            throw new ValidatorException(message);
        }
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public String getResultString() {
        return resultString;
    }

    public List<InvDTO> getWines() {
        return wines;
    }

    public void setWines(List<InvDTO> wines) {
        this.wines = wines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVineyard() {
        return vineyard;
    }

    public void setVineyard(String vineyard) {
        this.vineyard = vineyard;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry_origin() {
        return country_origin;
    }

    public void setCountry_origin(String country_origin) {
        this.country_origin = country_origin;
    }
}
