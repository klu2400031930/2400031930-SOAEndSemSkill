package com.klef.soa.exam.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.soa.exam.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> { User findByUsername(String username); }
