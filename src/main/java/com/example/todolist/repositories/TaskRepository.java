package com.example.todolist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> { 
    public Task findByTask(String task); 
    public List<Task> findAll(); 
    public Task getById(Long id); 
    
    public List<Task> findByUserId(Long userId);
    public List<Task> findByUserIdAndCompletedIsTrue(Long userId); 
    public List<Task> findByUserIdAndCompletedIsFalse(Long userId); 
} 