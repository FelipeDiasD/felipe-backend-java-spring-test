package com.felipe_dias.backend_java_spring_test.Service.impl;

import com.felipe_dias.backend_java_spring_test.Controller.Exceptions.ResourceNotFoundException;
import com.felipe_dias.backend_java_spring_test.Service.TaskService;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.felipe_dias.backend_java_spring_test.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        List<TaskDTO> dtoList = taskList.stream()
                                        .map(task -> new TaskDTO(task))
                                        .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Task createTask(Task task) {


        if(task.getTitle().isEmpty() || task.getTitle() == null){
            throw new IllegalArgumentException("TITLE CANNOT BE EMPTY/NULL");
        }

        if(task.getDescription().isEmpty() || task.getDescription() == null){
            throw new IllegalArgumentException("DESCRIPTION CANNOT BE EMPTY/NULL");
        }

        if(task.getDueDate() == null){
            throw new IllegalArgumentException("DUE DATE IS OBLIGATORY");
        }

        if(task.getStatus() == null){
            task.setStatus(Status.PENDENTE);
        }

        Long userId = task.getUser().getId();

        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException(userId);
        }
        if(task.getUser() == null){
            throw new IllegalArgumentException("USER IS OBLIGATORY");
        }

        return taskRepository.save(task);
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

        if(task.getStatus().getCode() > 3 || task.getStatus().getCode() < 1){
            throw  new IllegalArgumentException("INVALID STATUS");
        }

        if(task.getStatus() != null) {
            foundTask.setStatus(task.getStatus());
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
    public List<TaskDTO> getTasksByStatus(Integer statusCode) {
        Status status = Status.valueOf(statusCode);

        List<Task> taskListByStatus = taskRepository.findByStatus(status);

        List<TaskDTO> dtoListByStatus = taskListByStatus.stream()
                .map(task -> new TaskDTO(task))
                .collect(Collectors.toList());

        return dtoListByStatus;
    }

    @Override
    public List<TaskDTO> getTasksByUser(Long id) {

        List<Task> taskListByUser = taskRepository.findAllByUserId(id);

        List<TaskDTO> dtoListByUser = taskListByUser.stream()
                .map(task -> new TaskDTO(task))
                .collect(Collectors.toList());

       return dtoListByUser;
    }

    @Override
    public List<TaskDTO> orderTaskByDueDate() {

        List<Task> taskSortedByDueDate = taskRepository.findByOrderByDueDateAsc();

        List<TaskDTO> dtoSortedByDueDate = taskSortedByDueDate.stream()
                .map(task -> new TaskDTO(task))
                .collect(Collectors.toList());

        return dtoSortedByDueDate;
    }

    public Task fromDto(TaskDTO taskObj){

        Long userId = taskObj.getUser().getId();
        if(!userRepository.existsById(userId)){

            throw new ResourceNotFoundException(userId);
        }

        User taskUser = userRepository.findById(userId).get();

        Task taskToCreate =
                new Task(taskObj.getTitle(),
                                  taskObj.getDescription(),
                                  taskObj.getDueDate(),
                                  taskObj.getStatus(), taskUser);

        return taskToCreate;
    }


}
