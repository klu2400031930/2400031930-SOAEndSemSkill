package com.klef.soa.exam.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.klef.soa.exam.entity.Tourist;
import com.klef.soa.exam.security.JwtUtil;
import com.klef.soa.exam.service.TouristService;
@RestController @RequestMapping("/api/tourists")
public class TouristController {
 @Autowired private TouristService service;
 @Autowired private JwtUtil jwtUtil;
 private boolean valid(String token) {
  if(token==null || token.isBlank())return false;
  if(token.startsWith("Bearer "))token=token.substring(7).trim();
  return jwtUtil.validateToken(token);
 }
 private ResponseEntity<?> denied() { return ResponseEntity.status(500).body("Missing or invalid token"); }
 @PostMapping public ResponseEntity<?> add(@RequestHeader(value="Token",required=false) String token,@RequestBody Tourist t) {
  if(!valid(token))return denied();
  try{return ResponseEntity.status(201).body(service.addTourist(t));}
  catch(DataAccessException e){return ResponseEntity.status(500).body("Database error: "+e.getMostSpecificCause().getMessage());}
 }
 @GetMapping public ResponseEntity<?> all(@RequestHeader(value="Token",required=false) String token) {
  if(!valid(token))return denied();
  try{return ResponseEntity.ok(service.getAllTourists());}
  catch(DataAccessException e){return ResponseEntity.status(500).body("Database error: "+e.getMostSpecificCause().getMessage());}
 }
 @GetMapping("/{id}") public ResponseEntity<?> one(@PathVariable Long id,@RequestHeader(value="Token",required=false) String token) {
  if(!valid(token))return denied();
  try{Tourist t=service.getTouristById(id); if(t==null)return ResponseEntity.status(404).body("Tourist ID not found"); return ResponseEntity.ok(t);}
  catch(DataAccessException e){return ResponseEntity.status(500).body("Database error: "+e.getMostSpecificCause().getMessage());}
 }
 @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestHeader(value="Token",required=false) String token,@RequestBody Tourist t) {
  if(!valid(token))return denied();
  try{Tourist updated=service.updateTourist(id,t); if(updated==null)return ResponseEntity.status(404).body("Tourist ID not found"); return ResponseEntity.ok(updated);}
  catch(DataAccessException e){return ResponseEntity.status(500).body("Database error: "+e.getMostSpecificCause().getMessage());}
 }
 @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id,@RequestHeader(value="Token",required=false) String token) {
  if(!valid(token))return denied();
  try{if(!service.deleteTourist(id))return ResponseEntity.status(404).body("Tourist ID not found"); return ResponseEntity.ok("Tourist deleted successfully");}
  catch(DataAccessException e){return ResponseEntity.status(500).body("Database error: "+e.getMostSpecificCause().getMessage());}
 }
}
