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
public class CustomerDTO {
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

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String company, String phone, String email, String addr1, String addr2, String addrCity, String addrZip, String addrState, String password) {
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addrCity = addrCity;
        this.addrZip = addrZip;
        this.addrState = addrState;
        this.password = password;
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
}
