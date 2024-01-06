package com.app.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.school.entity.Staffes;

public interface StaffesRepository extends JpaRepository<Staffes,UUID> {

}
