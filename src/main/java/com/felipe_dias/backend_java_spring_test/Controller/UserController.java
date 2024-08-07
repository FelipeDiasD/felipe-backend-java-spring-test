package com.felipe_dias.backend_java_spring_test.Controller;

import com.felipe_dias.backend_java_spring_test.Service.impl.UserServiceImp;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, UserDTO userObj){
        userService.updateUser(id, userObj);
        return ResponseEntity.ok("USUARIO ATUALIZADO");
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("USUARIO DELETADO");
    }
}
