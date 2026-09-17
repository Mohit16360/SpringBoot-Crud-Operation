package com.example.crudSpringBoot.service;

import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
         Student studentResponse = studentRepository.save(studentReq); // save can do both things insert and update
         return studentResponse;
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







 }
