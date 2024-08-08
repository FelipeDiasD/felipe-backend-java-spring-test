package com.felipe_dias.backend_java_spring_test.model.dto;

import com.felipe_dias.backend_java_spring_test.model.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String nivel;

    public UserDTO(){

    }

    public UserDTO(User userObj){
        this.username = userObj.getUsername();
        this.nivel = userObj.getNivel().getLabel();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
