package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.UserService;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public User createUser(User user) {

        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new IllegalArgumentException("USERNAME ALREADY IN USE");
        }

        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new IllegalArgumentException("USERNAME CANNOT BE EMPTY/NULL");
        }

        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new IllegalArgumentException("PASSWORD CANNOT BE EMPTY/NULL");

        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO user){

        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        User foundUser = userRepository.findById(id).get();

        if(user.getUsername() != null || !user.getUsername().isEmpty()){
            foundUser.setUsername(user.getUsername());
        }

        if(user.getPassword() != null || !user.getPassword().isEmpty()){

            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            foundUser.setPassword(encodedPassword);
        }

        //ROLES CANNOT BE CHANGED ONCE CREATED


        return userRepository.save(foundUser);

    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public User fromDTO(UserDTO userObj){

        String password = (userObj.getPassword());

        User userToCreate = new User(userObj.getUsername(), userObj.getNivel(), password);

        return userToCreate;
    }


}
