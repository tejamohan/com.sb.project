package com.sb.project.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemsGlobalException {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<?> handleMethodArguemntNotValidException(MethodArgumentNotValidException ex){
		
		ApiResponse<?> customResponse=new ApiResponse<>();
		List<ErrorDTO> errors=new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			ErrorDTO dto=new ErrorDTO(error.getField(),error.getDefaultMessage());
			errors.add(dto);
			
		});
		customResponse.setStatus("FAILED");
		customResponse.setErrors(errors);
		return customResponse;
		
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ApiResponse<?> handleItemNotException(ItemNotFoundException ex){
		
		ApiResponse<?> customResponse=new ApiResponse<>();
		customResponse.setStatus("FAILED");
		customResponse.setErrors(Collections.singletonList(new ErrorDTO("",ex.getMessage())));
		return customResponse;
		
	}
	
	
	

}
