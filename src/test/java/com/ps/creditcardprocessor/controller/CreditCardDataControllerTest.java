package com.ps.creditcardprocessor.controller;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.CCGenericResponse;
import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardListResponse;
import com.ps.creditcardprocessor.repository.CreditCardProcessorDAO;
import com.ps.creditcardprocessor.service.CCProcessorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author  bmaduskar
 * @project Credit-Card-service
 */

@RunWith(MockitoJUnitRunner.class)
public class CreditCardDataControllerTest {

    @Mock
    CCProcessorService processorService;

    @Spy
    private List<Map<String, Object>> listOfCards;

    @Mock
    private CreditCardProcessorDAO creditCardProcessorDAO;

    @InjectMocks
    CreditCardDataController creditCardDataController;

    @Test
    public void test_addCard() {
        CCTransactionRequestModel CCTransactionRequestModel = new CCTransactionRequestModel();
        CCTransactionRequestModel.setCardNumber("4111111111111121");
        CCTransactionRequestModel.setFirstName("Bhupesh");
        CCTransactionRequestModel.setLastName("maduskar");

        CCGenericResponse ccGenericResponse = creditCardDataController.addCard(CCTransactionRequestModel);
        assertEquals(CreditCardDataConstants.CARD_NUMBER_ERROR, ccGenericResponse.getErrors().getMessage());
    }

    @Test
    public void test_add() {
        CCTransactionRequestModel CCTransactionRequestModel = new CCTransactionRequestModel();
        CCTransactionRequestModel.setCardNumber("4111111111111111");
        CCTransactionRequestModel.setFirstName("Bhupesh");
        CCTransactionRequestModel.setLastName("Maduskar");
        CCGenericResponse ccGenericResponse = mock(CCGenericResponse.class);
        when(processorService.addCard(Mockito.any())).thenReturn(ccGenericResponse);
        CCGenericResponse response = creditCardDataController.addCard(CCTransactionRequestModel);
        assertEquals(ccGenericResponse, response);

    }

}
