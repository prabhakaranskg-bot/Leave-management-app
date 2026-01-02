package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.model.LeaveType;
import com.leave.service.LeaveTypeService;

@RestController
@RequestMapping("/api/leave-types")
public class LeaveTypeController {
	@Autowired
    private LeaveTypeService leaveTypeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public LeaveType createLeaveType(@RequestBody LeaveType leaveType) {
        return leaveTypeService.saveLeaveType(leaveType);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
    public LeaveType getLeaveType(@PathVariable Integer id) {
        return leaveTypeService.getLeaveTypeById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public LeaveType updateLeaveType(@PathVariable Integer id, @RequestBody LeaveType leaveType) {
        leaveType.setLeaveTypeId(id);
        return leaveTypeService.saveLeaveType(leaveType);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteLeaveType(@PathVariable Integer id) {
        leaveTypeService.deleteLeaveType(id);
    }
}
