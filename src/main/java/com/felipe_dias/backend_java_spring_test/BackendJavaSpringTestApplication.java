package com.felipe_dias.backend_java_spring_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class BackendJavaSpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendJavaSpringTestApplication.class, args);
	}

}
