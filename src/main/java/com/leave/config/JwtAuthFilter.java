package com.leave.config;

import java.io.IOException;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JWTUtil jwtUtil;

	private final UserDetailsService userDetailsService;

	public JwtAuthFilter(JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super();
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		return path.startsWith("/auth");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			if (jwtUtil.validateToken(token)) {
				Claims claims = jwtUtil.validateAndExtractClaims(token);
				String username = claims.getSubject();
				List<String> roles = claims.get("roles", List.class);
				List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
						.toList();
				UserDetails usedetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
						authorities);
				System.out.println(authorities);
				SecurityContextHolder.getContext().setAuthentication(authToken);

			}
		}
		filterChain.doFilter(request, response);
	}
}
