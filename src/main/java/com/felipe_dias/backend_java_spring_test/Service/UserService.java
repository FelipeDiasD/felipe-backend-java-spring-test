package com.felipe_dias.backend_java_spring_test.Service;

import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<UserDTO> getAllUsers();
    public void createUser(User user);
    public void updateUser(Long id, UserDTO user);
    public void deleteUser(Long id);
}
