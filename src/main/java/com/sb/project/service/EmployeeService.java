package com.sb.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.project.dto.EmployeesNewLakhData;
import com.sb.project.repo.EmployeeRepository;
import java.util.*;

@Service
public class EmployeeService {
	
	  @Autowired
	    private EmployeeRepository employeeRepository;

	    public void insertEmployees() {
	        List<EmployeesNewLakhData> employees = new ArrayList<>();

	        for (int i = 1; i <= 10000; i++) {
	        	EmployeesNewLakhData employee = new EmployeesNewLakhData();
	            employee.setName("Employee " + i);
	            employee.setDesignation("Designation " + (i % 10));
	            employee.setDepartment("Department " + (i % 5));
	            employee.setSalary(30000.0 + (i % 5000));
	            employees.add(employee);
	        }
	        int batchSize = 500;
	        for (int i = 0; i < employees.size(); i += batchSize) {
	            int end = Math.min(i + batchSize, employees.size());
	            employeeRepository.saveAll(employees.subList(i, end));
	        }
	        
	      


}
	    
	        public List<EmployeesNewLakhData> getAllEmp(double salary){
	        	
	        	List<EmployeesNewLakhData> getAll=employeeRepository.findEmployeeWithSalary(salary);
				return getAll;
	        	
	        }
}