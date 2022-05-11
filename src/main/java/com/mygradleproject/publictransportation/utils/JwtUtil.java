package com.mygradleproject.publictransportation.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.mygradleproject.publictransportation.common.AccessDeniedException;
import com.mygradleproject.publictransportation.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static String secret = "publictransportation";
    private static final int expiryDuration = 60 * 60;
	
    public String generateJwt(User user){

        Claims claims = Jwts.claims()
                .setIssuer(Integer.toString(user.getUserId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiryDuration));

        claims.put("userId", user.getUserId());
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("email", user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    	
    }
    
    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }
    
}
