/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao;

import java.util.List;

/**
 *
 * @author saurabh
 */
public interface CartDetailsDao {

    List getList(String tablename, String searchFor, String searchValue);
    
    
}
