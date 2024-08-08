package com.felipe_dias.backend_java_spring_test.Controller.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id){
        super("RESOURCE NOT FOUND WITH ID: " + id);
    }

}