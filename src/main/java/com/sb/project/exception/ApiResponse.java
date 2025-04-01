package com.sb.project.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
	
	 private String status;
	 private List<ErrorDTO> errors;
	 private T results;


}
