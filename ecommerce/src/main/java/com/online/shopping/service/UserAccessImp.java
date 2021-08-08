package com.online.shopping.service;

import com.online.shopping.model.ERole;
import com.online.shopping.model.Role;
import com.online.shopping.model.UserAccess;
import com.online.shopping.repository.UserAccessRepository;
import com.online.shopping.repository.UserRoleRepository;
import com.online.shopping.util.payload.LoginRequest;
import com.online.shopping.util.payload.SignupRequest;
import com.online.shopping.util.userdetails.UserDetailsimp;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserAccessImp implements UserAccessService {
      @Autowired
      private UserAccessRepository userAccessRepository;
      @Autowired
      private UserRoleService userRoleService;
     @Autowired
      private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public UserAccess createUser(SignupRequest signupRequest) {


     Set<Role> roles=userRoleService.userRole(signupRequest);
     UserAccess userAccess=
                new UserAccess(signupRequest.getUsername(),
                        signupRequest.getName(),encoder.encode(signupRequest.getPassword()), signupRequest.getEmail(),roles);
     return userAccessRepository.save(userAccess);


    }

    @Override
    public UserDetailsimp loginUser(LoginRequest loginRequest) {



        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsimp userDetailsimp=(UserDetailsimp) authentication.getPrincipal();

        return userDetailsimp;

    }

    @Override
    public void deleteUser(UserAccess userAccess) {
          userAccessRepository.delete(userAccess);
    }

    @Override
    public UserAccess updateUser(UserAccess userAccess) {
        return null;
    }
}
