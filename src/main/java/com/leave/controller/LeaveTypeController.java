package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*")
public class LeaveTypeController {
	@Autowired
    private LeaveTypeService leaveTypeService;

    @PostMapping
    public LeaveType createLeaveType(@RequestBody LeaveType leaveType) {
        return leaveTypeService.saveLeaveType(leaveType);
    }

    @GetMapping
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    @GetMapping("/{id}")
    public LeaveType getLeaveType(@PathVariable Integer id) {
        return leaveTypeService.getLeaveTypeById(id);
    }

    @PutMapping("/{id}")
    public LeaveType updateLeaveType(@PathVariable Integer id, @RequestBody LeaveType leaveType) {
        leaveType.setLeaveTypeId(id);
        return leaveTypeService.saveLeaveType(leaveType);
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveType(@PathVariable Integer id) {
        leaveTypeService.deleteLeaveType(id);
    }
}
