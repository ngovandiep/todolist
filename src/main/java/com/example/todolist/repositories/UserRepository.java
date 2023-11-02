package com.example.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}