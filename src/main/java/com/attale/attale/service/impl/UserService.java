package com.attale.attale.service.impl;

import com.attale.attale.dto.LoginRequest;
import com.attale.attale.dto.Response;
import com.attale.attale.dto.UserDTO;
import com.attale.attale.entity.User;
import com.attale.attale.exception.OurException;
import com.attale.attale.repo.UserRepository;
import com.attale.attale.service.interfac.IUserService;
import com.attale.attale.utils.JWTUtils;
import com.attale.attale.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(User user) {

        Response response=new Response();
        try{
            if (user.getRole()==null || user.getRole().isBlank()){  // restriction to created admin users using postman
                user.setRole("USER");
            }
            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurException(user.getEmail()+"Already Existes");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser=userRepository.save(user);
            UserDTO userDTO= Utils.mapUserEntityToUserDTO(savedUser);
            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch(OurException e){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Registration" +e.getMessage());

        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response=new Response();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            var user =userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new OurException("User not Found"));

            var token=jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During User Login" +e.getMessage());


        }



        return response;
    }

    @Override
    public Response getAllUsers() {
        Response response=new Response();
        try{
            List<User> userList=userRepository.findAll();
            List<UserDTO> userDTOList=Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        }catch (OurException e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During Getting all Users" +e.getMessage());


        }
        return response;
    }

    @Override
    public Response getUserBookingHistory(String userId) {
        Response response=new Response();
        try{
            User user=userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("User Not Found"));
            UserDTO userDTO=Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During Getting all Users" +e.getMessage());


        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        return null;
    }

    @Override
    public Response getUserById(String userId) {
        return null;
    }

    @Override
    public Response getMyInfo(String userId) {
        return null;
    }
}
