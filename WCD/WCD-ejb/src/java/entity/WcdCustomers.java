/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author garto
 */
@Entity
@Table(name = "WCD_CUSTOMERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WcdCustomers.findAll", query = "SELECT w FROM WcdCustomers w")
    , @NamedQuery(name = "WcdCustomers.findByUserId", query = "SELECT w FROM WcdCustomers w WHERE w.userId = :userId")
    , @NamedQuery(name = "WcdCustomers.findByName", query = "SELECT w FROM WcdCustomers w WHERE w.name = :name")
    , @NamedQuery(name = "WcdCustomers.findByCompany", query = "SELECT w FROM WcdCustomers w WHERE w.company = :company")
    , @NamedQuery(name = "WcdCustomers.findByPhone", query = "SELECT w FROM WcdCustomers w WHERE w.phone = :phone")
    , @NamedQuery(name = "WcdCustomers.findByEmail", query = "SELECT w FROM WcdCustomers w WHERE w.email = :email")
    , @NamedQuery(name = "WcdCustomers.findByAddr1", query = "SELECT w FROM WcdCustomers w WHERE w.addr1 = :addr1")
    , @NamedQuery(name = "WcdCustomers.findByAddr2", query = "SELECT w FROM WcdCustomers w WHERE w.addr2 = :addr2")
    , @NamedQuery(name = "WcdCustomers.findByAddrCity", query = "SELECT w FROM WcdCustomers w WHERE w.addrCity = :addrCity")
    , @NamedQuery(name = "WcdCustomers.findByAddrZipcode", query = "SELECT w FROM WcdCustomers w WHERE w.addrZipcode = :addrZipcode")
    , @NamedQuery(name = "WcdCustomers.findByAddrState", query = "SELECT w FROM WcdCustomers w WHERE w.addrState = :addrState")})
public class WcdCustomers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 30)
    @Column(name = "COMPANY")
    private String company;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(name = "PHONE")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 30)
    @Column(name = "ADDR_1")
    private String addr1;
    @Size(max = 30)
    @Column(name = "ADDR_2")
    private String addr2;
    @Size(max = 25)
    @Column(name = "ADDR_CITY")
    private String addrCity;
    @Size(max = 4)
    @Column(name = "ADDR_ZIPCODE")
    private String addrZipcode;
    @Size(max = 25)
    @Column(name = "ADDR_STATE")
    private String addrState;
    @Size(max = 64)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 9)
    @Column(name = "APP_GROUP")
    private String appGroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<WcdOrders> wcdOrdersCollection;

    public WcdCustomers() {
    }

    public WcdCustomers(Integer userId) {
        this.userId = userId;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getAddrZipcode() {
        return addrZipcode;
    }

    public void setAddrZipcode(String addrZipcode) {
        this.addrZipcode = addrZipcode;
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

    @XmlTransient
    public Collection<WcdOrders> getWcdOrdersCollection() {
        return wcdOrdersCollection;
    }

    public void setWcdOrdersCollection(Collection<WcdOrders> wcdOrdersCollection) {
        this.wcdOrdersCollection = wcdOrdersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WcdCustomers)) {
            return false;
        }
        WcdCustomers other = (WcdCustomers) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WcdCustomers[ userId=" + userId + " ]";
    }

}
