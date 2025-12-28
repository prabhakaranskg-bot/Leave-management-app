package com.leave.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.dto.LeaveRequestDTO;
import com.leave.model.Employee;
import com.leave.model.LeaveRequest;
import com.leave.model.LeaveType;
import com.leave.repository.EmployeeRepository;
import com.leave.repository.LeaveRequestRepository;
import com.leave.repository.LeaveTypeRepository;

@Service
public class LeaveRequestService {
	 	@Autowired
	    private LeaveRequestRepository leaveRequestRepository;

	 	@Autowired
	 	private EmployeeRepository employeeRepository;
	 	@Autowired
	 	LeaveTypeRepository leaveTypeRepository;
	    public LeaveRequest applyLeave(LeaveRequestDTO leaveRequestDTO) {
	    	Employee emp = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
	                .orElseThrow(() -> new RuntimeException("Employee not found"));
	    	LeaveType lt = leaveTypeRepository.findById(leaveRequestDTO.getLeaveTypeId())
	                .orElseThrow(() -> new RuntimeException("Leave type not found"));
	    	LeaveRequest request = new LeaveRequest();
	    	request.setEmployee(emp);
	    	request.setLeaveType(lt);
	    	request.setStartDate(LocalDate.parse(leaveRequestDTO.getStartDate()));
	    	request.setEndDate(LocalDate.parse(leaveRequestDTO.getEndDate()));
	    	request.setStatus("PENDING");
	    	request.setReason(leaveRequestDTO.getReason());
	        request.setAppliedOn(LocalDateTime.now());
	        return leaveRequestRepository.save(request);
	    }

	    public List<LeaveRequest> getAllRequests() {
	        return leaveRequestRepository.findAll();
	    }

	    public List<LeaveRequest> getLeavesByEmployee(Integer empId) {
	        return leaveRequestRepository.findByEmployee_EmpId(empId);
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
