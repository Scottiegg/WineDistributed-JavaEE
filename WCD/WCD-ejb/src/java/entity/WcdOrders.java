/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author garto
 */
@Entity
@Table(name = "WCD_ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WcdOrders.findAll", query = "SELECT w FROM WcdOrders w")
    , @NamedQuery(name = "WcdOrders.findByOrderId", query = "SELECT w FROM WcdOrders w WHERE w.orderId = :orderId")
    , @NamedQuery(name = "WcdOrders.findByOrderDate", query = "SELECT w FROM WcdOrders w WHERE w.orderDate = :orderDate")
    , @NamedQuery(name = "WcdOrders.findByDelDate", query = "SELECT w FROM WcdOrders w WHERE w.delDate = :delDate")})
public class WcdOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar orderDate;
    @Column(name = "DEL_DATE")
    @Temporal(TemporalType.DATE)
    private Date delDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wcdOrders")
    private Collection<WcdWineOrders> wcdWineOrdersCollection;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private WcdCustomers user;

    public WcdOrders() {
    }

    public WcdOrders(Integer orderId) {
        this.orderId = orderId;
    }

    public WcdOrders(Integer orderId, Calendar orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    @XmlTransient
    public Collection<WcdWineOrders> getWcdWineOrdersCollection() {
        return wcdWineOrdersCollection;
    }

    public void setWcdWineOrdersCollection(Collection<WcdWineOrders> wcdWineOrdersCollection) {
        this.wcdWineOrdersCollection = wcdWineOrdersCollection;
    }

    public WcdCustomers getUser() {
        return user;
    }

    public void setUser(WcdCustomers user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WcdOrders)) {
            return false;
        }
        WcdOrders other = (WcdOrders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WcdOrders[ orderId=" + orderId + " ]";
    }
    
}
