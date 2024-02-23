package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.dto.StudentRequestDto;
import edu.hogwarts.studentadmin.dto.StudentResponseDto;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentServices;

    public StudentController(StudentService studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        var students = this.studentServices.findAll();
        if (students != null) {
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> get(@PathVariable Long id) {
        var student = this.studentServices.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }
        return ResponseEntity.notFound().build();
    }

//    @PostMapping
//    public ResponseEntity<Object> create(@RequestBody Student student) {
//        if (student.getFirstName() == null) {
//            return ResponseEntity.badRequest().body("First name is required.");
//        }
//        return ResponseEntity.ok(studentServices.save(student));
//    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@RequestBody StudentRequestDto student) {
        // Save the student to the database
        StudentResponseDto savedStudent = studentServices.save(student);

        // Return response with the saved student
        return ResponseEntity.ok(savedStudent);
    }



//    @PatchMapping("/{id}")
//    public ResponseEntity<Student> patch(@RequestBody Student updatedStudent, @PathVariable("id") Long id) {
//        var studentToUpdate = studentServices.findById(id);
//        if (studentToUpdate.isPresent()) {
//            var existingStudent = studentToUpdate.get();
//
//            if (updatedStudent.getFirstName() != null) {
//                existingStudent.setFirstName(updatedStudent.getFirstName());
//            }
//            if (updatedStudent.getMiddleName() != null) {
//                existingStudent.setMiddleName(updatedStudent.getMiddleName());
//            }
//            if (updatedStudent.getLastName() != null) {
//                existingStudent.setLastName(updatedStudent.getLastName());
//            }
//            if (updatedStudent.getDateOfBirth() != null) {
//                existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
//            }
//            if (updatedStudent.isPrefect() != null) {
//                existingStudent.setPrefect(updatedStudent.isPrefect());
//            }
//            if (updatedStudent.getEnrollmentYear() != null) {
//                existingStudent.setEnrollmentYear(updatedStudent.getEnrollmentYear());
//            }
//            if (updatedStudent.getGraduationYear() != null) {
//                existingStudent.setGraduationYear(updatedStudent.getGraduationYear());
//            }
//            if (updatedStudent.isGraduated() != null) {
//                existingStudent.setGraduated(updatedStudent.isGraduated());
//            }
//            if (updatedStudent.getHouse() != null) {
//                existingStudent.setHouse(updatedStudent.getHouse());
//            }
//
//            studentServices.save(existingStudent);
//            return ResponseEntity.ok(existingStudent);
//        }
//        return ResponseEntity.notFound().build();
//    }




    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto student, @PathVariable("id") Long id) {
        return ResponseEntity.of(studentServices.updateIfExist(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDto> delete(@PathVariable("id") Long id) {
        return ResponseEntity.of(studentServices.deleteById(id));
    }
}
