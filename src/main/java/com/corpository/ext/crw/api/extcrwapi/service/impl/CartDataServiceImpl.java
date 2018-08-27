/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.service.impl;

import com.corpository.ext.crw.api.extcrwapi.bean.CardDataBean;
import com.corpository.ext.crw.api.extcrwapi.bean.MappingRequestBean;
import com.corpository.ext.crw.api.extcrwapi.bean.SearchCompanyCartDetails;
import com.corpository.ext.crw.api.extcrwapi.bean.UnMappedDataRequestBean;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants;
import com.corpository.ext.crw.api.extcrwapi.service.CartDataService;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import com.corpository.ext.crw.api.extcrwapi.dao.CartDataDao;
import com.corpository.ext.crw.api.extcrwapi.dao.RatingAgencyTableMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author saurabh
 */
@Service
public class CartDataServiceImpl implements CartDataService {

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    @Autowired
    private CartDataDao cartData;
    @Autowired
    private RatingAgencyTableMapping ratingAgencyTableMapping;

    @Override
    public Object getUnMappedData(UnMappedDataRequestBean unMappedDataRequestBean) {
        if (unMappedDataRequestBean == null) {
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("requesturlisnotset", new Object[]{"input para "}, LocaleContextHolder.getLocale()), null);
        } else {

            List<CardDataBean> unMappedData = cartData.getUnMappedData(unMappedDataRequestBean);
            int size = unMappedData.size();
            if (size == 0) {
                return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_SUCCESS, messageSource.getMessage("norecordfound", null, LocaleContextHolder.getLocale()), null);
            } else {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("unmapped_size", size);
                map.put("unmapped_data", unMappedData);
                return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_SUCCESS, messageSource.getMessage("recordfound", null, LocaleContextHolder.getLocale()), map);
            }
        }
    }

    @Override
    public Object mapCompany(MappingRequestBean mappingRequestBean) {
        if (mappingRequestBean == null) {
            return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("requesturlisnotset", new Object[]{"input para "}, LocaleContextHolder.getLocale()), null);
        } else {
            String crPdfLink = mappingRequestBean.getCrPdfLink();
            String ratingLink = mappingRequestBean.getRatingLink();
            Integer sourceId = mappingRequestBean.getSourceId();
            Integer destId = mappingRequestBean.getDestId();
            String tablename = ratingAgencyTableMapping.getTableName(mappingRequestBean.getRatingAgency());
            if (Utils.isNullOrEmpty(tablename)) {
                String key = "Table Not Found FOR keyName :" + mappingRequestBean.getRatingAgency() + ", So Key Name is not valid ";
                return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{key}, LocaleContextHolder.getLocale()), null);
            } else {
                if (sourceId <= 0 || destId <= 0) {
                    return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_ERROR, messageSource.getMessage("parameternotvalid", new Object[]{"sourceId Or DestId"}, LocaleContextHolder.getLocale()), null);
                } else {
                    cartData.mapUrls(crPdfLink, ratingLink, sourceId, destId, tablename);
                    return Utils.generateResponse(CorpositoryConstants.RESPONSE_STATUS_SUCCESS, messageSource.getMessage("successfullmapping", null, LocaleContextHolder.getLocale()), null);
                }
            }
        }
    }

}
