package com.ps.creditcardprocessor.model;

import java.util.List;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */

public class CreditCardListResponse {

    private List<CreditCardData> creditCards;

    private CCError errors;

    public CCError getErrors() {
        return errors;
    }

    public void setErrors(CCError errors) {
        this.errors = errors;
    }

    public List<CreditCardData> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardData> creditCards) {
        this.creditCards = creditCards;
    }

}
