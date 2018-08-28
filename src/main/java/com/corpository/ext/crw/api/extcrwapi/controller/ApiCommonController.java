/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.controller;

import com.corpository.ext.crw.api.extcrwapi.bean.MappingRequestBean;
import com.corpository.ext.crw.api.extcrwapi.bean.RequestedAutoCompleteKeyBean;
import com.corpository.ext.crw.api.extcrwapi.bean.SearchCompanyCartDetails;
import com.corpository.ext.crw.api.extcrwapi.bean.UnMappedDataRequestBean;
import com.corpository.ext.crw.api.extcrwapi.bean.WebApiCommonInputBean;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants.WebApi;
import com.corpository.ext.crw.api.extcrwapi.service.CartDataService;
import com.corpository.ext.crw.api.extcrwapi.service.CommonFillingService;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author saurabh
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiCommonController {

    private static final Logger LOGGER = LogManager.getLogger(ApiCommonController.class);
    @Autowired
    ReloadableResourceBundleMessageSource messageSource;
    @Autowired
    CommonFillingService commonFillingService;
    @Autowired
    CartDataService cartDataService;

    @RequestMapping(value = "/1.0", method = RequestMethod.GET)
    public Object getListMaster() {
        return "i am in api 1.0";
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object getListMaster(@RequestBody WebApiCommonInputBean inputBean) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);

            String request = inputBean.getRequest();
            if (Utils.isNullOrEmpty(request)) {
                return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("requesturlisnotset", new Object[]{"Request"}, LocaleContextHolder.getLocale()), null);
            } else {

                String[] url = request.split("/");
                String urlName = url[url.length - 1];
                WebApi webApi = WebApi.valueOf(urlName);
                LOGGER.debug("API Request for : " + urlName);
                if (webApi == null) {
                    return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{request}, LocaleContextHolder.getLocale()), null);
                } else {
                    switch (webApi) {
                        case getlist: {
                            return commonFillingService.getListMasters(mapper.convertValue(inputBean.getCommonInputBeanObject(), String.class));
                        }
                        case unmappeddatalist: {
                            return cartDataService.getUnMappedData(mapper.convertValue(inputBean.getCommonInputBeanObject(), UnMappedDataRequestBean.class));
                        }
                        case getcompanyname_cart_data: {
                            return commonFillingService.autoCompleteListMaster(mapper.convertValue(inputBean.getCommonInputBeanObject(), RequestedAutoCompleteKeyBean.class));
                        }
                        case getcompanyname_cart_details: {
                            return commonFillingService.autoCompleteListMaster(mapper.convertValue(inputBean.getCommonInputBeanObject(), SearchCompanyCartDetails.class));
                        }
                        case mappdflink: {
                            return cartDataService.mapCompany(mapper.convertValue(inputBean.getCommonInputBeanObject(), MappingRequestBean.class));
                        }

                    }
                    return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("apinotcoded", new Object[]{webApi}, LocaleContextHolder.getLocale()), null);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("Error in ApiCommonController: ", ex);
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("unknownmessage", null, LocaleContextHolder.getLocale()), null);
        }
    }

    @RequestMapping(value = "/otpmaster/lsit/", method = RequestMethod.POST)
    @ApiOperation(value = "Get list master with pagination", notes = "Get list master with pagination")
    public Object getOtpMaster(@RequestParam(name = "start", defaultValue = "0") Integer start,
            @RequestParam(name = "length", defaultValue = "10") Integer length,
            @RequestParam(name = "search[value]", required = false) String search,
            @RequestParam(name = "otptype", required = false, defaultValue = "0") Integer otpType
    ) throws Exception {
        return otpMasterService.getOtpMaster(start, length, search, otpType);
    }

}
