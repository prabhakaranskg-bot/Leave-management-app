package com.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leave.model.User;
import com.leave.repository.UserRepository;
@Service
public class UserService {
	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public User save(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public User get(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
