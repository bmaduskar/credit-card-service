package com.ps.creditcardprocessor.controller;

import com.ps.creditcardprocessor.constants.CreditCardDataConstants;
import com.ps.creditcardprocessor.model.*;
import com.ps.creditcardprocessor.service.CCProcessorService;
import com.ps.creditcardprocessor.utils.CCProcessorUtils;
import com.ps.creditcardprocessor.utils.CreditCardValidator;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author  bmaduskar
 * @project Credit-Card-Service
 */
@CrossOrigin
@RestController
public class CreditCardDataController {

    @Autowired
    private CCProcessorService processorService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping(path = "/add-card")
    @ApiOperation("Add a new credit card")
    public CCGenericResponse addCard(@RequestBody CCTransactionRequestModel request) {
        CreditCardData creditCardData = new CreditCardData(request);
        CCGenericResponse ccGenericResponse = new CCGenericResponse();
        if (CreditCardValidator.luhnCheck(request.getCardNumber()) && CCProcessorUtils.validateCardDetails(creditCardData)) {
            ccGenericResponse = processorService.addCard(creditCardData);
        } else {
            ccGenericResponse.setCardNumber(request.getCardNumber());
            CCError errors = new CCError();
            errors.setMessage(CreditCardDataConstants.CARD_NUMBER_ERROR);
            ccGenericResponse.setErrors(errors);
        }
        return ccGenericResponse;
    }

    @GetMapping(path = "/list-cards", produces = "application/json")
    @ApiOperation("List all cards from the system")
    public CreditCardListResponse getCards() {
        AtomicReference<CreditCardListResponse> cardList = new AtomicReference<>(processorService.getAllCards());
        return cardList.get();
    }
}
