package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.UserService;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {

        if(user.getUsername().isEmpty() || user.getUsername() == null){
            throw new IllegalArgumentException("CAMPO USERNAME OBRIGATORIO");
        }

        if(user.getNivel().isEmpty() || user.getNivel() == null){
            throw new IllegalArgumentException("CAMPO NIVEL OBRIGATORIO");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO user) {

        User foundUser = userRepository.findById(id).get();

        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        if(user.getUsername() != null || !user.getUsername().isEmpty()){
            foundUser.setUsername(user.getUsername());
        }




    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}
