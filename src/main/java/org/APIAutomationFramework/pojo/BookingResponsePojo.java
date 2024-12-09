package org.APIAutomationFramework.pojo;

public class BookingResponsePojo {

    private String bookingid;
    private CreateBooking booking;

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public CreateBooking getBooking() {
        return booking;
    }

    public void setBooking(CreateBooking booking) {
        this.booking = booking;
    }
}
