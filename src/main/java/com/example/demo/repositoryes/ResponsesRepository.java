package com.example.demo.repositoryes;

import com.example.demo.models.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsesRepository extends JpaRepository<UserResponse, Long> {
    public UserResponse findByText(String text);
}