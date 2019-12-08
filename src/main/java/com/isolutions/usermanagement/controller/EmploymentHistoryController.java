package com.isolutions.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.model.EmploymentHistory;
import com.isolutions.usermanagement.repository.EmploymentHistoryRepository;

@RestController
@RequestMapping("/employmenthistory")
public class EmploymentHistoryController {

	@Autowired
	private EmploymentHistoryRepository employmentHistoryRepository;
	
	

//	@GetMapping
//	public List<EmploymentHistory> getEmploymentHistory() {
//		return employmentHistoryRepository.findAll();
//	}
//	
//	@GetMapping("/employee")
//	public ResponseEntity<?> getHistoryByEmployee(@RequestBody @Valid Employee employee){
//		
//		if (!employmentHistoryRepository.exists(employee)) {
//			return ResponseEntity.notFound().build();
//		}
//		return null;
//		
//	}

}
