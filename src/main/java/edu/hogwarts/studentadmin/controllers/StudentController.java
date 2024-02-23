package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentServices;

    public StudentController(StudentService studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public ResponseEntity<Iterable<Student>> getAll() {
        var students = this.studentServices.findAll();
        if (students != null) {
            return ResponseEntity.ok(students);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
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
    public ResponseEntity<Object> create(@RequestBody Student student) {
        // Assuming you have a valid full name in the student object
        String fullName = student.getFullName();
        if (fullName == null || fullName.isEmpty()) {
            return ResponseEntity.badRequest().body("Full name cannot be empty.");
        }

        // Save the student to the database
        Student savedStudent = studentServices.save(student);

        // Return response with the saved student
        return ResponseEntity.ok(savedStudent);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<Student> patch(@RequestBody Student updatedStudent, @PathVariable("id") Long id) {
        var studentToUpdate = studentServices.findById(id);
        if (studentToUpdate.isPresent()) {
            var existingStudent = studentToUpdate.get();

            if (updatedStudent.getFirstName() != null) {
                existingStudent.setFirstName(updatedStudent.getFirstName());
            }
            if (updatedStudent.getMiddleName() != null) {
                existingStudent.setMiddleName(updatedStudent.getMiddleName());
            }
            if (updatedStudent.getLastName() != null) {
                existingStudent.setLastName(updatedStudent.getLastName());
            }
            if (updatedStudent.getDateOfBirth() != null) {
                existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            }
            if (updatedStudent.isPrefect() != null) {
                existingStudent.setPrefect(updatedStudent.isPrefect());
            }
            if (updatedStudent.getEnrollmentYear() != null) {
                existingStudent.setEnrollmentYear(updatedStudent.getEnrollmentYear());
            }
            if (updatedStudent.getGraduationYear() != null) {
                existingStudent.setGraduationYear(updatedStudent.getGraduationYear());
            }
            if (updatedStudent.isGraduated() != null) {
                existingStudent.setGraduated(updatedStudent.isGraduated());
            }
            if (updatedStudent.getHouse() != null) {
                existingStudent.setHouse(updatedStudent.getHouse());
            }

            studentServices.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        }
        return ResponseEntity.notFound().build();
    }




    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody Student student, @PathVariable("id") Long id) {
        return ResponseEntity.of(studentServices.updateIfExist(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        return ResponseEntity.of(studentServices.deleteById(id));
    }
}
