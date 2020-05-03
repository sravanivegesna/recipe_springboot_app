package com.personal.mocktail.ingredients.bean;

public class ApiError {

	private String code;
	private String message;

	public ApiError(String code, String message) {
		// TODO Auto-generated constructor stub
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
