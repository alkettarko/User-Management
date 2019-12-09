package com.isolutions.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.model.EmploymentHistory;
import com.isolutions.usermanagement.service.EmploymentHistoryService;

@RestController
@RequestMapping("/employmenthistory")
public class EmploymentHistoryController {

	@Autowired
	private EmploymentHistoryService employmentHistoryService;

	@GetMapping
	public List<EmploymentHistory> getEmploymentHistory() {
		return employmentHistoryService.getEmploymentHistory();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getHistoryByEmployee(@RequestBody @Valid Employee employee, @PathVariable int employeeId) {

		return ResponseEntity.ok(employmentHistoryService.getHistoryByEmployee(employee, employeeId));
	}

}
