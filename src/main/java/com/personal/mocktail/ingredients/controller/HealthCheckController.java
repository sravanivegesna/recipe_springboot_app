package com.personal.mocktail.ingredients.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.mocktail.ingredients.bean.ApiResponse;
import com.personal.mocktail.ingredients.bean.ApiResponseStatus;

@RestController
public class HealthCheckController {

	@GetMapping
	public ApiResponse checkHealth() {
		return new ApiResponse(ApiResponseStatus.SUCCESS, null, "server is running");
	}
}
