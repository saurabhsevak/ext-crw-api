/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.service.impl;

import com.corpository.ext.crw.api.extcrwapi.bean.RequestedAutoCompleteKeyBean;
import com.corpository.ext.crw.api.extcrwapi.bean.SearchCompanyCartDetails;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants.ListOfAutoComplete;
import static com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants.ListOfAutoComplete.cart_data_company_name;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants.ListOfObjects;
import com.corpository.ext.crw.api.extcrwapi.service.CommonFillingService;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import com.corpository.ext.crw.api.extcrwapi.dao.CartDataDao;
import com.corpository.ext.crw.api.extcrwapi.dao.CartDetailsDao;
import com.corpository.ext.crw.api.extcrwapi.dao.RatingAgencyTableMapping;
import org.springframework.context.NoSuchMessageException;

/**
 *
 * @author saurabh
 */
@Service
public class CommonFillingServiceImpl implements CommonFillingService {

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    @Autowired
    private CartDataDao cartDataDao;
    @Autowired
    private RatingAgencyTableMapping ratingAgencyTableMapping;
    @Autowired
    private CartDetailsDao cartDetailsDao;

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CommonFillingServiceImpl.class);

    @Override
    public Object getListMasters(String key) {
        if (Utils.isNullOrEmpty(key)) {
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{key}, LocaleContextHolder.getLocale()), null);
        } else {
            List returnDataList = getList(key);
            return getResponseForList(returnDataList, key);
        }
    }

    private  Object getResponseForList(List returnDataList, String key) {
        int size = returnDataList.size();
        if (size == 0) {
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_SUCCESS, messageSource.getMessage("norecordfound", null, LocaleContextHolder.getLocale()), null);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key + "_size", size);
            map.put(key, returnDataList);
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_SUCCESS, messageSource.getMessage("recordfound", null, LocaleContextHolder.getLocale()), map);
        }
    }

    @Override
    public Object autoCompleteListMaster(SearchCompanyCartDetails searchCompanyCartDetails) {
        String keyName = searchCompanyCartDetails.getRatingAgency();
        String tablename = ratingAgencyTableMapping.getTableName(keyName);
        if (Utils.isNullOrEmpty(tablename) || Utils.isNullOrEmpty(searchCompanyCartDetails.getSearchFor()) || Utils.isNullOrEmpty(searchCompanyCartDetails.getSearchValue())) {
            String key = "";
            if (Utils.isNullOrEmpty(keyName)) {
                key = "Table Not Found FOR keyName :" + keyName + ", So Key Name is not valid ";
            } else if (Utils.isNullOrEmpty(searchCompanyCartDetails.getSearchFor())) {
                key = "search_for";
            } else {
                key = "search_value";
            }
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{key}, LocaleContextHolder.getLocale()), null);
        } else {
            List returnDataList = cartDetailsDao.getList(tablename,searchCompanyCartDetails.getSearchFor(),searchCompanyCartDetails.getSearchValue());
            return getResponseForList(returnDataList, keyName);
        }
    }

    @Override
    public Object autoCompleteListMaster(RequestedAutoCompleteKeyBean requestedAutoCompleteKeyBean) {

        String keyName = requestedAutoCompleteKeyBean.getKeyName();

        if (Utils.isNullOrEmpty(keyName) || Utils.isNullOrEmpty(requestedAutoCompleteKeyBean.getSearchFor()) || Utils.isNullOrEmpty(requestedAutoCompleteKeyBean.getSearchValue())) {
            String key = "";
            if (Utils.isNullOrEmpty(keyName)) {
                key = "key_name";
            } else if (Utils.isNullOrEmpty(requestedAutoCompleteKeyBean.getSearchFor())) {
                key = "search_for";
            } else {
                key = "search_value";
            }
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{key}, LocaleContextHolder.getLocale()), null);
        } else {

            ListOfAutoComplete listOfBeans = ListOfAutoComplete.valueOf(keyName);
            if (listOfBeans == null) {
                return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{keyName}, LocaleContextHolder.getLocale()), null);
            } else {
                List returnDataList = getList(requestedAutoCompleteKeyBean);
                return getResponseForList(returnDataList, keyName);
            }

        }
    }

    private List getList(String key) {
        List returnDataList = new LinkedList();
        ListOfObjects listOfBeans = ListOfObjects.valueOf(key);
        if (listOfBeans != null) {
            switch (listOfBeans) {
                case unique_agency_name: {
                    return cartDataDao.getUniqueAgencyName();
                }
            }
        }
        return returnDataList;
    }

    private List getList(RequestedAutoCompleteKeyBean requestedAutoCompleteKeyBean) {
        List returnDataList = new LinkedList();
        if (requestedAutoCompleteKeyBean != null) {
            String keyName = requestedAutoCompleteKeyBean.getKeyName();
            String searchFor = requestedAutoCompleteKeyBean.getSearchFor();
            String searchValue = requestedAutoCompleteKeyBean.getSearchValue();
            ListOfAutoComplete listOfBeans = ListOfAutoComplete.valueOf(keyName);
            switch (listOfBeans) {
                case cart_data_company_name: {
                    returnDataList = cartDataDao.getAutoList(searchFor, searchValue);
                }
            }
        }
        return returnDataList;
    }

}
