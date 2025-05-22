package com.example.myapp.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	
	private static final String SECRET_KEY_STRING ="t4tuZuzWdZInaEPTUWuwxDPCDSKJBwOB";
	
	private final SecretKey SECRET_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	public String generateToken(UserDetails userDetails){
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60))
				.signWith(SECRET_KEY,SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean validateToken(String token,UserDetails userDetails) {
		return extractUsername(token).equals(userDetails.getUsername());
	}
	
	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	    		.setSigningKey(SECRET_KEY)
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}
}