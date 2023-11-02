package com.example.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.models.User;
import com.example.todolist.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

}