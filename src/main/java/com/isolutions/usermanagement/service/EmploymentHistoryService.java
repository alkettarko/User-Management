package com.isolutions.usermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolutions.usermanagement.dto.EmploymentHistoryDto;
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

	public List<EmploymentHistoryDto> getHistoryByEmployee(Employee employee) {

		List<EmploymentHistory> employmentList = employmentHistoryRepository.findByEmployee(employee);

		return convertToDto(employmentList);

	}

	private List<EmploymentHistoryDto> convertToDto(List<EmploymentHistory> employmentList) {
		List<EmploymentHistoryDto> employmentHistoryDtoList = new ArrayList<EmploymentHistoryDto>();

		employmentList.forEach(em -> {

			EmploymentHistoryDto employmentHistoryDto = new EmploymentHistoryDto();
			employmentHistoryDto.setId(em.getId());
			employmentHistoryDto.setCurrentDepartment(em.getCurrentDepartment().getName());
			employmentHistoryDto.setPreviousDepartment(em.getPreviousDepartment().getName());
			employmentHistoryDto.setDate(em.getDepartmentJoinDate());
			employmentHistoryDto.setEmployeeName(em.getEmployee().getFirstName());
			employmentHistoryDtoList.add(employmentHistoryDto);

		});
		return employmentHistoryDtoList;
	}

}
