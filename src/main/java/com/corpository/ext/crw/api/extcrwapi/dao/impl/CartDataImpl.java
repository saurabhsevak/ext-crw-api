/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao.impl;

import com.corpository.ext.crw.api.extcrwapi.bean.CardDataBean;
import com.corpository.ext.crw.api.extcrwapi.bean.UnMappedDataRequestBean;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants;
import com.corpository.ext.crw.api.extcrwapi.constants.TableConstants;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.corpository.ext.crw.api.extcrwapi.dao.CartDataDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author saurabh
 */
@Repository
public class CartDataImpl implements CartDataDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<String> getUniqueAgencyName() {
        return jdbcTemplate.queryForList("SELECT distinct param1 FROM " + TableConstants.DB_CRW_EXT_CART_DATA, String.class);
    }
    
    @Override
    public List<String> getAutoList(String searchFor, String searchValue) {
        if (Utils.isNullOrEmpty(searchFor) || Utils.isNullOrEmpty(searchValue)) {
            return new ArrayList<String>();
        }
        String query = "";
        if (searchFor != null) {
            switch (searchFor) {
                case CorpositoryConstants.COMPANYNAME: {
                    query = "SELECT  company_name FROM " + TableConstants.DB_CRW_EXT_CART_DATA + " WHERE company_name LIKE '" + searchValue.replaceAll("'", "''") + "%'  ";
                }
                default: {
                    
                }
            }
        }
        return jdbcTemplate.queryForList(query, String.class);
    }
    
    @Override
    public List<CardDataBean> getUnMappedData(UnMappedDataRequestBean unMappedDataRequestBean) {
        StringBuilder queryBuilder = new StringBuilder()
                .append(" SELECT * FROM ")
                .append(TableConstants.DB_CRW_EXT_CART_DATA)
                .append(" cd  ");
        
        String agencyName = unMappedDataRequestBean.getAgencyname();
        String companyName = unMappedDataRequestBean.getCompanyName();
        String fromDate = unMappedDataRequestBean.getFromDate();
        String toDate = unMappedDataRequestBean.getToDate();
        Integer length = unMappedDataRequestBean.getLength();
        Integer start = unMappedDataRequestBean.getStart();
        
        Utils.appenderValue(queryBuilder, " ( cd.param20 IS NULL )  ", CorpositoryConstants.MYSQL_OPRAN_AND);
        
        if (!Utils.isNullOrEmpty(agencyName)) {
            Utils.appenderValue(queryBuilder, " ( cd.param1 = '" + agencyName + "' )  ", CorpositoryConstants.MYSQL_OPRAN_AND);
        }
        
        if (!Utils.isNullOrEmpty(companyName)) {
            Utils.appenderValue(queryBuilder, " ( cd.company_name LIKE '%" + companyName + "%' )  ", CorpositoryConstants.MYSQL_OPRAN_AND);
        }
        
        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()
                && toDate.equalsIgnoreCase(fromDate)) {
            Utils.appenderValue(queryBuilder,
                    " ( STR_TO_DATE(cd.param11, '%d/%m/%Y')=STR_TO_DATE('"
                    + fromDate
                    + "','%d/%m/%Y') OR ( STR_TO_DATE(cd.param15, '%m/%d/%Y')=STR_TO_DATE('" + fromDate + "','%m/%d/%Y') ) ) ", CorpositoryConstants.MYSQL_OPRAN_AND);
            
        } else {
            if (fromDate != null && !fromDate.isEmpty()) {
                Utils.appenderValue(queryBuilder,
                        " ( STR_TO_DATE(cd.param11, '%d/%m/%Y')>=STR_TO_DATE('"
                        + fromDate
                        + "','%d/%m/%Y') OR ( STR_TO_DATE(cd.param15, '%m/%d/%Y')>=STR_TO_DATE('" + fromDate + "','%m/%d/%Y') ) ) ", CorpositoryConstants.MYSQL_OPRAN_AND);
            }
            
            if (toDate != null && !toDate.isEmpty()) {
                Utils.appenderValue(queryBuilder,
                        " ( STR_TO_DATE(cd.param11, '%d/%m/%Y')<=STR_TO_DATE('"
                        + toDate
                        + "','%d/%m/%Y') OR ( STR_TO_DATE(cd.param15, '%m/%d/%Y')<=STR_TO_DATE('" + toDate + "','%m/%d/%Y') ) )  ", CorpositoryConstants.MYSQL_OPRAN_AND);
                
            }
        }
        
        queryBuilder.append(" LIMIT ").append(length).append(" OFFSET ").append(start);
        
        return jdbcTemplate.query(queryBuilder.toString(), new BeanPropertyRowMapper(CardDataBean.class));
    }
    
    @Override
    public Integer mapUrls(String crPdfLink, String ratingLink, Integer sourceId, Integer destId, String tablename) {       
        StringBuilder queryBuilder = new StringBuilder().append(" UPDATE  ").append(TableConstants.DB_CRW_EXT_CART_DATA).append(" cdata,(select rating_link,cr_pdf_link FROM  ").append(TableConstants.DB_CRW_EXT).append(".").append(tablename).append(" FROM WHERE id=?) as tmp ").append(" SET  cdata.param14=tmp.cr_pdf_link, cdata.param13=tmp.rating_link ,cdata.param20='Manually Mapped' WHERE cdata.id=? ");

        return jdbcTemplate.update(queryBuilder.toString(),sourceId,destId);
        
    }
    
}
