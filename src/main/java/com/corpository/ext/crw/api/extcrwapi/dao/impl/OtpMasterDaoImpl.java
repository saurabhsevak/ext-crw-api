/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corpository.ext.crw.api.extcrwapi.dao.impl;

import com.aruhat.Bean.table.OtpMasterBean;
import com.aruhat.Bean.table.SystemParamsBean;
import com.aruhat.utility.ConversionsUtility;
import com.corpository.dao.OtpMasterDao;
import com.corpository.request.InsertOtpMasterBean;
import com.google.common.base.Joiner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author saurabh
 */
@Repository
public class OtpMasterDaoImpl implements OtpMasterDao {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(OtpMasterDaoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Object getOtpMaster(Integer start, Integer length, String search, Integer otpType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(" SELECT ")
                .append(" op.otp_id,op.otp,op.insertion_time,op.credit_card_id,op.workorder_id,op.is_used, ")
                .append(" op.attempt_time,op.attempt_status,op.attempt_message,op.attempt_page_html, ")
                .append(" op.otp_message_id,op.last_modified,op.remarks,cm.credit_card_id,cm.credit_card_number, ")
                .append(" IF(LENGTH(RIGHT(cm.credit_card_number, 4)) > 0,CONCAT('XXXX-XXXX-XXXX-', RIGHT(cm.credit_card_number, 4)), NULL) AS 'credit_card_number_string', ")
                .append(" cm.name,cm.cvv,cm.dob,cm.valid_from_date,cm.expiry_date,cm.credit_card_provider,om.received_message_content ")
                .append(" FROM ")
                .append(" crawler.otp_master op LEFT JOIN crawler.creditcard_master cm ON cm.credit_card_id = op.credit_card_id ")
                .append(" LEFT JOIN crawler.otp_message om ON op.otp_message_id = om.otp_message_id ")
                .append(" WHERE ")
                .append(" ( op.workorder_id LIKE '%").append(search).append("%'")
                .append(" OR cm.name LIKE '%").append(search).append("%'")
                .append(" OR op.insertion_time LIKE '%").append(search).append("%'")
                .append(" OR op.attempt_status LIKE '%").append(search).append("%'")
                .append(" ) ");

        if (otpType != null && otpType > 0) {
            if (otpType == 1) {
                stringBuilder
                        .append(" AND  op.is_used= 1 ");
            } else if (otpType == 2) {
                stringBuilder
                        .append(" AND (op.is_used IS NULL ")
                        .append(" OR op.is_used=0 ) ");
            }

        }

        stringBuilder
                .append(" ORDER BY op.insertion_time, last_modified DESC , otp_id DESC ")
                .append(" LIMIT ").append(length).append(" offset ").append(start);

        logger.info("Query : " + stringBuilder.toString());

        List<OtpMasterBean> listSystemParamsBean = jdbcTemplate.query(stringBuilder.toString(),
                new RowMapper<OtpMasterBean>() {

                    @Override
                    public OtpMasterBean mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new OtpMasterBean()
                        .setOtpId(resultSet.getInt("otp_id"))
                        .setOtp(resultSet.getString("otp"))
                        .setInsertionTime(resultSet.getString("insertion_time"))
                        .setCreditCardId(resultSet.getInt("credit_card_id"))
                        .setWorkorderId(resultSet.getInt("workorder_id"))
                        .setIsUsed(resultSet.getInt("is_used"))
                        .setAttemptTime(resultSet.getString("attempt_time"))
                        .setAttemptPageHtml(Jsoup.parse(resultSet.getString("attempt_page_html") == null ? "-" : resultSet.getString("attempt_page_html")).html())
                        .setAttemptStatus(resultSet.getString("attempt_status"))
                        .setAttemptMessage(resultSet.getString("attempt_message"))
                        .setOtpMessageId(resultSet.getInt("otp_message_id"))
                        .setLastModified(resultSet.getString("last_modified"))
                        .setCreditCardId(resultSet.getInt("credit_card_id"))
                        //                        .setCreditCardNumber(resultSet.getString("credit_card_number"))   
                        .setCreditCardNumberString(resultSet.getString("credit_card_number_string"))
                        .setCreditCardHolderName(resultSet.getString("name"))
                        //                        .setCvv(resultSet.getInt("cvv"))
                        .setValidFromDate(resultSet.getString("valid_from_date"))
                        .setExpiryDate(resultSet.getString("expiry_date"))
                        .setCreditCardProvider(resultSet.getString("credit_card_provider"))
                        .setReceivedMessageContent(resultSet.getString("received_message_content"));
                    }
                });
        return listSystemParamsBean;
    }

