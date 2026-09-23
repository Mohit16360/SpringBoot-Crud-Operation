package com.example.crudSpringBoot.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name cannot be null/Empty or blank")
    @Size(min = 2 , max = 50, message = "Student name ,ust be within 2 to 50 character long")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student must be 18 year old")
    private int age;

    @NotEmpty(message = "Roll is required")
    private int  roll_no;

    @NotBlank(message = "Student name cannot be blank")
    @Email(message = "Student email must be valid")
    private String email;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject() {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
