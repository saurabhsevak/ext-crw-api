/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.constants;

/**
 *
 * @author saurabh
 */
public class CorpositoryConstants {

    public final static Integer RESPONSE_STATUS_SUCCESS = 1;
    public final static Integer RESPONSE_STATUS_ERROR = 0;
    public static final String MYSQL_OPRAN_AND = " AND ";
    public static final String MYSQL_OPRAN_OR = " OR ";
    public static final String COMPANYNAME = "companyname";

    public enum WebApi {
        getlist, unmappeddatalist,getcompanyname_cart_data,getcompanyname_cart_details,mappdflink
    }

    public enum ListOfObjects {
        unique_agency_name
    }

    public enum ListOfAutoComplete {
        cart_data_company_name
    }

}
