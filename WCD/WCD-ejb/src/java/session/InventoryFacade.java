/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.InvDTO;
import entity.WcdInventory;
import entity.WcdWines;
import entity.WineDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author garto
 */
@Stateless
public class InventoryFacade implements InventoryFacadeRemote, InventoryFacadeLocal {

    @PersistenceContext(unitName = "WCD-ejbPU")
    private EntityManager em;

    @Override
    public boolean changeStockLevel(int wineId, int orderQty) {
        WcdInventory item = findByWineId(wineId);
        int qtySold = item.getQtySold() + orderQty;
        int stockQty = item.getStockQty() - orderQty;
        try {
            int updatedEntries = em.createQuery("UPDATE WcdInventory i SET i.qtySold = :qtySold WHERE i.wineId = :wineId")
                    .setParameter("qtySold", qtySold)
                    .setParameter("wineId", wineId)
                    .executeUpdate();
            if (updatedEntries != 1) {
                throw new IllegalStateException("Too many fields updated");
            }
            updatedEntries = em.createQuery("UPDATE WcdInventory i SET i.stockQty = :stockQty WHERE i.wineId = :wineId")
                    .setParameter("stockQty", stockQty)
                    .setParameter("wineId", wineId)
                    .executeUpdate();
            if (updatedEntries != 1) {
                throw new IllegalStateException("Too many fields updated");
            }
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

    private WcdInventory findByWineId(int wineId) {
        return em.find(WcdInventory.class, wineId);
    }

    @Override
    public List<InvDTO> findByWine(WineDTO searchWine) {
        List<InvDTO> result = null;
        String queryStr = null;
        Map<String, String> wineValues = new HashMap<>();

        //***** create query string *******
        if (searchWine.getName() != null && !searchWine.getName().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.name LIKE :name";
            } else {
                queryStr += " AND w.name LIKE :name";
            }
            wineValues.put("name", searchWine.getName());
        }

        if (searchWine.getVariety() != null && !searchWine.getVariety().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.variety LIKE :variety";
            } else {
                queryStr += " AND w.variety LIKE :variety";
            }
            wineValues.put("variety", searchWine.getVariety());
        }

        if (searchWine.getVintage() != null && !searchWine.getVintage().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.vintage LIKE :vintage";
            } else {
                queryStr += " AND w.vintage LIKE :vintage";
            }
            wineValues.put("vintage", searchWine.getVintage());
        }

