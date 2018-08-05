/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author garto
 */
@Entity
@Table(name = "WCD_WINE_ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WcdWineOrders.findAll", query = "SELECT w FROM WcdWineOrders w")
    , @NamedQuery(name = "WcdWineOrders.findByOrderId", query = "SELECT w FROM WcdWineOrders w WHERE w.wcdWineOrdersPK.orderId = :orderId")
    , @NamedQuery(name = "WcdWineOrders.findByWineId", query = "SELECT w FROM WcdWineOrders w WHERE w.wcdWineOrdersPK.wineId = :wineId")
    , @NamedQuery(name = "WcdWineOrders.findByQty", query = "SELECT w FROM WcdWineOrders w WHERE w.qty = :qty")
    , @NamedQuery(name = "WcdWineOrders.findByUnitPrice", query = "SELECT w FROM WcdWineOrders w WHERE w.unitPrice = :unitPrice")})
public class WcdWineOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WcdWineOrdersPK wcdWineOrdersPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private int qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WcdOrders wcdOrders;
    @JoinColumn(name = "WINE_ID", referencedColumnName = "WINE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private WcdWines wcdWines;

    public WcdWineOrders() {
    }

    public WcdWineOrders(WcdWineOrdersPK wcdWineOrdersPK) {
        this.wcdWineOrdersPK = wcdWineOrdersPK;
    }

    public WcdWineOrders(WcdWineOrdersPK wcdWineOrdersPK, int qty, BigDecimal unitPrice) {
        this.wcdWineOrdersPK = wcdWineOrdersPK;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public WcdWineOrders(int orderId, int wineId) {
        this.wcdWineOrdersPK = new WcdWineOrdersPK(orderId, wineId);
    }

    public WcdWineOrdersPK getWcdWineOrdersPK() {
        return wcdWineOrdersPK;
    }

    public void setWcdWineOrdersPK(WcdWineOrdersPK wcdWineOrdersPK) {
        this.wcdWineOrdersPK = wcdWineOrdersPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public WcdOrders getWcdOrders() {
        return wcdOrders;
    }

    public void setWcdOrders(WcdOrders wcdOrders) {
        this.wcdOrders = wcdOrders;
    }

    public WcdWines getWcdWines() {
        return wcdWines;
    }

    public void setWcdWines(WcdWines wcdWines) {
        this.wcdWines = wcdWines;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wcdWineOrdersPK != null ? wcdWineOrdersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WcdWineOrders)) {
            return false;
        }
        WcdWineOrders other = (WcdWineOrders) object;
        if ((this.wcdWineOrdersPK == null && other.wcdWineOrdersPK != null) || (this.wcdWineOrdersPK != null && !this.wcdWineOrdersPK.equals(other.wcdWineOrdersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WcdWineOrders[ wcdWineOrdersPK=" + wcdWineOrdersPK + " ]";
    }
    
}
