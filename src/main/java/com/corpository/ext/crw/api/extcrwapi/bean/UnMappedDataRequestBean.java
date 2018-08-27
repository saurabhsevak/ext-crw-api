/*
 * Corpository web-api version - 1.0
 * 
 * Created By : Vijay Gajera
 */
package com.corpository.ext.crw.api.extcrwapi.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class WebApiCommonInputBean.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnMappedDataRequestBean implements Serializable {

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the agencyname
     */
    public String getAgencyname() {
        return agencyname;
    }

    /**
     * @param agencyname the agencyname to set
     */
    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    /**
     * @return the fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * @param CompanyName the CompanyName to set
     */
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    /**
     * The Constant serialVersionUID.
     */
    private static long serialVersionUID = 1L;

    /**
     * The request.
     */
    @JsonProperty("agency-name")
    private String agencyname;
    @JsonProperty("from-date")
    private String fromDate;
    @JsonProperty("to-date")
    private String toDate;
    @JsonProperty("company-name")
    private String CompanyName;
    @JsonProperty("start")
    private Integer start=0;
    @JsonProperty("length")
    private Integer length=10;

}
