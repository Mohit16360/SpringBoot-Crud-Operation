package com.example.crudSpringBoot.service;

import com.example.crudSpringBoot.dto.StudentRequestDto;
import com.example.crudSpringBoot.dto.StudentResponseDto;
import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

        Student student = mapDtoToEntity(studentRequestDto);
        student.setCretaedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        Student studentResp = studentRepository.save(student);



        return MapEntityToDto(studentResp);



    }




    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;
    }





    public List<Student> getAll() {
        List<Student> studentList =  studentRepository.findByDeletedIsFalse();
        return studentList;
    }





    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentTosave = existingStudent.get();

        studentTosave.setName(studentReq.getName());
        studentTosave.setAge(studentReq.getAge());
        studentTosave.setEmail(studentReq.getEmail());
        studentTosave.setRoll_no(studentReq.getRoll_no());
        studentTosave.setSubject(studentReq.getSubject());
        studentTosave.setDeleted(false);

        return studentRepository.save(studentTosave);

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



    private Student mapDtoToEntity(StudentRequestDto studentRequestDto){
        Student  student = new Student();

        student.setDeleted(false);

        student.setSubject(studentRequestDto.getSubject());
        student.setRoll_no(studentRequestDto.getRoll_no());
        student.setEmail(studentRequestDto.getEmail());
        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());

        return student;

    }

    private StudentResponseDto MapEntityToDto(Student student) {
        StudentResponseDto studentResponseDto = new StudentResponseDto();

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







 }
