package edu.hogwarts.studentadmin.services;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }


    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public Optional<Student> deleteById(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return existingStudent;
    }

    public Optional<Student> updateIfExist(Long id, Student student) {
        var studentToUpdate = studentRepository.findById(id);
        if (studentToUpdate.isPresent()) {
            var updatedStudent = studentToUpdate.get();
            updatedStudent.setFullName(student.getFullName());
            updatedStudent.setDateOfBirth(student.getDateOfBirth());
            updatedStudent.setPrefect(student.isPrefect());
            updatedStudent.setEnrollmentYear(student.getEnrollmentYear());
            updatedStudent.setGraduationYear(student.getGraduationYear());
            updatedStudent.setGraduated(student.isGraduated());
            updatedStudent.setHouse(student.getHouse());
            studentRepository.save(updatedStudent);
            return Optional.of(updatedStudent);
        }
        return Optional.empty();
    }
}
