/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author saurabh
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CardDataBean {

    @JsonProperty("dynamic-data-master-id")
    private Integer dynamicDataMasterId;
    @JsonProperty("company-name")
    private String companyName;
    @JsonProperty("agency-name")
    private String param1;
    @JsonProperty("rating")
    private String param2;
    @JsonProperty("param3")
    private String param3;
    @JsonProperty("param4")
    private String param4;
    @JsonProperty("param5")
    private String param5;
    @JsonProperty("param6")
    private String param6;
    @JsonProperty("param7")
    private String param7;
    @JsonProperty("param8")
    private String param8;
    @JsonProperty("param9")
    private String param9;
    @JsonProperty("amount")
    private String param10;
    @JsonProperty("converted-date")
    private String param11;
    @JsonProperty("param12")
    private String param12;
    @JsonProperty("param13")
    private String param13;
    @JsonProperty("param14")
    private String param14;
    @JsonProperty("originale-date")
    private String param15;
    @JsonProperty("param16")
    private String param16;
    @JsonProperty("param17")
    private String param17;
    @JsonProperty("param18")
    private String param18;
    @JsonProperty("param19")
    private String param19;
    @JsonProperty("param20")
    private String param20;
    @JsonProperty("list-master-id")
    private Integer listMasterId;
    @JsonProperty("company-master-id")
    private Integer companyMasterId;
    @JsonProperty("company-mapping-id")
    private Integer companyMappingId;
    @JsonProperty("company-mapping-status")
    private Integer companyMappingStatus;
    @JsonProperty("last-modified")
    private String lastModified;
    @JsonProperty("created-date")
    private String createdDate;

    public Integer getDynamicDataMasterId() {
        return dynamicDataMasterId;
    }

    public void setDynamicDataMasterId(Integer dynamicDataMasterId) {
        this.dynamicDataMasterId = dynamicDataMasterId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    public String getParam7() {
        return param7;
    }

    public void setParam7(String param7) {
        this.param7 = param7;
    }

    public String getParam8() {
        return param8;
    }

    public void setParam8(String param8) {
        this.param8 = param8;
    }

    public String getParam9() {
        return param9;
    }

    public void setParam9(String param9) {
        this.param9 = param9;
    }

    public String getParam10() {
        return param10;
    }

    public void setParam10(String param10) {
        this.param10 = param10;
    }

    public String getParam11() {
        return param11;
    }

    public void setParam11(String param11) {
        this.param11 = param11;
    }

    public String getParam12() {
        return param12;
    }

    public void setParam12(String param12) {
        this.param12 = param12;
    }

    public String getParam13() {
        return param13;
    }

    public void setParam13(String param13) {
        this.param13 = param13;
    }

    public String getParam14() {
        return param14;
    }

    public void setParam14(String param14) {
        this.param14 = param14;
    }

    public String getParam15() {
        return param15;
    }

    public void setParam15(String param15) {
        this.param15 = param15;
    }

    public String getParam16() {
        return param16;
    }

    public void setParam16(String param16) {
        this.param16 = param16;
    }

    public String getParam17() {
        return param17;
    }

    public void setParam17(String param17) {
        this.param17 = param17;
    }

    public String getParam18() {
        return param18;
    }

    public void setParam18(String param18) {
        this.param18 = param18;
    }

    public String getParam19() {
        return param19;
    }

    public void setParam19(String param19) {
        this.param19 = param19;
    }

    public String getParam20() {
        return param20;
    }

    public void setParam20(String param20) {
        this.param20 = param20;
    }

    public Integer getListMasterId() {
        return listMasterId;
    }

    public void setListMasterId(Integer listMasterId) {
        this.listMasterId = listMasterId;
    }

    public Integer getCompanyMasterId() {
        return companyMasterId;
    }

    public void setCompanyMasterId(Integer companyMasterId) {
        this.companyMasterId = companyMasterId;
    }

    public Integer getCompanyMappingId() {
        return companyMappingId;
    }

    public void setCompanyMappingId(Integer companyMappingId) {
        this.companyMappingId = companyMappingId;
    }

    public Integer getCompanyMappingStatus() {
        return companyMappingStatus;
    }

    public void setCompanyMappingStatus(Integer companyMappingStatus) {
        this.companyMappingStatus = companyMappingStatus;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
