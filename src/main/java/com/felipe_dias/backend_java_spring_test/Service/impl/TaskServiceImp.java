package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Service.TaskService;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.felipe_dias.backend_java_spring_test.repository.TaskRepository;

import java.util.List;
@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public void createTask(Task task) {

    }

    @Override
    public void updateTask(Long id, Task task) {

    }

    @Override
    public void deleteTask(Long id, Task task) {

    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        return List.of();
    }

    @Override
    public List<Task> getTasksByUser(Long id) {
        return List.of();
    }
}
