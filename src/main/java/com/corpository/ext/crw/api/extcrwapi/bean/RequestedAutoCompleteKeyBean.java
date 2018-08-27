/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author saurabh
 */
public class RequestedAutoCompleteKeyBean {

    @JsonProperty("key-name")
    private String keyName;
    @JsonProperty("search-for")
    private String searchFor;
    @JsonProperty("search-value")
    private String searchValue;

    /**
     * @return the keyName
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param keyName the keyName to set
     */
    public RequestedAutoCompleteKeyBean setKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }

    /**
     * @return the searchFor
     */
    public String getSearchFor() {
        return searchFor;
    }

    /**
     * @param searchFor the searchFor to set
     */
    public RequestedAutoCompleteKeyBean setSearchFor(String searchFor) {
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
    public RequestedAutoCompleteKeyBean setSearchValue(String searchValue) {
        this.searchValue = searchValue;
        return this;

    }

}
