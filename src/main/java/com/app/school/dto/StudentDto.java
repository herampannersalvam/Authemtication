package com.app.school.dto;

import java.util.UUID;

import lombok.Data;

@Data

public class StudentDto {

    private UUID id;
	
	
	private String name;

	
	private String email;

	
	private long mobile;

	
	private String password;

	
	private char gender;

	
	private String dob;
}
