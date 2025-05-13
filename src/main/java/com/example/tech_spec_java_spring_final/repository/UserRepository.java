package com.example.tech_spec_java_spring_final.repository;

import com.example.tech_spec_java_spring_final.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
