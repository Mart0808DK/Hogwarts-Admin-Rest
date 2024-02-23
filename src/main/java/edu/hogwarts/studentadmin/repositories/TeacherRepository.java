package edu.hogwarts.studentadmin.repositories;

import edu.hogwarts.studentadmin.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
