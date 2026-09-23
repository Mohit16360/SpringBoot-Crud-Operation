package com.example.crudSpringBoot.controller;


import com.example.crudSpringBoot.dto.CreateStudentRequestDto;
import com.example.crudSpringBoot.dto.CreateStudentResponseDto;
import com.example.crudSpringBoot.dto.UpdateStudentRequestDto;
import com.example.crudSpringBoot.dto.UpdateStudentResponseDto;
import com.example.crudSpringBoot.entity.Student;
import com.example.crudSpringBoot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
            @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        CreateStudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }




    //read one student
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id) {
        CreateStudentResponseDto studentResp = studentService.getStudent(id);

        return ResponseEntity.
                status(HttpStatus.OK).
                body(studentResp);
    }



    //get all
   @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAll() {
        List<CreateStudentResponseDto> studentListResp = studentService.getAll();
       if(studentListResp.isEmpty()){
           return ResponseEntity.
                   status(HttpStatus.NOT_FOUND).body(null);
       }
       return ResponseEntity.
               status(HttpStatus.OK).
               body(studentListResp);
    }



    //update student
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDto> UpdateStudent(@RequestParam Long id,
                                                                 @RequestBody UpdateStudentRequestDto studentReq) {
        UpdateStudentResponseDto studentResp =
                studentService.updateStudent(id, studentReq);

        if(studentResp == null){
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studentResp);
    }




    //delete student
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        boolean isdeleted = studentService.deletedStudent(id);
        if(!isdeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");
    }





    @PatchMapping("/delete-soft")
    public  ResponseEntity<String> deletestudentSoftly(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudentSoft(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Record Deleted");
    }


}
