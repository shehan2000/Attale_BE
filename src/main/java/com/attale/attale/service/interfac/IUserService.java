package com.attale.attale.service.interfac;

import com.attale.attale.dto.LoginRequest;
import com.attale.attale.dto.Response;
import com.attale.attale.entity.User;

public interface IUserService {

    Response register(User loginRequest);
    Response login(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUserBookingHistory(String userId);
    Response deleteUser(String userId);

    Response getUserById(String userId);
    Response getMyInfo(String email);

}
