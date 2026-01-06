package com.leave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.config.JWTUtil;
import com.leave.dto.AuthResponse;
import com.leave.model.User;


@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	public AuthenticationManager authenticationManager;
	@Autowired
	public JWTUtil jwtUtil;
	
	@PostMapping("/login")
	public AuthResponse  login(@RequestBody User authRequest) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.username,
				authRequest.password));
		List<String> roles = auth.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				 .map(role -> role.replace("ROLE_", ""))
				.toList();	
		return new AuthResponse(jwtUtil.genrateToken(authRequest.username, roles));
		
}
}
