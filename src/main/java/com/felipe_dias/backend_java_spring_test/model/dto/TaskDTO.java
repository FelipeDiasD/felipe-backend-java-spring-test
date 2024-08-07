package com.felipe_dias.backend_java_spring_test.model.dto;

import com.felipe_dias.backend_java_spring_test.model.Status;

import java.time.LocalDate;

public class TaskDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;

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
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
