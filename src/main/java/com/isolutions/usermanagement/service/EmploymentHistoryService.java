package com.isolutions.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isolutions.usermanagement.exception.UserManagementException;
import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.model.EmploymentHistory;
import com.isolutions.usermanagement.repository.EmploymentHistoryRepository;

@Service
public class EmploymentHistoryService {

	@Autowired
	private EmploymentHistoryRepository employmentHistoryRepository;

	public List<EmploymentHistory> getEmploymentHistory() {
		return employmentHistoryRepository.findAll();
	}

	public List<EmploymentHistory> getHistoryByEmployee( Employee employee, int id) {

		if (!employmentHistoryRepository.existsById(id)) {
			throw new UserManagementException("didnt find it", HttpStatus.BAD_REQUEST);
		}

		return employmentHistoryRepository.findByEmployee(employee);

	}

}
