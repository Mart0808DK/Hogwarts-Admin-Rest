package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> gettAll() {
        var teacher = this.teacherRepository.findAll();
        if (!teacher.isEmpty()) {
            return ResponseEntity.ok(teacher);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> get(@PathVariable Long id) {
        var teacher = this.teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return ResponseEntity.ok(teacher.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Teacher teacher) {
        if (teacher.getFirstName() == null) {
            return ResponseEntity.badRequest().body("First name is required.");
        }
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher, @PathVariable("id") Long id) {
        var teacherToUpdate = teacherRepository.findById(id);
        if(teacherToUpdate.isPresent()) {
            var updatedTeacher = teacherToUpdate.get();
            updatedTeacher.setFirstName(teacher.getFirstName());
            updatedTeacher.setMiddleName(teacher.getMiddleName());
            updatedTeacher.setLastName(teacher.getLastName());
            updatedTeacher.setDateOfBirth(teacher.getDateOfBirth());
            updatedTeacher.setHeadOfHouse(teacher.isHeadOfHouse());
            updatedTeacher.setEmployment(teacher.getEmployment());
            updatedTeacher.setEmploymentStart(teacher.getEmploymentStart());
            updatedTeacher.setEmploymentEnd(teacher.getEmploymentEnd());
            updatedTeacher.setHouse(teacher.getHouse());
            teacherRepository.save(updatedTeacher);
            return ResponseEntity.ok(updatedTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Teacher> partialUpdate(@RequestBody Teacher updatedTeacher, @PathVariable("id") Long id) {
        var teacherToUpdate = teacherRepository.findById(id);
        if(teacherToUpdate.isPresent()) {
            var existingTeacher = teacherToUpdate.get();

            if(updatedTeacher.getFirstName() != null) {
                existingTeacher.setFirstName(updatedTeacher.getFirstName());
            }
            if(updatedTeacher.getMiddleName() != null) {
                existingTeacher.setMiddleName(updatedTeacher.getMiddleName());
            }
            if(updatedTeacher.getLastName() != null) {
                existingTeacher.setLastName(updatedTeacher.getLastName());
            }
            if(updatedTeacher.getDateOfBirth() != null) {
                existingTeacher.setDateOfBirth(updatedTeacher.getDateOfBirth());
            }

            existingTeacher.setHeadOfHouse(updatedTeacher.isHeadOfHouse());

            if(updatedTeacher.getEmployment() != null) {
                existingTeacher.setEmployment(updatedTeacher.getEmployment());
            }
            if(updatedTeacher.getEmploymentStart() != null) {
                existingTeacher.setEmploymentStart(updatedTeacher.getEmploymentStart());
            }
            if(updatedTeacher.getEmploymentEnd() != null) {
                existingTeacher.setEmploymentEnd(updatedTeacher.getEmploymentEnd());
            }
            if(updatedTeacher.getHouse() != null) {
                existingTeacher.setHouse(updatedTeacher.getHouse());
            }

            teacherRepository.save(existingTeacher);
            return ResponseEntity.ok(existingTeacher);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> delete(@PathVariable("id") Long id) {
        var studentToDelete = this.teacherRepository.findById(id);
        if (studentToDelete.isPresent()) {
            this.teacherRepository.delete(studentToDelete.get());
            return ResponseEntity.ok(studentToDelete.get());
        }
        return ResponseEntity.notFound().build();
    }
}
