package com.isolutions.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYMENT_HISTORY")
public class EmploymentHistory {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURRENT_DEPARTMENT_ID")
	private Department currentDepartment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PREVIOUS_DEPARTMENT_ID")
	private Department previousDepartment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;

	@Column(name = "DEPARTMENT_JOIN_DATE")
	private Date departmentJoinDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Department getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public Department getPreviousDepartment() {
		return previousDepartment;
	}

	public void setPreviousDepartment(Department previousDepartment) {
		this.previousDepartment = previousDepartment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDepartmentJoinDate() {
		return departmentJoinDate;
	}

	public void setDepartmentJoinDate(Date departmentJoinDate) {
		this.departmentJoinDate = departmentJoinDate;
	}

}
