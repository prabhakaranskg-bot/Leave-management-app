package com.leave.controller;

import java.util.List;

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
@CrossOrigin("*")
public class DepartmentController {
	private final DepartmentService service;
    public DepartmentController(DepartmentService service){ this.service = service; }

    @PostMapping 
    public Department create(@RequestBody Department d){ 
    	return service.save(d); 
    	}
    @GetMapping 
    public List<Department> all(){ 
    	return service.getAll(); 
    	}
}
