/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao.impl;

import com.corpository.ext.crw.api.extcrwapi.constants.TableConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.corpository.ext.crw.api.extcrwapi.dao.RatingAgencyTableMapping;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;

/**
 *
 * @author saurabh
 */
@Repository
public class RatingAgencyTableMappingDaoImpl implements RatingAgencyTableMapping {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String getTableName(String key) {
        if (Utils.isNullOrEmpty(key)) {
            return null;
        } else {
            return jdbcTemplate.queryForObject(" SELECT table_name FROM " + TableConstants.DB_CRW_EXT_RATING_AGENCY_TABLE_MAPPING + " WHERE Upper(trim(rating_agency_name))=? ", new Object[]{key.trim().toUpperCase()}, String.class);
        }
    }

}
