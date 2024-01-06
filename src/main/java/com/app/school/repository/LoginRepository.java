package com.app.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.school.entity.Login;
@Repository
public interface LoginRepository extends JpaRepository<Login,UUID>{

	
	@Query(value ="select * from login as s where s.log_email=:email and s.log_password=:password",nativeQuery = true)
	Login findAllLogin(@Param("email") String email,@Param("password") String password);

	

}
