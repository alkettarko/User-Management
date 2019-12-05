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

import com.isolutions.usermanagement.model.Department;
import com.isolutions.usermanagement.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping
	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id) {

		if (!departmentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(departmentRepository.getOne(id));
	}

	@PostMapping
	public Department createDepartment(@RequestBody Department department) {

		return departmentRepository.saveAndFlush(department);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> update(@RequestBody @Valid Department departmentRequest,
			@PathVariable("id") int id) {
		if (!departmentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Department department = departmentRepository.getOne(id);

		department.setName(departmentRequest.getName());
		department.setDescription(departmentRequest.getDescription());

		return ResponseEntity.ok(departmentRepository.saveAndFlush(department));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Department> delete(@PathVariable("id") int id) {
		if (!departmentRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		departmentRepository.deleteById(id);

		return ResponseEntity.ok().build();

	}

}
