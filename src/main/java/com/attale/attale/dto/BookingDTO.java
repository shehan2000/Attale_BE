package com.attale.attale.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.time.LocalDate;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;  // Add this field for the ID added

    private LocalDate checkInDate;
    private LocalDate checkOutData;

    private int noOfAdults;
    private int noOfChildren;
    private int totalNoOfGuests;

    private String bookingConfirmationCode;

    private UserDTO user;

    private RoomDTO room;
}
