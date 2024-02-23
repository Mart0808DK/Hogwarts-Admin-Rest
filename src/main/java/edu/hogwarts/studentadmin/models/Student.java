package edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.EAGER)
    private House house;

    private String houseOrgin;
    private Boolean prefect;
    private Integer enrollmentYear;
    private Integer graduationYear;
    private Boolean graduated;
    private Integer schoolYear;

    public Student(Long id, String firstName, String middleName, String lastName, LocalDate dateOfBirth, House house, Boolean prefect, Integer enrollmentYear, Integer graduationYear, Boolean graduated, Integer schoolYear) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
        this.schoolYear = schoolYear;
    }

    public Student(String fullname, LocalDate dateOfBirth){
        setFullName(fullname);
        this.dateOfBirth = dateOfBirth;
        this.id = getId();
    }

    public Student(String firstName, String middleName, String lastName) {
        this();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Student(){}

    public Student(String studentName) {
    }



    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }

    @JsonIgnore
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonIgnore
    public String getMiddleName() {
        return middleName;
    }

    @JsonIgnore
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public House getHouse() {
        return house;
    }


    public void setHouse(House house) {
        this.house = house;
    }

    //@JsonGetter("house")
    public String getHouseJson() {
        if(house == null) return null;
        return house.getName();
    }

    //@JsonSetter("house")
    public void setHouseName(String houseOrgin) {
        char firstLetter = houseOrgin.charAt(0);
        houseOrgin = Character.toUpperCase(firstLetter) + houseOrgin.substring(1);
        this.houseOrgin = houseOrgin;
    }

    public Boolean getPrefect() {
        return prefect;
    }

    public Boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(Boolean prefect) {
        this.prefect = prefect;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public Boolean isGraduated(){
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    @JsonGetter("name")
    public String getFullName() {
        if (hasMiddleName() && getMiddleName() != null) {
            return firstName + " " + middleName + " " + lastName;
        } else {
            return firstName + " " + lastName;
        }
    }

    public boolean hasMiddleName() {
        return middleName != null;
    }

    @JsonSetter("name")
    public void setFullName(String fullName) {
        int firstSpaceIndex = fullName.indexOf(" ");
        int lastSpaceIndex = fullName.lastIndexOf(" ");

        if (firstSpaceIndex != -1) {
            firstName = fullName.substring(0, firstSpaceIndex);

            if (lastSpaceIndex > firstSpaceIndex) {
                middleName = fullName.substring(firstSpaceIndex + 1, lastSpaceIndex);
                lastName = fullName.substring(lastSpaceIndex + 1);
            } else {
                lastName = fullName.substring(firstSpaceIndex + 1);
            }
        } else {
            firstName = fullName;
            middleName = null;
            lastName = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", house=" + house +
                ", prefect=" + prefect +
                ", enrollmentYear=" + enrollmentYear +
                ", graduationYear=" + graduationYear +
                ", graduated=" + graduated +
                ", schoolYear=" + schoolYear +
                '}';
    }
}
