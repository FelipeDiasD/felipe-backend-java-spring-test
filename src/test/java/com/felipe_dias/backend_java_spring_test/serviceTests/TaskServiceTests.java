package com.felipe_dias.backend_java_spring_test.serviceTests;

import com.felipe_dias.backend_java_spring_test.Service.TaskService;
import com.felipe_dias.backend_java_spring_test.Service.impl.TaskServiceImp;
import com.felipe_dias.backend_java_spring_test.model.Task;
import com.felipe_dias.backend_java_spring_test.model.User;
import com.felipe_dias.backend_java_spring_test.model.enums.Nivel;
import com.felipe_dias.backend_java_spring_test.model.enums.Status;
import com.felipe_dias.backend_java_spring_test.repository.TaskRepository;
import com.felipe_dias.backend_java_spring_test.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TaskServiceTests {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImp taskService;

    @Test
    void testGettingAllTasks(){

        Date today = new Date();

        //given
        User u1 = new User(1L, "user1", Nivel.USER, "1234");
        Task t1 = new Task(1L, "task1", "task1", today, today, Status.PENDENTE, u1);
        Task t2 = new Task(2L, "task2", "task1", today, today, Status.PENDENTE, u1);
        Task t3 = new Task(3L, "task3", "task1", today, today, Status.PENDENTE, u1);

        //when
        given(taskRepository.findAll()).willReturn(List.of(t1, t2, t3));

        var taskList = taskService.getAllTasks();

        assertThat(taskList).isNotNull();
        assertThat(taskList.size()).isEqualTo(3);
    }

    @Test
    void testCreatingATask(){
        Date today = new Date();

        User u1 = new User(1L, "user1", Nivel.USER, "1234");
        Task t1 = new Task(1L, "task1", "task1", today, today, Status.PENDENTE, u1);

        given(taskRepository.save(t1)).willReturn(t1);
        given(userRepository.existsById(u1.getId())).willReturn(true);

        assertThat(taskService.createTask(t1)).isEqualTo(t1);

    }

    void testUpdatingTask(){

    }

    void testDeletingTask(){

    }


}