        if (searchWine.getVineyard() != null && !searchWine.getVineyard().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.vineyard LIKE :vineyard";
            } else {
                queryStr += " AND w.vineyard LIKE :vineyard";
            }
            wineValues.put("vineyard", searchWine.getVineyard());
        }

        if (searchWine.getRegion() != null && !searchWine.getRegion().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.region LIKE :region";
            } else {
                queryStr += " AND w.region LIKE :region";
            }
            wineValues.put("region", searchWine.getRegion());
        }

        if (searchWine.getStateOrigin() != null && !searchWine.getStateOrigin().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.stateOrigin LIKE :stateOrigin";
            } else {
                queryStr += " AND w.stateOrigin LIKE :stateOrigin";
            }
            wineValues.put("stateOrigin", searchWine.getStateOrigin());
        }

        if (searchWine.getCountryOrigin() != null && !searchWine.getCountryOrigin().equals("")) {
            if (queryStr == null) {
                queryStr = "SELECT w FROM WcdWines w WHERE w.countryOrigin LIKE :countryOrigin";
            } else {
                queryStr += " AND w.countyOrigin LIKE :countryOrigin";
            }
            wineValues.put("countryOrigin", searchWine.getCountryOrigin());
        }

        queryStr += " ORDER BY w.name";

        //***** get results *****
        try {
            //set params
            //Set<Parameter<?>> params = em.createQuery(queryStr).getParameters();
            Query query = em.createQuery(queryStr);
            for (Map.Entry<String, String> entry : wineValues.entrySet()) {
                query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
            }

            //get results
            List<WcdWines> r = query.getResultList();
            result = new ArrayList<>();

            // convert to dto
            if (!r.isEmpty()) {
                for (WcdWines wine : r) {
                    if (wine.getWcdInventory() != null) {
                        result.add(wineDAO2InvDTO(wine));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

        return result;
    }

    @Override
    public boolean createInventoryItem(InvDTO invDTO) {
        boolean result = false;
        WcdWines wine = wineDTO2DAO(invDTO);

        //create wine listing
        try {
            createWineListing(wine);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }

        int wineId = wine.getWineId();
        WcdInventory inventory = invDTO2DAO(wineId, invDTO);

        try {
            createInventoryListing(inventory);
            result = true;
        } catch (Exception ex) {
            //ex.printStackTrace(System.err);
        }

        return result;
    }

    private WcdInventory invDTO2DAO(int wineId, InvDTO invDTO) {
        WcdInventory inventory = new WcdInventory();
        //get newly created wine_id
        inventory.setWineId(wineId);

        inventory.setCurrPrice(invDTO.getCurrentPrice());
        inventory.setStockQty(invDTO.getStockQty());
        if (invDTO.getQtySold() == null) {
            inventory.setQtySold(0);
        } else {
            inventory.setQtySold(invDTO.getQtySold());
        }
        return inventory;
    }

    @Override
    public WineDTO wineDAO2WineDTO(WcdWines wine) {
        InvDTO invDTO = new InvDTO();
        WineDTO wineDTO = new WineDTO();
        wineDTO.setWineId(wine.getWineId());
        wineDTO.setName(wine.getName());
        wineDTO.setVariety(wine.getVariety());
        wineDTO.setVineyard(wine.getVineyard());
        wineDTO.setVintage(wine.getVintage());
        wineDTO.setRegion(wine.getRegion());
        if (wine.getStateOrigin() == null) {
            wineDTO.setStateOrigin(wine.getStateOrigin());
        }
        wineDTO.setCountryOrigin(wine.getCountryOrigin());

        return wineDTO;
    }

    private InvDTO wineDAO2InvDTO(WcdWines wine) {
        InvDTO invDTO = new InvDTO();
        WineDTO wineDTO = new WineDTO();
        wineDTO.setWineId(wine.getWineId());
        wineDTO.setName(wine.getName());
        wineDTO.setVariety(wine.getVariety());
        wineDTO.setVineyard(wine.getVineyard());
        wineDTO.setVintage(wine.getVintage());
        wineDTO.setRegion(wine.getRegion());
        if (wine.getStateOrigin() == null) {
            wineDTO.setStateOrigin(wine.getStateOrigin());
        }
        wineDTO.setCountryOrigin(wine.getCountryOrigin());

        invDTO.setWine(wineDTO);
        invDTO.setCurrentPrice(wine.getWcdInventory().getCurrPrice());
        invDTO.setQtySold(wine.getWcdInventory().getQtySold());
        invDTO.setStockQty(wine.getWcdInventory().getStockQty());

        return invDTO;
    }

    private WcdWines wineDTO2DAO(InvDTO invDTO) {
        WcdWines wine = new WcdWines();
        //set attributes
        //wine.setWineId(50);
        wine.setName(invDTO.getWine().getName());
        wine.setRegion(invDTO.getWine().getRegion());
        wine.setVariety(invDTO.getWine().getVariety());
        wine.setVineyard(invDTO.getWine().getVineyard());
        wine.setVintage(invDTO.getWine().getVintage());
        if (invDTO.getWine().getStateOrigin() != null) {
            wine.setStateOrigin(invDTO.getWine().getStateOrigin());
        }
        wine.setCountryOrigin(invDTO.getWine().getCountryOrigin());
        return wine;
    }

    private void createWineListing(WcdWines wine) {
        //em.getTransaction().begin();
        em.persist(wine);
        em.flush();
        //em.getTransaction().commit();
    }

    private void createInventoryListing(WcdInventory inventory) {
        //em.getTransaction().begin();
        em.persist(inventory);
        em.flush();
        //em.getTransaction().commit();
    }
}
