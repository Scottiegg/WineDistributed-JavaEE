/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.InvDTO;
import entity.WineDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author garto
 */
@Remote
public interface InventoryFacadeRemote {

    boolean createInventoryItem(InvDTO invDTO);

    List<InvDTO> findByWine(WineDTO wineDTO);
    
}
