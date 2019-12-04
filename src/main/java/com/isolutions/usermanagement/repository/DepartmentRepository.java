package com.isolutions.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isolutions.usermanagement.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
