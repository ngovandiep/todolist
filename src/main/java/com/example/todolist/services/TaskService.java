package com.example.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public Task createNewTask(User user, Task task) {
		task.setUser(user);
		return taskRepository.save(task);
	}

	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	public Optional<Task> findTaskById(Long id) {
		return Optional.of(taskRepository.getById(id));
	}

	public List<Task> findAllCompletedTask(Long userId) {
		return taskRepository.findByUserIdAndCompletedIsTrue(userId);
	}

	public List<Task> findAllInCompleteTask(Long userId) {
		return taskRepository.findByUserIdAndCompletedIsFalse(userId);
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> findByUserId(Long userId) {
		return taskRepository.findByUserId(userId);
	}
}