package io.ugurh.sphub.student.service;

import io.ugurh.sphub.student.entity.Student;
import io.ugurh.sphub.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 27.04.2023 - 23:55
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Student updateNameById(final Integer id, final String newName) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setName(newName);
        return student;
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }
}
