package com.felipe_dias.backend_java_spring_test.Controller.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id){
        super("RECURSO NÃO ENCONTRADO. ID: " + id);
    }

    public ResourceNotFoundException (String title){
        super("RECURSO NÃO ENCONTRADO. ID: " + title);
    }
}