package com.sb.project.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8Practice {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<EmployeeRegistration> employees = List.of(
				new EmployeeRegistration(111, "teja", 35, "male", "developer", 2015, 77000.00),
				new EmployeeRegistration(222, "chandrika", 32, "female", "HR", 2016, 47000.00),
				new EmployeeRegistration(333, "mohan", 34, "male", "CA", 2014, 77000.00),
				new EmployeeRegistration(444, "poori", 33, "female", "manager", 2013, 87000.00),
				new EmployeeRegistration(555, "chinna", 32, "male", "finance", 2018, 57000.00),
				new EmployeeRegistration(666, "swathi", 30, "female", "HR", 2019, 67000.00),
				new EmployeeRegistration(777, "santo", 34, "male", "developer", 2018, 57000.00),
				new EmployeeRegistration(888, "divya", 32, "female", "admin", 2020, 37000.00),
				new EmployeeRegistration(999, "dileep", 35, "male", "team lead", 2013, 97000.00),
				new EmployeeRegistration(112, "sree", 32, "female", "admin", 2014, 67000.00),
				new EmployeeRegistration(111, "teja", 35, "male", "developer", 2015, 77000.00));

		// how many male and female employees in the organaization
	    Map<String,Long> getEmployees= employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getGender,Collectors.counting()));
        System.out.println(getEmployees);
		// print the name of all departments in organaization
		//employees.stream().map(EmployeeRegistration::getDepartment).forEach(System.out::println);
		
		System.out.println("-----------------------------------------------------------------------");

		// what is the average age of male and female employees
		Map<String,Double> ageEmployee=employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getGender,Collectors.averagingInt(EmployeeRegistration::getAge)));
         System.out.println(ageEmployee);
		// get the details of highest paid employee
         System.out.println("-----------------------------------------------------------------------");
		   EmployeeRegistration getHigestPaidEmployee = employees.stream().
		  max(Comparator.comparingDouble(EmployeeRegistration::
		  getSalary)).get(); 
		    System.out.println(getHigestPaidEmployee);
		    System.out.println("-----------------------------------------------------------------------");
			
			 // get the names of all employees who joined after 2015
		List<String> getNames=	employees.stream().filter(j->j.getYearOfJoining()>2015).map(EmployeeRegistration::getAme).collect(Collectors.toList());
		    System.out.println(getNames);
		    System.out.println("-----------------------------------------------------------------------");

