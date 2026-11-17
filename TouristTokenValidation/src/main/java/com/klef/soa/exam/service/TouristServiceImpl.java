package com.klef.soa.exam.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.soa.exam.entity.Tourist;
import com.klef.soa.exam.repository.TouristRepository;
@Service
public class TouristServiceImpl implements TouristService {
 @Autowired private TouristRepository repo;
 public Tourist addTourist(Tourist t) { t.setId(null); return repo.save(t); }
 public List<Tourist> getAllTourists() { return repo.findAll(); }
 public Tourist getTouristById(Long id) { return repo.findById(id).orElse(null); }
 public Tourist updateTourist(Long id,Tourist t) {
  Tourist old=repo.findById(id).orElse(null); if(old==null)return null;
  old.setName(t.getName()); old.setEmail(t.getEmail()); old.setCountry(t.getCountry()); old.setContact(t.getContact());
  return repo.save(old);
 }
 public boolean deleteTourist(Long id) {
  Tourist old=repo.findById(id).orElse(null); if(old==null)return false; repo.delete(old); return true;
 }
}
