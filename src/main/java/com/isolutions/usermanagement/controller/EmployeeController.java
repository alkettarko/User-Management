package com.isolutions.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isolutions.usermanagement.dto.EmployeeRequest;
import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.repository.DepartmentRepository;
import com.isolutions.usermanagement.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping
	public List<Employee> getEmployes() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

		if (!employeeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employeeRepository.getOne(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid EmployeeRequest employeeRequest) {

		if (!departmentRepository.existsById(employeeRequest.getDepartmentId())) {
			return ResponseEntity.badRequest().body("The provided Department Id Doesn't Exist!");
		}   //TODO email validation

		Employee employee = convertToEmployee(employeeRequest);

		return ResponseEntity.ok(employeeRepository.saveAndFlush(employee));

	}

	private Employee convertToEmployee(EmployeeRequest employeeRequest) {
		Employee employee = new Employee();

		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setEmail(employeeRequest.getEmail());
		employee.setDateOfBirth(employeeRequest.getDateOfBirth());
		employee.setCellPhone(employeeRequest.getCellPhone());
		employee.setDepartment(departmentRepository.getOne(employeeRequest.getDepartmentId()));
		return employee;
	}
}