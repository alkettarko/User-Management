package com.isolutions.usermanagement.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isolutions.usermanagement.dto.EmployeeRequest;
import com.isolutions.usermanagement.exception.UserManagementException;
import com.isolutions.usermanagement.model.Department;
import com.isolutions.usermanagement.model.Employee;
import com.isolutions.usermanagement.model.EmploymentHistory;
import com.isolutions.usermanagement.repository.DepartmentRepository;
import com.isolutions.usermanagement.repository.EmployeeRepository;
import com.isolutions.usermanagement.repository.EmploymentHistoryRepository;

@Service
public class EmployeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmploymentHistoryRepository employmentHistoryRepository;

	public List<Employee> getEmployes() {

		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(int id) {

		validateEmployeeId(id);
		return employeeRepository.getOne(id);
	}

	public Employee create(EmployeeRequest employeeRequest) {

		if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
			throw new UserManagementException(
					String.format("Employee with email = %s already exists", employeeRequest.getEmail()),
					HttpStatus.BAD_REQUEST);
		}
		if (!departmentRepository.existsById(employeeRequest.getDepartmentId())) {
			throw new UserManagementException(
					String.format("Department with id = %s doesnt exist", employeeRequest.getDepartmentId()),
					HttpStatus.BAD_REQUEST);
		}

		Employee employee = convertToEmployee(employeeRequest);

		return employeeRepository.saveAndFlush(employee);

	}

	public Employee update(EmployeeRequest employeeRequest, int id) {
		validateEmployeeId(id);
		if (employeeRepository.existsByEmailAndNotId(employeeRequest.getEmail(), id)) {
			throw new UserManagementException(
					String.format("Employee with email = %s already exists", employeeRequest.getEmail()),
					HttpStatus.BAD_REQUEST);
		}

		return convertAndUpdate(employeeRequest, id);

	}

	public void delete(int id) {
		validateEmployeeId(id);
		employeeRepository.deleteById(id);
	}

	public void changeDepartment(int employeeId, int departmentId) {

		validateEmployeeId(employeeId);

		if (!departmentRepository.existsById(departmentId)) {
			throw new UserManagementException(String.format("Department with id = %s doesn't exist", departmentId), HttpStatus.BAD_REQUEST);
		}

		Employee employee = employeeRepository.getOne(employeeId);

		Department previousDepartment = employee.getDepartment();
		Department newDepartment = departmentRepository.getOne(departmentId);

		employee.setDepartment(newDepartment);
		employeeRepository.saveAndFlush(employee);

		saveHistory(employee, previousDepartment);
	}

	public void uploadImage(MultipartFile image, int id) {

		validateEmployeeId(id);

		Employee employee = employeeRepository.getOne(id);

		try {
			employee.setImage(image.getBytes());
			employeeRepository.saveAndFlush(employee);
		} catch (IOException e) {
			throw new UserManagementException("Error while uploading", HttpStatus.BAD_REQUEST);

		}

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

	private Employee convertAndUpdate(EmployeeRequest employeeRequest, int id) {
		Employee employee = employeeRepository.getOne(id);

		employee.setEmail(employeeRequest.getEmail());
		employee.setCellPhone(employeeRequest.getCellPhone());
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());

		return employeeRepository.saveAndFlush(employee);
	}

	private void saveHistory(Employee employee, Department previousDepartment) {
		EmploymentHistory employeeHistory = new EmploymentHistory();

		employeeHistory.setPreviousDepartment(previousDepartment);
		employeeHistory.setCurrentDepartment(employee.getDepartment());
		employeeHistory.setEmployee(employee);
		employeeHistory.setDepartmentJoinDate(new Date());

		employmentHistoryRepository.saveAndFlush(employeeHistory);
	}

	private void validateEmployeeId(int id) {
		if (!employeeRepository.existsById(id)) {
			throw new UserManagementException(String.format("Employee with id = %s not found", id),
					HttpStatus.NOT_FOUND);
		}
	}

}
