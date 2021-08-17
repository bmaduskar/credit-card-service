package com.ps.creditcardprocessor.utils;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */

@RunWith(MockitoJUnitRunner.class)
public class CCProcessorUtilsTest {

    @Test
    public void test_validateCardDetails() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("4111111111111111");
        assertTrue(CCProcessorUtils.validateCardDetails(creditCardData));
    }

    @Test
    public void test_validateCardDetails_AlphabeticCard() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("abcd3243");
        assertFalse(CCProcessorUtils.validateCardDetails(creditCardData));
    }

    @Test
    public void test_validateCardDetails_LengthFail() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("4111111111111111111122");
        assertFalse(CCProcessorUtils.validateCardDetails(creditCardData));
    }

    @Test
    public void test_validateCardDetails_negativeCard() {
        CreditCardData creditCardData = mock(CreditCardData.class);
        when(creditCardData.getCardNumber()).thenReturn("-4111111111111111");
        assertFalse(CCProcessorUtils.validateCardDetails(creditCardData));
    }

    @Test
    public void test_formatAmount() {
        String amount = "20";
        assertEquals(CreditCardDataConstants.POUND + "20.00", CCProcessorUtils.formatAmount(amount));
    }

    @Test
    public void test_formatAmount_negative() {
        String amount = "-20";
        assertEquals(CreditCardDataConstants.NEGATIVE_POUND + "20.00", CCProcessorUtils.formatAmount(amount));
    }
}
