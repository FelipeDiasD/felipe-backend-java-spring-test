package com.felipe_dias.backend_java_spring_test.Service;

import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();
    public void createTask(Task task);
    public void updateTask(Long id, TaskDTO task);
    public void deleteTask(Long id);
    public List<Task> getTasksByStatus(Integer statusCode);
    public List<Task> getTasksByUser(Long id);
    public List<Task> orderTaskByDueDate();

}
