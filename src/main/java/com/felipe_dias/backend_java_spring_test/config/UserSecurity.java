package com.felipe_dias.backend_java_spring_test.config;

import com.felipe_dias.backend_java_spring_test.Service.impl.AuthenticationService;
import com.felipe_dias.backend_java_spring_test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSecurity {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    public boolean checkUserId(Authentication authentication, int id) {

        User retrivedUser = (User) authentication.getPrincipal();

        return (id == retrivedUser.getId());

    }
}