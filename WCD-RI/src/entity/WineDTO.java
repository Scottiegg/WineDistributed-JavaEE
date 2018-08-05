/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author garto
 */
public class WineDTO implements Serializable {
    private Integer wineId;
    private String name;
    private String variety;
    private String vineyard;
    private String vintage;
    private String region;
    private String stateOrigin;
    private String countryOrigin;

    public WineDTO() {
    }

    public WineDTO(String name, String variety, String vineyard, String vintage, String region, String countryOrigin) {
        this.name = name;
        this.variety = variety;
        this.vineyard = vineyard;
        this.vintage = vintage;
        this.region = region;
        this.countryOrigin = countryOrigin;
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
}
