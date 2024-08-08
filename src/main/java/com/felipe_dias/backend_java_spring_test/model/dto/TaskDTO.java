package com.felipe_dias.backend_java_spring_test.model.dto;

import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;

public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer status;

    public TaskDTO(){

    }

    public TaskDTO(Task taskObj){
        this.title = taskObj.getTitle();
        this.description = taskObj.getDescription();
        this.dueDate = taskObj.getDueDate();
        this.status = taskObj.getStatus().getCode();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public void setStatus(Status status) {
        this.status = status.getCode();
    }
}
