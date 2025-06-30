package com.rhechim.bookingservice.service;

import com.rhechim.bookingservice.repository.CustomerRepository;
import com.rhechim.bookingservice.request.BookingRequest;
import com.rhechim.bookingservice.response.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;

    @Autowired
    public BookingService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        return new BookingResponse().builder().build();
    }
}
