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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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

        User oldUser = new User(1L, "nomeAntigo", Nivel.USER, "1234");
        User updatedUser = new User(1L, "nomeNovo", Nivel.USER, "1234");

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
    void testUpdatingUserPassword() {

        User oldUser = new User(1L, "nome", Nivel.USER, "senhaVelha");
        User updatedUser = new User(1L, "nome", Nivel.USER, "senhaNova");

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

    @Test
    void testDeletingUser(){

        User userToDelete = new User(1L, "user1", Nivel.USER, "1234");

        Long userId = userToDelete.getId();

        given(userRepository.existsById(userId)).willReturn(true);
        willDoNothing().given(userRepository).deleteById(userId);

        Assertions.assertDoesNotThrow(() -> {

            userService.createUser(userToDelete);
            userService.deleteUser(userId); } );

        Assertions.assertTrue(userService.getAllUsers().isEmpty());


    }
    @Test
    void testCreateUserWithNoName(){
        User userWithNoName = new User(1l, "", Nivel.USER, "1234");



        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(userWithNoName);
        });
    }

    @Test
    void testCreateUserWithNoPassword(){
        User userWithNoPassword = new User(1l, "User", Nivel.USER, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(userWithNoPassword);
        });
    }

    @Test
    void testCreatingUserWithSameUsername(){

        User originalUser = new User(1l, "username", Nivel.USER, "1234");
        User userWithSameName = new User(2l, "username", Nivel.USER, "1234");

        given(userRepository.findByUsername(originalUser.getUsername())).willReturn(originalUser);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(originalUser);
            userService.createUser(userWithSameName);
        });
    }

    @Test
    void testDeleteUserThatDoesntExist(){

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteUser(1l);
        });


    }





}