    @Override
    public Integer countOtpMaster(String search, Integer otpType) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" SELECT count(*) FROM  ")
                .append(" crawler.otp_master op LEFT JOIN crawler.creditcard_master cm ON cm.credit_card_id = op.credit_card_id ")
                .append(" LEFT JOIN crawler.otp_message om ON op.otp_message_id=om.otp_message_id ")
                .append(" WHERE ")
                .append(" ( op.workorder_id LIKE '%").append(search).append("%'")
                .append(" OR cm.name LIKE '%").append(search).append("%'")
                .append(" OR op.insertion_time LIKE '%").append(search).append("%'")
                .append(" OR op.attempt_status LIKE '%").append(search).append("%'")
                .append(" )  ");

        if (otpType != null && otpType > 0) {
            if (otpType == 1) {
                stringBuilder
                        .append(" AND  op.is_used= 1 ");
            } else if (otpType == 2) {
                stringBuilder
                        .append(" AND (op.is_used IS NULL ")
                        .append(" OR op.is_used=0 ) ");
            }

        }

        return jdbcTemplate.queryForObject(stringBuilder.toString(), Integer.class);
    }

    @Override
    public List<Integer> deleteOtps(LinkedList<Integer> otpIdList) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (otpIdList != null && !otpIdList.isEmpty()) {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" DELETE FROM crawler.otp_master WHERE otp_id in( ")
                    .append(Joiner.on(",").join(otpIdList))
                    .append(" ) ");

            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement prepareStatement = connection.prepareStatement(queryBuilder.toString(),
                            Statement.RETURN_GENERATED_KEYS);
                    return prepareStatement;
                }
            }, keyHolder);
        }
        return ConversionsUtility.getKeysFromKeyHolder(keyHolder);
    }

    @Override
    public List<Integer> saveOtp(InsertOtpMasterBean insertOtpMasterBean) {
        List<Integer> retrunIds = new LinkedList<Integer>();
        Integer cardHolderId = insertOtpMasterBean.getCardHolderId();
        Integer workOrderId = insertOtpMasterBean.getWorkOrderId();

        StringBuilder valueBuilder = new StringBuilder();

        StringBuilder queryBuilder = new StringBuilder()
                .append(" INSERT INTO crawler.otp_master ( otp , insertion_time  ");

        if (cardHolderId != null && cardHolderId > 0) {
            queryBuilder.append(" , credit_card_id");
            valueBuilder
                    .append(" , ")
                    .append(cardHolderId);
        }
        if (workOrderId != null && workOrderId > 0) {
            queryBuilder.append(" , workorder_id");
            valueBuilder
                    .append(" , ")
                    .append(workOrderId);
        }

        queryBuilder
                .append(" ) VALUES ( ")
                .append(insertOtpMasterBean.getOtp())
                .append(",NOW() ")
                .append(valueBuilder);

        queryBuilder.append(")");

        return bulkInsertRecords(queryBuilder.toString());
    }

    @Override
    public List<Integer> saveOtpMessge(InsertOtpMasterBean insertOtpMasterBean) {

        StringBuilder queryBuilder = new StringBuilder()
                .append(" INSERT INTO crawler.otp_message (received_message_content, insertion_time,  device_master_id) ")
                .append(" VALUES ")
                .append("'FROM AruHat: The OTP is ")
                .append(insertOtpMasterBean.getOtp())
                .append("and is valid for 15 min.'")
                .append(",current_timestamp")
                .append(",")
                .append(insertOtpMasterBean.getDeviceId());

        return bulkInsertRecords(queryBuilder.toString());
    }

    private List<Integer> bulkInsertRecords(String query) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement prepareStatement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
                return prepareStatement;
            }
        }, keyHolder);

        return ConversionsUtility.getKeysFromKeyHolder(keyHolder);
    }

}
