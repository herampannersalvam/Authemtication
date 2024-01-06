package com.app.school.util;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.app.school.entity.Login;
import com.app.school.entity.Student;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	

	public static String secuet="This_is_secuet";
	public static long expriytime= 60*60;
	
	
	
	public String generatejwt(Login Login) {
	    long millitime = System.currentTimeMillis();
	    long expritime = millitime + expriytime * 1000;

	    Date issuedAt = new Date(millitime);
	    Date expriyAt = new Date(expritime);

	    UUID uuid = Login.getId();
	    String issuer = uuid.toString(); // Convert UUID to string
	    Claims claim = Jwts.claims().setIssuer(issuer).setIssuedAt(issuedAt).setExpiration(expriyAt);

	    return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256, secuet).compact();
	}


	public void verify(String authorization) throws Exception {
		
		try {
		Jwts.parser().setSigningKey(secuet).parseClaimsJws(authorization);
		}catch (Exception e) {
			throw new Exception();
		}
	}
}
