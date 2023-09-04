package com.trustbycode.securitytoks.Service.JwtServiceConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "fd9c15232e77fb4a72aa4f0ac4e07d4499cc9823ff0dfdcd7fc718639df2ecaa";

    public <Tok> Tok extractClaim(String jwtToken,
                                  Function<Claims, Tok> claimsTokFunction){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsTokFunction
                .apply(claims);
    }

    public String extractUserName(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> claimS,
                                UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claimS)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 + 60 + 12))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    //Validate Token
    public boolean tokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        //return if I have username in the token == username in input parameter
        return (userName.equals(userDetails.getUsername())) && !tokenExpired(token);
    }

    private boolean tokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
         byte[] bytes = Decoders
                 .BASE64
                 .decode(SECRET_KEY);
        return Keys
                .hmacShaKeyFor(bytes);
    }
}