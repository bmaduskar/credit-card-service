package com.ps.creditcardprocessor.service;

import com.ps.creditcardprocessor.model.CCGenericResponse;
import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardData;
import com.ps.creditcardprocessor.model.CreditCardListResponse;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */

public interface CCProcessorService {

    CCGenericResponse addCard(CreditCardData creditCardData);

    CreditCardListResponse getAllCards();

}
