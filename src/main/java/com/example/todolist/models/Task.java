package com.example.todolist.models;

import jakarta.persistence.*;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String task;
	private boolean completed = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Task() {
	}

	public Task(Long id, String task, User user, boolean completed) {
		super();
		this.id = id;
		this.task = task;
		this.user = user;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}