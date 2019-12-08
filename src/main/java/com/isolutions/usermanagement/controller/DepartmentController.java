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
import com.isolutions.usermanagement.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	public List<Department> getDepartments() {
		return departmentService.getDepartments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id) {

		return ResponseEntity.ok(departmentService.getDepartmentById(id));
	}

	@PostMapping
	public Department create(@RequestBody Department department) {

		return departmentService.create(department);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> update(@RequestBody @Valid Department departmentRequest,
			@PathVariable("id") int id) {

		return ResponseEntity.ok(departmentService.update(departmentRequest, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Department> delete(@PathVariable("id") int id) {

		departmentService.delete(id);
		return ResponseEntity.ok().build();
	}

}
