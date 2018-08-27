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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDetailsBean {

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the ratingDate
     */
    public String getRatingDate() {
        return ratingDate;
    }

    /**
     * @param ratingDate the ratingDate to set
     */
    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    /**
     * @return the ratingLink
     */
    public String getRatingLink() {
        return ratingLink;
    }

    /**
     * @param ratingLink the ratingLink to set
     */
    public void setRatingLink(String ratingLink) {
        this.ratingLink = ratingLink;
    }

    /**
     * @return the crPdfLink
     */
    public String getCrPdfLink() {
        return crPdfLink;
    }

    /**
     * @param crPdfLink the crPdfLink to set
     */
    public void setCrPdfLink(String crPdfLink) {
        this.crPdfLink = crPdfLink;
    }

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("parent-id")
    private String parentId;
    @JsonProperty("company-name")
    private String companyName;
    @JsonProperty("rating-date")
    private String ratingDate;
    @JsonProperty("rating-link")
    private String ratingLink;
    @JsonProperty("cr-pdf-link")
    private String crPdfLink;

    

}
