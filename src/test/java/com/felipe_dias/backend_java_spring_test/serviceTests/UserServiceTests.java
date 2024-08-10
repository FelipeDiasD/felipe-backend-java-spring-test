package com.felipe_dias.backend_java_spring_test.serviceTests;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.impl.UserServiceImp;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;
import com.felipe_dias.backend_java_spring_test.model.enums.Nivel;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Test
    void testGettingAllUsers(){
        //given
        User u1 = new User(1L, "user1", Nivel.USER, "1234");
        User u2 = new User(2L, "user2", Nivel.ADMIN, "1234");
        User u3 = new User(3L, "user3", Nivel.USER, "1234");

        //when
        given(userRepository.findAll()).willReturn(List.of(u1,u2,u3));

        var userServiceList = userService.getAllUsers();

        assertThat(userServiceList).isNotNull();
        assertThat(userServiceList.size()).isEqualTo(3);
    }

    @Test
    void testCreatingAUser(){
        User userToCreate = new User(1L, "user1", Nivel.USER, "1234");

        given(userRepository.save(userToCreate)).willReturn(userToCreate);

        var userCreated = userService.createUser(userToCreate);

        assertThat(userCreated).isNotNull();
        assertThat(userCreated).isEqualTo(userToCreate);


    }

    @Test
    void testUpdatingUserUsername(){

        User updatedUser = new User(1L, "nomeNovo", Nivel.USER, "1234");
        User oldUser = new User(1L, "nomeAntigo", Nivel.USER, "1234");

        UserDTO dataToUpdate = new UserDTO(updatedUser);

        Long userId = oldUser.getId();

        given(userRepository.save(oldUser)).willReturn(oldUser);
        given(userRepository.findById(userId)).willReturn(Optional.of(oldUser));
        given(userRepository.existsById(userId)).willReturn(true);
        given(userRepository.save(updatedUser)).willReturn(updatedUser);

        var resultedUser = userService.updateUser(oldUser.getId(), dataToUpdate);

        assertThat(resultedUser).isEqualTo(updatedUser);

    }
    @Test
    void testUpdatingInexistentUser(){

        User updatedUser = new User(1L, "nomeNovo", Nivel.USER, "1234");
        User oldUser = new User(1L, "nomeAntigo", Nivel.USER, "1234");

        UserDTO dataToUpdate = new UserDTO(updatedUser);

        Long userId = oldUser.getId();


        given(userRepository.existsById(userId)).willReturn(false);




        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.updateUser(oldUser.getId(), dataToUpdate);
        });





    }

    void testDeletingUser(){

    }

    void testCreateUserWithNoName(){

    }

    void testCreateUserWithNoNivel(){

    }

    void testCreateUserWithNoPassword(){

    }



}
