/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.WcdWines;
import entity.WineDTO;
import javax.ejb.Local;

/**
 *
 * @author garto
 */
@Local
public interface InventoryFacadeLocal {
    boolean changeStockLevel(int wineId, int orderQty);
    
    WineDTO wineDAO2WineDTO(WcdWines wine);
}
