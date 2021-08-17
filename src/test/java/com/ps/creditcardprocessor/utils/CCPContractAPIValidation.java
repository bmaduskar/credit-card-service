package com.ps.creditcardprocessor.utils;

import com.atlassian.oai.validator.restassured.SwaggerValidationFilter;
import static io.restassured.RestAssured.given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * /**
 *
 * @author bmaduskar
 * @project Credit-Card-Service
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CCPContractAPIValidation.class)

public class CCPContractAPIValidation {

    /**
     * Swagger URL can be a json/yaml hosted or path to the contract in source code directory.
     * In this CC Processing application - SpringFox was already integrated and the /api-docs was hosted by default.
     * This implementation works with both file path and hosted contract.
     */
    private static final String SWAGGER_JSON_URL = "/Users/bhupesh/Desktop/Code/CreditCardProcessor/Credit-Card-service/src/main/resources/swagger_ccp.yaml";

    private final SwaggerValidationFilter validationFilter = new SwaggerValidationFilter(SWAGGER_JSON_URL);


    /**
     * Direct API can be accessed on a Sandbox to do a realtime validation with the contract.
     */
    @Test
    public void testGetValidContract() {
        given()

                .filter(validationFilter)
                .when()
                .get("http://localhost:8080/ccp/v1/list-cards")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
