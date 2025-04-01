package com.sb.project.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.sb.project.dto.EmployeesNewLakhData;

import jakarta.persistence.QueryHint;

public interface EmployeeRepository extends JpaRepository<EmployeesNewLakhData, Long> {
	
	
	@Query("SELECT e FROM EmployeesNewLakhData e WHERE e.salary> :salary")
	@QueryHints({
		@QueryHint(name = "org.hibernate.readOnly", value = "true"),
        @QueryHint(name = "org.hibernate.fetchSize", value = "50"),
        @QueryHint(name = "org.hibernate.cacheable", value = "true"),
        @QueryHint(name = "jakarta.persistence.cache.retrieveMode", value = "USE"),
        @QueryHint(name = "jakarta.persistence.cache.storeMode", value = "USE")})
	List<EmployeesNewLakhData> findEmployeeWithSalary(@Param("salary") double salary); 

	
}
