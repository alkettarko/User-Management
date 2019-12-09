package com.isolutions.usermanagement.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.model.EmploymentHistory;

@Repository
public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory, Integer> {
	
	@Query("SELECT e from EmploymentHistory e where e.employee = :employee")
	public List<EmploymentHistory> findByEmployee(@Valid Employee employee);
}
