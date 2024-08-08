package com.felipe_dias.backend_java_spring_test.model.dto;

import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private Date createdAt;
    private Date dueDate;
    private Status status;
    private Long userID;

    public TaskDTO(){

    }

    public TaskDTO(Task taskObj){
        this.title = taskObj.getTitle();
        this.description = taskObj.getDescription();
        this.createdAt = taskObj.getCreatedAt();
        this.dueDate = taskObj.getDueDate();
        this.status = taskObj.getStatus();
        this.userID = taskObj.getUser().getId();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus(){
        return status;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }


    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
