package com.felipe_dias.backend_java_spring_test.repository;

import com.felipe_dias.backend_java_spring_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);
}
