package edu.hogwarts.studentadmin.services;

import edu.hogwarts.studentadmin.dto.StudentRequestDto;
import edu.hogwarts.studentadmin.dto.StudentResponseDto;
import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;

    public StudentService(StudentRepository studentRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
    }

    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream().map(this::toDto).toList();
    }

    public Optional<StudentResponseDto> findById(Long id) {
        return studentRepository.findById(id).map(this::toDto);
    }


    public StudentResponseDto save(StudentRequestDto student) {
        return toDto(studentRepository.save(toEntity(student)));
    }



    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public Optional<StudentResponseDto> deleteById(Long id) {
        Optional<StudentResponseDto> existingStudent = findById(id);
        studentRepository.deleteById(id);
        return existingStudent;
    }

    public Optional<StudentResponseDto> updateIfExist(Long id, StudentRequestDto student) {
        if (studentRepository.existsById(id)){
            Student entity = toEntity(student);
            entity.setId(id);
            return Optional.of(toDto(studentRepository.save(entity)));
        }

        return Optional.empty();
    }



    public StudentResponseDto toDto(Student entity) {
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setPrefect(entity.isPrefect());
        dto.setEnrollmentYear(entity.getEnrollmentYear());
        dto.setGraduationYear(entity.getGraduationYear());
        dto.setSchoolYear(entity.getSchoolYear());
        dto.setGraduated(entity.isGraduated());
        dto.setHouse(entity.getHouse().getName());

        return dto;
    }

    private Student toEntity(StudentRequestDto dto) {
        Student entity = new Student();
        entity.setId(dto.id());
        entity.setFirstName(dto.firstName());
        entity.setMiddleName(dto.middleName());
        entity.setLastName(dto.lastName());
        entity.setDateOfBirth(dto.dateOfBirth());
        entity.setPrefect(dto.prefect());
        entity.setEnrollmentYear(dto.enrollmentYear());
        entity.setGraduationYear(dto.graduationYear());
        entity.setSchoolYear(dto.schoolYear());
        entity.setGraduated(dto.graduated());

        Optional<House> house = houseRepository.findById(dto.house());
        house.ifPresent(entity::setHouse);

        return entity;

    }
}
