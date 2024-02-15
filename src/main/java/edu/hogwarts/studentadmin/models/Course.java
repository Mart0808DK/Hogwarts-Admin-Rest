package edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.Arrays;
@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private int schoolYear;
    private Boolean current;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Student[] student;

    public Course(Long id, String subject, int schoolYear, Boolean current, Teacher teacher, Student[] student) {
        this.id = id;
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.teacher = teacher;
        this.student = student;
    }

    public Course(){}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudent() {
        return student;
    }

    public void setStudent(Student[] student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", schoolYear=" + schoolYear +
                ", current=" + current +
                ", teacher=" + teacher +
                ", student=" + Arrays.toString(student) +
                '}';
    }
}
