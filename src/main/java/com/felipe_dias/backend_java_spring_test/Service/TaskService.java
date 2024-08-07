package com.felipe_dias.backend_java_spring_test.Service;

import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.Status;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();
    public void createTask(Task task);
    public void updateTask(Long id, Task task);
    public void deleteTask(Long id, Task task);
    public List<Task> getTasksByStatus(Status status);
    public List<Task> getTasksByUser(Long id);

}
