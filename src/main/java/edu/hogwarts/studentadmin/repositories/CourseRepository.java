package edu.hogwarts.studentadmin.repositories;

import edu.hogwarts.studentadmin.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
