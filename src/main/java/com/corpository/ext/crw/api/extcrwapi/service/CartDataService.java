/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.service;

import com.corpository.ext.crw.api.extcrwapi.bean.MappingRequestBean;
import com.corpository.ext.crw.api.extcrwapi.bean.UnMappedDataRequestBean;

/**
 *
 * @author saurabh
 */
public interface CartDataService {

     Object getUnMappedData(UnMappedDataRequestBean convertValue);

     Object mapCompany(MappingRequestBean mappingRequestBean);
    
}
