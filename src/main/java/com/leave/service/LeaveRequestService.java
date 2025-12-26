package com.leave.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.model.Employee;
import com.leave.model.LeaveRequest;
import com.leave.repository.EmployeeRepository;
import com.leave.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {
	 	@Autowired
	    private LeaveRequestRepository leaveRequestRepository;

	 	@Autowired
	 	private EmployeeRepository employeeRepository;
	    public LeaveRequest applyLeave(LeaveRequest request) {
	        request.setStatus("PENDING");
	        request.setAppliedOn(LocalDateTime.now());
	        return leaveRequestRepository.save(request);
	    }

	    public List<LeaveRequest> getAllRequests() {
	        return leaveRequestRepository.findAll();
	    }

	    public List<LeaveRequest> getLeavesByEmployee(Integer empId) {
	        return leaveRequestRepository.findByEmployeeEmpId(empId);
	    }

	    public LeaveRequest approveLeave(Integer leaveId, Integer approverId) {

	        LeaveRequest req = leaveRequestRepository.findById(leaveId).orElse(null);

	        if (req == null) return null;
	        Employee approver = employeeRepository.findById(approverId).orElse(null);
	        req.setStatus("APPROVED");
	        req.setApprovedBy(approver);
	        req.setApprovedOn(LocalDateTime.now());

	        return leaveRequestRepository.save(req);
	    }

	    public LeaveRequest rejectLeave(Integer leaveId, Integer approverId) {

	        LeaveRequest req = leaveRequestRepository.findById(leaveId).orElse(null);

	        if (req == null) return null;

	        Employee approver = employeeRepository.findById(approverId).orElse(null);
	        req.setStatus("REJECTED");
	        req.setApprovedBy(approver);
	        req.setApprovedOn(LocalDateTime.now());

	        return leaveRequestRepository.save(req);
	    }

	    public List<LeaveRequest> getByStatus(String status) {
	        return leaveRequestRepository.findByStatus(status);
	    }
}
