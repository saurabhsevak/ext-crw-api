/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.service;

import com.corpository.ext.crw.api.extcrwapi.bean.RequestedAutoCompleteKeyBean;
import com.corpository.ext.crw.api.extcrwapi.bean.SearchCompanyCartDetails;

/**
 *
 * @author saurabh
 */
public interface CommonFillingService {

    Object getListMasters(String key);

    Object autoCompleteListMaster(RequestedAutoCompleteKeyBean requestedAutoCompleteKeyBean);

    Object autoCompleteListMaster(SearchCompanyCartDetails searchCompanyCartDetails);

}
