package com.personal.mocktail.ingredients.customHandler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.personal.mocktail.ingredients.bean.ApiError;
import com.personal.mocktail.ingredients.bean.ApiResponse;
import com.personal.mocktail.ingredients.bean.ApiResponseStatus;
import com.personal.mocktail.ingredients.exception.InvalidInputDataException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InvalidInputDataException.class)
	@ResponseBody
	public ApiResponse invalidDataInput(InvalidInputDataException ex, HttpServletResponse response){
		
		List<ApiError> errors = new ArrayList<ApiError>();
		ApiError apierror = new ApiError("100097", ex.getMessage());
		errors.add(apierror);
		
		return new ApiResponse(ApiResponseStatus.FAILURE, errors, null);
	}
}
