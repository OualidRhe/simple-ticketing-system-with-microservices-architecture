package com.rhechim.bookingservice.service;

import com.rhechim.bookingservice.client.InventoryServiceClient;
import com.rhechim.bookingservice.entity.Customer;
import com.rhechim.bookingservice.event.BookingEvent;
import com.rhechim.bookingservice.repository.CustomerRepository;
import com.rhechim.bookingservice.request.BookingRequest;
import com.rhechim.bookingservice.response.BookingResponse;
import com.rhechim.bookingservice.response.InventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;
    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient, final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        // check if user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer == null) {
            throw new RuntimeException("Customer not found");
        }

        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        if(inventoryResponse.getCapacity() < request.getTicketCount()) {
            throw new RuntimeException("Capacity less than ticket count");
        }

        // Booking creation
        final BookingEvent bookingEvent = createBookingEvent(request, customer, inventoryResponse);
        kafkaTemplate.send("booking", bookingEvent);

        log.info("Booking created and sent to Kafka : {}", bookingEvent);

        return new BookingResponse().builder().build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request,
                                            final Customer customer,
                                            final InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(request.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();

    }
}
