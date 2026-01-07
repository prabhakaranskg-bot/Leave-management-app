package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.leave.model.Employee;
import com.leave.model.User;
import com.leave.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public User create(@RequestBody User u) {
		return service.save(u);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userRequest) {
		User existingUser = service.get(id);
		if (existingUser == null) {
			return ResponseEntity.notFound().build();
		}
		existingUser.setUsername(userRequest.getUsername());
	    if (userRequest.getPassword() == null || userRequest.getPassword().isBlank()) {
	        userRequest.setPassword(existingUser.getPassword()); 
	    }
		existingUser.setRole(userRequest.getRole());
		User updatedUser = service.save(existingUser);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','MANAGER')")
	public List<User> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER','EMPLOYEE')")
	public User get(@PathVariable Long id) {
		return service.get(id);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
