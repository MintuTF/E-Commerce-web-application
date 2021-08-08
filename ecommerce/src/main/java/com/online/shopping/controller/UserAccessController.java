package com.online.shopping.controller;


import com.online.shopping.model.UserAccess;
import com.online.shopping.service.UserAccessService;
import com.online.shopping.util.payload.LoginRequest;
import com.online.shopping.util.payload.SignupRequest;
import com.online.shopping.util.userdetails.UserDetailsimp;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@Transactional
public class UserAccessController {

    @Autowired
    private UserAccessService userAccessService;



    @PostMapping ("signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest user) {


        ResponseEntity<?> responseEntity=null;
        try {
               UserDetailsimp userDetailsimp=userAccessService.loginUser(user);
            responseEntity =new ResponseEntity<>(userDetailsimp, HttpStatus.OK);

        } catch (Exception e) {
            responseEntity =new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        return responseEntity;

    }


    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        ResponseEntity<?> responseEntity=null;
        try {




            UserAccess getUser = userAccessService.createUser(signupRequest);
            if (getUser != null) {

                        responseEntity=new ResponseEntity<>(getUser, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseEntity =new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        return responseEntity;
    }

    @GetMapping("signup/test")
    public ResponseEntity<?> test() {

        return new ResponseEntity<>("Hello world", HttpStatus.BAD_REQUEST);

    }

}
