package com.klef.soa.exam.service;
import java.util.List;
import com.klef.soa.exam.entity.Tourist;
public interface TouristService {
 Tourist addTourist(Tourist t);
 List<Tourist> getAllTourists();
 Tourist getTouristById(Long id);
 Tourist updateTourist(Long id,Tourist t);
 boolean deleteTourist(Long id);
}
