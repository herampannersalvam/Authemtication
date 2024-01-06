package com.app.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.school.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,UUID>{


	

}
