package io.ugurh.sphub.student.controller;

import io.ugurh.sphub.student.entity.Student;
import io.ugurh.sphub.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 27.04.2023 - 23:58
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student newStudent) {
        Student student = studentService.save(newStudent);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Student> updateNameById(@PathVariable Integer id, @RequestBody Student newStudent) {
        Student student = studentService.updateNameById(id, newStudent.getName());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
