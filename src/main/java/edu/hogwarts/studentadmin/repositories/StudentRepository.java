package edu.hogwarts.studentadmin.repositories;

import edu.hogwarts.studentadmin.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findFirstByFirstNameIgnoreCase(String firstName);

    Optional<Student> findFirstByMiddleNameIgnoreCase(String middleName);

    Optional<Student> findFirstByLastNameIgnoreCase(String lastName);

    Optional<Student>findFirstByFirstNameIgnoreCaseAndMiddleNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String middleName, String lastName);

    Student findByFirstName(String studentName);
}
