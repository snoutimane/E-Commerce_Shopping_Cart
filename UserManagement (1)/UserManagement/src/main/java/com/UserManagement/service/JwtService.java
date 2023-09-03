package com.UserManagement.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	public static final String SECRET = "B64KpKVlMc0jA2GAY6UbUIh46vxnii6jCls8a4Hi9RkLL/qT19vzhqFawYxZCygt";;
	
	public String extractEmailId(String token) {
		System.out.println("-------------"+extractClaim(token, Claims::getSubject)+"--------------------");
		return extractClaim(token, Claims::getSubject);
		
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);

	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) {
	    return extractAllClaims(token).getExpiration().before(new Date());
	}


	public Boolean validateToken(String token, UserDetails userDetails) {
		final String emailId = extractEmailId(token);
		return (emailId.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	public String generateToken(String emailId) {
		Map<String,Object> claims =  new HashMap<>();
		return createToken(claims,emailId);
	}
	
	public String createToken(Map<String,Object> claims, String emailId) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(emailId)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
