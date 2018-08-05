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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author garto
 */
@Entity
@Table(name = "WCD_INVENTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WcdInventory.findAll", query = "SELECT w FROM WcdInventory w")
    , @NamedQuery(name = "WcdInventory.findByWineId", query = "SELECT w FROM WcdInventory w WHERE w.wineId = :wineId")
    , @NamedQuery(name = "WcdInventory.findByStockQty", query = "SELECT w FROM WcdInventory w WHERE w.stockQty = :stockQty")
    , @NamedQuery(name = "WcdInventory.findByQtySold", query = "SELECT w FROM WcdInventory w WHERE w.qtySold = :qtySold")
    , @NamedQuery(name = "WcdInventory.findByCurrPrice", query = "SELECT w FROM WcdInventory w WHERE w.currPrice = :currPrice")})
public class WcdInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WINE_ID")
    private Integer wineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_QTY")
    private int stockQty;
    @Column(name = "QTY_SOLD")
    private Integer qtySold;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CURR_PRICE")
    private BigDecimal currPrice;
    @JoinColumn(name = "WINE_ID", referencedColumnName = "WINE_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private WcdWines wcdWines;

    public WcdInventory() {
    }

    public WcdInventory(Integer wineId) {
        this.wineId = wineId;
    }

    public WcdInventory(Integer wineId, int stockQty) {
        this.wineId = wineId;
        this.stockQty = stockQty;
    }

    public Integer getWineId() {
        return wineId;
    }

    public void setWineId(Integer wineId) {
        this.wineId = wineId;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public Integer getQtySold() {
        return qtySold;
    }

    public void setQtySold(Integer qtySold) {
        this.qtySold = qtySold;
    }

    public BigDecimal getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(BigDecimal currPrice) {
        this.currPrice = currPrice;
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
        hash += (wineId != null ? wineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WcdInventory)) {
            return false;
        }
        WcdInventory other = (WcdInventory) object;
        if ((this.wineId == null && other.wineId != null) || (this.wineId != null && !this.wineId.equals(other.wineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WcdInventory[ wineId=" + wineId + " ]";
    }

}
