package org.APIAutomationFramework.payload;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.APIAutomationFramework.pojo.BookingResponsePojo;
import org.APIAutomationFramework.pojo.CreateBooking;
import org.APIAutomationFramework.pojo.CreateBookingDates;

public class CreatebookingPayload {

    static Gson gson = new Gson();

   public static Faker faker = new Faker();
//   public static String fName = faker.name().firstName();
   public static String fName;
//    public static String lName = faker.name().lastName();
    public static String lName;
    public static String additionalNeeds = faker.food().dish();

    public static String getCreateBookingPayload() {

        CreateBooking createBooking = new CreateBooking();
        createBooking.setFirstname(fName);
        createBooking.setLastname(lName);
        createBooking.setTotalprice(5000);
        createBooking.setDepositpaid(true);

        CreateBookingDates createBookingDates = new CreateBookingDates();
        createBookingDates.setCheckin("2024-12-20");
        createBookingDates.setCheckout("2024-12-25");

        createBooking.setBookingdates(createBookingDates);
        createBooking.setAdditionalneeds(additionalNeeds);

//        Convert the request payload
        String requestJson  = gson.toJson(createBooking);
        return requestJson;
    }

    public static BookingResponsePojo getDeSerializedResponse(String response){
        BookingResponsePojo bookingResponsePojo = gson.fromJson(response, BookingResponsePojo.class);
        return bookingResponsePojo;
    }
}