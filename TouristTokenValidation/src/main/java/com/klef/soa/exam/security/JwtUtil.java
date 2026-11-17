package com.klef.soa.exam.security;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
 @Value("${jwt.secret}") private String secret;
 @Value("${jwt.expiration}") private long expiration;
 private SecretKey key() { return Keys.hmacShaKeyFor(secret.getBytes()); }
 public String generateToken(String username) {
  Date now=new Date();
  return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(new Date(now.getTime()+expiration)).signWith(key()).compact();
 }
 public boolean validateToken(String token) {
  try { Claims c=Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody(); return c.getExpiration()!=null && c.getExpiration().after(new Date()); }
  catch(Exception e) { return false; }
 }
}
