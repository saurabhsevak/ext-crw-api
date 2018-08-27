/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author saurabh
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchCompanyCartDetails {

    @JsonProperty("rating-agency")
    private String ratingAgency;
    @JsonProperty("search-for")
    private String searchFor;
    @JsonProperty("search-value")
    private String searchValue;

    /**
     * @return the searchFor
     */
    public String getSearchFor() {
        return searchFor;
    }

    /**
     * @param searchFor the searchFor to set
     */
    public SearchCompanyCartDetails setSearchFor(String searchFor) {
        this.searchFor = searchFor;
        return this;

    }

    /**
     * @return the searchValue
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     * @param searchValue the searchValue to set
     */
    public SearchCompanyCartDetails setSearchValue(String searchValue) {
        this.searchValue = searchValue;
        return this;

    }

    /**
     * @return the ratingAgency
     */
    public String getRatingAgency() {
        return ratingAgency;
    }

    /**
     * @param ratingAgency the ratingAgency to set
     */
    public void setRatingAgency(String ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

}
