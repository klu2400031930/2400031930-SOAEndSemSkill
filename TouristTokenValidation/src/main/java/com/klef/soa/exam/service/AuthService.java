package com.klef.soa.exam.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.klef.soa.exam.entity.User;
import com.klef.soa.exam.repository.UserRepository;
import com.klef.soa.exam.security.JwtUtil;
@Service
public class AuthService {
 @Autowired private UserRepository repo;
 @Autowired private JwtUtil jwtUtil;
 private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
 public boolean register(User user) {
  if(repo.findByUsername(user.getUsername())!=null) return false;
  user.setPassword(encoder.encode(user.getPassword())); repo.save(user); return true;
 }
 public String login(User user) {
  User db=repo.findByUsername(user.getUsername());
  if(db!=null && encoder.matches(user.getPassword(),db.getPassword())) return jwtUtil.generateToken(db.getUsername());
  return null;
 }
}
