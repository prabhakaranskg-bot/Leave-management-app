package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.dto.LeaveRequestDTO;
import com.leave.model.LeaveRequest;
import com.leave.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
	@Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
    public LeaveRequest applyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        return leaveRequestService.applyLeave(leaveRequestDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public List<LeaveRequest> getAllRequests() {
        return leaveRequestService.getAllRequests();
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public List<LeaveRequest> getByEmployee(@PathVariable Integer empId) {
        return leaveRequestService.getLeavesByEmployee(empId);
    }

    @PutMapping("/{leaveId}/approve/{approverId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public LeaveRequest approveLeave(@PathVariable Integer leaveId, @PathVariable Integer approverId) {
        return leaveRequestService.approveLeave(leaveId, approverId);
    }

    @PutMapping("/{leaveId}/reject/{approverId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public LeaveRequest rejectLeave(@PathVariable Integer leaveId, @PathVariable Integer approverId) {
        return leaveRequestService.rejectLeave(leaveId, approverId);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
    public List<LeaveRequest> getByStatus(@PathVariable String status) {
        return leaveRequestService.getByStatus(status);
    }
}
