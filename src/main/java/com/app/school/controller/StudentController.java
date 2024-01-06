package com.app.school.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.school.comman.APIResponse;
import com.app.school.entity.Login;
import com.app.school.entity.Staffes;
import com.app.school.entity.Student;
import com.app.school.service.StudentService;
import com.app.school.util.JwtUtil;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/studentPost")
	@ApiOperation(value = "Allows to create new Student.")
	public String create(@RequestBody Student student) {
		return studentService.create(student);
	}
	@PostMapping("/staffesPost")
	@ApiOperation(value = "Allows to create new Staffes.")
	public String staffesPost(@RequestBody Staffes staffes) {
		return studentService.staffesPost(staffes);
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "Allows to create Token and Login")
	public ResponseEntity<APIResponse>logined(@RequestBody Login login){
		APIResponse apiResponse=studentService.logined(login);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
	}
	@GetMapping("/Verify")
	@ApiOperation(value = "Allows to  Token Verify")
	public ResponseEntity<APIResponse> verify(@RequestHeader(value = "Authorization") String auth) throws Exception{
		APIResponse apiResponse=new APIResponse();
		Map<String, Object> response = new HashMap<String, Object>();
		jwtUtil.verify(auth);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setData("Token Verify");
		
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	
	}
}
