package com.leave.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.model.Department;
import com.leave.service.DepartmentService;


@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	private final DepartmentService service;
    public DepartmentController(DepartmentService service){ this.service = service; }

    @PostMapping 
    @PreAuthorize("hasAuthority('ADMIN')")
    public Department create(@RequestBody Department d){ 
    	return service.save(d); 
    	}
    @GetMapping 
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','EMPLOYEE')")
    public List<Department> all(){ 
    	return service.getAll(); 
    	}
}
