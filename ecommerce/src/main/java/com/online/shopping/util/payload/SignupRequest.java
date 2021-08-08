package com.online.shopping.util.payload;

//import javax.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
//    @NotBlank
//    @Size(min = 3, max = 20)
    private String username;
 
//    @NotBlank
//    @Size(max = 50)
//    @Email
    private String email;
    
    private Set<String> roles;
    
//    @NotBlank
//    @Size(min = 6, max = 40)
    private String password;

    private String name;
  

}
