package com.example.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.services.TaskService;
import com.example.todolist.services.UserService;

@Controller
@RequestMapping("/api/v1/todos")
public class TodoController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<Task>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTask());
	}

	// Get all To-Do items for a specific user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getTodoItemsByUser(@PathVariable Long userId) {
		return ResponseEntity.ok(taskService.findByUserId(userId));
	}

	@GetMapping("/user/{userId}/completed")
	public ResponseEntity<List<Task>> getAllCompletedTasks(@PathVariable Long userId) {
		return ResponseEntity.ok(taskService.findAllCompletedTask(userId));
	}

	@GetMapping("/user/{userId}/incompleted")
	public ResponseEntity<List<Task>> getAllIncompleteTasks(@PathVariable Long userId) {
		return ResponseEntity.ok(taskService.findAllInCompleteTask(userId));
	}

	@PostMapping("/user/{userId}/create")
	public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get(); 
			return ResponseEntity.ok(taskService.createNewTask(user, task));
		} else {
			return ResponseEntity.notFound().build();
		}	
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
		Optional<Task> optionalTask = taskService.findTaskById(taskId);
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.setTask(updatedTask.getTask());
			task.setCompleted(updatedTask.isCompleted());
			return ResponseEntity.ok(taskService.updateTask(task));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<Boolean> getAllTasks(@PathVariable Long taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok(true);
	}

}