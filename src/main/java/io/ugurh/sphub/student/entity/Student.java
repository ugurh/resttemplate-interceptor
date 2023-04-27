package io.ugurh.sphub.student.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 27.04.2023 - 22:43
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}