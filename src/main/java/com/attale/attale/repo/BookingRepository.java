package com.attale.attale.repo;

import com.attale.attale.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findBookingConfirmationCode(String confirmationCode);

    List<Booking> findByUserId(Long userId);

}
