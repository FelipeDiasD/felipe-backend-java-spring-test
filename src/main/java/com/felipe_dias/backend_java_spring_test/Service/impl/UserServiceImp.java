package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.UserService;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> usersList = userRepository.findAll();

        List<UserDTO> dtoList = usersList.stream()
                                        .map(user -> new UserDTO(user))
                                        .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public void createUser(User user) {

        if(user.getUsername().isEmpty() || user.getUsername() == null){
            throw new IllegalArgumentException("CAMPO USERNAME OBRIGATORIO");
        }

        if(user.getNivel().getLabel().isEmpty() || user.getNivel() == null){
            throw new IllegalArgumentException("CAMPO NIVEL OBRIGATORIO");
        }
        //TODO: LIDAR COM O NIVEL DO USUARIO


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

        userRepository.save(foundUser);

    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        userRepository.deleteById(id);
    }


}
