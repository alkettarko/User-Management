package com.isolutions.usermanagement.dto;

import java.util.Date;

public class EmploymentHistoryDto {

	private int id;
	private String previousDepartment;
	private String currentDepartment;
	private Date date;
	private String employeeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreviousDepartment() {
		return previousDepartment;
	}

	public void setPreviousDepartment(String previousDepartment) {
		this.previousDepartment = previousDepartment;
	}

	public String getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	

}
