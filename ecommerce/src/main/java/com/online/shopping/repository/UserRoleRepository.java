package com.online.shopping.repository;

import com.online.shopping.model.ERole;
import com.online.shopping.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoleRepository extends MongoRepository<Role,Long> {
   public Role findRoleByName(ERole name);
    Optional<Role> findByName(ERole name);
}
