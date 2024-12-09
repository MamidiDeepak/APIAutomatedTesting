package org.APIAutomationFramework.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.APIAutomationFramework.endpoints.APIConstants;
import org.APIAutomationFramework.payload.CreatebookingPayload;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    public APIConstants apiConstants;
    public CreatebookingPayload createbookingPayload;
    public RequestSpecification requestSpecification;
    public Response response;

    @BeforeTest
    public void initialClass(){

        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASEURL)
                .contentType("application/json")
                .log().all();
    }
}
