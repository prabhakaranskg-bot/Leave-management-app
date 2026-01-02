package com.leave.config;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
private final String SECRET_KEY= "6bfe60c6faf434b4810ab3f09d94d64b";
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String genrateToken(String username, List<String> roles) {
		return Jwts.builder()
				.setSubject(username)
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractUserName(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();	
	}
	
	public Boolean validateToken(String token) {
		 try {
		validateAndExtractClaims(token);
		return true;
	}catch (JwtException | IllegalArgumentException e) {
        return false;
    }
	}

	public Claims validateAndExtractClaims(String token) {
	return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

}
