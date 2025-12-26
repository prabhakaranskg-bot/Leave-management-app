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

import com.leave.model.Employee;
import com.leave.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getByDept(@PathVariable Integer deptId) {
        return employeeService.getEmployeesByDepartment(deptId);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        employee.setEmpId(id);
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
