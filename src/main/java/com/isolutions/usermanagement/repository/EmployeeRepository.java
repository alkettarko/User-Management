package com.isolutions.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isolutions.usermanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public boolean existsByEmail(String email);

	@Query("SELECT COUNT(e)>0 from Employee e where e.email = :email and e.id != :employeeId ")
	public boolean existsByEmailAndNotId(String email, int employeeId);
}
