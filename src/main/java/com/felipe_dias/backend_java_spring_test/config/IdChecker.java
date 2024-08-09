package com.felipe_dias.backend_java_spring_test.config;

import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("idChecker")
public class IdChecker {

    @Autowired
    private UserRepository userRepository;

    public Boolean check(Authentication authentication, Long id){

        String username = authentication.getName();
        User retrievedUser = userRepository.findByUsername(username);

        return retrievedUser.getId() == id;
    }
}
