package com.ps.creditcardprocessor.repository;

import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardData;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Map;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */


public interface CreditCardProcessorDAO {

    List<Map<String, Object>> getAllCards() throws DataAccessException;

    int addCard(CreditCardData creditCardData) throws DataAccessException;

}
