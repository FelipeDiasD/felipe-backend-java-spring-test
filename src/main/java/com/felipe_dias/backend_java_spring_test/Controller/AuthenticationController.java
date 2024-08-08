package com.felipe_dias.backend_java_spring_test.Controller;

import com.felipe_dias.backend_java_spring_test.Service.impl.TokenService;
import com.felipe_dias.backend_java_spring_test.Service.impl.UserServiceImp;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.AuthenticationDTO;
import com.felipe_dias.backend_java_spring_test.model.dto.LoginResponseDTO;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationObj){

        var usernamePassword = new  UsernamePasswordAuthenticationToken(authenticationObj.username(),
                                                                        authenticationObj.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO){

        String encodedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());

        userDTO.setPassword(encodedPassword);

        User userToRegister = userService.fromDTO(userDTO);

        this.userService.createUser(userToRegister);


        return ResponseEntity.ok().build();
    }
}
