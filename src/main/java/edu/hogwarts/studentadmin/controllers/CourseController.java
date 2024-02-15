package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Course;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository ) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        var course = this.courseRepository.findAll();
        if (!course.isEmpty()) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable long id) {
        var course = this.courseRepository.findById(id);
        if(course.isPresent()) {
            return ResponseEntity.ok(course.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Course course){
        if(course.getSubject() == null){
            return ResponseEntity.badRequest().body("Subject is required");
        }
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@RequestBody Course course, @PathVariable("id") Long id) {
        var courseToUpdated = courseRepository.findById(id);
        if (courseToUpdated.isPresent()) {
            var updatedCourse = courseToUpdated.get();
            updatedCourse.setSubject(course.getSubject());
            updatedCourse.setSchoolYear(course.getSchoolYear());
            updatedCourse.setCurrent(course.isCurrent());
            courseRepository.save(updatedCourse);
            return ResponseEntity.ok(updatedCourse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable("id") Long id) {
        var courseToDelete = this.courseRepository.findById(id);
        if (courseToDelete.isPresent()) {
            this.courseRepository.delete(courseToDelete.get());
            return ResponseEntity.ok(courseToDelete.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> getTeacher(@PathVariable long id) {
        var courseOptional = this.courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = courseOptional.get();
        Teacher teacher = course.getTeacher();

        if (teacher == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudents(@PathVariable long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = courseOptional.get();
        List<Student> students = course.getStudents();

        if (students == null || students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}/teacher/{teacherId}")
    public ResponseEntity<Course> setTeacherForCourse(@PathVariable long id, @PathVariable long teacherId) {

        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if (teacher == null) {
            return ResponseEntity.notFound().build();
        }

        course.setTeacher(teacher);
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }


    @PutMapping("/{id}/students/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable long id, @PathVariable long studentId) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        List<Student> students = course.getStudents();
        if (students == null) {
            students = List.of(student); // Use List.of() for immutability
        } else {
            students.add(student);
        }

        course.setStudents(students);
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}/teacher")
    public ResponseEntity<Course> removeTeacherFromCourse(@PathVariable long id) {

        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        course.setTeacher(null);
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}/students/{studentId}")
    public ResponseEntity<Course> removeStudentFromCourse(@PathVariable long id, @PathVariable long studentId) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        List<Student> students = course.getStudents();
        if (students != null) {
            students.removeIf(student -> student.getId() == studentId);
        }

        course.setStudents(students);
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }
}