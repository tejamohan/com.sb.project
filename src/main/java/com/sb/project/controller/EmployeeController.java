package com.sb.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sb.project.dto.EmployeesNewLakhData;
import com.sb.project.service.EmployeeService;

@RestController
public class EmployeeController {
	
	 @Autowired
	    private EmployeeService employeeService;

	    @GetMapping("/insert-employees")
	    public String insertEmployees() {
	        employeeService.insertEmployees();
	        System.out.println("Inserted 10000 employee");
	        return "10,000 Employees Inserted Successfully!";
	    }
	    
	    @GetMapping("/getEmpAll/{salary}")
	    public ResponseEntity<List<EmployeesNewLakhData>> getAllEmpData(@PathVariable double salary){
	    	List<EmployeesNewLakhData> getResult=employeeService.getAllEmp(salary);
	    	System.out.println(getResult);
			return new ResponseEntity<List<EmployeesNewLakhData>>(getResult,HttpStatus.OK);
	    	
	    }
	

}
