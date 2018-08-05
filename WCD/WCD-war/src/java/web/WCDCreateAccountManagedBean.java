/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import entity.CustomerDTO;
import javax.ejb.EJB;
import session.CustomerFacadeRemote;

@Named(value = "wcdCreateAccountManagedBean")
@RequestScoped
public class WCDCreateAccountManagedBean {

    @EJB
    private CustomerFacadeRemote customerFacade;

    private String name;
    private String company;
    private String phone;
    private String email;
    private String addr1;
    private String addr2;
    private String addrCity;
    private String addrZip;
    private String addrState;
    private String password;
    private String confirmPassword;
    private String resultofAction;

    public String getResultofAction() {
        return resultofAction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrZip() {
        return addrZip;
    }

    public void setAddrZip(String addrZip) {
        this.addrZip = addrZip;
    }

    public String getAddrState() {
        return addrState;
    }

    public void setAddrState(String addrState) {
        this.addrState = addrState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Creates a new instance of WCDCreateAccountManagedBean
     */
    public WCDCreateAccountManagedBean() {

    }

    public String createNewAccount() {
        String result = "failure";
        resultofAction = "not been created";

        CustomerDTO customer = new CustomerDTO();
        PasswordHasher ph = new PasswordHasher();

        customer.setName(name);
        if (company != null) {
            customer.setCompany(company);
        }
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddr1(addr1);
        if (addr2 != null) {
            customer.setAddr2(addr2);
        }
        customer.setAddrCity(addrCity);
        customer.setAddrZip(addrZip);
        customer.setAddrState(addrState);
        customer.setPassword(ph.hashPassword(password));

        //customer.setPassword(password);
        if (customerFacade.createNewCustomer(customer)) {
            result = "success";
            resultofAction = "been created";
        }
        return result;
    }

    public void validateNewPasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String newPwd = (String) newValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        if (!newPwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "New Password and Confirm New Password are not the same. They must be the same.");
            throw new ValidatorException(message);
        }
    }
}
