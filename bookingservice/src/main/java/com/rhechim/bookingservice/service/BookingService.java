package com.rhechim.bookingservice.service;

import com.rhechim.bookingservice.request.BookingRequest;
import com.rhechim.bookingservice.response.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    public BookingResponse createBooking(final BookingRequest request) {
        return new BookingResponse().builder().build();
    }
}
