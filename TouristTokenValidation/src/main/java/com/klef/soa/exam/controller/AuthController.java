package com.klef.soa.exam.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.klef.soa.exam.entity.User;
import com.klef.soa.exam.service.AuthService;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 @Autowired private AuthService service;
 @PostMapping("/register") public ResponseEntity<?> register(@RequestBody User user) {
  if(user.getUsername()==null || user.getUsername().isBlank() || user.getPassword()==null || user.getPassword().isBlank()) return ResponseEntity.badRequest().body("Username and password are required");
  if(!service.register(user)) return ResponseEntity.status(409).body("Username already exists");
  return ResponseEntity.status(201).body("User registered successfully");
 }
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody User user) {
  String token=service.login(user); if(token==null)return ResponseEntity.status(401).body("Invalid username or password");
  return ResponseEntity.ok(Map.of("token",token,"tokenType","Token"));
 }
}
