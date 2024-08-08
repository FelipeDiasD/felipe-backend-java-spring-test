package com.felipe_dias.backend_java_spring_test.Controller;

import com.felipe_dias.backend_java_spring_test.Service.impl.TaskServiceImp;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.dto.TaskDTO;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImp taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody Task task){
        taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable Long id, @RequestBody TaskDTO task){
        taskService.updateTask(id, task);
        return ResponseEntity.status(HttpStatus.OK).body("UPDATED TASK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskById(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED TASK");
    }

   @GetMapping("status")
    public ResponseEntity<List<Task>> getTaskByStatus(@RequestParam Status status){
        List<Task> tasks = taskService.getTasksByStatus(status.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("sort")
    public ResponseEntity<List<Task>> orderTasks(@RequestParam String sort){
        if(sort.equals("dueDate")){
            List<Task> tasks = taskService.orderTaskByDueDate();
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        }

        throw new IllegalArgumentException("UNKNOWN SORT METHOD");
    }


}
