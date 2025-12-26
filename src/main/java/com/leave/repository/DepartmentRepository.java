package com.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {}
