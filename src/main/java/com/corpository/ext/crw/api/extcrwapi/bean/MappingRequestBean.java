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
public class MappingRequestBean {

    @JsonProperty("source-id")
    private Integer sourceId;
    @JsonProperty("rating_agency")
    private String ratingAgency;
    @JsonProperty("dest-id")
    private Integer destId;
    @JsonProperty("cr-pdf-link")
    private String crPdfLink;
    @JsonProperty("rating_link")
    private String ratingLink;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(String ratingAgency) {
        this.ratingAgency = ratingAgency;
    }

    public Integer getDestId() {
        return destId;
    }

    public void setDestId(Integer destId) {
        this.destId = destId;
    }

    public String getCrPdfLink() {
        return crPdfLink;
    }

    public void setCrPdfLink(String crPdfLink) {
        this.crPdfLink = crPdfLink;
    }

    public String getRatingLink() {
        return ratingLink;
    }

    public void setRatingLink(String ratingLink) {
        this.ratingLink = ratingLink;
    }

}
