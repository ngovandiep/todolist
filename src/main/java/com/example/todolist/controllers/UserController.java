package com.example.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.models.User;
import com.example.todolist.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllTasks() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setUsername(updatedUser.getUsername());

			userService.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isPresent()) {
			userService.deleteById(userId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}