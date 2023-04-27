package io.ugurh.sphub.student.repository;

import io.ugurh.sphub.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 27.04.2023 - 23:53
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
