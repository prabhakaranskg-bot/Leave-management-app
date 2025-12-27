package com.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.leave.model.Employee;
import com.leave.repository.EmployeeRepository;
@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepository employeeRepository;

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
    public Employee saveEmployee(Employee employee) {
    	
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByDepartment(Integer deptId) {
        return employeeRepository.findByDepartmentDeptId(deptId);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
        resetSequenceIfEmpty();
    }
    
    
    /**
     * Resets AUTO_INCREMENT to 1 if table is empty
     */
    private void resetSequenceIfEmpty() {
        Long count = employeeRepository.count();
        if (count == 0) {
            jdbcTemplate.execute("ALTER TABLE employee AUTO_INCREMENT = 1");
        }
    }
}
