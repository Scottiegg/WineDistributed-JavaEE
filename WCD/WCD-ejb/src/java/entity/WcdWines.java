/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author garto
 */
@Entity
@Table(name = "WCD_WINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WcdWines.findAll", query = "SELECT w FROM WcdWines w")
    , @NamedQuery(name = "WcdWines.findByWineId", query = "SELECT w FROM WcdWines w WHERE w.wineId = :wineId")
    , @NamedQuery(name = "WcdWines.findByName", query = "SELECT w FROM WcdWines w WHERE w.name = :name")
    , @NamedQuery(name = "WcdWines.findByVariety", query = "SELECT w FROM WcdWines w WHERE w.variety = :variety")
    , @NamedQuery(name = "WcdWines.findByVineyard", query = "SELECT w FROM WcdWines w WHERE w.vineyard = :vineyard")
    , @NamedQuery(name = "WcdWines.findByVintage", query = "SELECT w FROM WcdWines w WHERE w.vintage = :vintage")
    , @NamedQuery(name = "WcdWines.findByRegion", query = "SELECT w FROM WcdWines w WHERE w.region = :region")
    , @NamedQuery(name = "WcdWines.findByStateOrigin", query = "SELECT w FROM WcdWines w WHERE w.stateOrigin = :stateOrigin")
    , @NamedQuery(name = "WcdWines.findByCountryOrigin", query = "SELECT w FROM WcdWines w WHERE w.countryOrigin = :countryOrigin")})
public class WcdWines implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "WINE_ID")
    private Integer wineId;
    @Size(max = 30)
    @Column(name = "NAME")
    private String name;
    @Size(max = 30)
    @Column(name = "VARIETY")
    private String variety;
    @Size(max = 30)
    @Column(name = "VINEYARD")
    private String vineyard;
    @Size(max = 4)
    @Column(name = "VINTAGE")
    private String vintage;
    @Size(max = 30)
    @Column(name = "REGION")
    private String region;
    @Size(max = 30)
    @Column(name = "STATE_ORIGIN")
    private String stateOrigin;
    @Size(max = 30)
    @Column(name = "COUNTRY_ORIGIN")
    private String countryOrigin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wcdWines")
    private Collection<WcdWineOrders> wcdWineOrdersCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "wcdWines")
    private WcdInventory wcdInventory;

    public WcdWines() {
    }

    public WcdWines(Integer wineId) {
        this.wineId = wineId;
    }

    public Integer getWineId() {
        return wineId;
    }

    public void setWineId(Integer wineId) {
        this.wineId = wineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVineyard() {
        return vineyard;
    }

    public void setVineyard(String vineyard) {
        this.vineyard = vineyard;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStateOrigin() {
        return stateOrigin;
    }

    public void setStateOrigin(String stateOrigin) {
        this.stateOrigin = stateOrigin;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    @XmlTransient
    public Collection<WcdWineOrders> getWcdWineOrdersCollection() {
        return wcdWineOrdersCollection;
    }

    public void setWcdWineOrdersCollection(Collection<WcdWineOrders> wcdWineOrdersCollection) {
        this.wcdWineOrdersCollection = wcdWineOrdersCollection;
    }

    public WcdInventory getWcdInventory() {
        return wcdInventory;
    }

    public void setWcdInventory(WcdInventory wcdInventory) {
        this.wcdInventory = wcdInventory;
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
        if (!(object instanceof WcdWines)) {
            return false;
        }
        WcdWines other = (WcdWines) object;
        if ((this.wineId == null && other.wineId != null) || (this.wineId != null && !this.wineId.equals(other.wineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WcdWines[ wineId=" + wineId + " ]";
    }
    
}
