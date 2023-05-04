package io.ugurh.sphub.student.entity;


import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 27.04.2023 - 22:43
 */
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}