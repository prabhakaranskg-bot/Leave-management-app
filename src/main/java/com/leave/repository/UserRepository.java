package com.leave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
