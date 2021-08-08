package com.online.shopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Document("User_access")
@Data
@NoArgsConstructor

public class UserAccess {
    @Id
    private String id;
    private String username;
    private String name;
    private String password;
    private String email;
    private Set<Role> roles ;


    public UserAccess(String username,String name, String password,String email, Set<Role> role ) {
      this.username=username;
        this.name = name;
        this.password = password;
        this.email=email;
        this.roles = role;
    }
}
