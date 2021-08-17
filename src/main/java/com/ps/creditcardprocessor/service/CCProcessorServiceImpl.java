package com.ps.creditcardprocessor.service;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.*;
import com.ps.creditcardprocessor.repository.CreditCardProcessorDAO;
import com.ps.creditcardprocessor.utils.CCProcessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author  bmaduskar
 * @project Credit-Card-service
 */

@Service
public class CCProcessorServiceImpl implements CCProcessorService {

    @Autowired
    private CreditCardProcessorDAO creditCardProcessorDAO;

    @Override
    public CCGenericResponse addCard(CreditCardData creditCardData) {
        CCGenericResponse ccGenericResponse = new CCGenericResponse();
        ccGenericResponse.setCardNumber(creditCardData.getCardNumber());
        ccGenericResponse.setBalanceRemaining("0");

        try {
            int response = creditCardProcessorDAO.addCard(creditCardData);
        } catch (DuplicateKeyException e) {
            CCError errors = new CCError();
            errors.setMessage(CreditCardDataConstants.CARD_ALREADY_EXIST);
            ccGenericResponse.setErrors(errors);
        } catch (DataAccessException e) {
            CCError errors = new CCError();
            errors.setMessage(CreditCardDataConstants.GENERIC_ERROR_MESSAGE);
            ccGenericResponse.setErrors(errors);
        }

        return ccGenericResponse;

    }

    public CreditCardListResponse getAllCards() {
        CreditCardListResponse cardList = new CreditCardListResponse();
        List<CreditCardData> creditCards = new ArrayList<>();
        try {
            List<Map<String, Object>> cards = creditCardProcessorDAO.getAllCards();
            for (Map<String, Object> row : cards) {
                CreditCardData cardData = new CreditCardData();
                cardData.setCardNumber((String) row.get("ccnumber"));
                cardData.setFirstName((String) row.get("fname"));
                cardData.setLastName((String) row.get("lname"));
                cardData.setTotalCredit(CCProcessorUtils.formatAmount((String) row.get("credit_amount")));
                cardData.setTotalCharge(CCProcessorUtils.formatAmount((String) row.get("charge_amount")));
                cardData.setCreditLimit(CCProcessorUtils.formatAmount((String) row.get("credit_limit")));
                creditCards.add(cardData);
            }
        } catch (DataAccessException | NumberFormatException e) {
            CCError errors = new CCError();
            errors.setMessage(CreditCardDataConstants.GENERIC_ERROR_MESSAGE);
            cardList.setErrors(errors);
        }

        if (!creditCards.isEmpty()) {
            cardList.setCreditCards(creditCards);
        }
        return cardList;
    }
}
