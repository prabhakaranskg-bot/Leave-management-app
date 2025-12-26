package com.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.model.LeaveType;
import com.leave.repository.LeaveTypeRepository;

@Service
public class LeaveTypeService {
	@Autowired
    private LeaveTypeRepository leaveTypeRepository;

    public LeaveType saveLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    public LeaveType getLeaveTypeById(Integer id) {
        return leaveTypeRepository.findById(id).orElse(null);
    }

    public void deleteLeaveType(Integer id) {
        leaveTypeRepository.deleteById(id);
    }
}
