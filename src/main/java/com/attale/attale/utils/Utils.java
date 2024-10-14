package com.attale.attale.utils;

import com.attale.attale.dto.BookingDTO;
import com.attale.attale.dto.RoomDTO;
import com.attale.attale.dto.UserDTO;
import com.attale.attale.entity.Booking;
import com.attale.attale.entity.Room;
import com.attale.attale.entity.User;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomAlphanumeric(int length){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0;i<length;i++){
            int randomindex=secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomCharacter =ALPHANUMERIC_STRING.charAt(randomindex);
            stringBuilder.append(randomCharacter);
        }
        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityToUserDTO(User user){
        UserDTO userDTO =new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        return userDTO;

    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutData(booking.getCheckOutDate());
        bookingDTO.setNoOfAdults(booking.getNoOfAdults());
        bookingDTO.setNoOfChildren(booking.getNoOfChildren());
        bookingDTO.setTotalNoOfGuests(booking.getTotalNoOfGuests());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        return bookingDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room){
        RoomDTO roomDTO =new RoomDTO();



        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(String.valueOf(room.getRoomPrice()));
        roomDTO.setRoomPhotourl(roomDTO.getRoomPhotourl());

        return roomDTO;

    }
    public static RoomDTO mapRoomEntityToRoomDTOPlusBookings(Room room){
        RoomDTO roomDTO =new RoomDTO();


        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(String.valueOf(room.getRoomPrice()));
        roomDTO.setRoomPhotourl(roomDTO.getRoomPhotourl());

        if(room.getBookings() != null){
            roomDTO.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
        }

        return roomDTO;

    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndRoom(User user){
        UserDTO userDTO =new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()){
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedRoom(booking,false)).collect(Collectors.toList()));
        }



        return userDTO;

    }

}
