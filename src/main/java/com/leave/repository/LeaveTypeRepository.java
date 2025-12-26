package com.leave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {}
