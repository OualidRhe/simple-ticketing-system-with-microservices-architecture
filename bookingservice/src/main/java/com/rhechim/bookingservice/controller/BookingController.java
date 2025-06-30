package com.rhechim.bookingservice.controller;

import com.rhechim.bookingservice.request.BookingRequest;
import com.rhechim.bookingservice.response.BookingResponse;
import com.rhechim.bookingservice.service.BookingSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final BookingSevice bookingSevice;

    @Autowired
    public BookingController(BookingSevice bookingSevice) {
        this.bookingSevice = bookingSevice;
    }

    @PostMapping("/booking")
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }
}
