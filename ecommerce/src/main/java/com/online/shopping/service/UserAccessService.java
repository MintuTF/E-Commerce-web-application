package com.online.shopping.service;

import com.online.shopping.model.UserAccess;
import com.online.shopping.util.payload.LoginRequest;
import com.online.shopping.util.payload.SignupRequest;
import com.online.shopping.util.payload.response.JwtResponse;
import com.online.shopping.util.userdetails.UserDetailsimp;
import org.springframework.stereotype.Service;

@Service
public interface UserAccessService {
    UserAccess createUser(SignupRequest signupRequest);
    JwtResponse loginUser(LoginRequest loginRequest);
    void deleteUser(UserAccess userAccess);
    UserAccess updateUser(UserAccess userAccess);
}
