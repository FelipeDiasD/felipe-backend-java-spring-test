package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.TaskService;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.felipe_dias.backend_java_spring_test.repository.TaskRepository;

import java.util.List;
@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void createTask(Task task) {

        if(task.getTitle().isEmpty() || task.getTitle() == null){
            throw new IllegalArgumentException("TITLE CANNOT BE EMPTY");
        }

        if(task.getDescription().isEmpty() || task.getDescription() == null){
            throw new IllegalArgumentException("DESCRIPTION CANNOT BE EMPTY");
        }

        if(task.getDueDate() == null){
            throw new IllegalArgumentException("DUE DATE IS OBLIGATORY");
        }

        Long userId = task.getUser().getId();

        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException(userId);
        }
        if(task.getUser() == null){
            throw new IllegalArgumentException("USER IS OBLIGATORY");
        }

        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, TaskDTO task) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        Task foundTask = taskRepository.findById(id).get();

        if(!task.getTitle().isEmpty() && task.getTitle() != null){
            foundTask.setTitle(task.getTitle());
        }

        if(!task.getDescription().isEmpty() && task.getDescription() != null){
            foundTask.setDescription(task.getDescription());
        }

        if(task.getDueDate() != null){
            foundTask.setDueDate(task.getDueDate());
        }

        if(task.getStatus().getCode() == 2){
            foundTask.setStatus(Status.EM_ANDAMENTO);
        }
        if(task.getStatus().getCode() == 3){
            foundTask.setStatus(Status.CONCLUIDA);
        }


        taskRepository.save(foundTask);
    }

    @Override
    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }

        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(Integer statusCode) {
        Status status = Status.valueOf(statusCode);
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByUser(Long id) {
       return taskRepository.findAllByUserId(id);
    }

    @Override
    public List<Task> orderTaskByDueDate() {
        return taskRepository.findByOrderByDueDateAsc();
    }


}
