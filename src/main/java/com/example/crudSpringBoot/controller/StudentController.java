package com.example.crudSpringBoot.controller;


import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create students
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    //read one student
    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id) {
        Student studentResp = studentService.getStudent(id);
        if(studentResp == null){
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.
                status(HttpStatus.OK).
                body(studentResp);
    }

    //get all
   @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentListResp = studentService.getAll();
       if(studentListResp.isEmpty()){
           return ResponseEntity.
                   status(HttpStatus.NOT_FOUND).body(null);
       }
       return ResponseEntity.
               status(HttpStatus.OK).
               body(studentListResp);
    }



    //update student

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> UpdateStudent(@PathVariable Long id, @RequestBody Student studentReq) {
        Student studentResp = studentService.updateStudent(id, studentReq);
        if(studentResp == null){
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.
                status(HttpStatus.OK).
                body(studentResp);
    }


    //delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean isdeleted = studentService.deletedStudent(id);
        if(!isdeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");
    }

    @PatchMapping("/delete-soft/{id}")
    public  ResponseEntity<String> deletestudentSoftly(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudentSoft(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");
    }









}
