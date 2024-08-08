package com.felipe_dias.backend_java_spring_test.model.dto;

import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.enums.Nivel;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String username;
    private Nivel nivel;
    private String password;

    public UserDTO(){

    }

    public UserDTO(User userObj){
        this.username = userObj.getUsername();
        this.nivel = userObj.getNivel();
        this.password = userObj.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
