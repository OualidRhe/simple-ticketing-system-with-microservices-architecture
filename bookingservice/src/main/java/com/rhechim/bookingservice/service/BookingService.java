package com.rhechim.bookingservice.service;

import com.rhechim.bookingservice.client.InventoryServiceClient;
import com.rhechim.bookingservice.entity.Customer;
import com.rhechim.bookingservice.repository.CustomerRepository;
import com.rhechim.bookingservice.request.BookingRequest;
import com.rhechim.bookingservice.response.BookingResponse;
import com.rhechim.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);

        if(customer == null) {
            throw new RuntimeException("Customer not found");
        }

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());




        return new BookingResponse().builder().build();
    }
}
