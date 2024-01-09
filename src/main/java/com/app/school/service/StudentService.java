	package com.app.school.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.school.comman.APIResponse;

import com.app.school.entity.Login;
import com.app.school.entity.Staffes;
import com.app.school.entity.Student;
import com.app.school.repository.LoginRepository;
import com.app.school.repository.StaffesRepository;
import com.app.school.repository.StudentRepository;
import com.app.school.util.JwtUtil;

@Service
public class StudentService {

	@Autowired
	StaffesRepository staffesRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	JwtUtil jwtUtil;

	public String create(Student student) {
		Login log = null;
		studentRepository.saveAndFlush(student);
		log = new Login();

		log.setEmail(student.getEmail());
		log.setPassword(student.getPassword());
		log.setUserId(student.getStuId());
		log.setRole("Student");

		loginRepository.save(log);
		return "POST";
	}

	public String staffesPost(Staffes staffes) {
		// TODO Auto-generated method stub
		Login log = null;
		staffesRepository.saveAndFlush(staffes);
		log = new Login();

		log.setEmail(staffes.getEmail());
		log.setPassword(staffes.getPassword());
		log.setUserId(staffes.getStaId());
		log.setRole("Staff");

		loginRepository.save(log);
		return "POST";
	}

	public APIResponse logined(Login login) {
		// TODO Auto-generated method stub
		APIResponse apiRespons = new APIResponse();

		Login UserLogin = loginRepository.findAllLogin(login.getEmail(), login.getPassword());

		if (UserLogin != null) {
			apiRespons.setStatus(HttpStatus.OK.value());
			String token = jwtUtil.generatejwt(UserLogin);
			Map<Object, Object> data = new HashMap<Object, Object>();
			data.put("Token", token);
			data.put("message", "Logged in successfully");
			data.put("UserId", UserLogin.getUserId());
			data.put("Role", UserLogin.getRole());
			System.out.println(token);
			apiRespons.setData(data);
			return apiRespons;
		} else {
			apiRespons.setData("User login failed");
			apiRespons.setStatus(HttpStatus.BAD_REQUEST.value());
			apiRespons.setError("incorrect passward or emailId");
			return apiRespons;
		}
	}

}
