/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CustomerDTO;
import javax.ejb.Remote;

/**
 *
 * @author garto
 */
@Remote
public interface CustomerFacadeRemote {

    boolean createNewCustomer(CustomerDTO cust);

    int email2UserId(String email);
    
}
