package org.APIAutomationFramework.tests;

import groovyjarjarantlr4.v4.parse.ANTLRParser;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.APIAutomationFramework.asserts.AssertActions;
import org.APIAutomationFramework.base.BaseClass;
import org.APIAutomationFramework.endpoints.APIConstants;
import org.APIAutomationFramework.listeners.ReTryAnalyzer;
import org.APIAutomationFramework.payload.CreatebookingPayload;
import org.APIAutomationFramework.pojo.BookingResponsePojo;
import org.APIAutomationFramework.util.ReadDataFromExcel;
import org.APIAutomationFramework.util.ReadKey;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestPOSTAPI extends BaseClass {

//    @Epic("https://mymoneykarma.atlassian.net/browse/MMK-1322?atlOrigin=eyJpIjoiYzlhYmVkOTNmYTlkNGM5ZWEyZDMwNTZkYTM0NGI4OWUiLCJwIjoiamlyYS1zbGFjay1pbnQifQ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Creation of Booking with valid Request Payload")
    @Test (retryAnalyzer = ReTryAnalyzer.class, dataProvider = "getData", dataProviderClass = ReadDataFromExcel.class)
    public void testPOSTAPI(String fname, String lname){
        CreatebookingPayload.fName = fname;
        CreatebookingPayload.lName = lname;

      requestSpecification.basePath(APIConstants.CREATEBOOKING);

      response = RestAssured.given(requestSpecification)
                .when().body(CreatebookingPayload.getCreateBookingPayload()).post();

      response.then().log().all();

        BookingResponsePojo brp = CreatebookingPayload.getDeSerializedResponse(response.asString());
        String createdBookingId = brp.getBookingid();

        System.out.println("Created Id: "+createdBookingId);

        AssertActions.validateTheBodyData(brp.getBooking().getFirstname(),CreatebookingPayload.fName);
//        AssertActions.validateTheBodyData(brp.getBooking().getFirstname(), ReadKey.readkeyData("booking.post.firstName"));

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.statusLine()).isEqualTo("HTTP/1.1 200 OK");
        assertThat(response.headers().getValue("Content-Type")).isEqualTo("application/json; charset=utf-8");

        String responseJsonSchema = "{\n" +
                "    \"bookingid\": 215,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"deepak\",\n" +
                "        \"lastname\": \"mamidi\",\n" +
                "        \"totalprice\": 5000,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2024-12-20\",\n" +
                "            \"checkout\": \"2024-12-25\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"French Toast\"\n" +
                "    }\n" +
                "}";

        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(responseJsonSchema));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Creation of Booking status code when invalid endpoint is sent")
    @Test (dataProvider = "getData", dataProviderClass = ReadDataFromExcel.class)
    public void testPOSTAPIWithInvalidEndpoint(String fname, String lname){
        CreatebookingPayload.fName = fname;
        CreatebookingPayload.lName = lname;

        requestSpecification.basePath(APIConstants.INVALIDCREATEBOOKINGID);

        response = RestAssured.given(requestSpecification)
                .when().body(CreatebookingPayload.getCreateBookingPayload()).post();

//        response.then().log().all();

        assertThat(response.statusCode()).isEqualTo(404);

    }
}
