/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao.impl;

import com.corpository.ext.crw.api.extcrwapi.bean.CardDetailsBean;
import com.corpository.ext.crw.api.extcrwapi.constants.CorpositoryConstants;
import com.corpository.ext.crw.api.extcrwapi.constants.TableConstants;
import com.corpository.ext.crw.api.extcrwapi.dao.CartDetailsDao;
import com.corpository.ext.crw.api.extcrwapi.utility.Utils;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saurabh
 */
@Repository
public class CartDetailsDaoImpl implements CartDetailsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List getList(String tablename, String searchFor, String searchValue) {
        String query = "";
        if (searchFor != null) {
            switch (searchFor) {
                case CorpositoryConstants.COMPANYNAME: {
                    query = "SELECT id,parent_id,company_name,rating_date,rating_link,cr_pdf_link FROM " + TableConstants.DB_CRW_EXT + "." + tablename + " WHERE company_name LIKE '" + searchValue.replaceAll("'", "''") + "%'  ";
                }
                default: {

                }
            }
        }

        if (Utils.isNullOrEmpty(query)) {
            return new LinkedList();
        } else {
            return jdbcTemplate.query(query,  new BeanPropertyRowMapper(CardDetailsBean.class));
        }
    }

}
