package com.felipe_dias.backend_java_spring_test.Service.impl;


import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;
import com.felipe_dias.backend_java_spring_test.model.enums.Nivel;
import com.felipe_dias.backend_java_spring_test.repository.TaskRepository;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public boolean isTaskOwner(Authentication authentication, TaskDTO task){
        String username = authentication.getName();

        User requestUser = userRepository.findByUsername(username);

        return (task.getUser().getId() == requestUser.getId() || requestUser.getNivel() == Nivel.ADMIN);

    }

    public boolean isTaskOwnerByID(Authentication authentication, Long taskId){
        String username = authentication.getName();

        User requestUser = userRepository.findByUsername(username);

        Task targetTask = taskRepository.findById(taskId).get();

        return (targetTask.getUser().getId() == requestUser.getId() || requestUser.getNivel() == Nivel.ADMIN);

    }


}
