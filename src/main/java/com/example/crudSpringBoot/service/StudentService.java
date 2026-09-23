package com.example.crudSpringBoot.service;

import com.example.crudSpringBoot.dto.CreateStudentRequestDto;
import com.example.crudSpringBoot.dto.CreateStudentResponseDto;
import com.example.crudSpringBoot.dto.UpdateStudentRequestDto;
import com.example.crudSpringBoot.dto.UpdateStudentResponseDto;
import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto) {

        Student student = mapDtoToEntity(studentRequestDto);
        student.setCretaedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student studentResp = studentRepository.save(student);



        return MapEntityToDto(studentResp);



    }




    public CreateStudentResponseDto getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return MapEntityToDto(studentResp.get());
        }
        return null;
    }





    public List<CreateStudentResponseDto> getAll() {
        List<Student> studentList =  studentRepository.findByDeletedIsFalse();

        return studentList.stream()
                .map(this::MapEntityToDto)
                .toList();
    }





    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Optional<Student> existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentTosave = existingStudent.get();

        studentTosave.setName(studentReq.getName());
        studentTosave.setAge(studentReq.getAge());
        studentTosave.setRoll_no(studentReq.getRoll_no());
        studentTosave.setSubject(studentReq.getSubject());
        studentTosave.setUpdatedAt(LocalDateTime.now());

        Student savedStudent =  studentRepository.save(studentTosave);

        return mapToUpdateDto(savedStudent);

    }


    public Boolean deletedStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);
        if(!isStudent) return false;

        studentRepository.deleteById(id);
        return true;
    }



    public Boolean deleteStudentSoft(Long id) {
        //get
        //delete = 1
        //save

         Optional<Student> existingStudent
                 = studentRepository.findByIdAndDeletedIsFalse(id);

         if(existingStudent.isEmpty()) {
             return false;
         }

         Student studentToSave = existingStudent.get();
         studentToSave.setDeleted(true);

         studentRepository.save(studentToSave);

         return true;



    }



    private Student mapDtoToEntity(CreateStudentRequestDto studentRequestDto){
        Student  student = new Student();

        student.setDeleted(false);

        student.setSubject(studentRequestDto.getSubject());
        student.setRoll_no(studentRequestDto.getRoll_no());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());

        return student;

    }

    private CreateStudentResponseDto MapEntityToDto(Student student) {
        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setRoll_no(student.getRoll_no());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setMessage("Student saved Successfully");
        studentResponseDto.setCretaedAt(student.getCretaedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return  studentResponseDto;
    }


    private UpdateStudentResponseDto mapToUpdateDto(Student student) {
        UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setRoll_no(student.getRoll_no());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setMessage("Student Updated Successfully");

        studentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return  studentResponseDto;
    }







 }