//		// get the number of employee in each department
			
			   Map<String, Long> noOfEmployeesOnEachDept =
		    employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getDepartment,Collectors.counting()));
			 
			   System.out.println(noOfEmployeesOnEachDept);
		    System.out.println("-----------------------------------------------------------------------");

		// what is the average salary of each department
	     Map<String,Double> deptAvgSalary=   employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getDepartment,Collectors.averagingDouble(EmployeeRegistration::getSalary)));
	     System.out.println(deptAvgSalary);
	     System.out.println("-----------------------------------------------------------------------");
	     System.out.println("-----------------------------------------------------------------------");
		  //get the youngest male employee in HR and Development Department //
		 Optional<EmployeeRegistration> youngestEmployee= 
	         employees.stream().
	         filter(e->e.getGender().equals("female")).
	         filter(e->e.getDepartment()=="HR").
	         collect(Collectors.minBy(Comparator.comparing(EmployeeRegistration::getAge)));
		 System.out.println(youngestEmployee.get());
		  System.out.println("-----------------------------------------------------------------------");  
		  //who was the most working experience in the organization  
		 EmployeeRegistration getEmpsExperience =
		  employees.stream().min(Comparator.comparing(EmployeeRegistration::getYearOfJoining)).get();
		 System.out.println(getEmpsExperience);
		 System.out.println("-----------------------------------------------------------------------");
		 //how many male and female employees are there in the HR and developer		 
			/*
			 * Map<String,Long> genderCount=
			 * employees.stream().filter(e->e.getDepartment()=="HR").collect(Collectors.
			 * groupingBy(EmployeeRegistration::getGender,Collectors.counting()));
			 * System.out.println(genderCount);
			 */
		 System.out.println("/n"+"-----------------------------------------------------------------------");
	Map<String,Long> getCount=	 employees.stream().filter(e->e.getDepartment()=="HR").collect(Collectors.groupingBy(EmployeeRegistration::getGender,Collectors.counting()));
		System.out.println(getCount);
		
		System.out.println("/n"+"-----------------------------------------------------------------------");
		//get Avg salary of male and femle employees
		Map<String,Double> getAvgSalary=employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getGender,Collectors.averagingDouble(EmployeeRegistration::getSalary)));
		System.out.println(getAvgSalary);
		System.out.println("/n"+"-----------------------------------------------------------------------");
		
		//List down the names of employees in all depertment		
		Map<String,List<String>> empppps=employees.stream().collect(Collectors.groupingBy(EmployeeRegistration::getDepartment,Collectors.mapping(EmployeeRegistration::getAme, Collectors.toList())));
		empppps.entrySet().forEach(System.out::println);
		System.out.println("/n"+"-----------------------------------------------------------------------");
		
		//total salary of whole organization
		double totalSalary=employees.stream().collect(Collectors.summarizingDouble(EmployeeRegistration::getSalary)).getSum();
		System.out.println(totalSalary);
		System.out.println("/n"+"-----------------------------------------------------------------------");
		
	//get the employee names whose is younger or equals to 33 and also older than 33	
		
	Map<Boolean,List<String>> getEmployeees=	employees.stream().collect(Collectors.partitioningBy(e->e.getAge()<33,Collectors.mapping(EmployeeRegistration::getAme, Collectors.toList())));
	
		getEmployeees.entrySet().forEach(e->{
			System.out.println("-----------------------------------------------");
			if(e.getKey()) {
				System.out.println("Employees lesser than 33 :");
				System.out.println("-----------------------------------------------");
			}else {
				System.out.println("Employees greater 33");
				System.out.println("-------------------------------------------------");
			}
			List<String> getNamess=e.getValue();
			System.out.println(getNamess);
		});
		
	
	System.out.println("-----------------------------------------------------------------------"+"\n");
	
	
	  // get the oldest employee details in the organization //
	  Optional<EmployeeRegistration> oldage=
	  employees.stream().max(Comparator.comparing(EmployeeRegistration::getAge));
	  
	  System.out.println("-----------------------------------------------------------------------"+"\n");
	  //
	  System.out.println("Seniour Employee in the organization :"+"\n"+oldage.get()
	  .getAme()+"\n"+oldage.get().getDepartment()+"\n"+oldage.get().getAge()); //
	  //
	 	  //  employees.stream().max(Comparator.comparing(EmployeeRegistration::getAge));
	  
	  System.out.println("-----------------------------------------------------------------------");
	  List<EmployeeRegistration> limited = employees.stream()
	                                 .limit(3)
	                                 .collect(Collectors.toList());
	  System.out.println(limited);
	  // limited will contain [1, 2, 3]
	  System.out.println("/n"+"-----------------------------------------------------------------------");
	  Optional<EmployeeRegistration> skipped = employees.stream()
	                                 .skip(1)
	                                 .findFirst();
	  System.out.println(skipped);
	  // skipped will contain [3, 4, 5]
	  
	  //employees.stream().distinct.forEach(System.out::println);
	  
	  EmployeeRegistration emps= new EmployeeRegistration(111, "teja", 35, "male", "developer", 2015, 77000.00);
	  
	  
	 try {
		Field f= emps.getClass().getDeclaredField("id");
		f.setAccessible(true);
		System.out.println(f.get(emps));
	} catch (NoSuchFieldException | SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  
//	  EmployeeRegistration emps1=new EmployeeRegistration();
//	  try {
//		emps1=(EmployeeRegistration) emps.clone();
//	} catch (CloneNotSupportedException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	  System.out.println(emps1.getAme());
//	  System.out.println(emps1.getAge());
	  
//	  String projectPath = System.getProperty("user.dir"); // Gets the Eclipse project root
//      String filePath = projectPath + "/src/main/java/sample.txt";
//	  try {
//		FileOutputStream fos=new FileOutputStream(filePath);
//		ObjectOutputStream oos=new ObjectOutputStream(fos);
//		oos.writeObject(emps);
//		
//		FileInputStream fis=new FileInputStream(filePath);
//		ObjectInputStream ois=new ObjectInputStream(fis);
//		EmployeeRegistration regs=(EmployeeRegistration)ois.readObject();
//		System.out.println(regs.getAme());
//		System.out.println(regs.getAge());
//		fos.close();
//		oos.close();
		
		
		
//	} catch (FileNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	  
	  
	  
//	  Constructor<EmployeeRegistration> constructor;
//	   try {
//		constructor=EmployeeRegistration.class.getConstructor();
//		try {
//			EmployeeRegistration regs=constructor.newInstance();
//			System.out.println(regs.getAme());
//			System.out.println(regs.getAge());
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//				| InvocationTargetException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//	} catch (NoSuchMethodException | SecurityException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
	  
//	  String className="com.sb.project.dto.EmployeeRegistration";
//	  Class clazz=Class.forName(className);
//	  EmployeeRegistration emp=(EmployeeRegistration)clazz.newInstance();
//	  emp.setAme("teja");
//	  emp.setAge(35);
//	 // System.out.println(emp.getAge());
//	  System.out.println(emp.getAme());
//	  System.out.println(emp.getAge());
	  
	 
	
	}
	
	

}


