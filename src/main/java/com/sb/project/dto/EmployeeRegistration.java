package com.sb.project.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRegistration implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String ame;
	private int age;
	private String gender;
	private String department;
	private int yearOfJoining;
	private double salary;
	
@Override
public Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
}
}
