package com.felipe_dias.backend_java_spring_test.repository;

import com.felipe_dias.backend_java_spring_test.model.Status;
import com.felipe_dias.backend_java_spring_test.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);
}
