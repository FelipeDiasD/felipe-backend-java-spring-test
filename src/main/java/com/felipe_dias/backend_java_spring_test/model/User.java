package com.felipe_dias.backend_java_spring_test.model;

import com.felipe_dias.backend_java_spring_test.model.enums.Nivel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nivel;

    public User(){
    }

    public User(Long id, String username, Nivel nivel) {
        this.id = id;
        this.username = username;
        setNivel(nivel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Nivel getNivel() {
        return Nivel.valueOfLabel(nivel);
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel.getLabel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
