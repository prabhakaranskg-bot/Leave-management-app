package com.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByDepartmentDeptId(Integer deptId);}
