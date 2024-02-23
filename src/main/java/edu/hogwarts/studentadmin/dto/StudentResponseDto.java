package edu.hogwarts.studentadmin.dto;

import java.time.LocalDate;

public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Boolean prefect;
    private Integer enrollmentYear;
    private Integer graduationYear;
    private Boolean graduated;
    private Integer schoolYear;

    private String house;

    // TODO: HOUSE!

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getPrefect() {
        return prefect;
    }

    public void setPrefect(Boolean prefect) {
        this.prefect = prefect;
    }

    public Integer getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Boolean getGraduated() {
        return graduated;
    }

    public void setGraduated(Boolean graduated) {
        this.graduated = graduated;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

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

//    public void setFullName(String fullName) {
//        int firstSpaceIndex = fullName.indexOf(" ");
//        int lastSpaceIndex = fullName.lastIndexOf(" ");
//
//        if (firstSpaceIndex != -1) {
//            firstName = fullName.substring(0, firstSpaceIndex);
//
//            if (lastSpaceIndex > firstSpaceIndex) {
//                middleName = fullName.substring(firstSpaceIndex + 1, lastSpaceIndex);
//                lastName = fullName.substring(lastSpaceIndex + 1);
//            } else {
//                lastName = fullName.substring(firstSpaceIndex + 1);
//            }
//        } else {
//            firstName = fullName;
//            middleName = null;
//            lastName = null;
//        }
//    }
}
