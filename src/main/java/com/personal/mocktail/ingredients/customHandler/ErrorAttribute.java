package com.personal.mocktail.ingredients.customHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.personal.mocktail.ingredients.bean.ApiError;
import com.personal.mocktail.ingredients.bean.ApiResponseStatus;

@Component
public class ErrorAttribute extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		// Let Spring handle the error first, we will modify later :)
//	 	timestamp - The time that the errors were extracted
//		status - The status code
//		error - The error reason
//		exception - The class name of the root exception (if configured)
//		message - The exception message
//		errors - Any ObjectErrors from a BindingResult exception
//		trace - The exception stack trace
//		path - The URL path when the exception was raised
		Map<String, Object> errorAtribute = super.getErrorAttributes(webRequest, includeStackTrace);

		Map<String, Object> errorResponseMap = new HashMap<String, Object>();
		errorResponseMap.put("status", ApiResponseStatus.FAILURE);

		List<ApiError> listapiError = new ArrayList<>();

		ApiError apiError = new ApiError(errorAtribute.get("status").toString(), errorAtribute.get("error").toString());
		listapiError.add(apiError);

		errorResponseMap.put("errors", listapiError);
		errorResponseMap.put("data", null);

		return errorResponseMap;
	}
}
