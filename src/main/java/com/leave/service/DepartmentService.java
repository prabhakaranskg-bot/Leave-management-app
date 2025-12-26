package com.leave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leave.model.Department;
import com.leave.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
public class DepartmentService {
	
	private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public Department save(Department d){ return repo.save(d); }
    public List<Department> getAll(){ return repo.findAll(); }
}
