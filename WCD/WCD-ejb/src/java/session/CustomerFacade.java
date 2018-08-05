/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerDTO;
import entity.WcdCustomers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author garto
 */
@Stateless
public class CustomerFacade implements CustomerFacadeRemote {

    private final String CUST_APP_GROUP = "APP-USERS";

    @PersistenceContext(unitName = "WCD-ejbPU")
    private EntityManager em;

    private WcdCustomers custDTO2DAO(CustomerDTO custDTO) {
        WcdCustomers result = new WcdCustomers();
        result.setName(custDTO.getName());
        if (custDTO.getCompany() != null) {
            result.setCompany(custDTO.getCompany());
        }
        result.setPhone(custDTO.getPhone());
        result.setEmail(custDTO.getEmail());
        result.setAddr1(custDTO.getAddr1());
        if (custDTO.getAddr2() != null) {
            result.setAddr2(custDTO.getAddr2());
        }
        result.setAddrCity(custDTO.getAddrCity());
        result.setAddrZipcode(custDTO.getAddrZip());
        result.setAddrState(custDTO.getAddrState());
        result.setPassword(custDTO.getPassword());
        result.setAppGroup(this.CUST_APP_GROUP);
        return result;
    }

    private void createCustomer(WcdCustomers cust) {
        em.persist(cust);
        em.flush();
    }

    private int countCustomerByEmail(String email) {
        List<WcdCustomers> results = em.createNamedQuery("WcdCustomers.findByEmail")
                .setParameter("email", email).getResultList();
        return results.size();
    }

    @Override
    public int email2UserId(String email) {
        if (email != null) {
            List<WcdCustomers> results = em.createNamedQuery("WcdCustomers.findByEmail")
                    .setParameter("email", email).getResultList();
            if (results.size() == 1) {
                // return string of user id
                return results.get(0).getUserId();
            }
        }
        return 0;
    }

    @Override
    public boolean createNewCustomer(CustomerDTO custDTO) {
        boolean result = false;
        WcdCustomers cust = this.custDTO2DAO(custDTO);
        int count = countCustomerByEmail(custDTO.getEmail());
        try {
            // If email doesn't exist
            if (count == 0) {
                createCustomer(cust);
                result = true;
            }
        } catch (Exception ex) {
            //do nothing
        }
        return result;
    }
}
