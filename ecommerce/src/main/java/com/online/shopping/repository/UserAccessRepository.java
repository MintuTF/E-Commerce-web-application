package com.online.shopping.repository;

import com.online.shopping.model.UserAccess;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public interface UserAccessRepository extends MongoRepository<UserAccess,Long> {
    public UserAccess findUserAccessByUsername(String name);
    public UserAccess findUserAccessByPassword(String password);
}
