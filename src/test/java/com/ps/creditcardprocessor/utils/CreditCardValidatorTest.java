package com.ps.creditcardprocessor.utils;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */

@RunWith(MockitoJUnitRunner.class)
public class CreditCardValidatorTest {

    @Test
    public void test_luhnTest() {
        String number = "4111111111111111";
        assertTrue(CreditCardValidator.luhnCheck(number));
    }

    @Test
    public void test_luhnTest_False() {
        String number = "4111111111111121";
        assertFalse(CreditCardValidator.luhnCheck(number));
    }

}
