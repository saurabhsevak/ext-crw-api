/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.utility;

import com.corpository.ext.crw.api.extcrwapi.bean.Response.JSONResponse;
import java.util.Collection;

/**
 *
 * @author saurabh
 */
public class Utils {

    public static JSONResponse generateResponse(Integer status, String message, Object data) {
        JSONResponse resObj = new JSONResponse();
        resObj.setStatus(status);
        resObj.setMessage(message);
        resObj.setData(data);
        return resObj;
    }

    public static Boolean checkNullOrEmptyCollection(@SuppressWarnings("rawtypes") Collection c) {
        return c == null || c.isEmpty();
    }

    public static Boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static void checkWhereAppender(StringBuilder queryBuilder) {
        if (queryBuilder.lastIndexOf("WHERE") > 0) {
            queryBuilder.append(" AND ");
        } else {
            queryBuilder.append(" WHERE ");
        }
    }

    public static void appenderValue(StringBuilder queryBuilder, String appendervalue, String appender) {
        if (queryBuilder.lastIndexOf("WHERE") > 0) {
            queryBuilder.append(appender).append(appendervalue).append("  ");
        } else {
            queryBuilder.append(" WHERE ").append(appendervalue).append("  ");
        }
    }

    public static JSONResponsePagination generateResponsePagination(String status, String message, Object data,
            Integer totalCount, Integer filterd) {
        JSONResponsePagination resObj = new JSONResponsePagination();
        resObj.setStatus(status);
        resObj.setMessage(message);
        resObj.setData(data);
        resObj.setRecordsTotal(totalCount);
        resObj.setRecordsFiltered(filterd);
        return resObj;
    }

}
