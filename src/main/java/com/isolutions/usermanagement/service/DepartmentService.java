package com.isolutions.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isolutions.usermanagement.exception.UserManagementException;
import com.isolutions.usermanagement.model.Department;
import com.isolutions.usermanagement.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	public Department getDepartmentById(int id) {

		validateDepartmentId(id);
		departmentRepository.getOne(id);
		return null;
	}

	public Department create(Department department) {
		return departmentRepository.saveAndFlush(department);
	}

	public Department update(Department departmentRequest, int id) {

		validateDepartmentId(id);
		Department department = departmentRepository.getOne(id);
		department.setName(departmentRequest.getName());
		department.setDescription(departmentRequest.getDescription());

		return departmentRepository.saveAndFlush(department);

	}

	public void delete(int id) {
		validateDepartmentId(id);
		departmentRepository.deleteById(id);
	}

	private void validateDepartmentId(int id) {
		if (!departmentRepository.existsById(id)) {
			throw new UserManagementException(String.format("Department with id = %s not found", id),
					HttpStatus.NOT_FOUND);
		}

	}
}
