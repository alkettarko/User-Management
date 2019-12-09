package com.isolutions.usermanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isolutions.usermanagement.dto.DepartmentRequest;
import com.isolutions.usermanagement.dto.EmployeeRequest;
import com.isolutions.usermanagement.exception.UserManagementException;
import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.service.EmployeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeService employeeService;

	@GetMapping
	public List<Employee> getEmployes() {
		return employeeService.getEmployes();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid EmployeeRequest employeeRequest) {

		return ResponseEntity.ok(employeeService.create(employeeRequest));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid EmployeeRequest employeeRequest, @PathVariable("id") int id) {

		return ResponseEntity.ok(employeeService.update(employeeRequest, id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> delete(@PathVariable("id") int id) {

		employeeService.delete(id);

		return ResponseEntity.ok().build();

	}

	@PutMapping("/{id}/department")
	public ResponseEntity<?> changeDepartment(@PathVariable("id") int employeeId,
			@RequestBody DepartmentRequest departmentRequest) {

		employeeService.changeDepartment(employeeId, departmentRequest.getDepartmentId());
		return ResponseEntity.ok().build();

	}

	@PostMapping(value = "/{id}/image", consumes = { MediaType.IMAGE_JPEG_VALUE })
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable("id") int employeeId) {
		employeeService.uploadImage(image, employeeId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<?> downloadImage(@PathVariable int id) {

		Employee employee = employeeService.getEmployeeById(id);

		if (employee.getImage() == null) {
			throw new UserManagementException(String.format("Employee with id %s doesn't have an image", id),
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + employee.getFirstName() + "\"")
				.body(employee.getImage());
	}

}
