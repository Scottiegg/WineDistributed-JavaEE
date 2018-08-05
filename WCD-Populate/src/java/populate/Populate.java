/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populate;

import entity.InvDTO;
import entity.WineDTO;
import java.math.BigDecimal;
import javax.ejb.EJB;
import session.InventoryFacadeRemote;

/**
 *
 * @author garto
 */
public class Populate {

    @EJB
    private static InventoryFacadeRemote inventoryFacade;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Populate pop = new Populate();

        pop.createWines();
    }
    
    public void createWines() {
        final String[] name = {"Flowers","Tango","Godly","Nemo","Giraffe"};
        final String[] region = {"Hunter Valley","Barossa","Yarra Valley","Coonawarra", "Goldfields"};
        final String[] variety = {"Shiraz","Cab Sav", "Chardonnay","Riesling","GSM"};
        final String[] vineyard = {"Big Wines Inc","Farmers Direct","Cidery Co", "Big Little Wines", "Best Beverages"};
        final String[] vintage = {"1990","1995","2000","2010","2017"};
        final String[] countryOrigin = {"Australia","Australia","Australia","Australia","Australia"};
        final int[] stockQty = {50,5,40,100,87};
        final double[] currPrice = {49.90, 20, 55.75, 15.99, 35};

        for (int i = 0; i < name.length; i++) {
            WineDTO wine = new WineDTO();
            InvDTO item = new InvDTO();

            wine.setName(name[i]);
            wine.setRegion(region[i]);
            wine.setVariety(variety[i]);
            wine.setVineyard(vineyard[i]);
            wine.setVintage(vintage[i]);
            wine.setCountryOrigin(countryOrigin[i]);
            item.setStockQty(stockQty[i]);
            item.setCurrentPrice(BigDecimal.valueOf(currPrice[i]));

            item.setWine(wine);

            if (createInventoryItem(item)) {
                System.out.println("Wine Created: " + wine.getName());
            } else {
                System.out.println("Fail");
            }
        }
    }

    public boolean createInventoryItem(InvDTO inv) {
        return inventoryFacade.createInventoryItem(inv);
    }
}
