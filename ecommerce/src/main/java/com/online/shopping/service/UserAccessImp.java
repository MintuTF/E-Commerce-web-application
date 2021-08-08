package com.online.shopping.service;

import com.online.shopping.model.ERole;
import com.online.shopping.model.Role;
import com.online.shopping.model.UserAccess;
import com.online.shopping.repository.UserAccessRepository;
import com.online.shopping.repository.UserRoleRepository;
import com.online.shopping.util.payload.LoginRequest;
import com.online.shopping.util.payload.SignupRequest;
import com.online.shopping.util.payload.response.JwtResponse;
import com.online.shopping.util.security.jwt.JwtUtils;
import com.online.shopping.util.userdetails.UserDetailsimp;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

   @Autowired
   private JwtUtils jwtUtils;

    @Override
    public UserAccess createUser(SignupRequest signupRequest) {


     Set<Role> roles=userRoleService.userRole(signupRequest);
     UserAccess userAccess=
                new UserAccess(signupRequest.getUsername(),
                        signupRequest.getName(),encoder.encode(signupRequest.getPassword()), signupRequest.getEmail(),roles);
     return userAccessRepository.save(userAccess);


    }

    @Override
    public JwtResponse loginUser(LoginRequest loginRequest) {



        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
       // set to the context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //get current user or principal
        UserDetailsimp userDetailsimp=(UserDetailsimp) authentication.getPrincipal();
        System.out.println(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);


        List<String> roles = userDetailsimp.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse=  new JwtResponse(jwt,userDetailsimp);
        return jwtResponse;

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
