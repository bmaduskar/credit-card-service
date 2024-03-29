package com.ps.creditcardprocessor.repository;


import com.ps.creditcardprocessor.model.CreditCardData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author  bmaduskar
 * @project Credit-Card-service
 */

@Repository
public class CreditCardProcessorDAOImpl implements CreditCardProcessorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> getAllCards() throws DataAccessException {
        return jdbcTemplate.queryForList("SELECT * FROM creditcardinfo");
    }

    public int addCard(CreditCardData creditCardData) throws DataAccessException {
        return jdbcTemplate.update("insert into creditcardinfo (ccnumber,fname,lname,credit_amount,charge_amount,credit_limit) values (?,?,?,?,?,?)", new Object[]{
                creditCardData.getCardNumber(), creditCardData.getFirstName(), creditCardData.getLastName(),
                creditCardData.getTotalCredit(), creditCardData.getTotalCharge(), creditCardData.getCreditLimit()
        });
    }
}
