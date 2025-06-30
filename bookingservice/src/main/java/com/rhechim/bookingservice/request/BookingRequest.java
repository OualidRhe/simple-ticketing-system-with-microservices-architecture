package com.rhechim.bookingservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
    private Long userId;
    private Long eventId;
    private Long ticketCount;
    //private BigDecimal ticketPrice;
}
