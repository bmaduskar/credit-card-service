package com.ps.creditcardprocessor.service;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.CCGenericResponse;
import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardData;
import com.ps.creditcardprocessor.model.CreditCardListResponse;
import com.ps.creditcardprocessor.repository.CreditCardProcessorDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author  bmaduskar
 * @project Credit-Card-service
 */


@RunWith(MockitoJUnitRunner.class)
public class CCProcessorServiceTest {

    @Mock
    private CreditCardProcessorDAO creditCardProcessorDAO;

    @InjectMocks
    private CCProcessorServiceImpl ccProcessServiceImpl;

    @Test
    public void test_addCard() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("4111111111111111");
        CCGenericResponse ccGenericResponse = ccProcessServiceImpl.addCard(creditCardData);
        assertNotNull(ccGenericResponse);
    }

    @Test
    public void test_addCard_duplicate() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("5105105105105100");
        when(creditCardProcessorDAO.addCard(creditCardData)).thenThrow(DuplicateKeyException.class);
        CCGenericResponse ccGenericResponse = ccProcessServiceImpl.addCard(creditCardData);
        assertEquals(CreditCardDataConstants.CARD_ALREADY_EXIST, ccGenericResponse.getErrors().getMessage());
    }

    @Test
    public void test_getAllCards() {
        CreditCardListResponse response = ccProcessServiceImpl.getAllCards();
        assertNull(response.getErrors());
    }

}
