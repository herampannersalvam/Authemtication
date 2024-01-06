package com.app.school.comman;

import lombok.Data;

@Data
public class APIResponse {

    private Integer status;
	
	private Object data;
	
	private Object error;
}
