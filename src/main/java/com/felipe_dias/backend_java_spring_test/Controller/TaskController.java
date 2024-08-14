package com.felipe_dias.backend_java_spring_test.Controller;

import com.felipe_dias.backend_java_spring_test.Service.impl.TaskServiceImp;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImp taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskDTO taskObj){


        Task taskToCreate = taskService.fromDto(taskObj);
        taskToCreate = taskService.createTask(taskToCreate);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(taskToCreate.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/task/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskDTO task){
        taskService.updateTask(id, task);
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED TASK");
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity deleteTaskById(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED TASK");
    }

   @GetMapping("/status")
    public ResponseEntity<List<TaskDTO>> getTaskByStatus(@RequestParam Status status){
        List<TaskDTO> tasks = taskService.getTasksByStatus(status.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/userId")
    public ResponseEntity<List<TaskDTO>> getTaskByUserId(@RequestParam Long userId){
        List<TaskDTO> tasks = taskService.getTasksByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<TaskDTO>> orderTasks(@RequestParam String sort){

        if(sort.equals("dueDate")){
            List<TaskDTO> tasks = taskService.orderTaskByDueDate();
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        }

        throw new IllegalArgumentException("UNKNOWN SORT METHOD");
    }


}
