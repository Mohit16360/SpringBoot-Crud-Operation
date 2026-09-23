package com.example.crudSpringBoot.repository;

import com.example.crudSpringBoot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedIsFalse();

    Boolean existsByEmail(String email);

    // I do not need to override all the method provided by jpaRepo the spring jpa provide their implementation at the runtime


}
