package com.isolutions.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
//	@Autowired
//	private EmploymentHistory employmentHistory;

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
		} // TODO email validation

		Employee employee = convertToEmployee(employeeRequest);

		return ResponseEntity.ok(employeeRepository.saveAndFlush(employee));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid EmployeeRequest employeeRequest, @PathVariable("id") int id) {

		if (!employeeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Employee employee = employeeRepository.getOne(id);

		employee.setEmail(employeeRequest.getEmail());
		employee.setCellPhone(employeeRequest.getCellPhone());

		return ResponseEntity.ok(employeeRepository.saveAndFlush(employee));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> delete(@PathVariable("id") int id) {

		if (!employeeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		employeeRepository.deleteById(id);

		return ResponseEntity.ok().build();

	}

	@PutMapping("/{id}/department/{id}")
	public ResponseEntity<?> changeDepartment(@PathVariable("id") int employeeId,
			@PathVariable("id") int departmentId) {
		
		if (!employeeRepository.existsById(employeeId)) {
			return ResponseEntity.notFound().build();
		} else if (!departmentRepository.existsById(departmentId)) {
			return ResponseEntity.notFound().build();
		}
		
		//TODO
		Employee employee = employeeRepository.getOne(employeeId);
		employee.setDepartment(departmentRepository.getOne(departmentId));
		
		
		

		return null;

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
