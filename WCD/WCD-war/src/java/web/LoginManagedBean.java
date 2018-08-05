/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author garto
 */
@Named(value = "loginManagedBean")
@Dependent
public class LoginManagedBean {

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }
    
        public String logoutResult() {
        // terminate the session by invalidating the session context
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (Exception ex) {
            // do nothing
            System.out.println("log out ...");
        }
        // terminate the session by invalidating the session context
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        System.out.println("log out ...");
        // terminate the user's login credentials
        return "logout";
    }
}
