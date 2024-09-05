package com.attale.attale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull(message = "Check in Date is Required")
    private LocalDate checkInDate;
    @Future(message = "Check in Date must be in the Future")
    private LocalDate checkOutDate;

    @Min(value=1 ,message="Number of adults must be greater than 0")
    private int noOfAdults;
    @Min(value=0 ,message="Number of Children cant be negative")
    private int noOfChildren;
    private int totalNoOfGuests;

    private String bookingConfirmationCode;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="room_id",referencedColumnName = "id")
    private Room room;

    public void calculateTotalNoOfGuests(){
        this.totalNoOfGuests =this.noOfAdults+this.noOfChildren;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
        calculateTotalNoOfGuests();
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
        calculateTotalNoOfGuests();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutData=" + checkOutDate +
                ", noOfAdults=" + noOfAdults +
                ", noOfChildren=" + noOfChildren +
                ", totalNoOfGuests=" + totalNoOfGuests +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +

                '}';
    }
}
