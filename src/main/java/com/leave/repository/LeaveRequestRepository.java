package com.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findByEmployee_EmpId(Integer empId);

	List<LeaveRequest> findByStatus(String status);
}

