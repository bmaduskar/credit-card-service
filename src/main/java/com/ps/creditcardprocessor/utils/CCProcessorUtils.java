package com.ps.creditcardprocessor.utils;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.CCTransactionRequestModel;
import com.ps.creditcardprocessor.model.CreditCardData;

/**
 * @author  bmaduskar
 * @project Credit-Card-service
 */

public class CCProcessorUtils {

    public static boolean validateCardDetails(CreditCardData creditCardData) {
        boolean cardResult = true;

        try {
            if (creditCardData.getCardNumber().length() > 19 || Long.parseLong(creditCardData.getCardNumber()) < 0) {
                cardResult = false;
            }
        } catch (NumberFormatException e) {
            cardResult = false;
        }

        return cardResult;
    }

    public static String formatAmount(String amount) {
        String formattedAmount;
        amount = String.format("%.2f", Double.parseDouble(amount));
        if (Double.parseDouble(amount) >= 0) {
            formattedAmount = CreditCardDataConstants.POUND + amount;
        } else {
            formattedAmount = CreditCardDataConstants.NEGATIVE_POUND + String.format("%.2f", Math.abs(Double.parseDouble(amount)));
        }

        return formattedAmount;
    }

}
