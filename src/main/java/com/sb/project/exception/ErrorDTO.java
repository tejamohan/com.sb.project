package com.sb.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDTO {
	
	String field;
	String defaultMessage;

}
