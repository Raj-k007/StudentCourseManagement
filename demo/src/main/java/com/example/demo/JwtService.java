package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private String secret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private int expiration_ms= 24*60*60*1000;
    public String generateToken(String username, Role role){
        return Jwts.builder().
                subject(username)
                .claim("role",role)
                .expiration(new Date(System.currentTimeMillis()+expiration_ms))
                .signWith(getSignKey())
                .compact();
    }
    public String getUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractClaims(token, Claims::getSubject);
        return userDetails.getUsername().equals(username) && !tokenExpired(token);
    }
    public boolean tokenExpired(String token){
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
    public SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
