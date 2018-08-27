/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao;

import com.corpository.ext.crw.api.extcrwapi.bean.CardDataBean;
import com.corpository.ext.crw.api.extcrwapi.bean.UnMappedDataRequestBean;
import java.util.List;

/**
 *
 * @author saurabh
 */
public interface CartDataDao {

    public List<String> getUniqueAgencyName();

    public List<CardDataBean> getUnMappedData(UnMappedDataRequestBean unMappedDataRequestBean);

    public List<String> getAutoList(String searchFor, String searchValue);

    public Integer mapUrls(String crPdfLink, String ratingLink, Integer sourceId, Integer destId, String tablename);

    
}
